package com.mycompany.tad_epd3_ej2;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.RadioButton;

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

        final TextField nombre = new TextField();
        nombre.setCaption("Nombre:");

        final TextField apellidos = new TextField();
        apellidos.setCaption("Apellidos:");

        final DateField fecha_nacimiento = new DateField();
        fecha_nacimiento.setCaption("Fecha de nacimiento:");
        fecha_nacimiento.setDateFormat("dd/MM/yyyy");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        final TextField trabaja = new TextField();
        trabaja.setCaption("Trabaja: (S/N):");

        Button button = new Button("Enviar");
        button.addClickListener((Button.ClickEvent e) -> {

            if (trabaja.getValue().equals("S")) {
                final TextField sueldo = new TextField();
                sueldo.setCaption("Sueldo bruto mensual:");
                
                final TextField pagas = new TextField();
                pagas.setCaption("Numero de pagas:");
                
                Button button1 = new Button("Enviar");
                button1.addClickListener((Button.ClickEvent v) -> {
                    layout.addComponent(new Label("Nombre: " + nombre.getValue()));
                    layout.addComponent(new Label("Apellidos: " + apellidos.getValue()));
                    layout.addComponent(new Label("Fecha de nacimiento: " + sdf.format(fecha_nacimiento.getValue())));
                    layout.addComponent(new Label("Sueldo: " + sueldo.getValue()));
                    layout.addComponent(new Label("Pagas: " + pagas.getValue()));
                });
                
                layout.addComponent(sueldo);
                layout.addComponent(pagas);
                layout.addComponent(button1);
            } else {
                layout.addComponent(new Label("Nombre: " + nombre.getValue()));
                layout.addComponent(new Label("Apellidos: " + apellidos.getValue()));
                layout.addComponent(new Label("Fecha de nacimiento: " + sdf.format(fecha_nacimiento.getValue())));
            }

        });

        layout.addComponent(nombre);
        layout.addComponent(apellidos);
        layout.addComponent(fecha_nacimiento);
        layout.addComponent(trabaja);
        layout.addComponent(button);

        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
