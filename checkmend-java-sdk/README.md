## Secrets
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

### JUnit Task Secrets
Running the `gradle test` task will use the secrets as they have been set above. This is because the `test` task
specifically sets gradle.properties values as system variables.

However, when running a JUnit task (ctrl-r or ctrl-d in test class) that system won't work.

Instead, edit your Run/Debug Configuration and add them as environment variables there.

