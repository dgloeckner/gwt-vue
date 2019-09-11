package dg.gwtvue.web.jsinterop;

import jsinterop.annotations.JsFunction;

/**
 * Callback for events from Vue.js.
 */
@JsFunction
public interface EventListenerCallback {

  void onEvent(Object event);
}
