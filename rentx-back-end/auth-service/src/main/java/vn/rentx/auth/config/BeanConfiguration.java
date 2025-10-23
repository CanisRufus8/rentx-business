package vn.rentx.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.rentx.auth.domain.user.service.PasswordPolicy;
import vn.rentx.auth.domain.user.service.impl.DefaultPasswordPolicy;

/**
 * BEANS Configuration
 */
@Configuration
public class BeanConfiguration {

    @Bean
    PasswordPolicy defaultPasswordPolicy() {
        return new DefaultPasswordPolicy();
    }

}
