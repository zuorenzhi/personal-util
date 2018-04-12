package camelot.cookie;

import javax.servlet.http.HttpServletRequest;

public class LoginToken {

    public static String getUserToken(HttpServletRequest request) {
        String loginName = CookieHelper.getCookieVal(request, "USER_TOKEN");
        return getLoginToken(request, loginName);
    }


    /**
     * <p>Description: [获取用户名+IP+项目名组成key]</p>
     * Created on 2016年5月29日
     */
    public static String getLoginToken(HttpServletRequest request, String loginName) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(loginName);
        buffer.append("|");
        buffer.append(request.getRemoteHost());
        buffer.append("|");
        buffer.append("token.suffix");
        return buffer.toString();
    }

    /**
     * <p>Description: [获取cookie中的用户名+IP+项目名组成key，默认"USER_TOKEN"]</p>
     * Created on 2016年5月29日
     * @param request
     * @return
     * @author: 李厚兵
     */
    public static String getLoginUsernameAndAddr(HttpServletRequest request) {
        return CookieHelper.getCookieVal(request, "USER_TOKEN");
    }
}
