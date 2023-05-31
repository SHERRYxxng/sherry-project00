package sherry.dao;

import com.alibaba.druid.sql.visitor.functions.If;
import org.apache.ibatis.annotations.Param;
import sherry.base.BaseDao;
import sherry.entity.RolePermission;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/27 10:19
 **/
public interface  RolePermissionDao extends BaseDao<RolePermission> {

    void deleteByRoleId(Long roleId);

    List<Long> findPermissionIdListByRoleId(Long roleId);

    void deletePermissionIdsByRoleId(Long roleId);
    void RoleIdAndPermissionId(@Param("roleId") Long roleId,
                               @Param("permissionId") Long permissionId);

}
