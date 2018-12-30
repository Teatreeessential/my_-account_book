package net.myapp.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class TestCache {

		@Setter(onMethod_=@Autowired)
		private CacheManager cacheManager;
		private Cache cache;
		
		@Test 
		public void live() throws InterruptedException{ 
			 cache = cacheManager.getCache("sampleCache");
			
			 System.out.println(cache.get("dsadasd")==null);
			
		}
		@Test
		public void test() throws InterruptedException{
			System.out.println(cache.get("title").get());
			System.out.println(cache.get("title").get());
			System.out.println(cache.get("title").get());
			System.out.println(cache.get("title").get());
		}

}
