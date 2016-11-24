package com.easycode.payClient.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import com.easycode.pay.common.Configure;

public class AliPayService extends AliBaseService{

	public AliPayService() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		super(Configure.ALI_PAY_API);
	}
	
	public String request(Object json) throws Exception{
		//--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(json);
        return responseString;
	}
	
}
