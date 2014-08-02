package app.domain

import groovy.transform.CompileStatic
import groovy.transform.ToString

@CompileStatic
@ToString
class LoginResult {

    Boolean success

    String errorMessage

    UserDetails userDetails

}
