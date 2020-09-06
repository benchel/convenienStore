package www.convenient.store.party.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import www.convenient.store.party.model.PartyInfoVO;
import www.convenient.store.party.model.PartyVO;
import www.convenient.store.party.model.RoleVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class PartyMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private PartyMapper partyMapper;
	
	@Setter(onMethod_= @Autowired)
	private PasswordEncoder pwdEncoder;

	@Test
	public void findByUserIdTest() {
		try {
			PartyVO user=partyMapper.findByUserId("Admin1");
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void registerUserTest() {
		PartyVO user = new PartyVO();
		PartyInfoVO userInfo = new PartyInfoVO();
		
		user.setUserId("benchel");
		user.setUserPwd(pwdEncoder.encode("1234"));
		user.setUserRole("ROLE_USER");
		
		userInfo.setUserId("benchel");
		userInfo.setYearOfBirth(951030);
		userInfo.setEmail("bencheljjang@abc.com");
		
		user.setPartyInfo(userInfo);
		
		partyMapper.registerUser(user);
	}

}
