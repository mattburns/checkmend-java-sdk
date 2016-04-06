package uk.co.mattburns.checkmend.differentpackage;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Activity {
    private long propertyid;
    private ActivityType activity;
    private Date datetime;

    public enum ActivityType {
        lost, stolen, found, recovered, bought, sold;
    }

    public Activity(long propertyid, ActivityType activity, Date datetime) {
        this.propertyid = propertyid;
        this.activity = activity;
        this.datetime = datetime;
    }

    public long getPropertyid() {
        return propertyid;
    }

    public ActivityType getActivity() {
        return activity;
    }

    public Date getDatetime() {
        return datetime;
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        return gson.toJson(this);
    }
}
