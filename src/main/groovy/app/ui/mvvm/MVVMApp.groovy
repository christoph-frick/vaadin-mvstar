package app.ui.mvvm

import app.domain.LoginRequest
import app.domain.LoginService
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.CustomComponent
import org.springframework.beans.factory.annotation.Autowired
import org.vaadin.spring.UIScope
import org.vaadin.spring.navigator.VaadinView

@UIScope
@VaadinView(name='mvvm')
class MVVMApp extends CustomComponent implements View {

    final LoginService loginService

    @Autowired
    MVVMApp(LoginService loginService) {
        this.loginService = loginService
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent event) {
        setCompositionRoot(new LoginView(new LoginViewModel(loginService, new LoginRequest())))
    }

}
