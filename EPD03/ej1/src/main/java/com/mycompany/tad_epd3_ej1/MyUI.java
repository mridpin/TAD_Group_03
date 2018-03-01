package com.mycompany.tad_epd3_ej1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.Calendar;

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

        Calendar cal = Calendar.getInstance();

        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String mes = "";
                switch (Calendar.MONTH) {
                    case 1:
                        mes = "Enero";
                        break;
                    case 2:
                        mes = "Febrero";
                        break;

                    case 3:
                        mes = "Marzo";
                        break;

                    case 4:
                        mes = "Abril";
                        break;

                    case 5:
                        mes = "Mayo";
                        break;

                    case 6:
                        mes = "Junio";
                        break;

                    case 7:
                        mes = "Julio";
                        break;

                    case 8:
                        mes = "Agosto";
                        break;

                    case 9:
                        mes = "Septiembre";
                        break;

                    case 10:
                        mes = "Octumbre";
                        break;

                    case 11:
                        mes = "Noviembre";
                        break;

                    case 12:
                        mes = "Diciembre";
                        break;
                }

                if (cal.get(Calendar.HOUR_OF_DAY) >= 5 && cal.get(Calendar.HOUR_OF_DAY) < 12) { //Buenos dias
                    layout.addComponent(new Label("Buenos dias, hoy es " + cal.get(Calendar.DAY_OF_MONTH) + " de " + mes + " de " + cal.get(Calendar.YEAR)));
                } else if (cal.get(Calendar.HOUR_OF_DAY) >= 12 && cal.get(Calendar.HOUR_OF_DAY) < 21) { //Buenas tardes
                    layout.addComponent(new Label("Buenas tardes, hoy es " + cal.get(Calendar.DAY_OF_MONTH) + " de " + mes + " de " + cal.get(Calendar.YEAR)));
                } else { //Buenas noches
                    layout.addComponent(new Label("Buenas noches, hoy es " + cal.get(Calendar.DAY_OF_MONTH) + " de " + mes + " de " + cal.get(Calendar.YEAR)));
                }
            }
        });
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
