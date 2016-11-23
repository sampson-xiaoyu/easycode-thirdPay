package com.wcpay.service;


import com.wcpay.common.Configure;
import com.wcpay.protocol.pay_query_protocol.AppPayQueryReqData;

/**
 *
 */
public class AppPayQueryService extends BaseService{

    public AppPayQueryService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.PAY_QUERY_API);
    }

    /**
     * 请求支付查询服务
     * @param scanPayQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public String request(AppPayQueryReqData scanPayQueryReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(scanPayQueryReqData);

        return responseString;
    }


}
