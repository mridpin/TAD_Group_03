package com.azure.ej1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
        
        HorizontalLayout hLay = new HorizontalLayout();
        hLay.setSpacing(true);
        Button primero = new Button("Primero");
        Button segundo = new Button("Segundo");
        hLay.addComponents(primero, segundo);
        
        TextField texto = new TextField();
        
        layout.addComponents(hLay, texto);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
        
        primero.addClickListener((event) -> {
            texto.setValue("Primer Boton Pulsado");
        });
        
        segundo.addClickListener((event) -> {
            texto.setValue("Segundo Boton Pulsado");
        });
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
