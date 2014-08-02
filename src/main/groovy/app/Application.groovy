package app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan
import org.vaadin.spring.EnableVaadin

@EnableAutoConfiguration
@EnableVaadin
@ComponentScan
class Application extends SpringBootServletInitializer {

    static void main(String[] args) {
        final app = new SpringApplication(Application)
        app.showBanner = false
        def ctx = app.run(args)
        assert ctx.containsBean('loginService')
    }

}
