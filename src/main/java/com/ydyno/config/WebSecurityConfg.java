package com.ydyno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 开启Spring Security
 * create by xin.huang on 2022/12/11
 */
@Configuration
@EnableWebSecurity
//prePostEnabled属性决定Spring Security在接口前注解是否可用@PreAuthorize,@PostAuthorize等注解,设置为true,会拦截加了这些注解的接口
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfg extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/**
		 * 基于内存的方式，创建两个用户admin/123456，user/123456
		 * */
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password(passwordEncoder().encode("admin"))
				.roles("ADMIN");
		auth.inMemoryAuthentication()
				.withUser("root")
				.password(passwordEncoder().encode("pass1009"))
				.roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin();
		http.csrf().disable();
	}

	/**
	 * 指定加密方式
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		// 使用BCrypt加密密码
		return new BCryptPasswordEncoder();
	}
}