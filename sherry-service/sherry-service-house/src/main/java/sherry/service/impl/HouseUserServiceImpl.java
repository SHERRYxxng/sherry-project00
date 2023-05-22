package sherry.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import sherry.base.BaseDao;
import sherry.base.BaseServiceImpl;
import sherry.dao.HouseUserDao;
import sherry.entity.HouseUser;
import sherry.service.HouseUserService;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 8:44
 **/
@Service(interfaceClass = HouseUserService.class)
public class HouseUserServiceImpl extends BaseServiceImpl<HouseUser> implements HouseUserService {

    @Autowired
    private HouseUserDao houseUserDao;

    @Override
    public List<HouseUser> findListByHouseId(Long houseId) {
        return houseUserDao.findHouseUsersByHouseId(houseId);
    }


    @Override
    protected BaseDao<HouseUser> getEntityDao() {
        return houseUserDao;
    }
}