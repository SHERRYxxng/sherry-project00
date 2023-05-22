package sherry.dao;

import sherry.base.BaseDao;
import sherry.entity.HouseUser;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 8:48
 **/public interface  HouseUserDao extends BaseDao<HouseUser> {

    List<HouseUser> findHouseUsersByHouseId(Long houseId);
}