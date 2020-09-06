package www.convenient.store.party.model;

import lombok.Data;

@Data
public class RoleVO {
	public enum RoleType {
		//정의 순서는 최상위 권한에서 하위로 내려가야합니다.
		ROLE_ADMIN("/index"), ROLE_USER("/index");
		
		private String customePageUri;
		
		private RoleType(String uri) {
			customePageUri = uri;
		}

		public String getCustomePageUri() {
			return customePageUri;
		}
	};
	
	private String name;
}
