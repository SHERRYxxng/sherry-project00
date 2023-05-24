package sherry.service;

import com.github.pagehelper.PageInfo;
import sherry.base.BaseService;
import sherry.entity.UserFollow;
import sherry.vo.UserFollowVo;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 20:17
 **/
public interface UserFollowService extends BaseService<UserFollow> {

    PageInfo<UserFollowVo> findListPage(int pageNum, int pageSize, Long userId);
    /**
     * 用户是否关注房源
     * @param userId
     * @param houseId
     * @return
     */
    Boolean isFollowed(Long userId, Long houseId);
    /**
     * 关注房源
     * @param userId
     * @param houseId
     */
    void follow(Long userId, Long houseId);

    /**
     * 取消关注
     * @param id
     * @return
     */
    Boolean cancelFollow(Long id);
}
