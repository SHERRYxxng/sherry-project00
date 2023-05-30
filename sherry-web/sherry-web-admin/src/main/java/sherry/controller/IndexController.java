package sherry.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sherry.entity.Admin;
import sherry.entity.Permission;
import sherry.service.AdminService;
import sherry.service.PermissionService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName:IndexController
 * @Description:
 * @Author: SHERRY
 * @Version: 1.0
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/16 10:45
 **/
@Controller
@SuppressWarnings({"unchecked", "rawtypes"})
public class IndexController {

    @Reference
    private AdminService adminService;

    @Reference
    private PermissionService permissionService;
    private final static String PAGE_INDEX = "frame/index";
    private final static String PAGE_MAIN = "frame/main";

    private final static String PAGE_LOGIN = "frame/login";

    /**
     * 框架首页
     *
     * @return
     */
    @GetMapping("/")
    public String index(ModelMap model) {
        //后续替换为当前登录用户id
        //Long adminId = 1L;
        //通过Spring Security获取User对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        //调用adminService中根据用户名获取Admin对象的方法
        Admin admin = adminService.getAdminByUsername(user.getUsername());
        List<Permission> permissionList = permissionService.findMenuPermissionByAdminId(admin.getId());
        model.addAttribute("admin", admin);
        model.addAttribute("permissionList",permissionList);
        return PAGE_INDEX;
    }

    /**
     * 框架主页
     *
     * @return
     */
    @GetMapping("/main")
    public String main() {

        return PAGE_MAIN;
    }
    /**
     * @Description 登录的时候请求login
     * @Date 2023/5/29 11:30
     * @Param
     * @return
     * @Author SHERRY
     **/
    @GetMapping("/login")
    public String login () throws Exception{
    return PAGE_LOGIN;
    }
//    /**
//     * @Description
//     * @Date 2023/5/29 18:53
//     * @Param [session]
//     * @return String(JSON)
//     * @Author SHERRY
//     **/
//    public String logout(HttpSession session) throws Exception{
//        //将Session失效
//        session.invalidate();
//        //重定向到登录的请求
//        return "redirect:/login";
//    }
}