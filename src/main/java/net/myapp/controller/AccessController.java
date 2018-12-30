package net.myapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
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
import net.myapp.domain.BalanceVO;
import net.myapp.domain.MemberVO;
import net.myapp.domain.TransactionParameterVO;
import net.myapp.service.ManagerService;

@EnableAsync
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
	public String login(String id, String passwd, RedirectAttributes rttr, HttpSession session, Model model) {

		MemberVO member = service.getMember(id, passwd);
		if (member == null) {
			rttr.addFlashAttribute("error", "비밀번호 혹은 아이디가 틀렸습니다.");
			return "redirect:/user/login";
		} else {
			session.setAttribute("member", member);
			return "redirect:/user/balance";
		}
	}

	@RequestMapping(value = "/balance", method = RequestMethod.GET)
	public String balance(HttpSession session, Model model) {
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		session.setAttribute("fintech_use_nums", member.getFintech_use_nums());
		List<BalanceVO> balance_list = new ArrayList<>();
		cache = cacheManager.getCache("sampleCache");

		if (cache.get(member.getFintech_use_nums().get(0) + "balance") == null) {
			// 데이터가 캐싱되지 않을 경우 널포인터 익셉션이 걸림 따라서 여기서 for문을 통해 요청 후 캐싱
			for (int i = 0; i < member.getFintech_use_nums().size(); i++) {
				TransactionList transaction_job = new TransactionList();
				BalanceList balance_job = new BalanceList();
				service.getBalanceList(member.getAccess_token(), member.getFintech_use_nums().get(i));
				service.getTransactionList(member.getAccess_token(), member.getFintech_use_nums().get(i));
			}
		}
		while (true) {
			if (cache.get(member.getFintech_use_nums().get(0) + "balance") != null &&
					cache.get(member.getFintech_use_nums().get(1) + "balance") !=null) {
				for (int i = 0; i < member.getFintech_use_nums().size(); i++) {
					balance_list.add((BalanceVO) cache.get(member.getFintech_use_nums().get(i) + "balance").get());
				}
				break;
			}
		}
		
		model.addAttribute("balance_list", balance_list);
		System.out.println(balance_list);
		return "/user/balance";
	}

	@RequestMapping(value = "/transaction/{fintech_use_num}", method = RequestMethod.GET)
	public String transaction(@PathVariable("fintech_use_num") String fintech_use_num, HttpSession session,
			RedirectAttributes rttr, Model model) {

		cache = cacheManager.getCache("sampleCache");
		List<String> list = (List<String>) session.getAttribute("fintech_use_nums");

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
				BalanceVO balance = (BalanceVO) cache.get(fintech_use_num + "balance").get();

				System.out.println(consume_map);
				System.out.println(income_map);

				branch_map = datahandler.sortByValue(branch_map);
				total_income = datahandler.sum(income_map);
				total_consume = datahandler.sum(consume_map);
				top_three_income_map = datahandler.sortByValue(income_map);
				top_three_consume_map = datahandler.sortByValue(consume_map);

				model.addAttribute("branch_map", branch_map);
				model.addAttribute("total_income", total_income);
				model.addAttribute("total_consume", total_consume);
				model.addAttribute("top_three_income_map", top_three_income_map);
				model.addAttribute("top_three_consume_map", top_three_consume_map);
				model.addAttribute("balance", balance);
				model.addAttribute("income_map", gson.toJson(income_map));
				model.addAttribute("consume_map", gson.toJson(consume_map));

			} else {
				rttr.addFlashAttribute("error", "올바른 접근이 아닙니다.");
				return "/user/errorpage";
			}
		} catch (NullPointerException e) {
			rttr.addFlashAttribute("error", "캐싱된 데이터가 없습니다. 다시 로그인 해주세요!");
			return "redirect:/user/login";
		}
		return "/user/transaction";
	}
	
	@RequestMapping(value="/logout",method =RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "/user/login";
	}
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join() {
		return "/user/join";
	}
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(String id) {
		return "redirect:/user/balance";
	}

}
