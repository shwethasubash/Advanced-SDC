package dal.asd.catme.config;

import dal.asd.catme.accesscontrol.CatmeException;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static dal.asd.catme.util.DBQueriesUtil.SELECT_ROLE_SECURITY_QUERY;
import static dal.asd.catme.util.DBQueriesUtil.SELECT_USER_SECURITY_QUERY;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@EnableWebSecurity
public class CatmeSecurityConfig extends WebSecurityConfigurerAdapter
{
    private static final Logger log = LoggerFactory.getLogger(CatmeSecurityConfig.class);
    DatabaseAccess dataSource;

    @Override
    protected void configure(HttpSecurity http) throws CatmeException
    {
        log.info("Configuring authentication and authorization");
        try
        {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasAnyAuthority(CatmeUtil.ADMIN_ROLE)
                    .antMatchers("/profile/instructor/**").hasAnyAuthority(CatmeUtil.ROLE_INSTRUCTOR)
                    .antMatchers("/questions").hasAnyAuthority(CatmeUtil.ROLE_INSTRUCTOR)
                    .antMatchers("/profile/ta/**").hasAnyAuthority(CatmeUtil.TA_ROLE)
                    .antMatchers("**/survey/**").hasAnyAuthority(CatmeUtil.TA_ROLE, CatmeUtil.ROLE_INSTRUCTOR)
                    .antMatchers("/courseDisplay/**").hasAnyAuthority(CatmeUtil.TA_ROLE, CatmeUtil.ROLE_INSTRUCTOR, CatmeUtil.STUDENT_ROLE)
                    .antMatchers("/login", "/register", "/forgotPassword", "/reset-password/**", "/signup").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")//this URL should match with action URL in login.html
                    .loginProcessingUrl("/catme")//URL to submit User name and password
                    .successForwardUrl("/access")//redirecting to controller to decide the landing page
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .permitAll()
                    .and()
                    .csrf().disable();
        } catch (Exception e)
        {
            throw new CatmeException("URL Security configuration failed!! " + e.getMessage());
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws CatmeException
    {
        dataSource = new DatabaseAccess();
        try
        {
            auth.jdbcAuthentication().dataSource(dataSource)
                    .usersByUsernameQuery(SELECT_USER_SECURITY_QUERY)
                    .authoritiesByUsernameQuery(SELECT_ROLE_SECURITY_QUERY);
        } catch (Exception e)
        {
            log.error("Authentication failed to fetch username and password!!");
            throw new CatmeException("Authentication failed to fetch username and password!! " + e.getMessage());
        }
    }

    public List<String> fetchRolesHomePage() throws CatmeException
    {
        List<String> currentUserRoles = new ArrayList<>();

        try
        {
            Collection<GrantedAuthority> currentRoles = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            for (GrantedAuthority s : currentRoles)
            {
                currentUserRoles.add(s.getAuthority());
            }
        } catch (Exception e)
        {
            log.error("Authentication failed to roles of User!!");
            throw new CatmeException("Failed to fetch User roles from database!! " + e.getMessage());
        }

        return currentUserRoles;
    }
}