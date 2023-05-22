package sherry.service;

import sherry.base.BaseService;
import sherry.entity.HouseUser;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 8:44
 **/
public interface HouseUserService extends BaseService<HouseUser> {

    List<HouseUser> findListByHouseId(Long houseId);
}