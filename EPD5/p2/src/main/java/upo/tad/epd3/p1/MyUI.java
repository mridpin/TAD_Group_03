package upo.tad.epd3.p1;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.VerticalLayout;
import java.util.Arrays;

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

    // Fuente: https://demo.vaadin.com/docs/example-source/com/vaadin/demo/Calc.java.html
    private final Label display = new Label("");
    private final String[] valores = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private Double operador1 = 0.0;
    private Double operador2 = 0.0;
    private String ultimaOperacion = "C";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final GridLayout layout = new GridLayout(3, 6);
        layout.addComponent(display, 0, 0, 2, 0);
        String[] operations = new String[]{"7", "8", "9", "4", "5", "6",
            "1", "2", "3", "0", "+", "-", "*", "/", "C", "="};
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
        Double resultado = calcular(operacion);
        display.setValue(resultado.toString());
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    private Double calcular(String operacion) {
        // Comprobar si se han introducido numeros o operadores
        if (Arrays.asList(valores).contains(operacion)) {
            operador1 = operador1 * 10 + Double.parseDouble(operacion);
            return operador1;
        }
        try {
            switch (ultimaOperacion) {
                case "+":
                    operador2 = operador1 + operador2;
                    break;
                case "-":
                    operador2 -= operador1;
                    break;
                case "*":
                    operador2 *= operador1;
                    break;
                case "/":
                    operador2 /= operador1;
                    break;
                case "C":
                    operador2 = operador1;
                    break;
                default:
                    break;
            }
            ultimaOperacion = operacion;
            operador1 = 0.0;
            if (operacion.equals("C")) {
                operador2 = 0.0;
            }
        } catch (ArithmeticException e) {
//            TelemetryClient tc = new TelemetryClient();
//            tc.getContext().setInstrumentationKey("c2d08a94-9a97-4f4c-9835-1d5e2cb538dd");
//            tc.trackEvent("divisionPorCero");
            System.err.println("<p>ERROR: Division entre 0!</p>");
        }
        return operador2;
    }
}
