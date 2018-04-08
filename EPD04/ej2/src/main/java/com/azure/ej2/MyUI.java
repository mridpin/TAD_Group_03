package com.azure.ej2;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        Button mensaje = new Button("Mensaje normal");
        Button warning = new Button("Mensaje de warning");
        Button error = new Button("Mensaje de error");
        Button bandeja = new Button("Mensaje de bandeja");
        
        
        mensaje.addClickListener((event) -> {
        Notification.show("Mensaje normal", Notification.Type.HUMANIZED_MESSAGE);
        });
        warning.addClickListener((event) -> {
        Notification.show("Mensaje de warning", Notification.Type.WARNING_MESSAGE);
        });
        error.addClickListener((event) -> {
        Notification.show("Mensaje de error", Notification.Type.ERROR_MESSAGE);
        });
        bandeja.addClickListener((event) -> {
        Notification.show("Mensaje de bandeja", Notification.Type.TRAY_NOTIFICATION);
        });
        
        
        layout.addComponents(mensaje, warning, error, bandeja);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
