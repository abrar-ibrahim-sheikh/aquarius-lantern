package com.scribblesphere.aquariuslantern;

import com.scribblesphere.aquariuslantern.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AquariusLanternApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void RepoTest() {
		String classname = userRepository.getClass().getName();
		String packageName = userRepository.getClass().getPackageName();
		System.out.println(classname);
		System.out.println(packageName);
	}

}
