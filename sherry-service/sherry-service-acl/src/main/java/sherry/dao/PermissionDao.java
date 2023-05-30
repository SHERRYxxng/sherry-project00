package sherry.dao;

import sherry.base.BaseDao;
import sherry.entity.Permission;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/27 10:18
 **/
public interface PermissionDao extends BaseDao<Permission> {

        List<Permission> findAll();
        List<Permission> findListByAdminId(Long adminId);

    List<String> getAllPermissionCodes();

    List<String> getPermissionCodesByAdminId(Long id);
}