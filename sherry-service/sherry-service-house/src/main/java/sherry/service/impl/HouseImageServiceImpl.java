package sherry.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import sherry.base.BaseDao;
import sherry.base.BaseServiceImpl;
import sherry.dao.HouseImageDao;
import sherry.entity.HouseImage;
import sherry.service.HouseBrokerService;
import sherry.service.HouseImageService;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 8:44
 **/
@Service(interfaceClass = HouseImageService.class)
public class HouseImageServiceImpl extends BaseServiceImpl<HouseImage> implements HouseImageService {

    @Autowired
    private HouseImageDao houseImageDao;


    @Override
    public List<HouseImage> findList(Long houseId, Integer type) {
        return houseImageDao.findHouseImagesByHouseIdAndType(houseId,type);
    }

    @Override
    protected BaseDao<HouseImage> getEntityDao() {
        return houseImageDao;
    }
}
