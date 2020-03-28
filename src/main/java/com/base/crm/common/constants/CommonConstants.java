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
import com.base.crm.ad.entity.ConsumeAcctGroup;
import com.base.crm.ad.service.ConsumeAcctGroupService;
import com.base.crm.depository.entity.Depository;
import com.base.crm.depository.service.DepositoryService;
import com.base.crm.product.entity.Product;
import com.base.crm.product.entity.ProductAssort;
import com.base.crm.product.service.ProductAssortService;
import com.base.crm.product.service.ProductService;

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
	@Autowired
	private ConsumeAcctGroupService consumeAcctGroupService;
	@Autowired
	private DepositoryService depositoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductAssortService productAssortService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (dictionaryMap.size() == 0) {
			init();
			initProduct();
			initDepository();
			initAssort();
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
	
	public void initProduct(){
		Map<String, String> productMap = dictionaryMap.get("product_dict");
		
		if(productMap==null){
			productMap = new LinkedHashMap<String, String>();
			dictionaryMap.put("product_dict", productMap);
		}
		productMap.clear();
		List<Product> productList = productService.selectByObjectForList(null);
		for (Product product : productList) {
			if (product.getStatus().equals("0")) {
				continue;
			}

			productMap.put(product.getProductId()+"", product.getProductName());
		}
		
	}
	
	public void initDepository(){
		Map<String, String> depositoryMap = dictionaryMap.get("depository_dict");
		
		if(depositoryMap==null){
			depositoryMap = new LinkedHashMap<String, String>();
			dictionaryMap.put("depository_dict", depositoryMap);
		}
		depositoryMap.clear();
		List<Depository> depositoryList = depositoryService.selectByObjectForList(null);
		for (Depository depository : depositoryList) {
			depositoryMap.put(depository.getDepositoryId()+"", depository.getDepositoryName());
		}
		
	}
	public void initAssort(){
		Map<String, String> depositoryMap = dictionaryMap.get("assort_dict");
		
		if(depositoryMap==null){
			depositoryMap = new LinkedHashMap<String, String>();
			dictionaryMap.put("assort_dict", depositoryMap);
		}
		depositoryMap.clear();
		List<ProductAssort> depositoryList = productAssortService.selectByObjectForList(null);
		for (ProductAssort depository : depositoryList) {
			if (depository.getStatus().equals("0")) {
				continue;
			}
			depositoryMap.put(depository.getId()+"", depository.getAssortName());
		}
		
	}
	
	private static Map<String, ConsumeAcctGroup> adAcctGroup;
	public String getAdAccountType(String code){
		if(adAcctGroup==null){
			getAdAccountTypeMap();
		}
		return adAcctGroup.get(code)==null?null:adAcctGroup.get(code).getGroupName();
	}
	
	public Map<String, ConsumeAcctGroup> getAdAccountTypeMap(){
		adAcctGroup = consumeAcctGroupService.queryGroupAcctRelationData();
		return adAcctGroup;
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
