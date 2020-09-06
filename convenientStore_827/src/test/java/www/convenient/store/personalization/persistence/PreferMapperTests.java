package www.convenient.store.personalization.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import www.convenient.store.model.ReviewTargetVO;
import www.convenient.store.model.StoreVO;
import www.convenient.store.personalization.model.PreferVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class PreferMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private PreferMapper preferMapper;

	//@Test
	public void getPreferListTest() {	
		
		preferMapper.getPreferList("benchel");
		log.info(preferMapper);
	}
	
	//@Test
	public void registerPreferProductTest() {
		PreferVO prefer = new PreferVO();
		prefer.setUserId("benchel");

		prefer.setPreferProdc(new ReviewTargetVO("음료", "썬업", 1300));
		
		prefer.setStore(new StoreVO(1, "GS25"));
		
		preferMapper.registerPrefer(prefer);
		log.info(preferMapper);
	}

	@Test
	public void recommendPreferlistTest() {
		PreferVO prefer = new PreferVO();
		prefer.setUserId("benchel");
		prefer.setStore(new StoreVO(1, "GS25"));
		prefer.setPreferProdc(new ReviewTargetVO("음료", "썬업", 1300));
		
		preferMapper.recommendPreferlist(prefer);
		log.info(preferMapper);
	}
}
