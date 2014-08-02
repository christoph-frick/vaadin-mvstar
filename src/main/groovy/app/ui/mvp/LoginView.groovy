package app.ui.mvp

import app.domain.LoginRequest
import app.domain.LoginService
import com.vaadin.ui.Button
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.DateField
import com.vaadin.ui.FormLayout
import com.vaadin.ui.TextField
import groovy.util.logging.Slf4j
import org.vaadin.spring.UIScope
import org.vaadin.spring.VaadinComponent

@Slf4j
@UIScope
@VaadinComponent
class LoginView extends CustomComponent {

    final private LoginPresenter presenter

    TextField username = new TextField('Username')

    TextField password = new TextField('Password')

    Button loginButton = new Button('Login')

    LoginView(LoginService loginService, LoginRequest loginRequest) {
        log.debug "Login view init"
        presenter = new LoginPresenter(loginService, this)
        presenter.bind(loginRequest)
        username.setNullRepresentation("")
        username.setImmediate(true)
        password.setNullRepresentation("")
        password.setImmediate(true)
        setCompositionRoot(new FormLayout(username, password, loginButton))
    }

    void clear() {
        username.value = ""
        username.focus()
        password.value = ""
    }

}
