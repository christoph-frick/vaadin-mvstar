package app.ui.mvvm

import app.domain.LoginRequest
import app.domain.LoginResult
import app.domain.LoginService
import com.vaadin.data.Property
import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.util.AbstractProperty
import com.vaadin.data.util.BeanItem
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.data.util.ObjectProperty
import com.vaadin.ui.Notification
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@CompileStatic
class LoginViewModel extends BeanItem<LoginRequest> {

    // TODO: can we use an ASTTransformation
    AbstractProperty<String> username

    AbstractProperty<String> password

    AbstractProperty<String> server

    AbstractProperty<Boolean> loginAllowed

    BeanItemContainer<String> serverValues

    final private LoginService loginService

    LoginViewModel(final LoginService loginService, LoginRequest item) {
        super(item)
        this.loginService = loginService
        // FIXME: should be done automatically
        for (String it : itemPropertyIds.intersect(properties.keySet())) {
            def mappedProperty = getItemProperty(it)
            /*
            def property = getProperty(it)
            assert property.class instanceof Property
            assert property.class instanceof Property.ValueChangeListener
            assert (property as Property).type.isAssignableFrom(mappedProperty.type)
            */
            setProperty(it, mappedProperty)
            log.debug "Auto mapped $it"
        }

        // FIXME: add at least helper methods
        loginAllowed = new ObjectProperty<Boolean>(false)
        addItemProperty('loginAllowed', loginAllowed)

        serverValues = new BeanItemContainer<String>(String, ["A", "B", "C"])

        // FIXME: reactor?  rx?  actor/akka?
        final usernameAndPasswordValueChangeListener = {
            log.debug "triggered mvp button eval"
            loginAllowed.setValue(username.value?.length() && password.value?.length())
        } as Property.ValueChangeListener
        username.addValueChangeListener(usernameAndPasswordValueChangeListener)
        password.addValueChangeListener(usernameAndPasswordValueChangeListener)
    }

    LoginResult actionLogin() {
        log.debug "Action login"
        log.debug "Binder valid: ${binder.valid}"
        if (!binder.isValid()) {
            Notification.show("Invalid input", Notification.Type.ERROR_MESSAGE)
            return null
        }
        def result = loginService.login(bean as LoginRequest)
        log.debug "Result: ${result.toString()}"
        if (result.success) {
            log.info "Logged in ${result.userDetails}"
        } else {
            log.info "Login failed: ${result.errorMessage}"
            Notification.show("User unknown or password wrong", Notification.Type.ERROR_MESSAGE)
            password.value = ''
            // TODO: focus/clear fields
        }
        return result
    }

    private FieldGroup binder

    def bind(LoginView view) {
        binder = new BeanFieldGroup<LoginRequest>(LoginRequest)
        binder.setBuffered(false)
        binder.setItemDataSource(this)
        binder.bindMemberFields(view)
    }
}
