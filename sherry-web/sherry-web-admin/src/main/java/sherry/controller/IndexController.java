package sherry.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import sherry.entity.Admin;
import sherry.entity.Permission;
import sherry.service.AdminService;
import sherry.service.PermissionService;

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

    /**
     * 框架首页
     *
     * @return
     */
    @GetMapping("/")
    public String index(ModelMap model) {
        //后续替换为当前登录用户id
        Long adminId = 1L;
        Admin admin = adminService.getById(adminId);
        List<Permission> permissionList = permissionService.findMenuPermissionByAdminId(adminId);
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
}