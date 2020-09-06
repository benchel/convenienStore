package www.convenient.store.party.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import www.convenient.store.party.model.PartyVO;
import www.convenient.store.party.persistence.PartyMapper;
import www.convenient.store.springadapter.CustomUser;

/** org.springframework.security.core.userdetails
 * Interface UserDetailsService [의역 주의]
 * 
 * Core interface which loads user-specific data.
 * 유저의 특정한 데이터를 로드하는 코어 인터페이스다.
 * 
 * It is used throughout the framework as a user DAO and is the strategy used by
 * the DaoAuthenticationProvider.
 * DaoAuthenticationProvider에 의해서 사용되는 전략인 유저 DAO와 같은 프레임워크를 통하여 사용된다.
 * 
 * The interface requires only one read-only method, 
 * which simplifies support for new data-access strategies.
 * 이 인터페이스는 새로운 데이터 접근 전략에 대한 지원을 간소화하며,
 * 오직 하나의 읽기 전용 메소드를 요청한다.
 * 
 */
public class PartyServiceImpl implements PartyService, UserDetailsService {
	
	@Setter(onMethod_ = @Autowired)
	private PartyMapper partyMapper;
	
	/**
	 * loadUserByUsername(String username);
	 * 
	 * Locates the user based on the username.
	 * 유저이름을 바탕으로 유저를 찾아낸다.
	 * In the actual implementation,
	 * 실제 구현에서,
	 * the search may possibly be case sensitive,
	 * or case insensitive depending on 
	 * how the implementation instance is configured.
	 * 유저를 찾을 때 대소문자를 구분한다. 인스턴스의 구성에 따라  대소문자를 구분하지 않는다.
	 * 
	 * In this case, the UserDetails object 
	 * that comes back may have a username 
	 * that is of a different case than what was actually requested..
	 * 이 경우 되돌아오는 UserDetails 오브젝트는 실제 요청된 것과
	 * 다른 케이스의 사용자 이름을 가질 수 있다.
	 * 
	 * [결론]
	 * 지정한 유저이름으로 데이터베이스에 저장되어 있는 회원을 찾아주고 
	 * UserDetails 오브젝트로 반환하는 메서드
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// username은 customLogin에서 로그인할 때 입력한다. 
		PartyVO user = partyMapper.findByUserId(username);
		
		// 성공적으로 해당 유저를 찾으면 그 유저를 UserDetails 오브젝트로 반환한다.
		// User <-(상속)- CustomUser <-(상속)- UserDetails		
		return user == null ? null : new CustomUser(user);
	}

	@Override
	public void registerUser(PartyVO party) {
		partyMapper.registerUser(party);
	}

}
