package com.mycompany.p4;


import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
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

        Chart tarta = new Chart(ChartType.PIE);
        Configuration config = tarta.getConfiguration();
        
        config.setTitle("Elecciones generales 2016");
        config.setSubTitle("Diputados obtenidos");
        
        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setMarker(new Marker(false));
        config.setPlotOptions(plotOptions);
        
        DataSeries datos = new DataSeries();
        datos.add(new DataSeriesItem("PP", 137));
        datos.add(new DataSeriesItem("PSOE", 85));
        datos.add(new DataSeriesItem("UNIDOS PODEMOS", 71));
        datos.add(new DataSeriesItem("C'S", 32));
        datos.add(new DataSeriesItem("ERC-CATSI", 9));
        datos.add(new DataSeriesItem("CDC", 8));
        datos.add(new DataSeriesItem("EAJ-PNV", 5));
        datos.add(new DataSeriesItem("EH-BILDU", 2));
        datos.add(new DataSeriesItem("CCA-PNC", 1));
        
        config.addSeries(datos);
        
        layout.addComponent(tarta);
        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
