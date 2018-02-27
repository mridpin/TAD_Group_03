package upo.tad.epd3.p2;

import upo.tad.epd3.p2.model.Trabajador;
import upo.tad.epd3.p2.model.DataFactory;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.swing.text.html.HTML;


/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
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

         
        Table table = new Table("Entrada-Salida");

        // Define columns for the built-in container
        table.addContainerProperty("Nombre", String.class, null);
        table.addContainerProperty("Apellidos", String.class, null);
        table.addContainerProperty("DNI", String.class, null);
        table.addContainerProperty("Hora entrada", String.class, null);
        table.addContainerProperty("Hora salida", String.class, null);
        table.addContainerProperty("Fecha", String.class, null);

        DataFactory df = new DataFactory();
        List<Trabajador> list = df.generarDatos();
        for (Trabajador tr : list) {
            Object newItemId = table.addItem();
            Item row = table.getItem(newItemId);
            row.getItemProperty("Nombre").setValue(tr.getNombre());
            row.getItemProperty("Apellidos").setValue(tr.getApellidos());
            row.getItemProperty("DNI").setValue(tr.getDni());
            row.getItemProperty("Hora entrada").setValue(tr.getEntrada());
            row.getItemProperty("Hora salida").setValue(tr.getSalida());
            row.getItemProperty("Fecha").setValue(tr.getFecha());
        }
        table.setPageLength(table.size());
        layout.addComponents(table);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
