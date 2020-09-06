package www.convenient.store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import www.convenient.store.model.PostVO;
import www.convenient.store.model.ReplyVO;
import www.convenient.store.persistence.ReplyMapper;

@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	
	private ReplyMapper replyMapper;

	@Override
	public void insertReply(PostVO post, ReplyVO reply) {
		// TODO Auto-generated method stub	
	}

	@Override
	public int insertReReply(ReplyVO parent, ReplyVO reply) {
		return replyMapper.insertReReply(parent, reply);		
	}

	@Override
	public List<ReplyVO> getReplyList(ReplyVO reply) {
		return replyMapper.getReplyList(reply);
	}

}
