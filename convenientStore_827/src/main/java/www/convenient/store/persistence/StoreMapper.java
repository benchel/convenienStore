package www.convenient.store.persistence;

import java.util.List;

import www.convenient.store.model.StoreVO;

public interface StoreMapper {
	
	//목록조회
	public List<StoreVO> getStoreList();

}
