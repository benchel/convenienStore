package www.convenient.store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import www.convenient.store.model.StoreVO;
import www.convenient.store.persistence.StoreMapper;

@Service
@AllArgsConstructor
public class StoreServiceImp implements StoreService {
	
	private StoreMapper storeMapper;
	
	public List<StoreVO> getStoreList(){
		return storeMapper.getStoreList();
	}
}
