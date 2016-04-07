checkmend-java-sdk
==================

A java library for communicating with the [CheckMEND API](http://gapi.checkmend.com/docs/).



Building the jar
----------------
```
gradle clean build
```


Running Tests
-------------
```
gradle test
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



Dependencies
------------

All dependencies are managed using the gradle dependency management system.



Contribute/Extend
-----------------

Just import the project into [eclipse](http://www.eclipse.org/) or [intellij](https://www.jetbrains.com/idea/).
If you want to play, you can build the jar from source using [gradle](http://www.gradle.org/). 



More
----

See the [test code](https://github.com/mattburns/checkmend-java-sdk/blob/master/checkmend-java-sdk/src/test/java/uk/co/mattburns/checkmend/differentpackage/CheckmendTest.java) for more usage examples.


Secrets
-------
The following secrets are required for the Tests to work:
    1. Checkmend Partner ID
    2. Checkmend Persons ID
    3. Checkmend Secret Key

These are brought into the test system by the test-settings.properties file.

Currently this file is formatted in the following way:

    PROPERTY_NAME=${PLACEHOLDER_NAME}

In this format, the system expects the property to be set somewhere in the build environment.
These can be set in your build environment in the following ways:

    1. As a gradle property (in ~/.gradle/gradle.properties) 
    2. As a system property
    3. As an environment variable
    
The TestUtils class will try and resolve them from all locations.

Alternatively, you could manually replace the placeholder values with the actual values,
or format the placeholder values like so:

    PROPERTY_NAME=${PLACEHOLDER_NAME:DEFAULT_VALUE}

In this format, if the placeholder can't be resolved, the default value will be used instead.

**Note:** *It is not recommended that you check secrets into your project.*

##### JUnit Task Secrets
Running the `gradle test` task will use the secrets as they have been set above. This is because the `test` task
specifically sets gradle.properties values as system variables.

However, when running a JUnit task (ctrl-r or ctrl-d in test class) that system won't work.

Instead, edit your Run/Debug Configuration and add them as environment variables there.

