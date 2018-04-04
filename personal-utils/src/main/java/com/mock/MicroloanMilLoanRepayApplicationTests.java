package com.mock;

import com.WebApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Description : [spring-mock测试，替代http]
 * url: http://blog.csdn.net/weililansehudiefei/article/details/73734388
 * url: http://blog.csdn.net/unix21/article/details/52052871
 * Created on : 2018年01月31日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class MicroloanMilLoanRepayApplicationTests {

    //测试Controller接口
    @Autowired
//    private RepaymentScheduleController repaymentScheduleController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
//        mockMvc = MockMvcBuilders.standaloneSetup(repaymentScheduleController).build();
    }
    //验证controller是否正常响应并打印返回结果
    @Test
    public void getHello() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/loanrepay/repaymentSchedule/repayTerm").accept(MediaType.APPLICATION_JSON)
                .param("dayNum","8")
                .param("date","2017-7-18 00:00:00")
                .param("pageNum","1")
                .param("pageSize","10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }


}