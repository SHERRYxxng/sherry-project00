package sherry.service;

import sherry.base.BaseService;
import sherry.entity.Community;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/23 18:08
 **/
public interface CommunityService  extends BaseService<Community> {
    List<Community> findAll();

}
