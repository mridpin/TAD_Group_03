package com.azure.p2;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sun.font.TextLabel;

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

    public List<Trabajador> trabajadores = new ArrayList<>();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        HorizontalLayout registro = new HorizontalLayout();
        registro.setSpacing(true);

        TextField nom = new TextField();
        nom.setCaption("Nombre");

        TextField ape = new TextField();
        ape.setCaption("Apellidos");

        TextField dni = new TextField();
        dni.setCaption("DNI");

        TextField hEnt = new TextField();
        hEnt.setCaption("Hora entrada (hh:mm)");

        TextField hSal = new TextField();
        hSal.setCaption("Hora salida (hh:mm)");

        DateField fecha = new DateField();
        fecha.setCaption("Fecha");

        registro.addComponents(nom, ape, dni, hEnt, hSal, fecha);

        HorizontalLayout botones = new HorizontalLayout();
        botones.setSpacing(true);
        Button registrar = new Button("Registrar");
        Button mostrar = new Button("Mostrar registro");
        botones.addComponents(registrar, mostrar);

        VerticalLayout listado = new VerticalLayout();

        layout.addComponents(registro, botones, listado);

        setContent(layout);

        registrar.addClickListener((event) -> {
            trabajadores.add(new Trabajador(nom.getValue(), ape.getValue(), dni.getValue(), hEnt.getValue(), hSal.getValue(), fecha.getValue()));
            nom.clear();
            ape.clear();
            dni.clear();
            hEnt.clear();
            hSal.clear();
            fecha.clear();
        });

        mostrar.addClickListener((event) -> {
            listado.removeAllComponents();
            Label reg;
            for (Trabajador aux : trabajadores) {
                reg = new Label(aux.toString());
                listado.addComponent(reg);
            }
        });
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
