package sherry.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sherry.result.Result;
import sherry.result.ResultCodeEnum;
import sherry.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 20:21
 **/
/**
 * 前端登录拦截器
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class LoginInterceptor implements HandlerInterceptor {

    // 执行Controller，返回视图之后
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
            throws Exception {

    }
    // // 执行Controller，返回视图之前
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object object, ModelAndView model) throws Exception {

    }
    // //  执行Controller 之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object object) throws Exception {
        Object userInfo = request.getSession().getAttribute("USER");
        if(null == userInfo) {
            Result result = Result.build("未登陆", ResultCodeEnum.LOGIN_AUTH);
            WebUtil.writeJSON(response, result);
            return false;
        }
        return true;
    }

}