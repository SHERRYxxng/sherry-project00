package sherry.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import sherry.base.BaseDao;
import sherry.entity.House;
import sherry.vo.HouseQueryVo;
import sherry.vo.HouseVo;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/23 19:25
 **/
public interface HouseDao extends BaseDao<House> {

    Page<HouseVo> findListPage(@Param("vo") HouseQueryVo houseQueryVo);
}