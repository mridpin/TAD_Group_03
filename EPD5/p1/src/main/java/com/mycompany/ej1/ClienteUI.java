package com.mycompany.ej1;

import com.mycompany.ej1.Broadcaster.BroadcastListener;
import com.vaadin.annotations.Push;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@Push
public class ClienteUI extends UI implements BroadcastListener {

    final Label l = new Label("");

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Broadcaster.register(this);
        final VerticalLayout layout = new VerticalLayout();
        layout.addComponent(l);
        setContent(layout);
    }

    @Override
    public void receiveBroadcast(final String message) {
        access(new Runnable() {
            @Override
            public void run() {
                l.setValue(message);
            }
        });
    }

    @Override
    public void detach() {
        Broadcaster.unregister(this);
        super.detach();
    }

    @WebServlet(value = "/ClientePush/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = ClienteUI.class)
    public static class Servlet extends VaadinServlet {
    }
}
