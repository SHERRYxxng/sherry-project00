package sherry.dao;

import sherry.base.BaseDao;
import sherry.entity.Community;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/23 18:07
 **/
public interface CommunityDao extends BaseDao<Community> {
    List<Community> findAll();

}
