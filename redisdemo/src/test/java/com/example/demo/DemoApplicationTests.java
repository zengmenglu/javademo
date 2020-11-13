package com.example.demo;

import com.example.demo.bean.User;
import com.example.demo.dao.UserRedis;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {
	@Autowired
	UserRedis userRedis;

	@Test
	void contextLoads() {
	}

	@Test
	public void setup(){
		User user = new User();
		user.setName("zml");
		user.setAge(16);
		user.setTime(new Date());
		userRedis.add(this.getClass().getName(),user);
		System.out.println(userRedis.get(this.getClass().getName()));

		User user2 = new User();
		user2.setName("lmz");
		user2.setAge(16);
		user2.setTime(new Date());
		userRedis.update(this.getClass().getName(),user2);
		System.out.println(userRedis.get(this.getClass().getName()));

		userRedis.delete(this.getClass().getName());
		System.out.println(userRedis.get(this.getClass().getName()));
	}

}
