package www.convenient.store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import www.convenient.store.model.NoticeVO;
import www.convenient.store.paging.Criteria;
import www.convenient.store.persistence.NoticeMapper;

@Log4j
@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService{
	
	private NoticeMapper noticeMapper;
	
	@Override
	public void insertNotice(NoticeVO notice) {
		noticeMapper.insertNotice(notice);
	}
	
	@Override
	public NoticeVO findById(String hierarchyId) {
		return noticeMapper.findById(hierarchyId);
	}
	
	@Override
	public int getNoticeTotal(Criteria cri) {
		return noticeMapper.getNoticeTotal(cri);
	}

	@Override
	public List<NoticeVO> getNoticeList() {
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeList();
	}
	
	@Override
	public List<NoticeVO> getNoticeListWithPaging(Criteria cri) {
		return noticeMapper.getNoticeListWithPaging(cri);
	}	

	@Override
	public int updateNotice(String hierarchyId) {
		noticeMapper.updateNotice(hierarchyId);
		return 1;
	}

	@Override
	public int deleteNotice(String hierarchyId) {
		noticeMapper.deleteNotice(hierarchyId);
		return 1;
	}
	
}
