package sherry.dao;

import com.github.pagehelper.Page;
import sherry.base.BaseDao;
import sherry.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:RoleDao
 * @Description:
 * @Author: SHERRY
 * @Version: 1.0
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/16 10:15
 **/
public interface RoleDao extends BaseDao<Role> {
    List<Role> findAll();

    Integer insert(Role role);

    Role getById(Long id);

    Integer update(Role role);

    void delete(Long id);

    Page<Role> findPage(Map<String, Object> filters);
}
