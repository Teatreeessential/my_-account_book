package net.myapp.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.myapp.domain.MemberVO;
import net.myapp.domain.TransactionVO;
import net.myapp.service.ManagerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Log4j
public class Test {
	private Queue<TransactionList> q = new LinkedList<>();
	@Setter(onMethod_=@Autowired)
	private TaskExecutor taskExecutor;
	@Setter(onMethod_ = @Autowired)
	ManagerService service;
	@Autowired
	private ApplicationContext applicationContext;
	@Setter(onMethod_ = @Autowired)
	TranDate tran;
	String id = "abc";
	String passwd = "1111";

//	 @org.junit.Test
	public void test() {
		String access_token = "dae96f88-2980-4b2f-a600-24f467b91b84";
		String fintech_use_num = "199000840057721775623729";

		// json 데이터 파싱 및 java객체 변환 시 사용
		JsonParser parser = new JsonParser();
		Gson gson = new Gson();
		// 헤더 값에 엑세스 토큰 넣는 과정
		List<TransactionVO> list = new ArrayList<>();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization", "Bearer " + access_token);
		HttpEntity<?> requestEntity = new HttpEntity(requestHeaders);
		// 파라미터 값 추가 1달사이
		String next_page_yn = "Y";
		int page_index = 1;
		// 다음 페이지가 없을 경우 N 반복문 종료
		while (next_page_yn.equals("Y")) {

			URI uri = UriComponentsBuilder.newInstance().scheme("https").host("testapi.open-platform.or.kr")
					.path("/account/transaction_list").queryParam("fintech_use_num", fintech_use_num)
					.queryParam("tran_dtime", tran.tran_date()).queryParam("from_date", tran.from_date())
					.queryParam("to_date", tran.to_date()).queryParam("sort_order", "D")
					.queryParam("page_index", page_index).queryParam("inquiry_type", "A").build().encode().toUri();

			HttpEntity<String> response = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);

			// 응답의 모든 값이 옴 header body httpstatus등등.. 그중 body에 있는 데이터를 가져옴
			JsonObject json = parser.parse(response.getBody()).getAsJsonObject();
			// 반복작업을 수행하기 위해 다음 페이지 유무

			next_page_yn = json.get("next_page_yn").toString().substring(1, 2);

			// 다음페이지
			page_index++;
			// 실제 거래내역 25건
			String res_list = json.get("res_list").toString();

			TransactionVO[] array = gson.fromJson(res_list, TransactionVO[].class);

			for (TransactionVO vo : array) {
				list.add(vo);
			}

		}
		System.out.println(list.size());
		System.out.println(list);

		// 해당 계좌의 한달간의 입출금 내역을 모두 list에
		// 거래내역 그 자체 캐싱
		// 각 일자별 지출 캐싱
		Map<String, Integer> consume_map = new HashMap<>();
		// 각 일자별 수입 캐싱
		Map<String, Integer> income_map = new HashMap<>();
		// 이를 모두 더하면 한달간의 지출 및 수입
		// 가장 많이 간 가게 1,2,3위
		Map<String, Integer> branch_map = new HashMap<>();

		for (TransactionVO vo : list) {
			// 자주가는 가게 목록
			if (branch_map.containsKey(vo.getBranch_name())) {
				branch_map.put(vo.getBranch_name(), branch_map.get(vo.getBranch_name()) + 1);
			} else {
				branch_map.put(vo.getBranch_name(), 1);
			}

			// 하루단위로 출금만 모아서 데이터를 만들고
			if (vo.getInout_type().equals("출금")) {
				if (consume_map.containsKey(vo.getTran_date())) {
					consume_map.put(vo.getTran_date(),
							consume_map.get(vo.getTran_date()) + Integer.parseInt(vo.getTran_amt()));
				} else {
					consume_map.put(vo.getTran_date(), Integer.parseInt(vo.getTran_amt()));
				}
				// 하루단위로 모아서 데이터를 만듬
			} else {
				if (income_map.containsKey(vo.getTran_date())) {
					income_map.put(vo.getTran_date(),
							income_map.get(vo.getTran_date()) + Integer.parseInt(vo.getTran_amt()));
				} else {
					income_map.put(vo.getTran_date(), Integer.parseInt(vo.getTran_amt()));
				}
			}
		}

	}

	@org.junit.Test
	public void thread_Test() {
		MemberVO member = service.getMember("abc", "1111");

		if (member == null) {
			System.out.println("아이디 비번 틀림");
		} else {

			for (int i = 0; i < member.getFintech_use_nums().size(); i++) {
				BalanceList job = new BalanceList();
				job.setAccess_token(member.getAccess_token());
				job.setFintech_use_num(member.getFintech_use_nums().get(i).getFintech_use_num());
				applicationContext.getAutowireCapableBeanFactory().autowireBean(job);
				taskExecutor.execute(job);
			}
			
		}
		while(true) {
			
		}
		
	}
}
