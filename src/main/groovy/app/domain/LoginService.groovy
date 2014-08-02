package app.domain

import groovy.transform.CompileStatic
import org.springframework.stereotype.Service

import javax.validation.Valid

@Service
@CompileStatic
class LoginService {

    LoginResult login(@Valid LoginRequest request) {
        if (request.username=='AGGI' && request.password=='AGGI') {
            return new LoginResult(
                    success: true,
                    userDetails: new UserDetails(username: request.username, email: "${request.username}@example.com"),
            )
        }
        return new LoginResult(
                success: false,
                errorMessage: 'User unknown or password wrong',
        )
    }
}
