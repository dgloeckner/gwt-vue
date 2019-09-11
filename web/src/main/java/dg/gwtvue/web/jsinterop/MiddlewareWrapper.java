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
   * Sends an event so the Vue component will be notified.
   */
  public native void emitEvent(String id, String topic, Object data);

  /**
   * Registers a callback which gets notified for events from the Vue component for the given *
   * topic.
   */
  public native void onEvent(String id, String topic, EventListenerCallback callback);
}
