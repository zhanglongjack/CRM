package com.base.user.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.base.StartCRMApp;
import com.base.crm.users.entity.UserInfo;
import com.base.crm.users.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartCRMApp.class)
public class UserTest {

	@Autowired
	UserService userService;
	
	@Test
	public void test(){
		UserInfo user = userService.selectByUserPhone(15999585921L);
		System.out.println(user.getName());
	}
	
}
