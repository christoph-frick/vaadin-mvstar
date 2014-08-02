package app.ui

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.Label
import org.vaadin.spring.UIScope
import org.vaadin.spring.navigator.VaadinView

@VaadinView(name='error')
@UIScope
class ErrorApp extends CustomComponent implements View {
    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setCompositionRoot(new Label("No such view: ${viewChangeEvent.viewName}"))
    }
}
