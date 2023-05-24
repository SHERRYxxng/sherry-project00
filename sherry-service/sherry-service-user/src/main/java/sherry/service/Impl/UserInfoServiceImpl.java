package sherry.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sherry.base.BaseDao;
import sherry.base.BaseServiceImpl;
import sherry.dao.UserFollowDao;
import sherry.dao.UserInfoDao;
import sherry.entity.UserInfo;
import sherry.service.UserInfoService;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 19:46
 **/

@Transactional
@Service(interfaceClass = UserInfoService.class)
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;



    @Override
    protected BaseDao<UserInfo> getEntityDao() {
        return userInfoDao;
    }


    @Override
    public UserInfo getByPhone(String phone) {
        UserInfo byPhone = userInfoDao.getByPhone(phone);

        return byPhone;
    }
}