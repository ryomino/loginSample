package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        // 認証・認可に関する設定を追加します
		 http.authorizeRequests()
	        .antMatchers("/loginForm").permitAll()
	        .antMatchers("/create").permitAll()// /loginFormは、全ユーザからのアクセスを許可
	        .anyRequest().authenticated();

		 http.formLogin()
	        .loginProcessingUrl("/login") // ログイン処理のパス
	        .loginPage("/loginForm") // ログインページの指定
	        .usernameParameter("email") // ログインページのメールアドレス
	        .passwordParameter("password") // ログインページのパスワード
	        .defaultSuccessUrl("/list", true) // ログイン成功後のパス
	        .failureUrl("/loginForm?error"); // ログイン失敗時のパス

		 http.logout()
	        .logoutUrl("/logout") //ログアウト処理のパス
	        .logoutSuccessUrl("/loginForm"); //ログアウト成功後のパス


    }
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
