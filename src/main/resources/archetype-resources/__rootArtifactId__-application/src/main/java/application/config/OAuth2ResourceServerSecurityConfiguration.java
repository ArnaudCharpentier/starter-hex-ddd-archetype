package ${package}.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

/**
 * See documentation here:
 * https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.html#oauth2ResourceServer-org.springframework.security.config.Customizer-
 * The prePostEnabled attribute is what allows me to use @PreAuthorize
 * annotations in the REST controller to authorize requests depending on the
 * scope or the roles
 *
 * @author Fabian Bouché / Ippon
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.oauth2.client.provider.siteminder.user-info-uri}")
    String userInfoUri;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enabling OAuth 2.0 Resource server security with JWT Token validation
        http
                .oauth2ResourceServer()
                .opaqueToken()
                .introspector(pfIntrospector());
    }

    @Bean
    public OpaqueTokenIntrospector pfIntrospector() {
        return new PfUserInfoIntrospector(userInfoUri);
    }


}