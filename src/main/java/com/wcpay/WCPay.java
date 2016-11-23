package com.wcpay;


import com.wcpay.common.Configure;
import com.wcpay.protocol.pay_batch.BatchPayReqData;
import com.wcpay.protocol.pay_protocol.AppPayReqData;
import com.wcpay.protocol.pay_query_protocol.AppPayQueryReqData;
import com.wcpay.service.AppPayQueryService;
import com.wcpay.service.AppPayService;
import com.wcpay.service.BatchPayService;

/**
 * SDK总入口
 */
public class WCPay {

    /**
     * 初始化SDK依赖的几个关键配置
     * @param key 签名算法需要用到的秘钥
     * @param appID 公众账号ID
     * @param mchID 商户ID
     * @param certLocalPath HTTP证书在服务器中的路径，用来加载证书用
     * @param certPassword HTTP证书的密码，默认等于MCHID
     */
    public static void initSDKConfiguration(String key,String appID,String mchID,String certLocalPath,String certPassword){
        Configure.setKey(key);
        Configure.setAppID(appID);
        Configure.setMchID(mchID);
        Configure.setCertLocalPath(certLocalPath);
        Configure.setCertPassword(certPassword);
    }

    /**
     * 请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public static String requestAppPayService(AppPayReqData scanPayReqData) throws Exception{
        return new AppPayService().request(scanPayReqData);
    }

    /**
     * 请求支付查询服务
     * @param scanPayQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
	public static String requestScanPayQueryService(AppPayQueryReqData scanPayQueryReqData) throws Exception{
		return new AppPayQueryService().request(scanPayQueryReqData);
	}


    /**
     * 请求批量付款
     * @param batchPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据 需要返回数据格式已经提供好实体
     * @throws Exception
     */
	public static String requestBatchPayService(BatchPayReqData batchPayReqData)throws Exception{
        return new BatchPayService().request(batchPayReqData);
    }

}
