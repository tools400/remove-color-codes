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

## Building the  Plug-in

Follow these steps to build an archived update site:

* Enter project *Remove Color Codes Update Site*
* Clean the below folders:
  * Folder: `plugins`
  * Folder: `features`
* Clean the below `jar` files:
  * File: `artifacts.jar`
  * File: `content.jar`
* Right click `site.xml` and select option **Plug-in Tools - Build Site**.
* Zip folder **Remove Color Codes Update Site**, excluding:
  * File: `.project`
  * File: `site.xml`

## Updating the Version Number

Update the current version number in the following files:

* Remove Color Codes Feature: `feature.xml`
* Remove Color Codes Plugin: `plugin.xml`
* Remove Color Codes Update Site: `site.xml`
