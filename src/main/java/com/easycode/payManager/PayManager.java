package com.easycode.payManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface PayManager {
	
	/**
     * 支付宝手机端支付
     * @param paymentNum
     * @param currentUser
     * @return
     * @throws UnsupportedEncodingException
     */
    String toAliMobilePay(String paymentNum,String productName,BigDecimal totalFee,Date createTime) throws UnsupportedEncodingException;
    
    /**
     * 支付宝pc端支付
     * @param paymentNum
     * @param currentUser
     * @return
     * @throws UnsupportedEncodingException
     */
    String toAliPcPay(String paymentNum,String productName,BigDecimal totalFee,Date createTime) throws UnsupportedEncodingException;


    /**
     * 微信支付
     * @param paymentNum
     * @param currentUser
     * @return
     */
    Map<String,Object> toWChatPay(String productName,String paymentNum, int totalFee,String ip, String deviceInfo , Date createTime) throws IOException, SAXException, ParserConfigurationException;
	
}
