package app.domain

import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.NotNull

class LoginRequest {

    @NotNull
    @NotEmpty
    String username

    @NotNull
    @NotEmpty
    String password

    String server

}
