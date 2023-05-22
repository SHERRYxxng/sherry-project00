package sherry.service;

import sherry.base.BaseService;
import sherry.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/22 18:40
 **/
public interface DictService extends BaseService<Dict> {
    List<Map<String, Object>> findZnodes(Long id);
    //根据编码获取该节点下的所有子节点
    List<Dict> findListByParentId(Long parentId);

    List<Dict> findListByDictCode(String dictCode );

    String getNameById(Long houseTypeId);
}
