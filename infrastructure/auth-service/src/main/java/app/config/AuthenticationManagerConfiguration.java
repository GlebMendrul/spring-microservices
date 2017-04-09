package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by gleb on 3/19/2017.
 */
@Configuration
@Order(Ordered.LOWEST_PRECEDENCE - 20)
public class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

    private static final String USERS_SQL = "SELECT username, password, enabled FROM users WHERE username=?";
    private static final String AUTHORITIES_SQL = "SELECT username, role FROM user_roles WHERE username=?";

    @Autowired
    private DataSource dataSource;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder).usersByUsernameQuery(USERS_SQL);
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder).authoritiesByUsernameQuery(AUTHORITIES_SQL);
    }
}
