package sherry.dao;

import org.apache.ibatis.annotations.Param;
import sherry.base.BaseDao;
import sherry.entity.HouseImage;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 8:47
 **/
public interface HouseImageDao extends BaseDao<HouseImage> {

    List<HouseImage> findHouseImagesByHouseIdAndType(@Param("houseId") Long houseId, @Param("type") Integer type);
}