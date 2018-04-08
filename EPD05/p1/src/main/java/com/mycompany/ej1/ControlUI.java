package com.mycompany.ej1;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@Push(PushMode.AUTOMATIC)
public class ControlUI extends UI implements Broadcaster.BroadcastListener {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Broadcaster.register(this);
        final VerticalLayout layout = new VerticalLayout();
        final TextField input = new TextField();
        layout.addComponent(input);
        Button send = new Button("Send");
        layout.addComponent(send);
        send.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Broadcaster.broadcast(input.getValue()); // Broadcast the message input.setValue("");
            }
        });
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
    }

    @Override
    public void receiveBroadcast(final String message) {
        access(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
