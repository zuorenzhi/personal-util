package com.freemarker;

import com.camelot.VerifyUtils;
import com.common.constants.HtmlConstants;
import com.common.constants.ECMConstants;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

@Service("htmlBaseService")
public class HtmlBaseServiceImpl implements HtmlBaseService {
	/** 通用底部链接key */
//	private static final String KEY_FOOTER = "footers";
/*	@Resource
	private SolutionCategoryService solutionCategoryService;
	@Resource
	private ServiceAdviceService serviceAdviceService;
	@Resource
	private IndustryService industryService;
	@Resource
	private FooterService footerService;*/
	@Override
	public String toHtml(HtmlEntity htmlEntity, Map<String, Object> dynamicParameters){
		if ( !isHtmlEntityValid(htmlEntity) ) {
			throw new RuntimeException("参数不合法！");
		}
		Writer writer = null;
		File htmlFile = initHtmlFile(htmlEntity.getHtmlFilePath());
		try {
			// 初始化html文件
			// 初始化填充公共参数
			initCommonParam(dynamicParameters);
			
			Configuration configuration = new Configuration();
			configuration.setDirectoryForTemplateLoading(new File(HtmlConstants.TEMPLATE_ROOT_PATH));
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			configuration.setDefaultEncoding("UTF-8"); 
			
			Template template = configuration.getTemplate(htmlEntity.getTemplateName());
			writer = new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8");
			template.process(dynamicParameters, writer);
		} catch (Exception e) {
			throw new RuntimeException("静态化异常！", e);
		} finally {
			try {
				if ( writer!=null ) {
					writer.close();
				}
			} catch (IOException e) {
				
			}
		}
		return htmlFile.getAbsolutePath();
	}
	
