package jp.co.rakus.stockmanagement.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.rakus.stockmanagement.domain.LoginMember;
import jp.co.rakus.stockmanagement.domain.Member;
import jp.co.rakus.stockmanagement.repository.MemberRepository;

/**
 * ログイン後のユーザーに権限を付与するサービスクラス.
 * 
 * @author rui.toguchi
 *
 */
@Service
public class MemberDetailsServiceImple implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByMailAddress(email);
		if (member == null) {
			throw new UsernameNotFoundException("そのEmailは登録されていません");
		}

		// 権限付与
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
		// if(member.isAdmin()){
		// authorityList.add(new SimpleGrantedAuhotiry("Role_ADMIN"));
		// }
		return new LoginMember(member, authorityList);
	}

}
