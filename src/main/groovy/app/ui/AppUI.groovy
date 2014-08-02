package app.ui

import com.vaadin.annotations.PreserveOnRefresh
import com.vaadin.annotations.Title
import com.vaadin.navigator.Navigator
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.vaadin.spring.VaadinUI
import org.vaadin.spring.navigator.SpringViewProvider

@Slf4j
@VaadinUI
@Title("Application")
@PreserveOnRefresh
class AppUI extends UI {

    @Autowired
    private SpringViewProvider viewProvider

    @Autowired
    private ErrorApp errorApp

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        log.debug "AppUI init"
        def navigator = new Navigator(this,this)
        navigator.addProvider(viewProvider)
        navigator.setErrorView(errorApp)
        setNavigator(navigator)
    }
}
