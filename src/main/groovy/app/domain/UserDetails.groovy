package app.domain

import groovy.transform.CompileStatic
import groovy.transform.ToString
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.NotNull

@ToString
@CompileStatic
class UserDetails {

    @NotNull
    @NotEmpty
    String username

    @NotNull
    @NotEmpty
    @Email
    String email

}
