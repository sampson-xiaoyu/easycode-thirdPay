package com.wcpay.service;

import com.wcpay.common.Configure;
import com.wcpay.protocol.pay_batch.BatchPayReqData;

/**
 *
 */
public class BatchPayService extends BaseService {

    public BatchPayService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.PAY_BATCH_API);
    }

    /**
     * 请求支付服务
     * @param batchPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(BatchPayReqData batchPayReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(batchPayReqData);

        return responseString;
    }
}
