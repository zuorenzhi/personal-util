package com.utils.velocity;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

import camelot.bean.Pager;

/**
 * <p>Description: [分页模板标签]</p>
 * Created on 2017年4月16日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class PageVelocityUtils extends Directive {
	
	private static final VelocityEngine velocityEngine = new VelocityEngine(); 
	
	@Override
	public String getName() {
		return "page";
	}

	@Override
	public int getType() {
		return LINE;
	}
	
	
	@Override
	public boolean render(InternalContextAdapter context, Writer writer,
			Node node) throws IOException, ResourceNotFoundException,
			ParseErrorException, MethodInvocationException {
		//获取分页参数
		SimpleNode sn_region = (SimpleNode) node.jjtGetChild(0);
		SimpleNode sn_region1 = (SimpleNode) node.jjtGetChild(1);
		Object pagerObj = sn_region.value(context);
		Object formNameObj = sn_region1.value(context);
		//获取页面跳转方法
		String page_method = "topage";
		Pager pager = (Pager)pagerObj;
		String formName = formNameObj==null?null:(String)formNameObj;
		Map map = new HashMap();
		map.put("pager", pager);
		map.put("page_method", page_method);
		//渲染模板并输出到页面
		writer.write(renderTemplate(map,pageTemp(pager,page_method,formName)));
		return false;
	}
	
	/**
	 * 渲染模板
	 * @param params 参数
	 * @param vimStr 页面模板代码
	 * @return 页面输出
	 */
	public static String renderTemplate(Map params,String vimStr){  
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
	
	/**
	 * 获取分页页面代码模板 html + js
	 * @param pager 分页实体
	 * @return 模板代码
	 */
	private String pageTemp(Pager pager,String page_method,String formName){
		int pageStart = pager.getPageOffset()+1;
		int pageEnd = pager.getPageOffset()+pager.getRows();
		int page = pager.getPage();
		StringBuffer pageHtml = new StringBuffer();
		pageHtml.append("<div class=\"dataTables_wrapper no-footer clearfix\">");
		pageHtml.append("<div class=\"dataTables_paginate paging_simple_numbers\" id=\"DataTables_Table_2_paginate\">");
		pageHtml.append("<a href=\"javascript:" + page_method + "(1)\" class=\"paginate_button previous disabled\">首页</a><span>");
		pageHtml.append("<a href=\"javascript:" + page_method + "($pager.previewPage)\" class=\"paginate_button previous disabled\">上一页</a><span>");
		pageHtml.append("#foreach($pageNo in [$!pager.startPageIndex .. $!pager.endPageIndex])");
		pageHtml.append("#if($pager.totalPage>6&&($pageNo>=$pager.page - 2||$pageNo<=$pager.page + 2))");
		pageHtml.append("<a href=\"javascript:" + page_method + "($pageNo)\" class=\"paginate_button  #if($pageNo==$pager.page)current#end\">$pageNo</a></span>");
		pageHtml.append("#else");
		pageHtml.append("<a href=\"javascript:" + page_method + "($pageNo)\" class=\"paginate_button  #if($pageNo==$pager.page)current#end\">$pageNo</a></span>");
		pageHtml.append("#end");
		pageHtml.append("#end");
		pageHtml.append("<a href=\"javascript:" + page_method + "($pager.nextPage)\" class=\"paginate_button next disabled\">下一页</a>");
		pageHtml.append("<a href=\"javascript:" + page_method + "($pager.totalPage)\" class=\"paginate_button next disabled\">末页</a>");
		pageHtml.append("</div>");
		pageHtml.append(" <div class=\"r mt-15 mr-10\">显示 "+pageStart+" 到 "+pageEnd+" ，共 $pager.totalCount 条</div>");
		pageHtml.append("</div>");
		pageHtml.append("<script>function page_num(){var pageNumVal=$(\"#pageNum\").val();var reg = new RegExp(\"^[0-9]*$\");if(pageNumVal == '' || !reg.file2Bean(pageNumVal)){"+page_method+"(1);$(\"#pageNum\").val(1)}else if(parseInt(pageNumVal)>=parseInt($pager.totalPage)){"+page_method+"($pager.totalPage);}else{"+page_method+"(pageNumVal)}}");
		pageHtml.append("function enter_press_page(e){if(e == 13|| e == 32){page_num()}}");
		pageHtml.append("function topage(page) { $(\"input[name='page']\").val(page);");
		pageHtml.append("$(\"#"+formName+"\").submit();};");
		pageHtml.append("$(function () {$(\"#"+formName+"\").append('<input type=\"hidden\" name=\"page\" value=\""+page+"\">');})");
		pageHtml.append("</script>");
		return pageHtml.toString();
	}

}