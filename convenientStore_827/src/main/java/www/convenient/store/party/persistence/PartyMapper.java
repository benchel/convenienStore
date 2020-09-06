package www.convenient.store.party.persistence;

import org.apache.ibatis.annotations.Param;

import www.convenient.store.party.model.PartyVO;

public interface PartyMapper {

	public PartyVO findByUserId(String loginId);
	
	public void registerUser(@Param("party") PartyVO party);
}
