/** 
 * @Title:ZcmsWecashMessage.java 
 * @package: com.gomefinance.mj.sync.entity  
 * @Description:TODO 
 * @author: liguojiang 
 * @date: 2017年8月4日 下午6:46:21  
 * @version: 
 */
package com.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**  
 * 接收政审和核心的报文实体bean
 * 政审系统返回的报文格式：
 * {
 *  	"CONTRA_NBR": "XXXXX",
 *   "APPLY_NO": "XXXXX",
 *   "EXTRA": {
 *       "SINGLE_INVERTED": "P",
 *       "ACC_LMT": "1000",
 *       "PRODUCT_RATE": "D",
 *       "APP_TYPE": "MCEPEI"
 *   },
 *   "SERVICE_SN": "XXXXX",
 *   "SEND_TIME": "XXX",
 *   "MSG_TYPE": "S",
 *   "STATUS": "APPROVE_REFUSE",
 *   "SERVICE_ID": "SunlineCallback"
 *	}
 *	核心系统返回的报文
 *	{
 *	"CONTRA_NBR":"XXXXXX",
 *	"APPLY_NO":"XXXXX",
 *	"EXTRA":{
 *       "NEXT_PMT_DUE_DATE":"2017-09-03",
 *       "TXN_AMT":1000
 *   }
 *	"SERVICE_SN":"XXXXX",
 *	"SEND_TIME":"XXXXX",
 *	"MSG_TYPE":"Q",
 *	"STATUS":"PAY_SUCESS",
 *	"SERVICE_ID":"SunlineCallback",
 *	"UUID":"XXXXXXX",
 *   "MSG_CONTENT":null
 *	}
 * <p>Title:ZcmsWecashMessage</p> 
 * <p>Description:TODO</p> 
 * @author liguojiang  
 * @date 2017年8月4日 下午6:46:21  
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ZcmsWecashMessage {
    /**
     * 服务ID
     */
    private String SERVICE_ID;
    /**
     * 消息发送的时间
     */
    private String SEND_TIME;
    /**
     * 订单号
     */
    private String APPLY_NO;
    /**
     * 合同号
     */
    private String CONTRA_NBR;
    /**
     * 消息的主键Id
     */
    private String UUID;
    
    private String SERVICE_SN;
    /**
     * 消息类型   我们暂时写的是S
     */
    private String MSG_TYPE;
    /**
     * 核心系统的状态
     */
    private String STATUS;
    /**
     * 消息的描述内容
     */
    private String MSG_CONTENT;
    /**
     * 其他额外的字段
     */
    private EXTRA EXTRA;
    
    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public class EXTRA {
        /**
         * 到期还款日
         */
        private String NEXT_PMT_DUE_DATE;
        /**
         * 金额
         */
        private String TXN_AMT;
        /**
         * 产品类型 PDLOAN-美借  MCEPEI-美易分
         */
        private String APP_TYPE;
        /**
         * 判断版本 P:2.0 非P:1.0  倒趸交   值为“P”和非"P"    P代表2.0  其他为非2.0
         */
        private String SINGLE_INVERTED;
        /**
         * 费率等级
         */
        private String PRODUCT_RATE;
        /**
         * 授信额度
         */
        private String ACC_LMT;
        /**
         * 审批期数
         */
        private String LIMIT_TERM;
    }

     class B{}
     static class C{}
     public class D{}
}
