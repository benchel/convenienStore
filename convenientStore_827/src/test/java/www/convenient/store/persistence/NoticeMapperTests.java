package www.convenient.store.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import www.convenient.store.model.NoticeVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class NoticeMapperTests {

	@Setter(onMethod_ = @Autowired)
	private NoticeMapper noticeMapper;

	@Test
	public void testFindById() {
		noticeMapper.findById("2");
		log.info(noticeMapper);
	}

	//@Test
	public void testInsertNotice() {
		NoticeVO notice = new NoticeVO();

		notice.setObjType('N');
		notice.setContent("공지사항입니다.");
		notice.setWriter("admin01");
		notice.setBoardId(1);
		notice.setTitle("공지사항 제목.");

		noticeMapper.insertNotice(notice);
		log.info(noticeMapper);
	}

	@Test
	public void testGetNoticeList() {
		noticeMapper.getNoticeList();
		log.info(noticeMapper);
	}

	@Test
	public void testUpdateNotice() {
		NoticeVO notice = new NoticeVO();
		notice = noticeMapper.findById("2");
		notice.setTitle("공지사항_수정4");
		notice.setContent("공지사항_수정결과 내역4");
		noticeMapper.updateNotice(notice.getHierarchyId());
		log.info(noticeMapper);
	}
	
	//@Test
	public void testdeleteNotice() {
		NoticeVO notice = new NoticeVO();
		notice = noticeMapper.findById("8");
		noticeMapper.deleteNotice(notice.getHierarchyId());
		log.info(noticeMapper);
	}

}
