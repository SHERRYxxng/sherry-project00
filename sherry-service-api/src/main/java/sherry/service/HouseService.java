package sherry.service;

import sherry.base.BaseService;
import sherry.entity.House;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/23 19:26
 **/
public interface HouseService extends BaseService<House> {
    void publish(Long id, Integer status);
}

