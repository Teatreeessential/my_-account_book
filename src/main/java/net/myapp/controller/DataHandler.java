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
		Map<K,V> topthree =
			    map.entrySet().stream()
			       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			       .limit(3)
			       .collect(Collectors.toMap(
			          Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		return topthree;
	}
	

}
