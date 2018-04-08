package com.mycompany.ej1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class SesionBoton extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        Label h1 = new Label("TAD - Grupo03 - EPD05 EJ1: SesionBoton");
        h1.addStyleName("h1");
        TextField texto = new TextField();
        Button button = new Button("Enviar");
        button.addClickListener( e -> {
            WrappedSession session = getSession().getSession();
            session.setAttribute("valorUsuario", texto.getValue());
            getUI().getPage().setLocation("http://localhost:8080/SesionEtiqueta");
        });
        Label urllab = new Label("URL actual: " + Page.getCurrent().getLocation().toString());

        
        layout.addComponents(h1, texto, button, urllab);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = SesionBoton.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
