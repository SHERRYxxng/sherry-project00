package sherry.service;

import com.github.pagehelper.PageInfo;
import sherry.base.BaseService;
import sherry.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:RoleService
 * @Description:这里面是主页的角色管理模块的功能
 * @Author: SHERRY
 * @Version: 1.0
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/16 10:17
 **/
public interface RoleService extends BaseService<Role> {
    List<Role> findAll();
    Integer insert(Role role);

    Role getById(Long id);

    Integer update(Role role);

    void delete(Long id);

    PageInfo<Role> findPage(Map<String, Object> filters);

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Description 根据用户id获取用户角色
     * @Date 2023/5/26 2023年5月26日20:02:44
     * @Param [adminId]
     * @Author SHERRY
     **/
    Map<String, Object> findRoleByAdminId(Long adminId);

    /**
     * @return void
     * @Description 分配角色id
     * @Date 2023/5/26 2023年5月26日20:02:57
     * @Param [adminId, roleIds]
     * @Author SHERRY
     **/
    void saveUserRoleRealtionShip(Long adminId, Long[] roleIds);
}

