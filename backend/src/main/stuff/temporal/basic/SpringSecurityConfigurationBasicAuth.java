package temporal.basic;

import com.pomhotel.booking.ui.securityhandlers.CustomAuthenticationFailureHandler;
import com.pomhotel.booking.ui.securityhandlers.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter {
    //--- Functions ----------------------------------------------------
    @Autowired
    private DataSource securityDataSource;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;

    // Note: Cruizg, necesario para evitar que la seguridad se aplique a los resources estaticos. Como los css, imagenes y javascripts
    String[] resources = new String[]{
            "/css/**","/fonts/**","/images/**","/js/**","/**","/home/**","/reactjs/**"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //Note: Uso de jdbc para encontrar username, password, role y enabled
        auth.jdbcAuthentication().dataSource(securityDataSource)
                .usersByUsernameQuery("select username,password, enabled from logins where username=?")
                .authoritiesByUsernameQuery("select username, role from logins where username=?");
        //.passwordEncoder(new NoOpPasswordEncoder.getInstance());
    }


    @Bean
    // Note: NoOpPasswordEncoder deprecated clase chapu para hacer tener un plaintext de los passwords
    public PasswordEncoder passwordEncoder(){
        return new PasswordEnconderPlaintText();
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(securityDataSource);
        return jdbcUserDetailsManager;
    }


    //--- HttpSecurity ----------------------------------------------------

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //.formLogin().and()
                .httpBasic();
    }
}