package com.azure.ej3;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.client.ui.ChartOptionsConnector;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.themes.GrayTheme;
import com.vaadin.addon.charts.themes.GridTheme;
import com.vaadin.addon.charts.themes.SkiesTheme;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        Chart grafica = new Chart(ChartType.PIE);
        ChartOptions.get().setTheme(new SkiesTheme());

        Configuration conf = grafica.getConfiguration();
        conf.setTitle("Planets");
        conf.setSubTitle("The bigger they are the harder they pull");
        PlotOptionsLine plotOption = new PlotOptionsLine();
        plotOption.setMarker(new Marker(false));
        conf.setPlotOptions(plotOption);
        
        DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("Jupiter",46.5));
        series.add(new DataSeriesItem("Saturn",38.7));
        series.add(new DataSeriesItem("Uranus",7.2));
        series.add(new DataSeriesItem("Neptune",4.8));
        series.add(new DataSeriesItem("Mercurio",0.8));
        series.add(new DataSeriesItem("Venus",0.7));
        series.add(new DataSeriesItem("Earth",0.7));
        series.add(new DataSeriesItem("Mars",0.6));
        
        conf.addSeries(series);

        layout.addComponent(grafica);
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUI", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
