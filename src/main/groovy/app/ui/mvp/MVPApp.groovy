package app.ui.mvp

import app.domain.LoginRequest
import app.domain.LoginService
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.CustomComponent
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.vaadin.spring.UIScope
import org.vaadin.spring.navigator.VaadinView

@Slf4j
@UIScope
@VaadinView(name='mvp')
class MVPApp extends CustomComponent implements View {

    @Autowired
    LoginService loginService

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setCompositionRoot(new LoginView(loginService, new LoginRequest()))
    }

}
