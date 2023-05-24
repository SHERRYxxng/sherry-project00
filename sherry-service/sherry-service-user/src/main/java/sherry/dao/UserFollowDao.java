package sherry.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import sherry.base.BaseDao;
import sherry.entity.UserFollow;
import sherry.vo.UserFollowVo;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 20:17
 **/
public interface UserFollowDao extends BaseDao<UserFollow> {
    Integer countByUserIdAndHouserId(@Param("userId")Long userId, @Param("houseId")Long houseId);

    Page<UserFollowVo> findListPage(@Param("userId")Long userId);
}