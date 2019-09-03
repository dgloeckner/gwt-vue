GWT / Vue.js integration
=================

Overview
--------

Shows how vue.js components can be embedded into a GWT app.
Features bi-directional communication between Vue and GWT ui components.

Vue components can be built and tested individually, using npm, Babel, webpack...

Once the Vue component is ready, it can be tested in GWT application also, using
`build-vue-and-gwt.sh`.

Running via Maven in GWT Dev Mode
---------------------------------

In order to run the example via Maven in GWT Dev Mode, you need to do:

1. Start the web application in Tomcat 7 via Maven
2. Start GWT Dev Mode via Maven
3. Run the application in your browser

To accomplish the first point, issue the following Maven command on a shell:

    mvn clean install
    mvn tomcat7:run-war-only

Your application is now deployed at http://127.0.0.1:8082/parent/.

Now, you need to build Vue.js components and start GWT Dev Mode. Open a second shell and execute:

    build-vue-and-gwt.sh

On success, the GWT Dev Mode window opens. Click *Launch Default Browser* to open it in GWT Dev Mode.

What if GWT page does not update?
---------------------------------
Try `mvn clean install`, start `mvn tomcat7:run-war-only` again and finally run  `build-vue-and-gwt.sh` 
in a separate terminal. 

