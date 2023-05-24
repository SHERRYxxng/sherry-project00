package sherry.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sherry.base.BaseController;
import sherry.entity.UserInfo;
import sherry.result.Result;
import sherry.service.UserFollowService;
import sherry.vo.UserFollowVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 20:19
 **/
@RestController
@RequestMapping(value="/userFollow")
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserFollowController extends BaseController {

    @Reference
    private UserFollowService userFollowService;

    /**
     * 关注房源
     * @param houseId
     * @param request
     * @return
     */
    @GetMapping("/auth/follow/{houseId}")
    public Result follow(@PathVariable("houseId") Long houseId, HttpServletRequest request){
        //获取Session域中用户
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("USER");
        Long userId = userInfo.getId();
        //调用UserFollowService中关注房源的方法
        userFollowService.follow(userId, houseId);
        return Result.ok();
    }

    //查询我的关注
    @GetMapping(value = "/auth/list/{pageNum}/{pageSize}")
    public Result findListPage(@PathVariable Integer pageNum,
                               @PathVariable Integer pageSize,
                               HttpServletRequest request) {
        //获取Session域中中的UserInfo对象
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("USER");
        Long userId = userInfo.getId();
        //调用UserFollowService中查询我的关注的方法
        PageInfo<UserFollowVo> pageInfo = userFollowService.findListPage(pageNum, pageSize, userId);
        return Result.ok(pageInfo);
    }

    @GetMapping("auth/cancelFollow/{id}")
    public Result cancelFollow(@PathVariable("id") Long id, HttpServletRequest request){
        userFollowService.cancelFollow(id);
        return Result.ok();
    }


}