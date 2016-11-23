package com.easycode.payManager.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.xml.sax.SAXException;

import com.alipay.config.AlipayConfig;
import com.alipay.sign.RSA;
import com.alipay.util.AlipayCore;
import com.easycode.commons.time.DateFormatUtils;
import com.easycode.commons.time.DateUtils;
import com.easycode.payManager.PayManager;
import com.wcpay.WCPay;
import com.wcpay.common.Configure;
import com.wcpay.common.RandomStringGenerator;
import com.wcpay.common.Signature;
import com.wcpay.common.XMLParser;
import com.wcpay.protocol.pay_protocol.AppPayReqData;

public class PayManagerImpl implements PayManager{
	
	private static Logger logger = LoggerFactory.getLogger(PayManagerImpl.class.getName());
	
    /**
     * 微信支付所需信息
     */
    @Value("${wchatpay.return.url}")
    private String wchatReturnUrl;
    @Value("${wchatpay.key}")
    private String key;
    @Value("${wchatpay.appID}")
    private String appID;
    @Value("${wchatpay.mchID}")
    private String mchID;
    @Value("${wchatpay.certLocalPath}")
    private String certLocalPath;
    @Value("${wchatpay.certPassword}")
    private String certPassword;
    @Value("${wchatpay.limit_pay_max}")
    private int wchatlimitPay;
    
    /**
     * 阿里支付所需信息
     */
    @Value("${alipay.return.url}")
    private String alipayReturnUrl;
    @Value("${alipay.limit_pay_max}")
    private int alilimitPay;
	
	@Override
	public String toAliPay(String paymentNum,String productName,BigDecimal totalFee,Date createTime) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		 Map<String,String> params=getAliPayInfoMap(paymentNum,productName,totalFee,createTime);
	        //将post接收到的数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串。需要排序。
        String data= AlipayCore.createLinkString(params);
        //打印待签名字符串。工程目录下的log文件夹中。
        logger.info("支付验签数据:"+data);
        //将待签名字符串使用私钥签名。
        String rsa_sign= URLEncoder.encode(RSA.sign(data, AlipayConfig.private_key, AlipayConfig.input_charset),AlipayConfig.input_charset);
        //把签名得到的sign和签名类型sign_type拼接在待签名字符串后面。
        data=data+"&sign=\""+rsa_sign+"\"&sign_type=\""+AlipayConfig.sign_type+"\"";
        logger.info("支付验签后数据:"+data);
        return data;
	}
	
	private Map<String,String> getAliPayInfoMap(String paymentNum,String productName,BigDecimal totalFee,Date createTime){
        //封装参数
        Map<String,String> params=new HashMap<>();
        params.put("partner", getPayParamValueStr(AlipayConfig.partner));
        params.put("seller_id",getPayParamValueStr(AlipayConfig.partner));
        params.put("out_trade_no",getPayParamValueStr(paymentNum));
        params.put("subject",getPayParamValueStr(productName));
        params.put("body",getPayParamValueStr(productName));
        params.put("total_fee",getPayParamValueStr(totalFee.toString()));
        params.put("notify_url",getPayParamValueStr(alipayReturnUrl));
        params.put("service",getPayParamValueStr("mobile.securitypay.pay"));
        params.put("orderInfo",getPayParamValueStr("1"));
        params.put("_input_charset",getPayParamValueStr("utf-8"));
        params.put("it_b_pay",getPayParamValueStr(DateFormatUtils.format(DateUtils.addMinutes(createTime,30), "yyyy-MM-dd HH:mm:ss")));
        return params;



    }
	
	 private String getPayParamValueStr(String value){
	        return "\""+value+"\"";
	    }
	
	@Override
	public Map<String, Object> toWChatPay(String productName,String paymentNum, int totalFee,String ip, String deviceInfo , Date createTime)
			throws IOException, SAXException, ParserConfigurationException {
		// TODO Auto-generated method stub
		//调用微信支付的支付接口获取支付信息
        String resdata= null;
        try {
            WCPay.initSDKConfiguration(key,appID,mchID,certLocalPath,certPassword);
            //封装统一下单接口所需的数据
            AppPayReqData scanPayReqData= new AppPayReqData(
            		productName,
                    "",
                    paymentNum,
                    totalFee,
                    deviceInfo,
                    ip,
                    DateFormatUtils.format(createTime,"yyyyMMddHHmmss"),
                    DateFormatUtils.format(DateUtils.addMinutes(createTime,30),"yyyyMMddHHmmss"),
                    null,
                    wchatReturnUrl,
                    //对使用系用卡额度做限制limitPay为信用卡最大限额元为单位*100换算成分
                    (wchatlimitPay*100)>totalFee?null:"no_credit");
            resdata = WCPay.requestAppPayService(scanPayReqData);
        } catch (Exception e) {
            logger.error("请求统一下单失败:"+e.getMessage());
        }
        logger.info("统一下单数据："+resdata);
        Map<String,Object> map = XMLParser.getMapFromXML(resdata);
        //验证数据签名
        if(!Signature.checkIsSignValidFromResponseString(resdata)){
            logger.info("统一下单签名失败："+resdata);
            throw new UnsupportedOperationException("微信验证失败 返回信息被篡改");
        }
        //验证数据返回状态
        if(!"SUCCESS".equals(map.get("return_code"))){
            logger.info("统一下单请求失败："+resdata);
            throw new UnsupportedOperationException("微信验证失败");
        }
        //验证数据正常状态
        if(!"SUCCESS".equals(map.get("result_code"))){
            logger.info("统一下单数据失败："+resdata);
            throw new UnsupportedOperationException("微信验证失败");

        }
        /**
         * 封装客户端需要的数据
         */
        Map<String,Object> payData=new HashMap<String,Object>();
        payData.put("appid", Configure.getAppid());
        payData.put("prepayid", map.get("prepay_id").toString());
        payData.put("package", "Sign=WXPay");
        payData.put("partnerid", Configure.getMchid());
        payData.put("noncestr", RandomStringGenerator.getRandomStringByLength(32));
        payData.put("timestamp", new Date().getTime()/1000+"");
        //返回数据进行二次加密
        payData.put("sign", Signature.getSign(payData));
        return payData;
	}
	
}
