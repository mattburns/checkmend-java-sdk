checkmend-java-sdk
==================

A java library for communicating with the [CheckMEND API](http://gapi.checkmend.com/docs/).



Usage
-----

``` java
Person bob = new Person.PersonBuilder("ref123")
                       .withFamilyname("smith")
                       .withOthernames("bob")
                       .build();

Checkmend checkmend = new Checkmend(partnerid, secretKey);

long personid = checkmend.registerPerson(bob);
```



Error Handling
--------------

Any call to the API could throw a CheckmendError which is an unchecked exception. It's up to you if you want to catch and handle it.



Dependencies
------------

I have bundled up the Jars you need into checkmend-sdk-with-dependencies-*.zip. If you prefer to manage them yourself, they are:

- [Gson](https://code.google.com/p/google-gson/) - gson-2.2.jar
- [Jersey](http://jersey.java.net/) - jersey-core-1.12.jar, jersey-client-1.12.jar, jersey-multipart-1.12.jar, jsr311-api-1.1.1.jar
- [Commons-lang](http://commons.apache.org/lang/) - commons-lang3-3.0.1.jar



Contribute/Extend
-----------------

The project files are present so you can just import the project into [eclipse](http://www.eclipse.org/).
If you want to play, you can build the jars from source using [ant](http://ant.apache.org/). 



More
----

See the [test code](https://github.com/mattburns/checkmend-java-sdk/blob/master/checkmend-java-sdk/src/test/uk/co/mattburns/checkmend/CheckmendTest.java) for more usage examples.
