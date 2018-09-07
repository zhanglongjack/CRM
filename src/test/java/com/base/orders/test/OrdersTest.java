package com.base.orders.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.base.StartCRMApp;
import com.base.crm.orders.service.CustOrderService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartCRMApp.class)
public class OrdersTest {

	@Autowired
	private CustOrderService custOrderService;
	
	@Test
	public void test(){
		Long count = custOrderService.selectPageTotalCount(null);
		System.err.println(count);
	}
	
}
