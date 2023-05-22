package sherry.dao;

import sherry.base.BaseDao;
import sherry.entity.Dict;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/22 18:41
 **/

public interface DictDao extends BaseDao<Dict> {
    //根据父id获取该节点下所有的子节点
    List<Dict> findListByParentId(Long id);

    Integer countIsParent(Long id);

    Dict getByDictCode(String dictCode);

    String getNameById(Long areaId);
}
