package dg.gwtvue.web;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import dg.gwtvue.web.jsinterop.JsWindowWrapper;

public class parent implements EntryPoint {

  int count = 0;

  public void onModuleLoad() {
    // A GWT button.
    final Button sendButton = new Button("GWT - you clicked the Vue button "
        + count + " times");
    sendButton.addStyleName("sendButton");
    // A Vue.js button.
    final VueWidget buttonCounter = new VueWidget("button-counter");

    final TextBox query = new TextBox();

    RootPanel.get("gwtButtonContainer").add(sendButton);
    RootPanel.get("vueButtonContainer").add(buttonCounter);
    RootPanel.get("queryContainer").add(query);

    // Send events to vue.
    sendButton.addClickHandler(event -> {
      // Send event to Vue component
      buttonCounter.sendEvent("gwt-send-clicked", "Some data 123");
    });
    // Receive events from vue.
    buttonCounter.onEvent("vue-send-clicked",
        event -> sendButton.setText("GWT - you clicked the Vue button " + (++count) + " times"));
    // Another vue.js component.
    VueWidget jsonResult = new VueWidget("json-result");
    final Button addAnotherLabel = new Button("Add another vue.js label");
    final Button removeLabel = new Button("Remove a vue.js label");
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.add(addAnotherLabel);
    horizontalPanel.add(removeLabel);
    RootPanel.get().add(horizontalPanel);
    VerticalPanel labels = new VerticalPanel();
    RootPanel.get().add(labels);
    RootPanel.get().add(jsonResult);

    query.addKeyUpHandler(event -> {
      if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
        jsonResult.sendEvent("gwt-query-changed", query.getText());
      }
    });

    addAnotherLabel.addClickHandler(event -> {
      VueWidget label = new VueWidget("subscribed-label");
      labels.add(label);
      JsWindowWrapper.Middleware
          .emitGlobalEvent("label-text-changed", System.currentTimeMillis() + " label added");
    });

    removeLabel.addClickHandler(event -> {
      if (labels.getWidgetCount() > 0) {
        labels.remove(0);
        JsWindowWrapper.Middleware
            .emitGlobalEvent("label-text-changed", System.currentTimeMillis() + " label removed");
      }
    });

  }
}
