package sherry.service;

import com.github.pagehelper.PageInfo;
import sherry.base.BaseService;
import sherry.entity.House;
import sherry.vo.HouseQueryVo;
import sherry.vo.HouseVo;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/23 19:26
 **/
public interface HouseService extends BaseService<House> {
    void publish(Long id, Integer status);
    PageInfo<HouseVo> findListPage(int pageNum, int pageSize, HouseQueryVo houseQueryVo);
}

