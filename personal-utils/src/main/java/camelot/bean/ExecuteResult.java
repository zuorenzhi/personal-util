package camelot.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: [执行结果信息类，用于封装增删改方法的结果信息,失败时必须将错误信息设置到errorMessages中]</p>
 * Created on 2016-3-15
 *
 * @author <a href="mailto: wuchaoqiang@clt.com">武超强</a>
 * @version 1.0
 *          Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */

@Setter
@Getter
public class ExecuteResult<T> implements Serializable {

    private static final long serialVersionUID = -1854616725284151074L;

    private T result;// 执行成功返回结果集
    private String resultMessage;// 执行成功结果信息
    private List<String> errorMessages = new ArrayList<String>();// 调用失败时，返回的错误集合

}
