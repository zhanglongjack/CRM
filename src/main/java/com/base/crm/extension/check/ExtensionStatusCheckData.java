package com.base.crm.extension.check;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.base.common.util.DateUtils;

@Component
public class ExtensionStatusCheckData {
//	private Map<String, ExtentensionCheckInfo> data = new HashMap<String, ExtentensionCheckInfo>();
	private List<ExtentensionCheckInfo> data = new ArrayList<ExtentensionCheckInfo>();

	public List<ExtentensionCheckInfo> getData() {
		return data;
	}

	public void addData(ExtentensionCheckInfo data) {
		this.data.add(data);
	}

	public void removeExpireData() {
//		Iterator<Entry<String, ExtentensionCheckInfo>> it = data.entrySet().iterator();
//		while (it.hasNext()) {
//			Map.Entry<String, ExtentensionCheckInfo> entry = it.next();
//			ExtentensionCheckInfo value = entry.getValue();
//			if (value.getStatus() == 0 && !value.getDateTime().contains(DateUtils.getStringDateShort())) {
//				it.remove();
//			}
//		}
		
		Iterator<ExtentensionCheckInfo> it = data.iterator();
		while(it.hasNext()){
		    ExtentensionCheckInfo value = it.next();
		    if(value.getStatus() == 0 && !value.getDateTime().contains(DateUtils.getStringDateShort())){
		    	it.remove();
		    }
		}
	}

}
