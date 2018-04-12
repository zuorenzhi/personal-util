package camelot.email;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>Description: [邮件参数实体]</p>
 * Created on 2016年3月15日
 * @author  <a href="mailto: liuxiangping86@clt.com">刘香平</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部 
 */
@Setter
@Getter
public class EmailBean {

    /** 发件服务器地址 */
    private String smtpHost;
    
    /** 发件服务器端口 */
    private int smtpPort;

    /** 发件用户名 ==email*/
    private String smtpUser;

    /** 发件密码 */
    private String smtpPass;
    
    /** 发件邮箱地址 */
    private String email;
    
    /** 发件人显示名称 */
    private String personal;

}