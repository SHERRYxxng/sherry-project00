package sherry.dao;

import sherry.base.BaseDao;
import sherry.entity.Admin;

import java.util.List;

/**
 * @ClassName:AdminDao
 * @Description:
 * @Author: SHERRY
 * @Version: 1.0
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/16 21:04
 **/
public interface AdminDao extends BaseDao<Admin> {
    List<Admin> findAll();

    Admin getAdminByUsername(String username);
}