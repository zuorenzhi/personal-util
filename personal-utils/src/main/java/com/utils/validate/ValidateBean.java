package com.utils.validate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by Administrator on 2017/8/10.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class ValidateBean implements  java.io.Serializable{


    private static final long serialVersionUID = -7661342706577271417L;
    /**
     * h5 调用传递 sid
     */
    @NotBlank(message = "sid 不能为null")
    @Size(min = 5,max = 20,message = "sid 长度 必须在5-20之间")
    private String sid;

    /**
     * 盐值
     */
    @NotNull(message = "sign 不能为null")
    private String sign;

    /**
     * 渠道号
     */
    @NotNull(message = "cooperatorId 不能为null")
    private String cooperatorId;

    /**
     * 产品代码
     */
    @NotNull(message = "productCd 不能为null")
    private String productCd;

    public void setSid(@NotBlank String sid) {
        this.sid = sid;
    }

    public static void main(String[] args) {

        ValidateBean beanVO = new ValidateBean("20", null, null, "");
        String validate = ValidateUtil.validate(beanVO);
        log.info("1----{}-{}",validate, StringUtils.isBlank(validate));

//        BeanVO beanVOO = new BeanVO("12345", "66", "777", "888");
//        validate = ValidatorUtil.validate(beanVOO);
//        log.info("2----{}-{}",validate, StringUtils.isBlank(validate));
    }
}
