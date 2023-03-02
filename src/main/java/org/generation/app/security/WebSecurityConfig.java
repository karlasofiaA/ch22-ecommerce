package org.generation.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//Paso 1. Personalizar la seguridad, creando la clase
@SuppressWarnings("deprecation")
@Configuration //Para detectar el Bean
@EnableGlobalMethodSecurity(prePostEnabled = true) //Paso 8. Habilitar los roles en los endpoints
public class WebSecurityConfig {

	//Paso 2. Creación de Bean
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//Construimos el interceptor de solicitud
		http
			.httpBasic()
			.and()
			.authorizeHttpRequests()
			.anyRequest() // .permitAll(); //Paso 3. Deshabilita la seguridad
			.authenticated() //Paso 4. Habilitamos la seguridad
			.and()
			.csrf().disable();
		
		return http.build();
	}
	
	//Paso 5. Crear usuarios y contraseñas en memoria para la autorización de la petición
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails juan = User
						.builder()
						.username("juan")
						//.password("{noop}malote") //TODO encriptar contraseña *esta línea no está encriptada*
						.password(passwordEncoder().encode("malote")) //Paso 7. Codificar la contraseña
						.roles("ADMIN")
						.build();
		
		UserDetails luis = User
				.builder()
				.username("luis")
				//.password("{noop}fierro") //TODO encriptar contraseña
				.password(passwordEncoder().encode("fierro")) //Paso 7. Codificar la contraseña
				.roles("USER")
				.build();
		
		 return new InMemoryUserDetailsManager(juan, luis);
	}
	
	//Paso 6. Crear el bean codificar contraseña
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
