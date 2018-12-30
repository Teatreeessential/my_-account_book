package net.myapp.controller;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class DataHandler {

	public static int sum(Map<String, Integer> map) {
		int sum = 0;
		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println(key);
			System.out.println(map.get(key));

			sum += map.get(key);
		}
		return sum;
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		Map<K, V> topthree = map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(3)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		return topthree;
	}

	public static String bankName(int banknum) {
		switch (banknum) {
		case 2:
			return "산업은행";
		case 3:
			return "기업은행";
		case 4:
			return "국민은행";
		case 7:
			return "수협중앙회";
		case 11:
			return "NH농협은행";
		case 20:
			return "우리은행";
		case 23:
			return "SC제일은행";
		case 27:
			return "한국씨티은행";
		case 31:
			return "대구은행";
		case 32:
			return "부산은행";
		case 34:
			return "광주은행";
		case 35:
			return "제주은행";
		case 37:
			return "전북은행";
		case 39:
			return "경남은행";
		case 81:
			return "KEB하나은행";
		case 88:
			return "신한은행";
		default:
			return "오픈 api 은행";

		}
	}

	public static String balanceType(int balancenum) {
		switch (balancenum) {
			case 1:
				return "수시입출금";
			case 2:
				return "예적금";
			case 6:
				return "수익증권";
			default:
				return "해당되는 계좌 타입이 없습니다";
		}

	}
	
	public static String bankImage(int banknum) {
		switch (banknum) {
		case 2:
			return "/resources/bank_image/산업.jpg";
		case 3:
			return "/resources/bank_image/기업.jpg";
		case 4:
			return "/resources/bank_image/국민.jpg";
		case 7:
			return "/resources/bank_image/수협.jpg";
		case 11:
			return "/resources/bank_image/농협.jpg";
		case 20:
			return "/resources/bank_image/우리.jpg";
		case 23:
			return "/resources/bank_image/제일.jpg";
		case 27:
			return "/resources/bank_image/시티.jpg";
		case 31:
			return "/resources/bank_image/대구.png";
		case 32:
			return "/resources/bank_image/부산.jpg";
		case 34:
			return "/resources/bank_image/광주.jpg";
		case 35:
			return "/resources/bank_image/제주.png";
		case 37:
			return "/resources/bank_image/전북.png";
		case 39:
			return "/resources/bank_image/경남.jpg";
		case 81:
			return "/resources/bank_image/하나.jpeg";
		case 88:
			return "/resources/bank_image/신한.png";
		default:
			return "https://ununsplash.imgix.net/photo-1431578500526-4d9613015464?fit=crop&amp;fm=jpg&amp;h=300&amp;q=75&amp;w=400";

		}
	}

}
