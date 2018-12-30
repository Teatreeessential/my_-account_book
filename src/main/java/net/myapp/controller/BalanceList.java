package net.myapp.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.Data;
import lombok.Setter;
import net.myapp.domain.BalanceVO;
import net.myapp.domain.TransactionVO;

@Data
public class BalanceList implements Runnable {
	@Setter(onMethod_=@Autowired)
	private CacheManager cacheManager;
	@Setter(onMethod_=@Autowired)
	private DataHandler datahandler;
	private Cache cache;
	private String fintech_use_num;
	private String access_token;
	
	//json 데이터 파싱 및 java객체 변환 시 사용
	
	
	@Override
	public void run() {
		JsonParser parser = new JsonParser();
		Gson gson = new Gson();
		TranDate tran = new TranDate();
		cache = cacheManager.getCache("sampleCache");
		RestTemplate rest = new RestTemplate();
		List<TransactionVO> list = new ArrayList<>();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization", "Bearer "+this.access_token);
		HttpEntity<?> requestEntity = new HttpEntity(requestHeaders);
		
		
		URI uri = UriComponentsBuilder.newInstance().scheme("https").host("testapi.open-platform.or.kr")
                .path("/account/balance")
                .queryParam("fintech_use_num",this.fintech_use_num)
                .queryParam("tran_dtime", tran.tran_date())
                .build()
                .encode()
                .toUri();
		
		HttpEntity<String> response = rest.exchange(uri,HttpMethod.GET, requestEntity,String.class);
		//응답의 모든 값이 옴 header body httpstatus등등.. 그중 body에 있는 데이터를 가져옴
		JsonObject json = parser.parse(response.getBody()).getAsJsonObject();
		
		BalanceVO balance = gson.fromJson(json.toString(), BalanceVO.class);
		
		balance.setAccount_type(datahandler.balanceType(Integer.parseInt(balance.getAccount_type())));
		balance.setBank_code_tran(datahandler.bankName(Integer.parseInt(balance.getBank_code_tran())));
		
		
		cache.put(fintech_use_num+"balance", balance);
		

	}

}
