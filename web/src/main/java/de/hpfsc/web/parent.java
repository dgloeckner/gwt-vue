package de.hpfsc.web;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class parent implements EntryPoint {

  int count = 0;

  public void onModuleLoad() {
    // A GWT button.
    final Button sendButton = new Button("GWT - you clicked the Vue button "
        + count + " times");
    sendButton.addStyleName("sendButton");
    // A Vue.js button.
    final VueWidget buttonCounter = new VueWidget("button-counter");

    RootPanel.get("gwtButtonContainer").add(sendButton);
    RootPanel.get("vueButtonContainer").add(buttonCounter);

    // Send events to vue.
    sendButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        // Send event to Vue component
        buttonCounter.sendEvent("gwt-send-clicked", "Some data 123");
      }
    });
    // Receive events from vue.
    buttonCounter.onEvent("vue-send-clicked", new Callback<String, Throwable>() {
      @Override
      public void onFailure(Throwable reason) {

      }

      @Override
      public void onSuccess(String result) {
        sendButton.setText("GWT - you clicked the Vue button "
            + ++count + " times");
      }
    });
    // Another vue.js component.
    VueWidget jsonResult = new VueWidget("json-result");
    RootPanel.get().add(jsonResult);

  }
}
