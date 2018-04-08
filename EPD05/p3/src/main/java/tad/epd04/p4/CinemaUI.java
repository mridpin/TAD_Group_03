package tad.epd04.p4;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

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
public class CinemaUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalSplitPanel layout = new HorizontalSplitPanel();
        VerticalLayout leftLayout = new VerticalLayout();
        VerticalLayout rightLayout = new VerticalLayout();
        Cinema cinema = new Cinema();

        // Headers
        Label h1 = new Label("FILMS");
        h1.addStyleName("h1");
        Label h2 = new Label("More Information");
        h2.addStyleName("h1");

        // Right side
        Label name = new Label("Name: ");
        Label director = new Label("Director: ");
        Label year = new Label("Year: ");
        Label score = new Label("Socre: ");
        Label synopsis = new Label("Synopsis: ");

        // Table
        Table table = new Table("Films");
        table.addContainerProperty("Name", String.class, null);
        table.addContainerProperty("Room", String.class, null);
        table.addContainerProperty("Sessions", String.class, null);
        drawtable(cinema.getFilms(), table);
        table.setSelectable(true);
        table.setImmediate(true);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                int i = (int) event.getItemId();
                Film f = cinema.getFilms().get(i);
                name.setValue("Name: " + f.getName());
                director.setValue("Director: " + f.getDirector());
                year.setValue("Year: " + f.getYear());
                score.setValue("Score: " + f.getScore() + "/5");
                synopsis.setValue("Synopsis: \n" + f.getSynopsis());
                layout.setSplitPosition(40, Unit.PERCENTAGE);
            }
        });

        // Search function
        TextField searchBox = new TextField("Search by title:");
        Button searchButton = new Button("Search");
        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String search = searchBox.getValue();
                List<Film> filteredFilms = cinema.searchByName(search);
                drawtable(filteredFilms, table);
            }
        });
        
        // Collapser Button
        Button collapser = new Button("Collapse");
        collapser.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                layout.setSplitPosition(100, Unit.PERCENTAGE);
            }
        });
        
        leftLayout.addComponent(h1);
        leftLayout.addComponent(table);
        leftLayout.addComponent(searchBox);
        leftLayout.addComponent(searchButton);

        rightLayout.addComponent(h2);        
        rightLayout.addComponent(collapser);
        rightLayout.addComponent(name);
        rightLayout.addComponent(director);
        rightLayout.addComponent(year);
        rightLayout.addComponent(score);
        rightLayout.addComponent(synopsis);

        layout.setFirstComponent(leftLayout);
        layout.setSecondComponent(rightLayout);
        layout.setSplitPosition(40, Unit.PERCENTAGE); 
        setContent(layout);
    }

    public void drawtable(List<Film> films, Table table) {
        int i = 0;
        table.removeAllItems();
        for (Film f : films) {
            Object[] cells = new Object[]{f.getName(), f.getRoom(), f.getSessions()};
            table.addItem(cells, i);
            i++;
        }
        table.setPageLength(table.size());
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = CinemaUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
