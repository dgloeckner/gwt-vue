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
    final Button sendButton = new Button("GWT - you clicked the Vue button "
        + count + " times");
    sendButton.addStyleName("sendButton");
    final VueWidget buttonCounter = new VueWidget("button-counter");

    RootPanel.get("gwtButtonContainer").add(sendButton);
    RootPanel.get("vueButtonContainer").add(buttonCounter);

    sendButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        GWT.log("GWT --> onclick");
        // Send event to Vue component
        buttonCounter.sendClicked();
      }
    });
    buttonCounter.setCallback(new Callback<String, Throwable>() {
      @Override
      public void onFailure(Throwable reason) {

      }

      @Override
      public void onSuccess(String result) {
        GWT.log("Was called back from vue - " + result);
        sendButton.setText("GWT - you clicked the Vue button "
            + ++count + " times");
      }
    });

  }
}
