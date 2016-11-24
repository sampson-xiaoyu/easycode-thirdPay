package com.easycode.payClient;

import com.easycode.payClient.service.AliPayService;


public class AliPayClient {
	
    public static String requestAliPcPayService(String jsonStr) throws Exception{
        return new AliPayService().request(jsonStr);
    }

}
