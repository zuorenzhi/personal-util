package camelot.bean;


import com.alibaba.fastjson.JSON;

/**
 * Description: AjaxInfo 工具类 <br/>
 * Created on: 2016/11/18 17:49 <br/>
 *
 * @author: <a href="mailto: liruifeng@clt.com">李瑞丰</a><br/>
 * @version: 1.0 <br/>
 * Copyright (c) 2016年 北京柯莱特科技有限公司 交付部
 */
public class AjaxInfoUtils {
    public static final Integer CODE_SUCESS = 0;
    public static final String MSG_SUCESS = "操作成功！";
    public static final Integer CODE_FAILURE = 1;
    public static final String MSG_FAILURE = "操作失败！";

    /**
     * Discription: 返回操作成功ajaxinfo
     * Created on: 2016/11/18 18:13
     * @return:  AjaxInfo
     * @author: <a href="mailto: liruifeng@clt.com">李瑞丰</a>
     */
    public static AjaxInfo success() {
        return success(null);
    }

    /**
     * Discription: 返回操作成功ajaxinfo
     * Created on: 2016/11/18 18:02
     *
     * @param: data 返回的数据
     * @return: AjaxInfo
     * @author: <a href="mailto: liruifeng@clt.com">李瑞丰</a>
     */
    public static AjaxInfo success(Object data) {
        return success(MSG_SUCESS, data);
    }

    /**
     * Discription: 返回操作成功ajaxinfo
     * Created on: 2016/11/18 18:02
     *
     * @param: data 返回的数据 msg 提示信息
     * @return: AjaxInfo
     * @author: <a href="mailto: liruifeng@clt.com">李瑞丰</a>
     */
    public static AjaxInfo success(String msg, Object data) {
        return ajaxInfo(CODE_SUCESS, msg, data);
    }

    /**
     * Discription: 返回操作失败ajaxinfo
     * Created on: 2016/11/18 18:02
     *
     * @return: AjaxInfo
     * @author: <a href="mailto: liruifeng@clt.com">李瑞丰</a>
     */
    public static AjaxInfo failure() {
        return failure(null);
    }

    /**
     * Discription: 返回操作失败ajaxinfo
     * Created on: 2016/11/18 18:02
     *
     * @param: data 返回的数据
     * @return: AjaxInfo
     * @author: <a href="mailto: liruifeng@clt.com">李瑞丰</a>
     */
    public static AjaxInfo failure(Object data) {
        return failure(MSG_FAILURE, data);
    }

    /**
     * Discription: 返回操作失败ajaxinfo
     * Created on: 2016/11/18 18:02
     *
     * @param: data 返回的数据  msg 提示
     * @return: AjaxInfo
     * @author: <a href="mailto: liruifeng@clt.com">李瑞丰</a>
     */
    public static AjaxInfo failure(String msg, Object data) {
        return ajaxInfo(CODE_FAILURE, msg, data);
    }

    /**
     * Discription: 返回ajaxinfo
     * Created on: 2016/11/18 18:02
     * @param: data 返回的数据  msg 提示 code 状态码
     * @return: AjaxInfo
     * @author: <a href="mailto: liruifeng@clt.com">李瑞丰</a>
     */
    public static AjaxInfo ajaxInfo(Integer code, String msg, Object data) {
        return new AjaxInfo(code,msg,data);
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(AjaxInfoUtils.success("haha","nice")));
        System.out.println(JSON.toJSONString(AjaxInfoUtils.ajaxInfo(44,"haha","nice")));
    }
}
