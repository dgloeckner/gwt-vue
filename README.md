GWT / Vue.js integration
=================

Overview
--------

The goal of this project is to show how [Vue.js](https://vuejs.org) components can be embedded into a [GWT](http://www.gwtproject.org) app.

_Imagine you have a large, dated GWT application and need to migrate it to a state-of-the-art UI framework ;)_

So what do we need to be able to migrate our big, fat GWT app step by step?
* Develop Vue.js components independently with a fast development cycle (e.g. hot code replacement).
* Talk to Vue.js from GWT and the other way around.
* Create and dispose Vue.js components from GWT without major headaches. 

The main goal ist not to show how a "beautiful" Vue application could be built but focuses more on the aspect of clean integration between the 2 worlds.

Main contributions
------------------

* `web/src/main/java/dg/gwtvue/web/VueWidget.java` is a GWT widget which enables seamless integration of Vue components with GWT apps.
* `vuecomponent/src/components/Parent.vue` provides the top level class for Vue components. 
* `vuecomponent/src/main.ts` provides the middleware for integration.
* Using `npm run serve` in `vuecomponent-dev` Vue components can be built and tested individually with hot code replace.
  * `vuecomponent-dev/src/components/GwtMockEnvironment.vue` provides a mock application framework in Vue similar to what the "toy GWT app" provides.

Running via Maven in GWT Dev Mode
---------------------------------

In order to run the example via Maven in GWT Dev Mode, you need to do:

1. Start the web application in Tomcat 7 via Maven
2. Trigger integrated Vue.js build and GWT dev mode
3. Run the application in your browser

To accomplish the first point, issue the following Maven command on a shell:

    mvn clean install
    mvn tomcat7:run-war-only

Your application is now deployed at http://127.0.0.1:8082/parent/.

To accomplish the second point, you need to build Vue.js components and start GWT Dev Mode. Open a second shell and execute:

    build-vue-and-gwt.sh

On success, the GWT Dev Mode window opens. Click *Launch Default Browser* to open it in GWT Dev Mode.

What if GWT page does not update?
---------------------------------
Try `mvn clean install`, start `mvn tomcat7:run-war-only` again and finally run  `build-vue-and-gwt.sh` 
in a separate terminal. 

