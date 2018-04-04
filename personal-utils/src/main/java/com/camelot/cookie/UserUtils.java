package com.camelot.cookie;

/**
 * <p>Description: [用户工具类]</p>
 */
/*public class UserUtils {


    public static UserDTO getUserDTO(HttpServletRequest request) {
        String key = LoginToken.getUserToken(request);
        if (VerifyUtils.isEmpty(key)) {
            return null;
        }
        return getUserDTO(key);
    }

    public static UserDTO getUserDTO(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        RedisDB redisDB = SpringApplicationContextHolder.getBean("redisDB");
        if (redisDB.exists(key)) {
            Object obj = redisDB.getObject(key);
            if (obj instanceof UserDTO) {
                return (UserDTO) obj;
            }
        }
        return null;
    }

    public static void delRedisUser(String key) {
        RedisDB redisDB = SpringApplicationContextHolder.getBean("redisDB");
        redisDB.del(key);
    }

    public static void delRedisUser(HttpServletRequest request) {
        delRedisUser(LoginToken.getLoginUsernameAndAddr(request));
    }

}*/
