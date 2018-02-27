package upo.tad.epd3.p1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
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
public class MyUI extends UI implements Button.ClickListener {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final GridLayout layout = new GridLayout(3, 4);

        Table table = new Table("Entrada-Salida");
        String[] operations = new String[]{"7", "8", "9", "4", "5", "6",
            "1", "2", "3", "0", "+", "-", "*", "/", "C",};
        for (String chr : operations) {
            Button button = new Button(chr);
            button.addClickListener(this);
            layout.addComponent(button);
        }

        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        // Este es el button que ha sido clicado
        Button button = event.getButton();
        // Contenido del button
        String operacion = button.getCaption();

        // Calculate the new value
        double newValue = calcular(requestedOperation);

        // Update the result label with the new value
        display.setValue(newValue);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    private Double calcular(double valorActual, String operador) {
        Double resultado;
        try {
            if (operador.equals("+")) {
                resultado = operador1 + operador2;
            } else if (request.getParameter("operador").equals("-")) {
                resultado = operador1 - operador2;
            } else if (request.getParameter("operador").equals("*")) {
                resultado = operador1 * operador2;
            } else if (request.getParameter("operador").equals("%")) {
                resultado = operador1 / operador2;
            }
        } catch (ArithmeticException e) {

            TelemetryClient tc = new TelemetryClient();
            tc.getContext().setInstrumentationKey("c2d08a94-9a97-4f4c-9835-1d5e2cb538dd");
            tc.trackEvent("divisionPorCero");
            out.write("<p>ERROR: Division entre 0!</p>");
        }
        out.write("<p>Resultado: " + resultado + "</p>");
    }
}
