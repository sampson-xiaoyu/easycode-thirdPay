package com.easycode.payClient.service;

import com.easycode.pay.common.Configure;
import com.easycode.payClient.service.request.IServiceRequest;

public class WeChatBaseService extends AbstractPayService{

    public WeChatBaseService(String api) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        super.setApiURL(api);
        Class c = Class.forName(Configure.HttpsRequestClassName);
        super.setServiceRequest((IServiceRequest) c.newInstance());
    }

}
