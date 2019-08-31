package de.hpfsc.web;

import com.google.gwt.core.client.Callback;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class VueWidget extends Widget {

  Element divElement;

  Callback<String, Throwable> callback;

  private final String tagName;

  private final String elementId;

  public VueWidget(String tagName) {
    this.tagName = tagName;
    elementId = tagName + "-" + DOM.createUniqueId();
    divElement = Document.get().createDivElement();
    DivElement mainElement = Document.get().createDivElement();
    mainElement.appendChild(divElement);
    divElement.setId(elementId);
    // Instantiate the top-level vue.js component
    Element buttonCounter = Document.get().createElement(tagName);
    divElement.appendChild(buttonCounter);
    setElement(mainElement);
  }

  @Override
  public void onLoad() {
    super.onLoad();
    mountVueInstance(elementId, tagName );
  }

  @Override
  public void onUnload() {
    super.onUnload();
    // TODO: Dispose vue instance
  }

  private native void mountVueInstance(String id, String tagName)/*-{
      $wnd.middleware.createComponent(id, tagName);
   }-*/;

  public void sendClicked() {
     emitEvent(this.elementId);
  }

  private native void emitEvent(String id)/*-{
      $wnd.middleware.emitEvent(id, 'send-clicked', 'the data');
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
}
