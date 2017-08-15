package jp.co.rakus.stockmanagement.web;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * メンバー関連のリクエストパラメータが入るフォーム.
 * 
 * @author igamasayuki
 *
 */
public class MemberForm {
	/** 名前 */
	@NotBlank(message = "名前を入力してください。")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスを入力してください。")
	@Email(message = "Eメールの形式が不正です。")
	private String mailAddress;
	/** パスワード */
	@Size(min = 4, max = 16, message = "パスワードは4~16文字で入力してください。")
	private String password;

	/** 確認用パスワード */
	private String confirmPassword;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
