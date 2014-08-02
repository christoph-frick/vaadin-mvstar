package app.ui.mvvm

import com.vaadin.data.fieldgroup.PropertyId
import com.vaadin.event.ShortcutAction
import com.vaadin.ui.*

class LoginView extends CustomComponent {

    private LoginViewModel viewModel

    @PropertyId('username')
    TextField usernameField

    @PropertyId('password')
    TextField passwordField

    @PropertyId('server')
    ComboBox serverField

    LoginView(LoginViewModel viewModel) {
        this.viewModel = viewModel

        usernameField = new TextField('Username').with{
            setNullRepresentation("")
            setImmediate(true)
            return it
        }

        passwordField = new TextField('Password').with{
            setNullRepresentation("")
            setImmediate(true)
            return it
        }

        serverField = new ComboBox('Server', viewModel.serverValues)
        serverField.setInputPrompt('default')

        viewModel.bind(this)

        final loginButton = new Button("Login").with{
            addClickListener({
                def result = viewModel.actionLogin()
                if (result && !result.success) {
                    usernameField.focus()
                    usernameField.selectAll()
                }
            })
            setClickShortcut(ShortcutAction.KeyCode.ENTER)
            setEnabled(viewModel.loginAllowed.value)
            return it
        }
        viewModel.loginAllowed.addValueChangeListener({ loginButton.setEnabled(viewModel.loginAllowed.value) })

        final layout = new FormLayout(usernameField, passwordField, serverField, loginButton)
        setCompositionRoot(layout)
    }

}
