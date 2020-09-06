package www.convenient.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import www.convenient.store.model.PostVO;
import www.convenient.store.model.ReplyVO;

public interface ReplyService {
	// 일반적인 댓글
	public void insertReply(PostVO post, ReplyVO reply);
	
	// 대댓글 작성
	public int insertReReply(ReplyVO parent, ReplyVO reply);
	
	// 특정한 게시글에 달린 모든 댓글 조회
	public List<ReplyVO> getReplyList(ReplyVO reply);
}
