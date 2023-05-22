package sherry.service;

import sherry.base.BaseService;
import sherry.entity.HouseImage;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 8:43
 **/
public interface HouseImageService extends BaseService<HouseImage> {

    List<HouseImage> findList(Long houseId, Integer type);
}