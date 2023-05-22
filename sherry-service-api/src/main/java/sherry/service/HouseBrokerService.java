package sherry.service;

import sherry.base.BaseService;
import sherry.entity.HouseBroker;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 8:42
 **/
public interface HouseBrokerService extends BaseService<HouseBroker> {
    List<HouseBroker> findListByHouseId(Long id);
}
