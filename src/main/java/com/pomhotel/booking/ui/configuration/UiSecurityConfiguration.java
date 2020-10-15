package com.pomhotel.booking.ui.configuration;

import com.pomhotel.booking.ui.securityhandlers.CustomAuthenticationFailureHandler;
import com.pomhotel.booking.ui.securityhandlers.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

//--- Configuration Security -------------------------------------------
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class UiSecurityConfiguration extends WebSecurityConfigurerAdapter {
    // Note: Con solo meter la dependencias en el pom spring ya crea como un login

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                //API
                    .antMatchers("/api/bookroomnow/**").hasRole("CLIENT")
                //MVC
                    .antMatchers("/bookroomnow/**").hasRole("CLIENT") //protegido por el role

                .antMatchers(resources).permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    //.loginPage("/signin")  // endpoint que pasa el form de login
                    .loginPage("/api/signin")
                    //.loginPage("/contact.html") // de ser un static para hacerlo asin
                    .loginProcessingUrl("/authenticateTheUser") //endpoint gestionado por spring
                    //.defaultSuccessUrl("/login?ok") // no me redirige quizas por el handler
                    .defaultSuccessUrl("/defaultbooknow")
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/signin?logout") //no lo entiendo...
                    .permitAll();

        //http.csrf().disable(); //TODO Seguridad desactivada mientras migro a la api

        //https://stackoverflow.com/questions/54345301/react-springboot-csrf
        //asi creamos automaticamente una cookie con el XSRF-TOKEN
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        //CrossOrigin
        //https://www.baeldung.com/spring-cors
        //https://www.baeldung.com/spring-security-cors-preflight
        //https://docs.spring.io/spring-security/site/docs/current/reference/html5/#cors
        //http.cors(withDefaults()); //disable this line to reproduce the CORS 401


    }

    //Cors
    /*
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    */



}


