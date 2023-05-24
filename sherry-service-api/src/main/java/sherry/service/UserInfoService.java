package sherry.service;

import sherry.base.BaseService;
import sherry.entity.UserInfo;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 19:45
 **/
public interface  UserInfoService extends BaseService<UserInfo> {
    UserInfo getByPhone(String phone);

}
