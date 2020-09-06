package www.convenient.store.party.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import www.convenient.store.party.model.PartyVO;
import www.convenient.store.party.model.RoleVO;
import www.convenient.store.party.model.RoleVO.RoleType;
import www.convenient.store.party.service.PartyService;

/**
 * AccessDeniedHandler : 잘못된 접근이 있을 경우 임의의 화면으로 가도록 지정하는 메서드를 제공
 * AuthenticationSuccessHandler : 로그인 성공 이후의 처리를 조정
 * AuthenticationFailureHandler : 로그인 실패 이후의 처리를 조정
 */
@Log4j
@Controller
public class PartyController implements AccessDeniedHandler, 
	AuthenticationSuccessHandler{
	
	@Setter(onMethod_ = @Autowired)
	private PartyService partyService;
	
	@Setter(onMethod_= @Autowired)
	private PasswordEncoder pwdEncoder;
	
	@GetMapping("/customLogin")
	public void openLoginPage(String error, Model model) {
		if (error != null) {
			model.addAttribute("error", "로그인 실패");
			log.info(model);
		}
	/*
		if (logout != null) {
			model.addAttribute("logout", "로그아웃");
			log.info(model);
		}
	*/
	}
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		model.addAttribute("msg", "접근이 거부되었습니다.");
		log.info(model);
	}
	
	/** 
	 * 로그인 실패 또는 권한에 맞지 않는 접근이 있을 경우에 지정된 화면으로 보낸다.
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.sendRedirect("/accessError");
	}

	/**
	 * 로그인 성공 이후에 사용자의 권한을 확인하여 권한에 맞는 화면으로 보낸다.
	 * 예) 관리자는 관리자만이 접근할 수 있는 페이지로 보내고(관리페이지), 
	 * 회원은 회원만이 접근할 수 있는 마이페이지로 보낸다.
	 */
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, HttpServletResponse response,
			Authentication aut) throws IOException, ServletException {
		// 권한을 담을 리스트 객체 생성
		List<String> roleNames = new ArrayList<>();
		
		// 로그인 생성 이후 생성된 Authentication을 익명 객체로 구현(람다식 적용)
		aut.getAuthorities().forEach(authentication ->{
			// 생성된 익명 객체에서 권한을 뽑아 위에서 생성한 리스트 객체에 담는다.
			roleNames.add(authentication.getAuthority());
		});
		
		// RoleVO의 필드로서 존재하는 열거형 RoleType의 값을 뽑아서 for으로 돌린다.
		for (RoleType role : RoleVO.RoleType.values()) {
			// roleNames에 열거형 RoleType이 존재한다면
			if (roleNames.contains(role.toString())) {
				// 사용자를 지정된 곳으로 보낸다.
				response.sendRedirect(role.getCustomePageUri());
				return;
			}
		}
		response.sendRedirect("/");	
	}
	
	@GetMapping("/customLogout")
	public void logoutGet() {
		log.info("logout");
	}
	
	//회원가입 페이지로 이동
	@GetMapping("/joinInSite")
	public void joinInSitePage() {
		log.info("join");
	}
	
	
	@PostMapping("/joinUser")
	public String userCreate(PartyVO party, RedirectAttributes rttr) {
		
		// 패스워드 해쉬함수로 암호화
		party.setUserPwd(pwdEncoder.encode(party.getUserPwd()));
		
		partyService.registerUser(party);
		
		rttr.addFlashAttribute("partyComplete", "가입이 정상적으로 완료되었습니다.");
		
		return "redirect:/index";
	}
	
	@GetMapping("/myPage")
	public void moveMyPage() {
		log.info("move to myPage");
	}
}
