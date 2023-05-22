package sherry.service;

import sherry.base.BaseService;
import sherry.entity.Admin;

import java.util.List;

/**
 * @ClassName:AdminService
 * @Description:
 * @Author: SHERRY
 * @Version: 1.0
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/16 21:04
 **/
public interface AdminService extends BaseService<Admin> {
    List<Admin> findAll();
}
