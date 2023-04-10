package com.becom.security;

import com.becom.entity.UserInfo;
import com.becom.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Resource
    UserServiceImpl userService;
    @Bean
    public PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String userName = authentication.getName();
                String password = authentication.getCredentials().toString();
                UserInfo user = (UserInfo) userService.loadUserByUsername(userName);
                // if(password.equals(user.getPassword())){
                if(passwordEncoder().matches(password,user.getPassword())){
                    System.out.println("access success");
                    return new UsernamePasswordAuthenticationToken(userName,password,user.getAuthorities());
                }else{
                  throw new BadCredentialsException("the username or password is waring");
                }
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return authentication.equals(UsernamePasswordAuthenticationToken.class);
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http) throws Exception{
        /**
         * 无状态的基于接口方式的验证
         *          http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         *          http.authorizeHttpRequests((authz)-> authz.anyRequest().authenticated()).httpBasic();
         *          http.build();
         */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        http.authorizeRequests().and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/check_login")
                .defaultSuccessUrl("/success.html")
                .permitAll()
                .usernameParameter("u")
                .passwordParameter("p")
                .and()
                .authorizeRequests().antMatchers("/checkMessage/index").permitAll()
                //1、当前用户只有admin权限才可以访问该资源 。2、在userDetails中设置admin权限
                .antMatchers("/getuserinfo").hasAuthority("admin")
                 .and()
                 .logout()
                //注销功能URL地址
                 .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                //注销成功后跳转地址
                .logoutSuccessUrl("/login.html")
                .and().csrf().disable();
        return  http.build();
    }
}
