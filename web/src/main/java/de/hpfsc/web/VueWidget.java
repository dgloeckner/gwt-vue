package de.hpfsc.web;

import com.google.gwt.core.client.Callback;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class VueWidget extends Widget {

  Element divElement;

  Callback<String, Throwable> callback;

  public VueWidget() {
    divElement = Document.get().createDivElement();
    DivElement mainElement = Document.get().createDivElement();
    mainElement.appendChild(divElement);
    divElement.setId("vueWidget");
    // Instantiate the top-level vue.js component
    Element buttonCounter = Document.get().createElement("button-counter");
    divElement.appendChild(buttonCounter);
    setElement(mainElement);
  }

  @Override
  public void onLoad() {
    super.onLoad();
    //registerVueComponent();
    mountVueInstance(divElement);
  }

  @Override
  public void onUnload() {
    super.onUnload();
    // TODO: Dispose vue instance
  }

  private native void mountVueInstance(Element element)/*-{
      $wnd.middleware.app.$mount("#vueWidget");
   }-*/;

  private native void registerVueComponent()/*-{
      $wnd.middleware.registerVueComponent();
   }-*/;

  public native void sendClicked()/*-{
      $wnd.middleware.sendClicked();
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
