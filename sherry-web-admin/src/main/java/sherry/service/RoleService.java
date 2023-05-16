package sherry.service;

import com.github.pagehelper.PageInfo;
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
public interface RoleService {
    List<Role> findAll();
    Integer insert(Role role);
    Role getById(Long id);

    Integer update(Role role);

    void delete(Long id);

}
