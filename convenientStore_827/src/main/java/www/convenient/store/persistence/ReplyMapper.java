package www.convenient.store.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import www.convenient.store.model.PostVO;
import www.convenient.store.model.ReplyVO;

public interface ReplyMapper {
	
	// 일반적인 댓글
	public void insertReply(@Param("post") PostVO post, @Param("reply") ReplyVO reply);
	
	//대댓글 작성
	public int insertReReply(@Param("parent") ReplyVO parent, @Param("reply") ReplyVO reply);
	
	// 특정한 게시글에 달린 모든 댓글 조회
	public List<ReplyVO> getReplyList(@Param("parent") ReplyVO reply);
	
}
