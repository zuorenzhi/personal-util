/**
 * <b>项目名称：</b>application-account-api<br/>
 * <b>包    名：</b>com.gomefinance.mj.account.vo<br/>
 * <b>文 件 名：</b>SettlementVo.java<br/>
 * <b>版本信息：</b><br/>
 * <b>创建人：</b>liuhai<br/>
 * <b>创建时间：</b>2017年7月17日 下午4:59:13<br/>
 * <b>修改人：</b>liuhai<br/>
 * <b>修改时间：</b>2017年7月17日 下午4:59:13<br/>
 * <b>修改备注：</b><br/>
 */
package com.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.io.Serializable;

/**
* Description : [还款试算vo]
* Created on : 2017-08-23 18:22
* author : [左仁智]
* version : 1.0
* Copyright (c) 2017 国美金控-美借
*/
@Setter
@Getter
@ToString
public class FastJsonVo implements Serializable{

   private static final long serialVersionUID = -1112070863458637989L;

   private String mobile;
   private String bankCardName;
   @JSONField(name="bank_province")
   private String bankProvince;
   @JSONField(name="bank_prov_code")
   private String bankProvCode;

   @Test
   public void test(){


   }

   public static void main(String[] args) {
      FastJsonVo fastJsonVo = new FastJsonVo();

      String mobile = "1234567";

      fastJsonVo.setMobile(mobile);
      fastJsonVo.setBankCardName("622222222222222");
      fastJsonVo.setBankProvince("sichuan");
      fastJsonVo.setBankProvCode("10023");

      String jsonStr = JSON.toJSONString(fastJsonVo);
      System.out.println(jsonStr);

      FastJsonVo fastJsonVo1 = JSON.parseObject(jsonStr, FastJsonVo.class);
      System.out.println(fastJsonVo1.toString());

      JSONObject jsonObject = new JSONObject();
      jsonObject.put("age", 12);
      jsonObject.put("name", "hanmei");
      System.out.println(jsonObject);


   }
}
