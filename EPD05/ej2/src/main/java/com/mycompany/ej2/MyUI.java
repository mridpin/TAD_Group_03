package com.mycompany.ej2;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
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
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalSplitPanel verticalSplit = new VerticalSplitPanel();
        VerticalSplitPanel subVerticalSplit = new VerticalSplitPanel();
        HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();

        VerticalLayout leftLayout = new VerticalLayout();
        HorizontalLayout topLayout = new HorizontalLayout();
        HorizontalLayout subTopLayout = new HorizontalLayout();
        HorizontalLayout subBottomLayout = new HorizontalLayout();

        Tree tree = new Tree("The Planets and Major Moons");

        final Object[][] planets = new Object[][]{
            new Object[]{"Mercury"},
            new Object[]{"Venus"},
            new Object[]{"Earth", "The Moon"},
            new Object[]{"Mars", "Phobos", "Deimos"},
            new Object[]{"Jupiter", "Io", "Europa", "Ganymedes",
                "Callisto"},
            new Object[]{"Saturn", "Titan", "Tethys", "Dione",
                "Rhea", "Iapetus"},
            new Object[]{"Uranus", "Miranda", "Ariel", "Umbriel",
                "Titania", "Oberon"},
            new Object[]{"Neptune", "Triton", "Proteus", "Nereid",
                "Larissa"}
        };
                
        setContent(verticalSplit);        

        for (int i = 0; i < planets.length; i++) {
            String planet = (String) (planets[i][0]);
            tree.addItem(planet);

            if (planets[i].length == 1) {
                tree.setChildrenAllowed(planet, false);
            } else {
                for (int j = 1; j < planets[i].length; j++) {
                    String moon = (String) planets[i][j];
                    tree.addItem(moon);
                    tree.setParent(moon, planet);
                    tree.setChildrenAllowed(moon, false);
                }
                tree.expandItemsRecursively(planet);
            }
        }

        topLayout.addComponent(new Label("The Ultimate Cat Finder"));        
        leftLayout.addComponent(tree);
        subTopLayout.addComponent(new Label("Details"));        
        subBottomLayout.addComponent(new Label("Where is the cat?"));
        subBottomLayout.addComponent(new Label("I don't know!"));

        verticalSplit.setFirstComponent(topLayout);
        verticalSplit.setSecondComponent(horizontalSplit);
        verticalSplit.setSplitPosition(30, Unit.PERCENTAGE);

        horizontalSplit.setFirstComponent(leftLayout);
        horizontalSplit.setSecondComponent(subVerticalSplit);
        horizontalSplit.setSplitPosition(30, Unit.PERCENTAGE);

        subVerticalSplit.setFirstComponent(subTopLayout);
        subVerticalSplit.setSecondComponent(subBottomLayout);
        subVerticalSplit.setSplitPosition(30, Unit.PERCENTAGE);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
