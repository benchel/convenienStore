package www.convenient.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import www.convenient.store.model.ReplyVO;
import www.mydream.com.KeyWordTarget;

//댓글이 갖는 필드들은 게시글에서도 상속 관계에 의하여 활용됨
@Data
@NoArgsConstructor // 기본 생성자를 생성
public class ReplyVO {
	// 게시글 : P, 댓글 : R, 공지사항 : N
	private static final char CLS_TYPE = 'R';
	private static final char HID_DELI = '-';
	
	private String hierarchyId; // 게시물 및 댓글 id
	@KeyWordTarget
    private String content; // 내용
    private String regdate;
    private String updateDate;
    
	private ReplyVO parent;
	private String writer;
	private int depth;
    
	public ReplyVO(String hierarchyId) {
		this.hierarchyId = hierarchyId;
	}
	
	public ReplyVO(String content, ReplyVO parent, String writer) {
		this.content = content;
		this.parent = parent;
		this.writer = writer;
	}
	
	public String getKeyWordSource() {
		return content;
	}
	
	public static char getClsType() {
		return CLS_TYPE;
	}
	
	public char getHidDeli() {
		return HID_DELI;
	}
}