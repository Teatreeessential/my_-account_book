package net.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.myapp.domain.MemberVO;
import net.myapp.domain.TransactionParameterVO;
import net.myapp.service.ManagerService;

@Controller
@RequestMapping("/user/*")
@Log4j
public class AccessController {
	@Setter(onMethod_ = @Autowired)
	private DataHandler datahandler;
	@Setter(onMethod_ = @Autowired)
	private CacheManager cacheManager;
	private Cache cache;
	@Setter(onMethod_ = @Autowired)
	private ManagerService service;
	@Setter(onMethod_ = @Autowired)
	private ApplicationContext applicationContext;
	@Setter(onMethod_ = @Autowired)
	private TaskExecutor taskExecutor;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(HttpServletRequest request, @ModelAttribute TransactionParameterVO vo) { // 로그인화면이 뜨고

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String id, String passwd, HttpSession session, RedirectAttributes rttr) {
			
			MemberVO member = service.getMember(id, passwd);

			if (member == null) {
				rttr.addFlashAttribute("error", "비밀번호 혹은 아이디가 틀렸습니다.");
				return "redirect:/user/login";
			} else {
				
				cache = cacheManager.getCache("sampleCache");
				session.setAttribute("fintech_use_nums", member.getFintech_use_nums());
				// 캐싱된 작업이 이미 존재하면 중복 수행 되지 않게끔 if문
				for (int i = 0; i < member.getFintech_use_nums().size(); i++) {
					TransactionList transaction_job = new TransactionList();
					BalanceList balance_job = new BalanceList();
					transaction_job.setAccess_token(member.getAccess_token());
					transaction_job.setFintech_use_num(member.getFintech_use_nums().get(i));
					balance_job.setAccess_token(member.getAccess_token());
					balance_job.setFintech_use_num(member.getFintech_use_nums().get(i));
					// 쓰레드를 통해서 api요청 -> 데이터 핸들링 -> 캐싱 작업
					applicationContext.getAutowireCapableBeanFactory().autowireBean(transaction_job);
					applicationContext.getAutowireCapableBeanFactory().autowireBean(balance_job);

					taskExecutor.execute(transaction_job);
					taskExecutor.execute(balance_job);
				}
				
				try {
					if(cache.get(member.getFintech_use_nums().get(0)+"income_map")!=null) {
						System.out.println("바로된거??");
						
						return "/user/balance";
					}
					
				}catch(Exception e){
					System.out.println("캐싱된 데이터 ㅇㄷ?");
					
					for (int i = 0; i < member.getFintech_use_nums().size(); i++) {
						TransactionList transaction_job = new TransactionList();
						BalanceList balance_job = new BalanceList();
						transaction_job.setAccess_token(member.getAccess_token());
						transaction_job.setFintech_use_num(member.getFintech_use_nums().get(i));
						balance_job.setAccess_token(member.getAccess_token());
						balance_job.setFintech_use_num(member.getFintech_use_nums().get(i));
						// 쓰레드를 통해서 api요청 -> 데이터 핸들링 -> 캐싱 작업
						applicationContext.getAutowireCapableBeanFactory().autowireBean(transaction_job);
						applicationContext.getAutowireCapableBeanFactory().autowireBean(balance_job);

						taskExecutor.execute(transaction_job);
						taskExecutor.execute(balance_job);
					}
					return "/user/balance";
				}
				
			}
		 
			
			return "/user/balance";
		
	}

	@RequestMapping(value = "/transaction/{fintech_use_num}", method = RequestMethod.GET)
	public String transaction(@PathVariable("fintech_use_num") String fintech_use_num, HttpSession session,
			RedirectAttributes rttr, Model model) {

		cache = cacheManager.getCache("sampleCache");
		List<String> list = (List<String>) session.getAttribute("fintech_use_nums");

		System.out.println(list);
		System.out.println(list.contains(fintech_use_num));
		try {

			if (list.contains(fintech_use_num)) {
				Gson gson = new Gson(); 
				int total_income = 0;
				int total_consume = 0;
				Map<String, Integer> top_three_income_map = null;
				Map<String, Integer> top_three_consume_map = null;

				Map<String, String> branch_map = (HashMap<String, String>) cache.get(fintech_use_num + "branch_map")
						.get();
				Map<String, Integer> income_map = (HashMap<String, Integer>) cache.get(fintech_use_num + "income_map")
						.get();
				Map<String, Integer> consume_map = (HashMap<String, Integer>) cache.get(fintech_use_num + "consume_map")
						.get();

				System.out.println(consume_map);
				System.out.println(income_map);

				branch_map = datahandler.sortByValue(branch_map);
				total_income = datahandler.sum(income_map);
				total_consume = datahandler.sum(consume_map);
				top_three_income_map = datahandler.sortByValue(income_map);
				top_three_consume_map = datahandler.sortByValue(consume_map);

				model.addAttribute("branch_map", branch_map);
				model.addAttribute("total_income",total_income);
				model.addAttribute("total_consume",total_consume);
				model.addAttribute("top_three_income_map",top_three_income_map);
				model.addAttribute("top_three_consume_map",top_three_consume_map);
				model.addAttribute("income_map", gson.toJson(income_map));
				model.addAttribute("consume_map", gson.toJson(consume_map));
				
				
			} else {
				rttr.addFlashAttribute("error", "올바른 접근이 아닙니다.");
				return "redirect:/user/errorpage";
			}
		} catch (NullPointerException e) {
			rttr.addFlashAttribute("error", "캐싱된 데이터가 없습니다. 다시 로그인 해주세요!");
			return "redirect:/user/login";
		}
		return "/user/transaction";
	}

	// @RequestMapping(value = "view", method = RequestMethod.GET) // 거래내역을 받아
	// view.jsp로 표기해주는 메서드
	// public ModelAndView balance_show() { // 잔액을 보여주는 메서드
	//
	// ModelAndView mav = new ModelAndView("transaction/balance_view");
	//
	// try {
	// get_usernumVOs = service.get_fintech_num(); // 핀테크 이용번호 정보를 가져오는 곳
	// service.get_transaction(get_transactionVO); // 각종 파라미터의 디폴트 값 설정
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	//
	// }
	//
	// mav.addObject("get_transactionVO", get_transactionVO);
	// mav.addObject("get_usernumVOs", get_usernumVOs);
	// return mav;
	// }
	//
	// @RequestMapping(value = "list", method = RequestMethod.GET)
	// public ModelAndView transaction_list(String bank_name) {
	// ModelAndView mav = new ModelAndView("transaction/transaction_list");
	// try {
	// System.out.println(bank_name);
	// Get_UseNumVO = service.transaction_list(bank_name); // 거래내역 불러오는 곳
	// service.get_transaction(get_transactionVO);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	//
	// }
	// mav.addObject("get_transactionVO", get_transactionVO);
	// mav.addObject("Get_UseNumVO", Get_UseNumVO);
	// return mav;
	// }
	//
	// @RequestMapping(value = "save", method = RequestMethod.GET)
	// public String save_data(String category, String branch_name, String trandate,
	// HttpSession session) {
	//
	// String id = (String) session.getAttribute("id");
	// service.save_data(id, category, branch_name, trandate);
	//
	// return "redirect:./list";
	// }
	//
	// @RequestMapping(value = "piechart", method = RequestMethod.GET)
	// public String piechart() {
	// return "transaction/piechart";
	// }

}
