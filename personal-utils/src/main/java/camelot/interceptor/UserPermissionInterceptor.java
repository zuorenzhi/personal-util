/*
package camelot.interceptor;

import camelot.didipay.pc.service.util.LoginToken;
import camelot.didipay.pc.service.util.RedisDB;
import camelot.didipay.pc.utils.UserUtils;
import camelot.didipay.usercenter.export.dto.UserDTO;
import camelot.didipay.usercenter.export.enums.UserType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * 
 * <p>Description: [员工权限校验]</p>
 * Created on 2016年8月11日
 * @author  <a href="mailto: hanshixiong@clt.com">hanshixiong</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 *//*

public class UserPermissionInterceptor  implements HandlerInterceptor {
	@Resource
	private RedisDB redisDB;
	@Override
	public boolean preHandle(HttpServletRequest rq, HttpServletResponse rs, Object handler) throws Exception {
		String key = LoginToken.getUserToken(rq);
		UserDTO userDTO = UserUtils.getUserDTO(key);
		// 未登陆
		if (userDTO == null) {
			// 登录超时直接注销
			rs.sendRedirect(rq.getContextPath() + "/logout");
			return false;
		}
		// 普通用户禁止访问这些地址
		if (userDTO.getUserType().equals(UserType.DIDI_USER.name())) {
			rs.sendRedirect("http://img6.didistatic.com/static/tms/shield/z/404/pc/index.html");
			return false;
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
*/
