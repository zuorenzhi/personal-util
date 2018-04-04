package com.camelot.cookie;

/*@Controller
public class LoginController {
	private static final Logger LOG= Log.get(LoginController.class);
	private static final String LOGIN_PATTERN = "login.pattern";
	@Resource
	private UserService userService;
	
	@Resource
	private RedisDB redisDB;

	@Resource
	private AccountService accountService;
	
	
	*//**
	 * <p>Description: [登陆入口]</p>
	 * Created on 2016年5月26日
	 * @param req
	 * @param rep
	 * @return
	 * @author: 李厚兵
	 *//*
	@RequestMapping("/login")
	public String index(HttpServletRequest req, HttpServletResponse rep, String username, String password) {
		String loginUrl = "login";
		if (VerifyUtils.isEmpty(username)) {
			req.setAttribute("errorMsg", "用户名不能为空");
			return loginUrl;
		}
		if (VerifyUtils.isEmpty(password)) {
			req.setAttribute("errorMsg", "密码不能为空");
			return loginUrl;
		}
		*//* 切换登录方式 *//*
		ExecuteResult<UserDTO> result = null;
		if(SysProperties.getProperty(LOGIN_PATTERN).equals("ldap")){
			result = this.ldapLogin(password, username); // ldap登陆
		}else if(SysProperties.getProperty(LOGIN_PATTERN).equals("local")){
			result = this.devLogin(password, username); // 开发登录
		}
//		ExecuteResult<UserDTO> result = this.devLogin(password, username); // 开发登录
//		ExecuteResult<UserDTO> result = this.ldapLogin(password, username); // ldap登陆
		
		if (result.isSuccess() && result.getResult() != null) {
			// 冻结用户不允许登陆
			if (result.getResult().getStatus() == 1) {
				req.setAttribute("errorMsg", "该账号已被冻结，请联系管理员！");
				return loginUrl;
			}
			// 冻结用户不允许登陆
			if (result.getResult().getStatus() == 2) {
				req.setAttribute("errorMsg", "该账号因结转被冻结，详情请联系管理员！");
				return loginUrl;
			}
			try {
				UserDTO userDTO = result.getResult();
				String key = LoginToken.getLoginToken(req, username);
				CookieHelper.setCookie(rep, Constants.USER_TOKEN, username);
				if (userDTO.getUserType().equals(UserType.DIDI_SELLER.name())) {
					// 滴滴商家用户将手机号和邮箱设置成负责人的
					ExecuteResult<UserDTO> sellerLinkMan = this.userService.querySellerLinkman(userDTO.getId());
					if (sellerLinkMan.isSuccess()) {
						userDTO.setMobile(sellerLinkMan.getResult().getMobile());
						userDTO.setEmail(sellerLinkMan.getResult().getEmail());
					}
				}
				if (userDTO.getUserType().equals(UserType.DIDI_DEPT.name())) {
					// 滴滴部门用户将手机号和邮箱设置成负责人的
					ExecuteResult<UserDTO> deptUserLinkMan = this.userService.queryDeptUserLinkman(userDTO.getId());
					if (deptUserLinkMan.isSuccess()) {
						userDTO.setMobile(deptUserLinkMan.getResult().getMobile());
						userDTO.setEmail(deptUserLinkMan.getResult().getEmail());
					}
				}
				redisDB.addObject(key, userDTO, 30*60);
			} catch (Exception e) {
				Log.error(LOG, "登录异常", e);
			}
			return "redirect:/";

		} else {
			req.setAttribute("errorMsg", "登陆失败，账号或密码错误！");
			return loginUrl;
		}

	}
	*//**
	 * 
	 * <p>Discription:[开发登录]</p>
	 * Created on 2016年8月24日
	 * @param password
	 * @param username
	 * @return
	 * @author:[hanshixiong]
	 *//*
	private ExecuteResult<UserDTO> devLogin(String password,String username) {
		password = PasswordUtil.getMd5String(password).toUpperCase();
		ExecuteResult<UserDTO> result = userService.login(username, password);
		return result;
	}
	*//**
	 * 
	 * <p>Discription:[生产环境ldap登录]</p>
	 * Created on 2016年8月24日
	 * @param password
	 * @param username
	 * @return
	 * @author:[hanshixiong]
	 *//*
	private ExecuteResult<UserDTO> ldapLogin(String password,String username) {
		ExecuteResult<UserDTO> result = userService.ldapLogin(username, password);
		if (result==null||result.getResult()==null || !result.isSuccess()) {
			password = PasswordUtil.getMd5String(password).toUpperCase();
			result = userService.login(username, password);
		}
		if(result.isSuccess()){
			ExecuteResult<AccountDTO> account = accountService.queryMoneyById(result.getResult().getId());
			if(account==null||!account.isSuccess()||account.getResult()==null){
				AccountDTO addAccount = new AccountDTO();
				addAccount.setUserId(result.getResult().getId());
				addAccount.setCreateDt(new Date());
				addAccount.setIsFrozen(false);
				addAccount.setType(AccountEnums.Type.WALLET.getCode());
				accountService.addAccount(addAccount);
			}
		}
		return result;
	}

	@RequestMapping("/toLogin")
	public String login() {
		return "/login";
	}
	
	
	*//**
	 * <p>Description: [注销]</p>
	 * Created on 2016年5月27日
	 * @param response
	 * @param request
	 * @return
	 * @author: 李厚兵
	 *//*
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response, HttpServletRequest request) {
		// 清除redis值
		UserUtils.delRedisUser(request);
		this.redisDB.del(LoginToken.getUserToken(request));
		// 清除cookie值
		CookieHelper.delCookie(request, response, Constants.USER_TOKEN);
		return "redirect:/toLogin";
	}
}*/
