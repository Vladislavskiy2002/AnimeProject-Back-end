//package com.vladislavskiy.Anime.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//import static org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION;
//
//@Configuration
//public class BasicAuthConfiguration {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
//        http.authorizeHttpRequests(
//                auth->{
//                    auth.anyRequest().authenticated();
//                });
//        http.sessionManagement(
//                session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        );
//        http.httpBasic();
//        http.headers().frameOptions().sameOrigin();
//        http.csrf().disable();
//        return http.build();
//    }
////    @Bean
////    public DataSource dataSource(){
////        return new EmbeddedDatabaseBuilder()
////                .setType(EmbeddedDatabaseType.H2)
////                .addScript(DEFAULT_USER_SCHEMA_DDL_LOCATION)
////                .build();
////    }
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        var user =
//                User.withUsername("vladi")
//                .password("12345")
//                        .passwordEncoder(str-> passwordEncoder().encode(str))
//                .roles("USER")
//                .build();
//        var admin =
//                User.withUsername("Admin")
//                .password("123456")
//                        .passwordEncoder(str-> passwordEncoder().encode(str))
//                        .roles("Admin","USER")
//                .build();
//        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(admin);
//        return jdbcUserDetailsManager;
//    }
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}