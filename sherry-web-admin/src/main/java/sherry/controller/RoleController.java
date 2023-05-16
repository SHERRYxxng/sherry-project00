package sherry.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sherry.entity.Role;
import sherry.service.RoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
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
@RequestMapping(value="/role")
@SuppressWarnings({"unchecked", "rawtypes"}) //上述注解是jse提供的注解。作用是屏蔽一些无关紧要的警告。使开发者能看到一些他们真正关心的警告。从而提高开发者的效率
public class RoleController {

    @Autowired
    private RoleService roleService;


    private final static String PAGE_INDEX = "role/index";


    /**
     * 列表
     * @param model
     * @return
     */
    @RequestMapping
    public String index(ModelMap model) {
        List<Role> list = roleService.findAll();

        model.addAttribute("list", list);
        return PAGE_INDEX;
    }
    private final static String PAGE_CREATE = "role/create";

    @GetMapping("/create")
    public String create(ModelMap model) {
        return PAGE_CREATE;
    }
    @PostMapping("/save")
    public String save(Role role, HttpServletRequest request) {
        roleService.insert(role);
        return "common/successPage";
    }
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model,@PathVariable Long id) {
        Role role = roleService.getById(id);
        model.addAttribute("role",role);
        return "role/edit";
    }

    @PostMapping(value="/update")
    public String update(Role role) {
        roleService.update(role);
        return "common/successPage";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return "redirect:/role";
    }
    /*
     * @Description 分页的实现
     * @Date  2023/5/16 
     * @Param [model, request]
     * @return java.lang.String
     * @Author SHERRY       
     * @Version 1.0
     **/

}