package www.convenient.store.persistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import www.convenient.store.model.PostVO;
import www.convenient.store.model.ReviewTargetVO;
import www.convenient.store.paging.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class PostMapperTests {

	@Setter(onMethod_ = @Autowired)
	private PostMapper postMapper;
	
	//@Test
	public void testFindByPostId() {
		postMapper.findByPostId("2");
		log.info(postMapper);
	}
	

	//@Test
	public void testFindByPostReviewTargetId() {
		postMapper.findByReviewTargetId("2");
		log.info(postMapper);
	}


	@Test
	public void testInsertPost() {
		PostVO post = new PostVO();
		ReviewTargetVO target = new ReviewTargetVO();
		post.setTitle("GS25 나혼자 족발");
		post.setContent("양념 족발에서 샴푸맛 난다. 사먹지마라");
		post.setWriter("user05");

		target.setStoreId(1); //GS25
		target.setPrdAssort("간편식");
		target.setPrdName("나혼자족발");
		target.setPrdPrice(5900);
		target.setPrdRate(1);

		postMapper.insertPost(post, target);
		log.info(postMapper);
	}


//	@Test
//	public void testGetPostList() {
//		postMapper.getPostList();
//		log.info(postMapper);
//	}
	
	@Test
	public void testGetPostListWithPagin() {
		
		Criteria cri = new Criteria();
		
		List<PostVO> list =postMapper.getPostListWithPaging(cri);
		list.forEach(post -> log.info(post));
	}
	

	//@Test
	public void testUpdatePost() {
		PostVO post = new PostVO();
		
		post.setHierarchyId("1");
		post.setTitle("수정된 제목");
		post.setContent("수정된 내용");		
		postMapper.updatePost(post);
		log.info(postMapper);
	}

	//@Test
	public void testupdateReviewTarget() {
		PostVO post = new PostVO();
		ReviewTargetVO target = new ReviewTargetVO();
		post.setHierarchyId("1");
		target.setPrdName("수정된 상품이름");
		target.setPrdPrice(3500);
		target.setPrdRate(3);
		postMapper.updateReviewTarget(post, target);
		log.info(postMapper);
	}
	
	//@Test
	public void testDeletePost() {
		PostVO post = new PostVO();
		post=postMapper.findByPostId("4");
		postMapper.deletePost(post.getHierarchyId());
		log.info(postMapper);		
	}
}
