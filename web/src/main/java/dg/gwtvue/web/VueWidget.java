package dg.gwtvue.web;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import dg.gwtvue.web.jsinterop.EventListenerCallback;
import dg.gwtvue.web.jsinterop.JsWindowWrapper;

/**
 * Generic widget which instantiates a <code>Vue.js</code> component, which actually is a Vue
 * instance.
 *
 * <p>Exposes an interface for
 * emitting events and getting event notifications.</p>
 */
public class VueWidget extends Widget {

  private final String tagName;

  private final String elementId;

  public VueWidget(String tagName) {
    // Tag name identifies the component type in Vue.js.
    this.tagName = tagName;
    // Create a unique ID for our component.
    elementId = tagName + "-" + DOM.createUniqueId();
    Element divElement = Document.get().createDivElement();
    DivElement mainElement = Document.get().createDivElement();
    mainElement.appendChild(divElement);
    divElement.setId(elementId);
    // Create a child element with the tag name matching the desired component.
    Element componentElement = Document.get().createElement(tagName);
    divElement.appendChild(componentElement);
    setElement(mainElement);
  }

  @Override
  public void onLoad() {
    super.onLoad();
    JsWindowWrapper.Middleware.createComponent(elementId, tagName);
  }

  @Override
  public void onUnload() {
    super.onUnload();
    // TODO: Dispose vue instance, remove listeners from event bus...
  }

  /**
   * Sends an event so the Vue component will be notified.
   */
  public void sendEvent(String topic, Object data) {
    GWT.log("Send event to Vue");
    JsWindowWrapper.Middleware.emitEvent(this.elementId, topic, data);
  }

  /**
   * Registers a callback which gets notified for events from the Vue component for the given
   * topic.
   */
  public void onEvent(String topic, EventListenerCallback callback) {
    JsWindowWrapper.Middleware.onEvent(this.elementId, topic, callback);
  }
}
