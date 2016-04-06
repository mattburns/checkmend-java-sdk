checkmend-java-sdk
==================

A java library for communicating with the [CheckMEND API](http://gapi.checkmend.com/docs/).


Building the jars
-----------------
```
gradle build
```


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


Contribute/Extend
-----------------

The project files are present so you can just import the project into [eclipse](http://www.eclipse.org/).
If you want to play, you can build the jars from source using [ant](http://ant.apache.org/). 


More
----

See the [test code](https://github.com/mattburns/checkmend-java-sdk/blob/master/checkmend-java-sdk/src/test/uk/co/mattburns/checkmend/differentpackage/CheckmendTest.java) for more usage examples.

To run the tests, you will need to create the file `checkmend-java-sdk/src/test/resources/uk/co/mattburns/checkmend/test-settings.properties` with your api keys in it and it will need to look something like this:

```
PARTNER_ID: 123
PERSON_ID: 321
SECRET_KEY: 012345678abcd123456
```