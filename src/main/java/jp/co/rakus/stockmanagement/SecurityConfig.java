package jp.co.rakus.stockmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration	//設定用のクラスです！宣言
@EnableWebMvcSecurity	//Spring Securityのweb用の機能を追加します！宣言
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService memberDetailsService;
	
	//ログイン認証不要なものは無視するように設定
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**","/img/**","/js/**","fonts/**");		
	}
	
	//ログインアウトに関する設定
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/","/member/form","member/create").permitAll() //全てのユーザーにアクセスを許可する
			.anyRequest().authenticated(); //それ以外は認証が必要
			//.antMatchers("/admin/**").hasRole("ADMIN") // /admin/から始まるパスはADMIN権限でログインしている場合のみアクセス可(権限設定時の「ROLE_」を除いた文字列を指定)
			//.antMatchers("/member/**").hasRole("MEMBER") // /member/から始まるパスはMEMBER権限でログインしている場合のみアクセス可(権限設定時の「ROLE_」を除いた文字列を指定)
		
		http.formLogin()
			.loginPage("/")//ログイン画面に遷移させる。（ログイン認証に必要なパスを指定して、かつログインされていないと、このパスに遷移される。）
			.loginProcessingUrl("/login")//ログインボタンを押したときに遷移させるパス（ここに遷移させれば自動的にログインが行われる。）
			.failureUrl("/?error=true")//ログイン失敗に遷移させるパス
			.defaultSuccessUrl("/book/list",false)//第一引数：デフォルトでログイン成功時に遷移させるパス
												  //第二引数：　true　：認証後常に第一引数のパスに遷移
			.usernameParameter("mailAddress")//認証に使用するリクエストパラメーター
			.passwordParameter("password");//認証に使用するパスワードのパラメーター
		
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))//ログアウトをさせる際に遷移させるパス
			.logoutSuccessUrl("/")//ログアウト後に遷移させるパス（ここではログイン画面を設定）
			.deleteCookies("JSESSIONID")//ログアウト後、Cookiesに保存されているIDを削除
			.invalidateHttpSession(true); //true:ログアウト後、セッションを無効にする false:セッションを無効にしない
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(memberDetailsService) //認証ユーザを取得する「UserDetailsService」設定
			.passwordEncoder(new StandardPasswordEncoder()); //パスワード照合時に使うエンコーダーの設定。
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new StandardPasswordEncoder();
	}
}
