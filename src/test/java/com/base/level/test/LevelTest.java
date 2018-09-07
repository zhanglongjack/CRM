package com.base.level.test;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.base.App;
import com.base.crm.common.constants.CustomerLevelContainer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class LevelTest {

	@Autowired
	private CustomerLevelContainer level;
	
	@Test
	public void test(){
		System.err.println(level.levelMap);
	}
	
}
