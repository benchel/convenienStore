package www.convenient.store.searchkey.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //디폴트 생성자
public class SearchkeyVO {
	private int keywordId;
	private String postId;
	private String keywordName;
	
	public SearchkeyVO(String keywordName, String postId) {
		super();
		this.keywordName = keywordName;
		this.postId=postId;
	}
	
	/**
	 * equals()를 재정의한 이유
	 * 
	 * 기존에 있는 키워드를 List로 전부 가져온다.
	 * 새로 생성된 키워드와 기존에 있는 키워드를 비교하여 중복되는 것을 삭제한다.
	 * 이때 비교 과정에서 equals()를 사용한다.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchkeyVO other = (SearchkeyVO) obj;
		if (postId == null) {
			if (other.postId != null)
				return false;
		} else if (!postId.equals(other.postId))
			return false;	
		if (keywordName == null) {
			if (other.keywordName != null)
				return false;
		} else if (!keywordName.equals(other.keywordName))
			return false;
		return true;
	}
}
