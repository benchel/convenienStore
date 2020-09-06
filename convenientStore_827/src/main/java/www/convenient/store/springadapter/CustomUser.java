/**
 * Spring Security Framework에서 요구하는 방안에 따라 작성한다.
 * 이에 adapter package로 분리 정의함
 */
package www.convenient.store.springadapter;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import www.convenient.store.party.model.PartyVO;

@Data
public class CustomUser extends User{
	PartyVO party;

	public CustomUser(String username, String password, 
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	/**
	 *  www.study.com.party.service에서
	 *  loadUserByUsername(String username)의 결과로
	 *  반환되는 UserDetails에 partyVO를 담는다.
	 * @param party : LoginId로 찾아낸 user
	 */
	public CustomUser(PartyVO party) {
		super(party.getUserId(),
			party.getUserPwd(), 
			party.getListRole().stream().map(role->{
				return new SimpleGrantedAuthority(role.getName());
			}).collect(Collectors.toList()));
		this.party = party;
	}

}
