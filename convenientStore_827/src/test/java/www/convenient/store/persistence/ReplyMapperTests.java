package www.convenient.store.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import www.convenient.store.party.model.PartyVO;
import www.convenient.store.model.PostVO;
import www.convenient.store.model.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper replyMapper;

	// 포스트에 댓글 달기 테스트
	//@Test
	public void insertReplyTest() {
		ReplyVO reply = new ReplyVO();
		
		PostVO post = new PostVO();	
		post.setHierarchyId("6yJe");
		PartyVO party = new PartyVO();
		
		party.setUserId("user01");
		
		reply.setWriter(party.getUserId());
		reply.setContent("댓글내용2");
		
		replyMapper.insertReply(post, reply);
	}
	
	// 대댓글
	//@Test
	public void insertReReplyTest() {
		ReplyVO reply = new ReplyVO();
		
		PostVO parent = new PostVO();	
		parent.setHierarchyId("6yJe-33");
		PartyVO party = new PartyVO();
		
		party.setUserId("user01");
		
		reply.setWriter(party.getUserId());
		reply.setContent("대댓글");
		
		replyMapper.insertReReply(parent, reply);
	}
	
	@Test
	public void getReplyListTest() {
		ReplyVO parent = new ReplyVO();
		parent.setHierarchyId("6yJe");
		
		replyMapper.getReplyList(parent);
	}
}
