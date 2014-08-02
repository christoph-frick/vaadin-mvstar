package app.ui

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Button
import com.vaadin.ui.CssLayout
import org.vaadin.spring.UIScope
import org.vaadin.spring.navigator.VaadinView

@UIScope
@VaadinView(name='')
class MainApp extends CssLayout implements View {

    MainApp() {
        super()
        final nav = { String view -> getUI().navigator.navigateTo(view) }
        addComponent(new Button('MVP',  {nav('mvp')}  as Button.ClickListener))
        addComponent(new Button('MVVM', {nav('mvvm')} as Button.ClickListener))
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent event) {
        // nothing
    }

}
