package www.convenient.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // �⺻ �����ڸ� ����
public class BoardVO {
	private int boardId; 
	private String boardName;
	// 1:��������, 2:����Խ���
}
