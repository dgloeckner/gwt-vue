package de.hpfsc.web;

import com.google.gwt.core.client.Callback;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * Generic widget which instantiates a <code>Vue.js</code> component, which actually is a Vue
 * instance.
 *
 * <p>Exposes an interface for
 * emitting events and getting event notifications.</p>
 */
public class VueWidget extends Widget {

  Callback<String, Throwable> callback;

  private final String tagName;

  private final String elementId;

  public VueWidget(String tagName) {
    this.tagName = tagName;
    elementId = tagName + "-" + DOM.createUniqueId();
    Element divElement = Document.get().createDivElement();
    DivElement mainElement = Document.get().createDivElement();
    mainElement.appendChild(divElement);
    divElement.setId(elementId);
    Element componentElement = Document.get().createElement(tagName);
    divElement.appendChild(componentElement);
    setElement(mainElement);
  }

  @Override
  public void onLoad() {
    super.onLoad();
    createComponent(elementId, tagName);
  }

  @Override
  public void onUnload() {
    super.onUnload();
    // TODO: Dispose vue instance
  }

  /**
   * Emits an event so the Vue component will be notified.
   */
  public void emitEvent(String topic, Object data) {
    emitEvent(this.elementId, topic, data);
  }

  private native void emitEvent(String id, String topic, Object data)/*-{
      $wnd.middleware.emitEvent(id, topic, data);
   }-*/;

  public void setCallback(Callback<String, Throwable> callback) {
    this.callback = callback;
    registerCallback();
  }

  private native void registerCallback() /*-{
    var self = this;
    var callbackFn = $entry(function(val) {
      self.@de.hpfsc.web.VueWidget::handleCallback(Ljava/lang/String;)(val);
    });
    $wnd.middleware.addCallback(callbackFn);
  }-*/;

  public void handleCallback(String val) {
    callback.onSuccess(val);
  }

  private native void createComponent(String id, String tagName)/*-{
      $wnd.middleware.createComponent(id, tagName);
   }-*/;
}
