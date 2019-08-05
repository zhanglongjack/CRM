package com.base.crm.common.constants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.base.common.dictionary.entity.Dictionary;
import com.base.common.dictionary.service.DictionaryService;

@Component
public class CommonConstants implements ApplicationListener<ContextRefreshedEvent> {
	public static final String COMMON_LAYOUT = "commonLayout";
	public static Map<String, String> custOrderStatusMap;
//	public static Map<String, String> buyStatusMap;
//	public static Map<String, String> adAccountTypeMap;
	public static final Map<String, Map<String, String>> dictionaryMap = new HashMap<String, Map<String, String>>();
	static {
		custOrderStatusMap = CustOrderStatus.getValues();
	}

	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (dictionaryMap.size() == 0) {
			init();
		}
	}

	public void init() {
		List<Dictionary> dictionList = dictionaryService.selectBySelective(null);
		for (Dictionary dict : dictionList) {
			if (dict.getStatus() != 1) {
				continue;
			}

			if (!dictionaryMap.containsKey(dict.getBizCode())) {
				dictionaryMap.put(dict.getBizCode(), new LinkedHashMap<String, String>());
			}

			dictionaryMap.get(dict.getBizCode()).put(dict.getCode(), dict.getName());
		}
	}
	
	public String getAdAccountType(String code){
		return getDictionarysByKey("AdAccountType").get(code);
	}
	
	public Map<String, String> getAdAccountTypeMap(){
		return getDictionarysByKey("AdAccountType");
	}
	
	public Map<String, String> getDictionarysByKey(String bizCode){
		
		return dictionaryMap.get(bizCode);
	}
	
	public String getDictionarysBy(String bizCode,String code){
		Assert.isTrue(bizCode!=null&&code!=null, "业务代码和字典代码不允许为空");
		return dictionaryMap.get(bizCode).get(code);
	}

	public void updateDictionaryBy(String bizCode, String code,String value) {
		String msg = String.format("业务代码[%s],字典代码[%s],更新值[%s]均不允许为空", bizCode,code,value);
		Assert.isTrue(bizCode!=null&&code!=null&&value!=null, msg);
		dictionaryMap.get(bizCode).put(code, value);
	}
	

}
