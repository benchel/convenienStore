package www.convenient.store.party.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PartyVO {
	private String userId;
	private String userPwd;
	private String userRole;
	
	private PartyInfoVO partyInfo; // 회원정보
	
	private List<RoleVO> listRole; // 권한 
	
	public PartyVO(String userId, String userPwd, String userRole) {
		this.userId=userId;
		this.userPwd=userPwd;
		this.userRole=userRole;
	}
}
