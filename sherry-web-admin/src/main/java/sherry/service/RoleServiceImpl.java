package sherry.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sherry.dao.RoleDao;
import sherry.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:RoleServiceImpl
 * @Description:
 * @Author: SHERRY
 * @Version: 1.0
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/16 10:17
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> findAll() {
        return roleDao.findAll();
    }
    @Override
    public Integer insert(Role role) {
        return roleDao.insert(role);
    }
    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    public Integer update(Role role) {
        return roleDao.update(role);
    }
    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }
    /*
     * @Description 分页的实现
     * @Date  2023/5/16 
     * @Param [filters]
     * @return com.github.pagehelper.PageInfo<sherry.entity.Role>
     * @Author SHERRY       
     * @Version 1.0
     **/

}