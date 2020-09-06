package www.convenient.store.party.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PartyInfoVO {
	private String userId;
	private int yearOfBirth;
	private String email;
	private char status; // DEFAULT 'O', -- O(활동), X(정지) 
    private String joindate; // 가입일
    private String blockdate; // 차단일 
}
