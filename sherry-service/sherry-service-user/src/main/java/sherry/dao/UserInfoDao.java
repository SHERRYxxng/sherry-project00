package sherry.dao;

import sherry.base.BaseDao;
import sherry.entity.UserInfo;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 19:45
 **/
public interface  UserInfoDao extends BaseDao<UserInfo> {
    UserInfo getByPhone(String phone);
}
