package dg.gwtvue.web;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

  private final Map<String, Set<Callback<String, Throwable>>> callbacksByTopic = new HashMap<String, Set<Callback<String, Throwable>>>();

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
    // TODO: Dispose vue instance, remove listeners from event bus...
  }

  /**
   * Sends an event so the Vue component will be notified.
   */
  public void sendEvent(String topic, Object data) {
    emitEvent(this.elementId, topic, data);
  }

  private native void emitEvent(String id, String topic, Object data)/*-{
      $wnd.middleware.emitEvent(id, topic, data);
   }-*/;

  /**
   * Registers a callback which gets notified for events from the Vue component for the given
   * topic.
   */
  public void onEvent(String topic, Callback<String, Throwable> callback) {
    Set<Callback<String, Throwable>> cbs = callbacksByTopic.get(topic);
    if (cbs == null) {
      cbs = new HashSet<>();
      callbacksByTopic.put(topic, cbs);
    }
    cbs.add(callback);
    registerCallback(this.elementId, topic);
  }

  private native void registerCallback(String id, String topic) /*-{
    var self = this;
    var theTopic = topic;
    var callbackFn = $entry(function(val) {
      self.@dg.gwtvue.web.VueWidget::handleCallback(Ljava/lang/String;Ljava/lang/String;)(topic, val);
    });
    $wnd.middleware.onEvent(id, topic, callbackFn);
  }-*/;

  private void handleCallback(String topic, String val) {
    GWT.log(topic);
    Set<Callback<String, Throwable>> cbs = callbacksByTopic.get(topic);
    if (cbs != null) {
      for (Callback<String, Throwable> cb : cbs) {
        cb.onSuccess(val);
      }
    }
  }

  private native void createComponent(String id, String tagName)/*-{
      $wnd.middleware.createComponent(id, tagName);
   }-*/;
}
