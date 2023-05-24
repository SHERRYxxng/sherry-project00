package sherry.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import sherry.entity.*;
import sherry.result.Result;
import sherry.service.*;
import sherry.vo.HouseQueryVo;
import sherry.vo.HouseVo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 18:10
 **/
@RestController
@RequestMapping(value="/house")
@SuppressWarnings({"unchecked", "rawtypes"})
public class HouseController {

    @Reference
    private HouseService houseService;

    @Reference
    private CommunityService communityService;
    @Reference
    private UserFollowService userFollowService;
    @Reference
    private HouseBrokerService houseBrokerService;

    @Reference
    private HouseImageService houseImageService;



    //查看房源详情
    @GetMapping("info/{id}")
    public Result info(@PathVariable Long id, HttpServletRequest request) {
        //调用HouseService中根据id查询房源的方法
        House house = houseService.getById(id);
        //根据房源中小区的id获取所在的小区
        Community community = communityService.getById(house.getCommunityId());
        //获取该房源的经纪人
        List<HouseBroker> houseBrokerList = houseBrokerService.findListByHouseId(id);
        //获取房源图片
        List<HouseImage> houseImage1List = houseImageService.findList(id, 1);

        Map<String, Object> map = new HashMap<>();
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseImage1List",houseImage1List);
        //从Session域中获取UserInfo对象
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("USER");
        Boolean isFollow = false;
        if(null != userInfo) {
            //证明已经登录，获取用户的id
            Long userId = userInfo.getId();
            //查询该用户是否已经关注该房源
            isFollow = userFollowService.isFollowed(userId, id);
        }
        //是否关注了该房源
        map.put("isFollow",isFollow);
        return Result.ok(map);
    }

    /**
     * 房源列表
     *
     * @return
     */
//获取房源
    @RequestMapping("/list/{pageNum}/{pageSize}")
    public Result findListPage(@RequestBody HouseQueryVo houseQueryVo ,
                               @PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize){
        //调用HouseService中前端展示房源数据的方法
        PageInfo<HouseVo> pageInfo = houseService.findListPage(pageNum,pageSize,houseQueryVo);
        return Result.ok(pageInfo);
    }

}