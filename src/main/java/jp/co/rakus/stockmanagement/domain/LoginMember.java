package jp.co.rakus.stockmanagement.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 利用者のログイン情報を格納するエンティティ.
 * 
 * @author rui.toguchi
 *
 */
public class LoginMember extends User{
	private Member member;
	
	/**
	 * 通常のメンバー情報に加え、許可用ロールを設定する.
	 * @param member
	 * @param authorityList
	 */
	public LoginMember(Member member,Collection<GrantedAuthority> authorityList){
		super(member.getMailAddress(),member.getPassword(),authorityList);
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
}
