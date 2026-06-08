package br.com.bookly.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        //Users
                        .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/users/").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/users/me").hasAnyRole("CLIENT", "ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/users/*").hasAnyRole("CLIENT", "ADMINISTRATOR")
                        .requestMatchers(HttpMethod.POST, "/api/users/*/favorites/*").hasAnyRole("CLIENT", "ADMINISTRATOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/*/favorites/*").hasAnyRole("CLIENT", "ADMINISTRATOR")

                        //Author
                        .requestMatchers(HttpMethod.POST, "/api/authors").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/authors/**").hasRole("ADMINISTRATOR") //ID
                        .requestMatchers(HttpMethod.GET, "/api/authors/name").hasRole("CLIENT") //Nome
                        .requestMatchers(HttpMethod.GET, "/api/authors").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.PUT, "/api/authors/*").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/authors/*").hasRole("ADMINISTRATOR")

                        //ClubMessage
                        .requestMatchers(HttpMethod.GET, "/api/clubmessage").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/clubmessage/*").hasRole("CLIENT") //Id
                        .requestMatchers(HttpMethod.GET, "/api/clubmessage/user/*").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/clubmessage/club/*").hasAnyRole("CLIENT", "ADMINISTRATOR")
                        .requestMatchers(HttpMethod.POST, "/api/clubmessage").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/clubmessage/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/clubmessage/*").hasRole("CLIENT")

                        //BookClub
                        .requestMatchers(HttpMethod.GET, "/api/bookclub").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/bookclub/*").hasAnyRole("CLIENT", "ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/bookclub/name/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.POST, "/api/bookclub").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/bookclub/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/bookclub/*").hasRole("CLIENT")

                        //ParticipantUser
                        .requestMatchers(HttpMethod.GET, "/api/participantuser/byclub/*").hasAnyRole("CLIENT", "ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/participantuser/byuser/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/participantuser/*").hasRole("CLIENT") //Id
                        .requestMatchers(HttpMethod.GET, "/api/participantuser").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.POST, "/api/participantuser").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/participantuser/*").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/participantuser/*").hasAnyRole("CLIENT", "ADMINISTRATOR")

                        //Colection
                        .requestMatchers(HttpMethod.POST, "/api/colection").hasAnyRole("ADMINISTRATOR", "CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/colection/**").hasAnyRole("ADMINISTRATOR", "CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/colection/name").hasAnyRole("ADMINISTRATOR", "CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/colection/user/*").hasAnyRole("ADMINISTRATOR", "CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/colection").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/colection/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/colection/*").hasRole("CLIENT")

                        //BookClubAssignment
                        .requestMatchers(HttpMethod.GET, "/api/bookclubassignment").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/bookclubassignment/book/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/bookclubassignment/club/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/bookclubassignment/*").hasRole("ADMINISTRATOR")//Id
                        .requestMatchers(HttpMethod.POST, "/api/bookclubassignment").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/bookclubassignment/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/bookclubassignment/*").hasRole("CLIENT")

                        //Purchase
                        .requestMatchers(HttpMethod.POST, "/api/purchase").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/purchase/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/purchase/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/purchase/id/*").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/purchase/date/*").hasAnyRole("CLIENT", "ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/purchase/PageIdUser/*").hasAnyRole("CLIENT", "ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/purchase").hasRole("ADMINISTRATOR")


                        //PurhcaseBook
                        .requestMatchers(HttpMethod.GET, "/api/purchaseBook/*").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/purchaseBook/purchase/*").hasRole("CLIENT") //so uma
                        .requestMatchers(HttpMethod.GET, "/api/purchaseBook/book/*").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/purchaseBook/bypurchase/*").hasRole("ADMINISTRATOR") //lista de compras

                        //ReadingStatus
                        .requestMatchers(HttpMethod.GET, "/api/readingstatus/byBookAndUser").hasAnyRole("CLIENT", "ADMINISTRATOR")
                        .requestMatchers(HttpMethod.POST, "/api/readingstatus").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/readingstatus/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/readingstatus/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/readingstatus/id/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/readingstatus/idUser/*").hasRole("CLIENT")

                        //Rating
                        .requestMatchers(HttpMethod.POST, "/api/ratings").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/ratings/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/ratings/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/ratings").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/ratings/all/*").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/ratings/average/*").hasRole("CLIENT")

                        //Book
                        .requestMatchers(HttpMethod.GET, "/api/books").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/books/**").hasRole("CLIENT") //title e genre
                        .requestMatchers(HttpMethod.GET, "/api/books/availables").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/api/books/unavailables").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.POST, "/api/books").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.PUT, "/api/books/**").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/books/*").hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/books/*").hasAnyRole("CLIENT", "ADMINISTRATOR")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
                "http://localhost:5500",
                "http://127.0.0.1:5500",
                "http://localhost:63342",
                "https://projeto-web-bice.vercel.app"
        ));
        configuration.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE"
        ));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
     source.registerCorsConfiguration("/**", configuration);
     return source;
    }
}
