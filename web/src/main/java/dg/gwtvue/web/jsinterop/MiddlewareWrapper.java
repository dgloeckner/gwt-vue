package dg.gwtvue.web.jsinterop;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Middleware")
public class MiddlewareWrapper {

  /**
   * Creates a new Vue.js component with the type matching the given tag name and the given element
   * id.
   */
  public native void createComponent(String id, String tagName);


  /**
   * Disposes the Vue.js component with the given id.
   */
  public native void disposeComponent(String id);

  /**
   * Sends an event so the Vue component will be notified.
   */
  public native void emitEvent(String id, String topic, Object data);

  /**
   * Sends a global event so all listeners of the topic will be notified.
   */
  public native void emitGlobalEvent(String topic, Object data);

  /**
   * Registers a callback which gets notified for events from the Vue component for the given *
   * topic.
   */
  public native void onEvent(String id, String topic, EventListenerCallback callback);

}
