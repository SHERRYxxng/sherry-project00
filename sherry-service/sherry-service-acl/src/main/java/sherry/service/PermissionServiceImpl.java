package sherry.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sherry.base.BaseDao;
import sherry.base.BaseServiceImpl;
import sherry.dao.PermissionDao;
import sherry.dao.RolePermissionDao;
import sherry.entity.Permission;
import sherry.entity.RolePermission;
import sherry.helper.PermissionHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/27 10:17
 **/
@Transactional
@Service(interfaceClass = PermissionService.class)
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public void assignPermission(Long roleId, Long[] permissionIds) {
        //调用RolePermissionDao中根据角色id删除已分配权限的方法
        rolePermissionDao.deletePermissionIdsByRoleId(roleId);
        //遍历所有的权限id
        for (Long permissionId : permissionIds) {
            //调用RolePermissionDao中保存权限id和角色id的方法
            if(permissionId !=null){
                //调用RolePermissionDao中保存权限id和角色id的方法
                    rolePermissionDao.RoleIdAndPermissionId(roleId,permissionId);
            }
        }
    }

    @Override
    public List<String> getPermissionCodesByAdminId(Long id) {
        List<String> permissionCodes=null;
        if(id==1){
            //证明是系统管理员,就走if
            permissionCodes=permissionDao.getAllPermissionCodes();
        }else{
            //根据用户id查询权限
            permissionCodes = permissionDao.getPermissionCodesByAdminId(id);

        }
        return permissionCodes;
    }

    @Override
    protected BaseDao<Permission> getEntityDao() {
        return permissionDao;
    }


    @Override
    public List<Map<String,Object>> findPermissionByRoleId(Long roleId) {
        //全部权限列表
        List<Permission> permissionList = permissionDao.findAll();

        //获取角色已分配的权限数据
        List<Long> permissionIdList = rolePermissionDao.findPermissionIdListByRoleId(roleId);

        //构建ztree数据
        //参考文档：http://www.treejs.cn/v3/demo.php#_201
        // { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
        List<Map<String,Object>> zNodes = new ArrayList<>();
        for(Permission permission : permissionList) {
            Map<String,Object> map = new HashMap<>();
            map.put("id", permission.getId());
            map.put("pId", permission.getParentId());
            map.put("name", permission.getName());
            if(permissionIdList.contains(permission.getId())) {
                map.put("checked", true);
            }
            zNodes.add(map);
        };
        return zNodes;
    }

    /**
     * 保存角色权限
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void saveRolePermissionRealtionShip(Long roleId, Long[] permissionIds) {
        //清空
        rolePermissionDao.deleteByRoleId(roleId);
        //循环获取到所有勾选的
        for(Long permissionId : permissionIds) {
            if(StringUtils.isEmpty(permissionId.toString())) continue;
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionDao.insert(rolePermission);
        }
    }
    public List<Permission> findMenuPermissionByAdminId(Long adminId) {
        List<Permission> permissionList = null;
        //admin账号id为：1
        if(adminId.longValue() == 1) {
            //如果是超级管理员，获取所有菜单
            permissionList = permissionDao.findAll();
        } else {
            permissionList = permissionDao.findListByAdminId(adminId);
        }
        //把权限数据构建成树形结构数据
        List<Permission> result = PermissionHelper.bulid(permissionList);
        return result;
    }
    @Override
    public List<Permission> findAllMenu() {
        //全部权限列表
        List<Permission> permissionList = permissionDao.findAll();
        if(CollectionUtils.isEmpty(permissionList)) return null;

        //构建树形数据,总共三级
        //把权限数据构建成树形结构数据
        List<Permission> result = PermissionHelper.bulid(permissionList);
        return result;
    }

}
