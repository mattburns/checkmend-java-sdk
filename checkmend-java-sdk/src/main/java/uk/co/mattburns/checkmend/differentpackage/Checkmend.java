package uk.co.mattburns.checkmend.differentpackage;

import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.core.util.Base64;

public class Checkmend {

    private long partnerid;
    private String secretKey;

    private WebResource webResource;

    private static String URL = "https://gapi.checkmend.com/";

    /**
     * This is the main class for talking to the CheckMEND API. See
     * http://gapi.checkmend.com/docs/ for detailed examples.
     * 
     * For all method calls, if an error occurs, a {@link CheckmendError} will
     * be thrown
     * 
     * @param partnerid
     *            As supplied by CheckMEND
     * @param secretKey
     *            As supplied by CheckMEND
     */
    public Checkmend(long partnerid, String secretKey) {
        this(null, partnerid, secretKey);
    }

    /**
     * This is the main class for talking to the CheckMEND API. See
     * http://gapi.checkmend.com/docs/ for detailed examples.
     * 
     * For all method calls, if an error occurs, a {@link CheckmendError} will
     * be thrown
     * 
     * @param partnerid
     *            As supplied by CheckMEND
     * @param secretKey
     *            As supplied by CheckMEND
     * @param loggingStream
     *            Requests and responses will be written to this
     */
    public Checkmend(long partnerid, String secretKey, PrintStream loggingStream) {
        this(new LoggingFilter(loggingStream), partnerid, secretKey);
    }

    /**
     * This is the main class for talking to the CheckMEND API. See
     * http://gapi.checkmend.com/docs/ for detailed examples.
     * 
     * For all method calls, if an error occurs, a {@link CheckmendError} will
     * be thrown
     * 
     * @param partnerid
     *            As supplied by CheckMEND
     * @param secretKey
     *            As supplied by CheckMEND
     * @param logger
     *            Requests and responses will be written to this
     */
    public Checkmend(long partnerid, String secretKey, Logger logger) {
        this(new LoggingFilter(logger), partnerid, secretKey);
    }

    private Checkmend(LoggingFilter loggingFilter, long partnerid,
            String secretKey) {
        this.partnerid = partnerid;
        this.secretKey = secretKey;

        Client client = Client.create();
        if (loggingFilter != null) {
            client.addFilter(loggingFilter);
        }
        client.setConnectTimeout(0);
        client.setReadTimeout(0);

        webResource = client.resource(URL);
    }

    /**
     * Register a new Person
     * 
     * @param person
     * @return The personid of the created Person. You will need this to make
     *         changes.
     */
    public long registerPerson(Person person) {
        String json = person.toJson();
        String auth = generateSignatureHash(partnerid, secretKey, json);

        ClientResponse response = webResource.path("person")
                .header("Authorization", "Basic " + auth)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, json);

        throwIfBad(response);

        JsonParser parser = new JsonParser();
        JsonObject dataObject = parser.parse(response.getEntity(String.class))
                .getAsJsonObject();
        return dataObject.get("personid").getAsLong();
    }

    public void removePerson(long personid) {
        String auth = generateSignatureHash(partnerid, secretKey, "");

        ClientResponse response = webResource.path("remove/person/" + personid)
                .header("Authorization", "Basic " + auth)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class);

        throwIfBad(response);
    }

    public long registerProperty(Property property) {
        String json = property.toJson();
        String auth = generateSignatureHash(partnerid, secretKey, json);

        ClientResponse response = webResource.path("property")
                .header("Authorization", "Basic " + auth)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, json);

        throwIfBad(response);

        JsonParser parser = new JsonParser();
        JsonObject dataObject = parser.parse(response.getEntity(String.class))
                .getAsJsonObject();
        return dataObject.get("propertyid").getAsLong();
    }

    public void removeProperty(long propertyid) {
        String auth = generateSignatureHash(partnerid, secretKey, "");

        ClientResponse response = webResource
                .path("remove/property/" + propertyid)
                .header("Authorization", "Basic " + auth)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class);

        throwIfBad(response);
    }

    public void registerActivity(Activity activity) {
        String json = activity.toJson();
        String auth = generateSignatureHash(partnerid, secretKey, json);

        ClientResponse response = webResource
                .path("activity/" + activity.getPropertyid())
                .header("Authorization", "Basic " + auth)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, json);

        throwIfBad(response);
    }

    private void throwIfBad(ClientResponse response) {
        if (response.getStatus() < 200 || response.getStatus() >= 300) {
            throw toError(response);
        }
    }

    private CheckmendError toError(ClientResponse response) {
        return jsonToCheckMENDError(response.getEntity(String.class));
    }

    public static CheckmendError jsonToCheckMENDError(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, CheckmendError.class);
    }

    public static String generateSignatureHash(long partnerid,
            String secretKey, String json) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] hashBytes = (secretKey + json).getBytes();
        String sha1Hash = byteArrayToHexString(md.digest(hashBytes));

        return new String(Base64.encode(partnerid + ":" + sha1Hash));
    }

    private static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
