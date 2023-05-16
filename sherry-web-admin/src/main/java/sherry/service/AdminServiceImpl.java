package sherry.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sherry.base.BaseDao;
import sherry.base.BaseServiceImpl;
import sherry.dao.AdminDao;
import sherry.entity.Admin;
import sherry.entity.Role;
import sherry.util.CastUtil;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:AdminServiceImpl
 * @Description:
 * @Author: SHERRY
 * @Version: 1.0
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/16 21:04
 **/

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    protected BaseDao<Admin> getEntityDao() {
        return adminDao;
    }
}
