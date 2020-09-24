package com.pomhotel.booking.ui.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class UiSecurityConfiguration extends WebSecurityConfigurerAdapter {
/*
Con solo meter la dependencias en el pom spring ya mete un login
 */

    @Autowired
    private DataSource securityDataSource;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;

    //cruizg
    //Necesario para evitar que la seguridad se aplique a los resources estaticos
    //Como los css, imagenes y javascripts
    String[] resources = new String[]{
            "/css/**","/fonts/**","/images/**","/js/**","/**","/home/**"
            };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // uso de jdbc para encontrar username, password, role y enabled
        auth.jdbcAuthentication().dataSource(securityDataSource)
                .usersByUsernameQuery("select username,password, enabled from logins where username=?")
                .authoritiesByUsernameQuery("select username, role from logins where username=?");
                //.passwordEncoder(new NoOpPasswordEncoder.getInstance());
    }

    //NoOpPasswordEncoder deprecated clase chapu para hacer tener un plaintext de los passwords
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEnconderTest();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/book/**").hasRole("CLIENT") //protegido por el role
                    .antMatchers("/bookroomnow/**").hasRole("CLIENT") //protegido por el role
                    .antMatchers("/finalbooking/**").hasRole("CLIENT")
                .antMatchers(resources).permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/showMyLoginPage")  // entrypoint que pasar el form de login
                    //.loginPage("/contact.html") // de ser un static para hacerlo asin
                    .loginProcessingUrl("/authenticateTheUser") //entrypoint gestionado por spring
                    //.defaultSuccessUrl("/login?ok") // no me redirige quizas por el handler
                    .defaultSuccessUrl("/defaultbooknow")
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/showMyLoginPage?logout")
                    .permitAll()
                ;

    }



    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/resources/**","/login/**","/static/**","/Script/**","/Style/**","/Icon/**",
                "/js/**","/vendor/**","/bootstrap/**","/Image/**");

        //logoutSuccessUrl("/customLogout")
    }


    @Bean
    public UserDetailsManager userDetailsManager() {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();

        jdbcUserDetailsManager.setDataSource(securityDataSource);

        return jdbcUserDetailsManager;
    }




}

class PasswordEnconderTest implements PasswordEncoder {
    /*
    esto es una chapu...hay que darle una vuelta mas y crear una clase como toca o comprobar si NoOpPasswordEncoder rula.
     */

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}
