package com.easycode.payClient.service;

import com.easycode.pay.common.Configure;
import com.easycode.payClient.service.request.IServiceRequest;

public class AliBaseService extends AbstractPayService{

	public AliBaseService(String url) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		super.setApiURL(url);
        Class c = Class.forName(Configure.AliHttpsRequestClassName);
        super.setServiceRequest((IServiceRequest) c.newInstance());
	}
	
}
