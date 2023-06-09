package sherry.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sherry.base.BaseController;
import sherry.entity.Admin;
import sherry.service.AdminService;
import sherry.service.RoleService;
import sherry.util.QiniuUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName:AdminController
 * @Description:
 * @Author: SHERRY
 * @Version: 1.0
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/16 21:05
 **/
@Controller
@RequestMapping(value = "/admin")
@SuppressWarnings({"unchecked", "rawtypes"})
public class AdminController extends BaseController {
    @Reference
    private RoleService roleService;
    //注入密码加密器
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final static String PAGE_ASSGIN_SHOW = "admin/assignShow";
    private final static String LIST_ACTION = "redirect:/admin";
    private final static String PAGE_INDEX = "admin/index";
    private final static String PAGE_CREATE = "admin/create";
    private final static String PAGE_EDIT = "admin/edit";
    private final static String PAGE_SUCCESS = "common/successPage";
    private final static String PAGE_UPLOED_SHOW = "admin/upload";
    @Reference
    private AdminService adminService;

    /**
     * @return java.lang.String
     * @Description 去分配角色的页面
     * @Date 2023/5/27 10:20
     * @Param [model, adminId]
     * @Author SHERRY
     **/
    @GetMapping("/assignShow/{adminId}")
    public String assignShow(ModelMap model, @PathVariable Long adminId) {
        //将用户的id放到request域中
        model.addAttribute("adminId", adminId);

        //调用RoleService中根据用户id查询用户角色的方法
        Map<String, Object> roleMap = roleService.findRoleByAdminId(adminId);
        model.addAllAttributes(roleMap);

        return PAGE_ASSGIN_SHOW;
    }

    /**
     * @return java.lang.String
     * @Description
     * @Date 2023/5/27 8:52
     * @Param [adminId, roleIds]
     * @Author SHERRY
     **/
    @PostMapping("/assignRole")
    public String assignRole(Long adminId, Long[] roleIds) {
        roleService.saveUserRoleRealtionShip(adminId, roleIds);
        return PAGE_SUCCESS;
    }

    @GetMapping("/uploadShow/{id}")
    public String uploadShow(ModelMap model, @PathVariable Long id) {
        model.addAttribute("id", id);
        return PAGE_UPLOED_SHOW;
    }

    @PostMapping("/upload/{id}")
    public String upload(@PathVariable Long id, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws IOException {
        try {
            String newFileName = UUID.randomUUID().toString();
            // 上传图片
            QiniuUtils.upload2Qiniu(file.getBytes(),newFileName);
            String url= "http://rv5kih93z.hn-bkt.clouddn.com//"+ newFileName;
            Admin admin = new Admin();
            admin.setId(id);
            admin.setHeadUrl(url);
            adminService.update(admin);
            return PAGE_SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 列表
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<Admin> page = adminService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        return PAGE_INDEX;
    }

    /**
     * 进入新增页面
     *
     * @param model
     * @param admin
     * @return
     */
    @GetMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * 保存新增
     *
     * @param admin
     * @param request
     * @return
     */
    @PostMapping("/save")
    public String save(Admin admin) {
        //对Admin对象中的密码进行加密 获取提交的明文密码,再把密码进行加密后set到admin对象中的Password属性中
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        admin.setPassword(encoder.encode(admin.getPassword()));
        //设置默认头像
        admin.setHeadUrl("http://47.93.148.192:8080/group1/M00/03/F0/rBHu8mHqbpSAU0jVAAAgiJmKg0o148.jpg");
        adminService.insert(admin);

        return PAGE_SUCCESS;
    }

    /**
     * 进入编辑页面
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id) {
        Admin admin = adminService.getById(id);
        model.addAttribute("admin", admin);
        return PAGE_EDIT;
    }

    /**
     * 保存更新
     *
     * @param id
     * @param admin
     * @param request
     * @return
     */
    @PostMapping(value = "/update")
    public String update(Admin admin) {

        adminService.update(admin);

        return PAGE_SUCCESS;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminService.delete(id);
        return LIST_ACTION;
    }
}
