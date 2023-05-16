package sherry.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sherry.base.BaseController;
import sherry.entity.Role;
import sherry.service.RoleService;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
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

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return LIST_ACTION;
    }
}