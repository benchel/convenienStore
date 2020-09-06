package www.convenient.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 기본 생성자를 생성
public class BoardVO {
	private int boardId; 
	private String boardName;
	// 1:공지사항, 2:리뷰게시판
}