	/**
	 * 
	 * <p>Discription:[初始化填充公共参数]</p>
	 * Created on 2016年11月25日
	 * @param dynamicParameters
	 * @author[hanshixiong]
	 */
	private void initCommonParam(Map<String, Object> dynamicParameters) {
		// 初始化头部解决方案分类子菜单
//		this.setHeaderSolutionCagegories(dynamicParameters);
		//初始化头部服务与咨询菜单
//		this.setHeaderServiceAdvices(dynamicParameters);
		// 初始化头部行业子菜单
//		this.setHeaderIndustry(dynamicParameters);
		// 初始化底部菜单
//		this.setFooters(dynamicParameters);
		// 放入图片服务地址
		dynamicParameters.put("imageServer", ECMConstants.IMAGE_SHOW_URL);
		//放入官网访问ip
		dynamicParameters.put("websiteServerIp", ECMConstants.CDRF_WEB_SERVER_IP);
	}
	/*private void setHeaderIndustry(Map<String, Object> dynamicParameters) {
		IndustryBean query = new IndustryBean();
		query.setStatus(Shelves.SHELVES.getCode());
		Pager<IndustryBean> page = new Pager<IndustryBean>();
		// 菜单中最多展示12个
		int showNumber = 12;
		page.setRows(showNumber);
		DataGrid<IndustryBean> industry = industryService.queryList(query, page);
		if ( industry.getRows()!=null ) {
			dynamicParameters.put(KEY_HEADER_INDUSTRIES, industry.getRows());
		} else {
			Log.error("####### 查询解决方案出错 #######");
			// 当查询解决方案出错时，填充空list保证程序可以继续运行
			dynamicParameters.put(KEY_HEADER_INDUSTRIES, new ArrayList<SolutionBean>());
		}
	}*/
	/**
	 * <p>Discription:[初始化头部解决方案分类子菜单]</p>
	 * Created on 2016年12月27日
	 * @param dynamicParameters
	 * @author:[左仁智]
	 */
	/*private void setHeaderSolutionCagegories(Map<String, Object> dynamicParameters) {
		SolutionCategoryBean query = new SolutionCategoryBean();
		// 仅查询已上架的解决方案
		query.setStatus(StatusEnums.Shelves.SHELVES.getCode());
		ExecuteResult<DataGrid<SolutionCategoryBean>> executeResult = solutionCategoryService.queryList(query , null);
		
		if ( executeResult.isSuccess() ) {
			dynamicParameters.put(KEY_HEADER_SOLUTION_CATEGORIES, executeResult.getResult().getRows());
		} else {
			Log.error("####### 查询解决方案分类出错 #######");
			// 当查询解决方案分类出错时，填充空list保证程序可以继续运行
			dynamicParameters.put(KEY_HEADER_SOLUTION_CATEGORIES, new ArrayList<SolutionCategoryBean>());
		}
	}*/
	/**
	 * <p>Discription:[初始化头部服务与咨询菜单]</p>
	 * Created on 2016年12月30日
	 * @param dynamicParameters
	 * @author:[左仁智]
	 */
	/*private void setHeaderServiceAdvices(Map<String, Object> dynamicParameters) {
		ServiceAdviceBean query = new ServiceAdviceBean();
		// 仅查询已上架的服务与咨询
		query.setStatus(StatusEnums.Shelves.SHELVES.getCode());
		Pager<ServiceAdviceBean> page = new Pager<ServiceAdviceBean>();
		// 菜单中最多展示12个
		int showNumber = 12;
		page.setRows(showNumber);
		ExecuteResult<DataGrid<ServiceAdviceBean>> executeResult = serviceAdviceService.queryList(query, page);
		
		if ( executeResult.isSuccess() ) {
			dynamicParameters.put(KEY_HEADER_SERVICE_ADVICES, executeResult.getResult().getRows());
		} else {
			Log.error("####### 查询服务与咨询出错 #######");
			// 当查询服务与咨询出错时，填充空list保证程序可以继续运行
			dynamicParameters.put(KEY_HEADER_SERVICE_ADVICES, new ArrayList<ServiceAdviceBean>());
		}
	}*/
	/**
	 * <p>Discription:[初始化底部链接]</p>
	 * Created on 2016年11月30日
	 * @param dynamicParameters
	 * @author:[左仁智]
	 */
	/*private void setFooters(Map<String, Object> dynamicParameters) {
		// 查询所有上架数据
		FooterBean footerBean = new FooterBean();
		footerBean.setStatus(StatusEnums.Shelves.SHELVES.getCode());
		ExecuteResult<DataGrid<FooterBean>> executeResult = footerService.queryList(footerBean, null);
		
		if ( executeResult.isSuccess() ) {
			dynamicParameters.put(KEY_FOOTER, executeResult.getResult().getRows());
		} else {
			Log.error("####### 查询底部链接出错 #######");
			// 当查询底部链接出错时，填充空list保证程序可以继续运行
			dynamicParameters.put(KEY_FOOTER, new ArrayList<FooterBean>());
		}
	}*/
	/**
	 * 
	 * <p>Discription:[初始化html文件，校验父目录是否存在，不存在时创建]</p>
	 * Created on 2016年11月25日
	 * @param htmlFilePath
	 * @return
	 * @author[hanshixiong]
	 */
	private File initHtmlFile(String htmlFilePath) {
		String htmlPath = new File(ECMConstants.HTML_ROOT_PATH + File.separator + htmlFilePath).getAbsolutePath();
		File htmlFile = new File(htmlPath);
		if ( !htmlFile.getParentFile().exists() ) {
			htmlFile.getParentFile().mkdirs();
		}
		return htmlFile;
	}
	
	/**
	 * 
	 * <p>Discription:[校验参数合法性]</p>
	 * Created on 2016年11月25日
	 * @param htmlEntity
	 * @return
	 * @author[hanshixiong]
	 */
	private boolean isHtmlEntityValid(HtmlEntity htmlEntity) {
		
		if ( htmlEntity == null ) {
			return false;
		} else if ( VerifyUtils.isBlank(htmlEntity.getHtmlFilePath()) ) {
			return false;
		} else if ( VerifyUtils.isBlank(htmlEntity.getTemplateName()) || !htmlEntity.getTemplateName().endsWith("ftl") ) {
			return false;
		}
		return true;
	}
	
}
