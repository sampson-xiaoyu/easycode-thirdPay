package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088411690943421";
	// 商户的私钥				             
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ4vlpISpbOCRbg6CtBCcC8O6gRmKtuJPtWwrtOBi752EXZ6FrqKSzaODVqiZs8VsZpCcoZpHsM6TGrkCppow4BfCQniMExOWhbcpzKUXzc8ar3oTi/K+wTdAMwYRbv7Tq9lKFoUdCIVGgFqXciptvUYN8GcH7cjy5kDCa776VCHAgMBAAECgYBKHfwL10bidBCcwD1e1TJhKjjF9OTx1j2lvFI98b37cLC/9XZ+0tto5WgmZl+LehusLqQgjFKIoOE7WDwLmzi2SbwKeUJQ98aNOW0R9YEb3VH1BO5lmZbotvehl+NJDgeMPzONojIDghSmes9+eAIbg66vSiQzjeg8et70ZeOU+QJBAMqWUS6xjA4WW2QO8YBYARc6VUbE+YIpGx3JoSrxk6001laah3pmn+sKu8C5MXf5hIOrwEHu0SEJbCbX+sylrbsCQQDH5GSSRkRQgkAcWEyHPjcM58IreWaja7V+FcYdCIR/80vB6jaQFbbixRPlZtqTs5938QPSS8u1AruPsQWroBWlAkEAnpWIu0qU3jo2FF92QQei4vHw61MqFe/joC/ED//HoGCqIGUbBH/tU2q1iqntxYL3brM17ubpojjkBowvcZ+IHQJBAKzsaRCdlwWq2DltLa8l5EvWxoQDfwZK7HRJ89wz/SQPXm8/5bnZ/x/IbADdbKOwFXmWV776cRyDY6zX8Fbboz0CQB/3IWwzRcFhz1tJTz6OO0W3z0aNR7W58LCEu3ukOpp8mCezULWa/JeqLq7nmJVwHlmWwZ8TdCdgdcsaLYlMrAU=";

    // 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    //企业收款账户
    public static String seller_id="";
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
