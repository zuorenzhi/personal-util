package com.utils.velocity;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.utils.DateUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

/**
 * <p>Description: [格式化前台页面date时间,使用:#fmtDate(date)或#fmtDate(date,pattern)]</p>
 * Created on 2017年2月10日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class DateVelocityUtils extends Directive {
	
	private static final VelocityEngine velocityEngine = new VelocityEngine(); 
	
	@Override
	public String getName() {
		return "fmtDate";
	}

	@Override
	public int getType() {
		return LINE;
	}
	
	
	/**
	 * 	@Override
	 * <p>Discription:[velocity日期格式化]</p>
	 * 修改内容:增加了根据格式化参数进行日期格式化
	 * Created on 2017年2月10日
	 * @param context
	 * @param writer
	 * @param node
	 * @return 
	 * @author:[左仁智]
	 */
	public boolean render(InternalContextAdapter context, Writer writer,
			Node node) throws IOException, ResourceNotFoundException,
			ParseErrorException, MethodInvocationException {
		String formatedDate = "";
		//设置默认 日期格式
		String formatPattern = DateUtils.YYYYMMDDHHMMSS;
		int length = node.jjtGetNumChildren();
		if(length == 1) {
			formatedDate = getDateStr(context,node,formatPattern);
		}else if(length == 2) {
			formatPattern = getDateFormatStr(context,node,formatPattern);
			formatedDate = getDateStr(context,node,formatPattern);
		}
		//渲染模板并输出到页面
		writer.write(renderTemplate(new HashMap<String,Object>(),formatedDate));
		return false;
	}

	/**
	 * <p>Discription:[获取参数中时间格式]</p>
	 * Created on 2017年2月10日
	 * @param context
	 * @param node
	 * @param formatPattern
	 * @return
	 * @throws MethodInvocationException
	 * @author:[左仁智]
	 */
	private String getDateFormatStr(InternalContextAdapter context, Node node,String formatPattern)
			throws MethodInvocationException {
		SimpleNode secondNode = (SimpleNode) node.jjtGetChild(1);//获取第二个参数:时间格式
		Object formatPatternObj = secondNode.value(context);
		if(null != formatPatternObj){
			formatPattern = (String) formatPatternObj;
		}
		return formatPattern;
	}

	/**
	 * <p>Discription:[获取参数中时间对象]</p>
	 * Created on 2017年2月10日
	 * @param context
	 * @param node
	 * @param formatPattern
	 * @return
	 * @throws MethodInvocationException
	 * @author:[左仁智]
	 */
	private String getDateStr(InternalContextAdapter context, Node node,String formatPattern)throws MethodInvocationException {
		Date date = null;
		SimpleNode firstNode = (SimpleNode) node.jjtGetChild(0);//获取第一个参数:日期
		Object dateObj = firstNode.value(context);
		if(null != dateObj){
			date = (Date)dateObj;
		}
		return DateUtils.dateToStr(formatPattern, date);
	}
	
	/**
	 * 渲染模板
	 * @param params 参数
	 * @param vimStr 页面模板代码
	 * @return 页面输出
	 */
	public static String renderTemplate(Map<String,Object> params,String vimStr){  
        VelocityContext context = new VelocityContext(params);  
        StringWriter writer = new StringWriter();  
        try {  
            velocityEngine.evaluate(context, writer, "", vimStr);  
        } catch (ParseErrorException e) {  
            e.printStackTrace();  
        } catch (MethodInvocationException e) {  
            e.printStackTrace();  
        } catch (ResourceNotFoundException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return writer.toString();  
    }  

}