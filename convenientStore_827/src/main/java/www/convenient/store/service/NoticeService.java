package www.convenient.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import www.convenient.store.model.NoticeVO;
import www.convenient.store.paging.Criteria;

public interface NoticeService {
	
	//공지사항 작성
	public void insertNotice(@Param("notice") NoticeVO notice);
	
	//공지사항 상세조회
	public NoticeVO findById(String hierarchyId);
	
	// 공지사항 총 개수
	public int getNoticeTotal(Criteria cri);
	
	//공지사항 목록 조회
	public List<NoticeVO> getNoticeList();
	
	//공지사항 목록 조회(페이징처리 포함)
	public List<NoticeVO> getNoticeListWithPaging(Criteria cri);
	
	//공지사항 수정
	public int updateNotice(String hierarchyId);
	
	//공지사항 삭제
	public int deleteNotice(String hierarchyId);
	
}
