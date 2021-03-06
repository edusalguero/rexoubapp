package com.edusalguero.rexoubapp.infrastructure.spring.configuration;

import com.edusalguero.rexoubapp.infrastructure.spring.error.AuthFailureHandler;
import com.edusalguero.rexoubapp.infrastructure.spring.security.JwtAuthenticationEntryPoint;
import com.edusalguero.rexoubapp.infrastructure.spring.security.JwtAuthenticationProvider;
import com.edusalguero.rexoubapp.infrastructure.spring.security.JwtAuthenticationSuccessHandler;
import com.edusalguero.rexoubapp.infrastructure.spring.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {

        return new ProviderManager(Arrays.asList(authenticationProvider));
    }



    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        NegatedRequestMatcher requiresAuthenticationRequestMatcher = new NegatedRequestMatcher(
                new OrRequestMatcher(
                        new AntPathRequestMatcher("/v1/auth/*"),
                        new AntPathRequestMatcher("/docs"),
                        new AntPathRequestMatcher("/swagger-ui.html"),
                        new AntPathRequestMatcher("/webjars/**/*"),
                        new AntPathRequestMatcher("/swagger-resources"),
                        new AntPathRequestMatcher("/swagger-resources/**/*")
                ));
        JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter(requiresAuthenticationRequestMatcher);
        authenticationTokenFilter.setAuthenticationManager(authenticationManager());
        authenticationTokenFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());

        System.out.println("Set authFailureHandlerauthFailureHandlerauthFailureHandlerauthFailureHandler");
        authenticationTokenFilter.setAuthenticationFailureHandler(authFailureHandler);
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and()
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                // Call our errorHandler if authentication/authorisation fails
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                // don't create session
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //.and()
                .and()

                // All urls must be authenticated (filter for token always fires (/**)
                    .authorizeRequests()
                    .antMatchers("/v1/auth/*").permitAll()
                    .antMatchers("/docs").permitAll()
                    .antMatchers("/swagger-ui.html").permitAll()
                    .antMatchers("/webjars/**/*").permitAll()
                    .antMatchers("/swagger-resources").permitAll()
                    .antMatchers("/swagger-resources/**/*").permitAll()
                    .antMatchers("/**/*").authenticated()
                .and()
                    .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();
    }

}