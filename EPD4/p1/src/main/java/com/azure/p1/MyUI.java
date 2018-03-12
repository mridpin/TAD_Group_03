package com.azure.p1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
       
        Label titulo = new Label("PRODUCTO");
        layout.addComponent(titulo);
        
        HorizontalSplitPanel vPanel = new HorizontalSplitPanel();
        VerticalLayout left = new VerticalLayout();
        VerticalLayout right = new VerticalLayout();
        vPanel.addComponents(left, right);
        
        FormLayout formulario = new FormLayout();

        Label lab1 = new Label("1 - ¿Conoce el producto/servicio?");
        OptionGroup q1 = new OptionGroup();
        q1.addItems("Si", "No");
        q1.setMultiSelect(false);
        formulario.addComponents(lab1, q1);
        
        Label lab2 = new Label("2 - ¿Utiliza actualmente el producto/servicio?");
        OptionGroup q2 = new OptionGroup();
        q2.addItems("Si", "No");
        q2.setMultiSelect(false);
        formulario.addComponents(lab2, q2);
        
        Label lab3 = new Label("3 - ¿Ha utilizado anteriormente el producto/servicio?");
        OptionGroup q3 = new OptionGroup();
        q3.addItems("Si", "No");
        q3.setMultiSelect(false);
        formulario.addComponents(lab3, q3);
        
        Label lab4 = new Label("4 - Enumere por favor, las marcas que miraría");
        TextArea q4 = new TextArea();
        q4.setWidth(50, Unit.PERCENTAGE);
        formulario.addComponents(lab4, q4);
        
        Button envio = new Button("Finalizar");
        formulario.addComponent(envio);
        
        left.addComponent(formulario);
        layout.addComponent(vPanel);
        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
        
        envio.addClickListener((event) -> {
            Label a1 = new Label("1 - ¿Conoce el producto/servicio?: " + q1.getValue());
            Label a2 = new Label("2 - ¿Utiliza actualmente el producto/servicio?: " + q2.getValue());
            Label a3 = new Label("3 - ¿Ha utilizado anteriormente el producto/servicio?: " + q3.getValue());
            Label a4 = new Label("4 - Enumere por favor, las marcas que miraría: " + q4.getValue());
            right.addComponents(a1, a2, a3, a4);
            right.setSpacing(true);
            right.setMargin(true);
        });
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
