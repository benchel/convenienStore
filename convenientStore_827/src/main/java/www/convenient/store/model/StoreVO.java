package www.convenient.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreVO {
	private int storeId;
	private String storeBrand;
	
	public StoreVO(int storeId, String storeBrand) {
		this.storeId = storeId;
		this.storeBrand = storeBrand;
	}
}
