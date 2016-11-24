package com.easycode.payClient.service;

import com.easycode.pay.common.Configure;
import com.easycode.pay.protocol.payProtocol.weChat.AppPayReqData;

public class WeChatAppPayService extends WeChatBaseService {

    public WeChatAppPayService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.PAY_API);
    }

    /**
     * 请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(AppPayReqData scanPayReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(scanPayReqData);

        return responseString;
    }
}
