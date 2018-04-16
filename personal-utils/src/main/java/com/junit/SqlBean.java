package com.junit;

import org.junit.Test;

/**
 * 类描述
 * Created on : 2018年04月12日
 *
 * @author : [左仁智]
 */

public class SqlBean {

    @Test
    public void test(){
        String str = "INSERT INTO `chat_record` (`tenant_id`, `robot_id`, `session_id`, `user_id`, `question_id`, `question`, `question_type`, `product`, `terminal`, `token`, `answer`, `answer_type`, `repository_type`, `repository_id`, `repository_name`, `intention_recognition`, `create_time`) VALUES " +
                "( '1', 'robot_5', 'session_5', 'user_5', 'question_5', 'question_5', 1, 'product_5', '1', '1', '1', '0', 1, 1, '1', '1', '2018-3-13 3:54:12');";
        for (int i = 0; i <10 ; i++) {
            System.out.println(str);
        }

    }
}
