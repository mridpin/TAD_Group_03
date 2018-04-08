package com.azure.ej3;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;

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
        fecha.setDateFormat("dd/MM/yyyy");
        fecha.setCaption("Fecha");

        registro.addComponents(nom, ape, dni, hEnt, hSal, fecha);

        Button registrar = new Button("Registrar");

        Table listado = new Table();
        listado.setWidth(100, Unit.PERCENTAGE);
        listado.setPageLength(listado.size());
        listado.addContainerProperty("Nombre", String.class, null);
        listado.addContainerProperty("Apellidos", String.class, null);
        listado.addContainerProperty("DNI", String.class, null);
        listado.addContainerProperty("Hora entrada", String.class, null);
        listado.addContainerProperty("Hora salida", String.class, null);
        listado.addContainerProperty("Fecha", Date.class, null);

        layout.addComponents(registro, registrar, listado);

        setContent(layout);

        registrar.addClickListener((event) -> {
            listado.addItem(new Object[]{nom.getValue(), ape.getValue(), dni.getValue(), hEnt.getValue(), hSal.getValue(), fecha.getValue()}, null);
            nom.clear();
            ape.clear();
            dni.clear();
            hEnt.clear();
            hSal.clear();
            fecha.clear();
        });

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
