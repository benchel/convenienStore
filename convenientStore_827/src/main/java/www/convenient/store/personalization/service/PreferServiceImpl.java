package www.convenient.store.personalization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import www.convenient.store.personalization.model.PreferVO;
import www.convenient.store.personalization.persistence.PreferMapper;


@Service
public class PreferServiceImpl implements PreferService{
	
	@Setter(onMethod_ = @Autowired)
	private PreferMapper preferMapper;

	@Override
	public int registerPrefer(PreferVO prefer) {
		return preferMapper.registerPrefer(prefer);
	}

	@Override
	public List<PreferVO> getPreferList(String userId) {
		// TODO Auto-generated method stub
		return preferMapper.getPreferList(userId);
	}

	@Override
	public List<PreferVO> recommendPreferlist(PreferVO prefer) {
		// TODO Auto-generated method stub
		return preferMapper.recommendPreferlist(prefer);
	}


}
