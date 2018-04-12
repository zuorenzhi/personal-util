package com.utils.context;

/**
 * @see http://blog.csdn.net/snannan_268/article/details/5511614
 */
import org.junit.Test;

/**
 * <p>Description: [获取类路径/main与servlet不同]</p>
 * Created on 2017年3月14日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class ContextPathUtils {

//	/D:/Workspaces-eclipse/workspace-ishugui/com-camelot-util/target/classes/
//	/D:/Workspaces-eclipse/workspace-ishugui/com-camelot-util/target/classes/template
//	/D:/Workspaces-eclipse/workspace-ishugui/com-camelot-util/target/classes/com/test/common/classloader/
//	/D:/Workspaces-eclipse/workspace-ishugui/com-camelot-util/target/classes/
//	/D:/Workspaces-eclipse/workspace-ishugui/com-camelot-util/target/classes/spring
	@Test
	public void getBeanPath(){
		System.out.println(ContextPathUtils.class.getClassLoader().getResource("").getPath());
		System.out.println(ContextPathUtils.class.getClassLoader().getResource("").getPath()+"template");
		//推荐
		System.out.println(ContextPathUtils.class.getResource("").getPath());
		System.out.println(ContextPathUtils.class.getResource("/").getPath());
		System.out.println(ContextPathUtils.class.getResource("/spring").getPath());
	}
}
