package com.base.orders.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.base.App;
import com.base.crm.orders.service.CustOrderService;
import com.base.crm.users.entity.UserInfo;
import com.base.crm.users.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class OrdersTest {

	@Autowired
	private CustOrderService custOrderService;
	
	@Test
	public void test(){
		Long count = custOrderService.selectPageTotalCount(null);
		System.err.println(count);
	}
	
}
