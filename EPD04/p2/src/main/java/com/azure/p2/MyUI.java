package com.azure.p2;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;

@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        List<Pelicula> peliculas = new ArrayList<>();
        HorizontalSplitPanel layout = new HorizontalSplitPanel();
        VerticalLayout left = new VerticalLayout();
        VerticalLayout right = new VerticalLayout();
        Table tabLeft = new Table();
        FormLayout formulario = new FormLayout();
        Table tabRight = new Table();
        layout.addComponents(left, right);
        left.addComponent(tabLeft);
        right.addComponents(formulario, tabRight);
        setContent(layout);

        Pelicula p1 = new Pelicula();
        Pelicula p2 = new Pelicula();
        Pelicula p3 = new Pelicula();
        Pelicula p4 = new Pelicula();
        Pelicula p5 = new Pelicula();

        p1.setTitulo("It");
        p2.setTitulo("Black Panther");
        p3.setTitulo("Los Vengadores");
        p4.setTitulo("Iron man");
        p5.setTitulo("Capitán América");

        p1.setSipnosis("Un payaso loco");
        p2.setSipnosis("Un tio vestido de pantera");
        p3.setSipnosis("Unos pocos tios en mayas");
        p4.setSipnosis("Un tio con un traje de acero");
        p5.setSipnosis("Un tio viejo pero joven");

        p1.setSala("5");
        p2.setSala("1");
        p3.setSala("6");
        p4.setSala("8");
        p5.setSala("3");

        p1.addSesiones("22:30");
        p1.addSesiones("19:45");
        p2.addSesiones("23:00");
        p3.addSesiones("21:15");
        p3.addSesiones("16:40");
        p3.addSesiones("12:20");
        p4.addSesiones("00:10");
        p4.addSesiones("17:45");
        p4.addSesiones("20:00");
        p5.addSesiones("00:00");

        peliculas.add(p1);
        peliculas.add(p2);
        peliculas.add(p3);
        peliculas.add(p4);
        peliculas.add(p5);

        tabLeft.setSelectable(true);
        tabLeft.addContainerProperty("Titulo", String.class, null);
        tabLeft.addContainerProperty("Sala", String.class, null);
        tabLeft.addContainerProperty("Sesiones", ArrayList.class, null);
        tabLeft.setPageLength(tabLeft.size());
        tabLeft.setWidth(100, Unit.PERCENTAGE);

        tabLeft.addItem(new Object[]{p1.getTitulo(), p1.getSala(), p1.sesiones}, null);
        tabLeft.addItem(new Object[]{p2.getTitulo(), p2.getSala(), p2.sesiones}, null);
        tabLeft.addItem(new Object[]{p3.getTitulo(), p3.getSala(), p3.sesiones}, null);
        tabLeft.addItem(new Object[]{p4.getTitulo(), p4.getSala(), p4.sesiones}, null);
        tabLeft.addItem(new Object[]{p5.getTitulo(), p5.getSala(), p5.sesiones}, null);

        formulario.setMargin(true);
        formulario.setSpacing(true);
        TextField nombre = new TextField("Nombre");
        TextField apellidos = new TextField("Apellidos");
        TextField telefono = new TextField("Teléfono");
        TextField pelic = new TextField("Pelicula");
        pelic.setEnabled(false);
        TextField sala = new TextField("Sala");
        sala.setEnabled(false);
        ComboBox sesiones = new ComboBox("Selecciona sesión");
        Button enviar = new Button("Reservar");

        formulario.addComponents(nombre, apellidos, telefono, pelic, sala, sesiones, enviar);

        tabRight.setSelectable(true);
        tabRight.addContainerProperty("Titulo", String.class, null);
        tabRight.addContainerProperty("Sala", String.class, null);
        tabRight.addContainerProperty("Sesiones", ArrayList.class, null);
        tabRight.setPageLength(tabRight.size());
        tabRight.setWidth(100, Unit.PERCENTAGE);

        tabRight.addItem(new Object[]{p1.getTitulo(), p1.getSala(), p1.sesiones}, null);
        tabRight.addItem(new Object[]{p2.getTitulo(), p2.getSala(), p2.sesiones}, null);
        tabRight.addItem(new Object[]{p3.getTitulo(), p3.getSala(), p3.sesiones}, null);
        tabRight.addItem(new Object[]{p4.getTitulo(), p4.getSala(), p4.sesiones}, null);
        tabRight.addItem(new Object[]{p5.getTitulo(), p5.getSala(), p5.sesiones}, null);

        tabLeft.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                int fila = (Integer) event.getItemId() - 1;
                Notification.show(peliculas.get(fila).titulo, peliculas.get(fila).sipnosis, Notification.Type.HUMANIZED_MESSAGE);
            }
        });

        tabRight.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                int fila = (Integer) event.getItemId() - 1;
                pelic.setValue(peliculas.get(fila).titulo);
                sala.setValue(peliculas.get(fila).sala);
                sesiones.removeAllItems();
                sesiones.addItems(peliculas.get(fila).sesiones);
            }
        });

        enviar.addClickListener((event) -> {
            right.removeAllComponents();
            Label nombreFinal = new Label("Nombre: " + nombre.getValue());
            Label apellidosFinal = new Label("Apellidos: " + apellidos.getValue());
            Label telefonoFinal = new Label("Teléfono: " + telefono.getValue());
            Label peliculaFinal = new Label("Película: " + pelic.getValue());
            Label salaFinal = new Label("Sala: " + sala.getValue());
            Label sesionFinal = new Label("Sesión: " + String.valueOf(sesiones.getValue()));
            right.addComponents(nombreFinal, apellidosFinal, telefonoFinal, peliculaFinal, salaFinal, sesionFinal);
            right.setMargin(true);
            right.setSpacing(true);
        });

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
