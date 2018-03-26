package com.mycompany.ej1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class SesionEtiqueta extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        WrappedSession session = getSession().getSession();
        
        Label result = new Label((String) session.getAttribute("valorUsuario"));
        
        layout.addComponent(result);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = SesionEtiqueta.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
