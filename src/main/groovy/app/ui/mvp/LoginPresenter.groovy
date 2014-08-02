package app.ui.mvp

import app.domain.LoginRequest
import app.domain.LoginService
import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.Notification
import groovy.util.logging.Slf4j
import org.vaadin.spring.UIScope
import org.vaadin.spring.VaadinComponent

@Slf4j
@UIScope
@VaadinComponent
class LoginPresenter extends CustomComponent {

    private LoginView view

    private BeanFieldGroup<LoginRequest> binder

    private LoginService loginService

    LoginPresenter(LoginService loginService, LoginView view) {
        log.debug "Login presenter init"
        this.loginService = loginService
        this.view = view
        binder = new BeanFieldGroup<LoginRequest>(LoginRequest)
    }

    void bind(LoginRequest loginRequest) {
        log.debug "Login presenter bind"
        binder.bindMemberFields(view)
        binder.setItemDataSource(loginRequest)
        view.loginButton.addClickListener({actionLogin()})
    }

    private void actionLogin() {
        if (binder.valid) {
            binder.commit()
            def result = loginService.login(binder.itemDataSource.bean)
            log.debug result.toString()
            if (result.success) {
                log.info "Logged in"
            } else {
                log.info "Login failed: ${result.errorMessage}"
                Notification.show("User unknown or password wrong", Notification.Type.ERROR_MESSAGE)
                view.clear()
            }
        }
    }
}
