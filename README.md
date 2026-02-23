# Remove Color Codes

This project is a plug-in for the IBM Rational Developer for i. Its
purpose is to remove legacy color codes from source members that
have been loaded intio the Lpex editor.

## Launching a Test Application

Follow these steps to launch a test application:

* Clone the repository to your PC.
* Import the projects into RDi.
* Open file `plugin.xml` of project **Remove Color Codes Plugin**.
* Select the **Overview** tab.
* Click the **Launch an Eclipse application in Debug mode** link below **Testing**.

## Building the Plug-in

### Prerequisites

The following conditions must be met for building the plug-in with **IBM Rational Developer for i 9.6**:

* Maven 3.9.6 or higher ([download page](https://maven.apache.org/download.cgi))
* Java 8 or higher (for command line build)
* IBM LPEX dependency ([see note below](#important-note---ibm-lpex-dependency))

Download the Maven zip file and extract it to a folder on your PC. Update property
`maven.executable` in file [build.properties](Remove%20Color%20codes%20Update%20Site/build/build.properties).

### Updating the Version Number

Update property `project.version` in file [build.properties](Remove%20Color%20codes%20Update%20Site/build/build.properties).

### Launching the Build Process

This project uses Maven with Tycho for building an archived update site.

#### Ant Script

Launch the `release` target of Ant script [Remove Color Codes Update Site](Remove%20Color%20codes%20Update%20Site/build/build.xml)
for building the archived update site.

The update site will be generated at: `RemoveColorCodes-v*.*.*.zip`

#### Command Line

Use the below command for building the archived update site on a command line:

```bash
# Full build with verification
mvn clean verify
```

The update site will be generated at: `target/de.tools400.removecolorcodes.updatesite-*.*.*-SNAPSHOT.zip`

## Important Note - IBM LPEX Dependency

The **Rational Developer for i** bundles are required but not available in public Maven repositories.
You need to create your own update site as desribed below:

```bash
set RDI_PLUGINS_FOLDER="c:\IBM\RDi_096\plugins"
set LOCAL_REPOSITORY="c:\\IBM\\local-repository"
FOR /F "tokens=*" %g IN ('where /r %RDI_PLUGINS_FOLDER% "org.eclipse.equinox.launcher_*.jar"') DO (SET launcher_jar=%g)
java -cp "%RDI_PLUGINS_FOLDER%" ^
  -jar "%launcher_jar%" ^
  -application org.eclipse.equinox.p2.publisher.FeaturesAndBundlesPublisher ^
  -metadataRepository "file:///%LOCAL_REPOSITORY%" ^
  -artifactRepository "file:///%LOCAL_REPOSITORY%" ^
  -source c:/IBM/SDPShared ^
  -publishArtifacts
```

| Variable           | Comment                                           |
| :----------------- | :------------------------------------------------ |
| RDI_PLUGINS_FOLDER | Path to the folder of the RDi plug-ins.           |
| LOCAL_REPOSITORY   | Path to the folder of the repository to create.   |
