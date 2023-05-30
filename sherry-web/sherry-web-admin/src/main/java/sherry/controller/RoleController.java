package sherry.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sherry.base.BaseController;
import sherry.entity.Role;
import sherry.service.PermissionService;
import sherry.service.RoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:RoleController
 * @Description:
 * @Author: SHERRY
 * @Version: 1.0
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/16 10:17
 **/

@Controller
@RequestMapping(value = "/role")
@SuppressWarnings({"unchecked", "rawtypes"})
//上述注解是jse提供的注解。作用是屏蔽一些无关紧要的警告。使开发者能看到一些他们真正关心的警告。从而提高开发者的效率
public class RoleController extends BaseController {

    private final static String PAGE_INDEX = "role/index";
    private final static String LIST_ACTION = "redirect:/role";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/successPage";

    private final static String PAGE_ASSGIN_SHOW = "role/assginShow";

    @Reference
    private PermissionService permissionService;

    //    @Autowired 改为了Reference
    @Reference
    private RoleService roleService;

    /**
     * 列表
     *
     * @param model
     * @return
     */

    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<Role> page = roleService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        return PAGE_INDEX;
    }

    @RequestMapping("/create")
    public String create(ModelMap model) {
        return PAGE_CREATE;
    }

    @RequestMapping("/save")
    public String save(Role role) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    @RequestMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id) {
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        return PAGE_EDIT;
    }

    @RequestMapping(value = "/update")
    public String update(Role role) {

        roleService.update(role);
        return PAGE_SUCCESS;
    }

    @PreAuthorize("hasAnyAuthority('Delete')")//此时只有Delete权限的时候才能调用该方法,系统管理员也没有这个权限,
    // 因为系统管理员拿到的权限为Role.Delete这个必须要一致才有权限,会报403没有权限拒接了
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return LIST_ACTION;
    }

    /**
     * 进入分配权限页面
     *
     * @param roleId
     * @return
     */
    @GetMapping("/assignShow/{roleId}")
    public String assignShow(ModelMap model, @PathVariable Long roleId) {
        List<Map<String, Object>> zNodes = permissionService.findPermissionByRoleId(roleId);
        model.addAttribute("zNodes", zNodes);
        model.addAttribute("roleId", roleId);
        return PAGE_ASSGIN_SHOW;
    }

    /**
     * 给角色分配权限
     * @RequestParam 可以将表单请求里面的参数都放到传参里面
     * @param roleId
     * @param permissionIds
     * @return
     */
    @PostMapping("/assignPermission")
    public String assignPermission(@RequestParam("roleId") Long roleId, Long[] permissionIds) {
        //调用PermissionService中分配权限的方法
        permissionService.saveRolePermissionRealtionShip(roleId, permissionIds);
        return PAGE_SUCCESS;
    }

}