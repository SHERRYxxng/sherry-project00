package sherry.service;

import sherry.base.BaseService;
import sherry.entity.Permission;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/27 10:16
 **/
public interface PermissionService extends BaseService<Permission> {

    /**
     * 菜单全部数据
     * @return
     */
    List<Permission> findAllMenu();


    /**
     * 获取用户菜单权限
     *
     * @param adminId
     * @return
     */
    List<Permission> findMenuPermissionByAdminId(Long adminId);

    /**
     * 根据角色获取授权权限数据
     *
     * @return
     */
    List<Map<String, Object>> findPermissionByRoleId(Long roleId);

    /**
     * 保存角色权限
     *
     * @param roleId
     * @param permissionIds
     */
    void saveRolePermissionRealtionShip(Long roleId, Long[] permissionIds);

}
