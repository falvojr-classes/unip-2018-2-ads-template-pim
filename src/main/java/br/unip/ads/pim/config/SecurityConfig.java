package br.unip.ads.pim.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Autowired
	private AuthProvider authProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Configuracao do provider customizado de autenticacao
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement()
				// Atribui o conceito de Stateless (API REST livre de estado/sessao)
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authorizeRequests()
				// Libera os endpoints de Cadastro (Cliente e Funcionário)
				.antMatchers(HttpMethod.POST, "/api/clientes").permitAll()
				.antMatchers(HttpMethod.POST, "/api/funcionarios").permitAll()
				// Evitar 401 no método OPTIONS: https://stackoverflow.com/a/44115120/3072570
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.and()
			.authorizeRequests()
				// Restringe acesso atravez do conceito Basic Authentication na API REST
				.antMatchers("/api/**").authenticated()
				.and()
			// Ativar o Basic Authentication nos endpoints com autenticacão 
			.httpBasic()
				.and()
			// CORS (Cross-Origin Resource Sharing) e CSRF (Cross-Site Request Forgery)
			.cors().and().csrf().disable();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// Permitir CORS: https://stackoverflow.com/a/46372687/3072570
		registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedOrigins("*")
				.allowedHeaders("*");
	}
}
