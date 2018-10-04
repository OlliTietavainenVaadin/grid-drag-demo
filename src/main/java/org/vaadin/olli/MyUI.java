package org.vaadin.olli;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.grid.DropMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.GridRowDragger;

/**
 * This UI is the application entry point. A UI may either represent a browser window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded. <p> The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        setMobileHtml5DndEnabled(true);
        layout.setSizeFull();
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            personList.add(new Person("Person " + i, i + 1));
        }
        Grid<Person> grid = new Grid<>(Person.class);
        ListDataProvider<Person> provider = new ListDataProvider<>(personList);
        grid.setDataProvider(provider);
        grid.setWidth("50%");
        grid.setHeight("700px");
        GridRowDragger<Person> dragger = new GridRowDragger<Person>(grid, grid, DropMode.BETWEEN);

        layout.addComponent(grid);
        setContent(layout);
    }

    public static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            setName(name);
            setAge(age);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
