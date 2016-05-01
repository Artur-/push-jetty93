package org.vaadin.artur;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@com.vaadin.annotations.Push(transport = Transport.WEBSOCKET)
public class PushUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue()
                    + ", it works!"));
        });

        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "PushUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = PushUI.class, productionMode = false)
    public static class PushUIServlet extends VaadinServlet {
    }
    @WebServlet(urlPatterns = "/jsr356/*", name = "JSRServlet", asyncSupported = true,initParams = { @WebInitParam(name = "org.atmosphere.cpr.asyncSupport", value = "org.atmosphere.container.JSR356AsyncSupport") })
    @VaadinServletConfiguration(ui = PushUI.class, productionMode = false)
    public static class JSRServlet extends VaadinServlet {
    }
}
