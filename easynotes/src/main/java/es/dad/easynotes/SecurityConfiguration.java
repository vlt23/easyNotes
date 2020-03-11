package es.dad.easynotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import es.dad.easynotes.repository.UsuarioRepositoryAuthenticationProvider;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UsuarioRepositoryAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        //Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/search").permitAll();
        http.authorizeRequests().antMatchers("/searchAsignatura").permitAll();
        http.authorizeRequests().antMatchers("/searchCarrera").permitAll();
        http.authorizeRequests().antMatchers("/searchUniversidad").permitAll();
        http.authorizeRequests().antMatchers("/mostrarBusqueda").permitAll();
        http.authorizeRequests().antMatchers("/h2console").permitAll();

        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**").permitAll();
        http.authorizeRequests().antMatchers("/resources/**").permitAll();
        http.authorizeRequests().antMatchers("src/main/resources/**").permitAll();
        //http.authorizeRequests().antMatchers(HttpMethod.POST).permitAll();
        //http.authorizeRequests().antMatchers(HttpMethod.GET).permitAll();
        http.authorizeRequests().antMatchers("/h2console").permitAll();
        http.authorizeRequests().antMatchers("/h2console/**").permitAll();


        //Login
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().failureUrl("/loginerror");
        http.formLogin().defaultSuccessUrl("/");    //que la pagina por defecto sea el index


        // Registrar usuario
        http.authorizeRequests().antMatchers("/registro").permitAll();
        http.authorizeRequests().antMatchers("/signup").permitAll();
        
        http.authorizeRequests().anyRequest().authenticated();

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");

        //Private pages
        /*http.authorizeRequests().antMatchers("/download/**").authenticated();
        http.authorizeRequests().antMatchers("/saveApunte/**").authenticated();
        http.authorizeRequests().antMatchers("/subirApunte").authenticated();
        http.authorizeRequests().antMatchers("/subirCarrera").authenticated();
        http.authorizeRequests().antMatchers("/subirAsignatura/**").authenticated();
        http.authorizeRequests().antMatchers("/delete/**").authenticated();*/






        // Public pages
        //http.authorizeRequests().antMatchers("/").permitAll();
        //http.authorizeRequests().antMatchers("/login").permitAll();
        //http.authorizeRequests().antMatchers("/loginerror").permitAll();
        //http.authorizeRequests().antMatchers("/logout").permitAll();
       // http.authorizeRequests().antMatchers("/search").permitAll();
       // http.authorizeRequests().antMatchers("/searchAsignatura").permitAll();
        //http.authorizeRequests().antMatchers("/searchCarrera").permitAll();
        //http.authorizeRequests().antMatchers("/searchUniversidad").permitAll();
        //http.authorizeRequests().antMatchers("/css/**").permitAll();
        //http.authorizeRequests().antMatchers("/mostrarBusqueda?tipo=asignatura").permitAll();
        //http.authorizeRequests().antMatchers("/mostrarBusqueda*").permitAll();
        //http.authorizeRequests().antMatchers(HttpMethod.GET).permitAll();


        

        //IMPORTANTE: que vaya lo ultimo de los permit
        //http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**").permitAll();
        //http.authorizeRequests().antMatchers("/resources/**").permitAll();
        //http.authorizeRequests().antMatchers("src/main/resources/**").permitAll().anyRequest().permitAll();


        // Private pages (all other pages)
        //Forbiden pages
        //http.authorizeRequests().antMatchers("/download/**").authenticated();
        //http.authorizeRequests().anyRequest().denyAll();
        // Login form
        //http.formLogin().loginPage("/login");
       // http.formLogin().usernameParameter("username");
        //http.formLogin().passwordParameter("password");
        //http.formLogin().defaultSuccessUrl("/");
       // http.formLogin().failureUrl("/loginerror");

        // Logout
       // http.logout().logoutUrl("/logout");
        //http.logout().logoutSuccessUrl("/");

        //http.authorizeRequests().anyRequest().permitAll();

        // Public pages
        //http.authorizeRequests().antMatchers("/").permitAll();
        //http.authorizeRequests().antMatchers("/searchAsignatura").denyAll();


        //Permit css and files
        //http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**").permitAll();
        //http.authorizeRequests().antMatchers("/resources/**").permitAll();
        //http.authorizeRequests().antMatchers("src/main/resources/**").permitAll().anyRequest().permitAll();



        // Private pages (all other pages)
        //http.authorizeRequests().antMatchers("/download").permitAll();
        //http.authorizeRequests().antMatchers("/home").hasAnyRole("USER");
        //http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
        //http.authorizeRequests().anyRequest().denyAll();

        // Login form
        //http.formLogin().loginPage("/login");
        //http.formLogin().usernameParameter("username");
        //http.formLogin().passwordParameter("password");
        //http.formLogin().defaultSuccessUrl("/home");
        //http.formLogin().failureUrl("/loginerror");

        // Logout
        //http.logout().logoutUrl("/logout");
        //http.logout().logoutSuccessUrl("/");
        //http.csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.authenticationProvider(authenticationProvider);

    }
}