package sherry.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import sherry.base.BaseDao;
import sherry.base.BaseServiceImpl;
import sherry.dao.DictDao;
import sherry.entity.Dict;
import sherry.service.DictService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/22 18:40
 **/
//发布服务，注意导包，指定发布的接口类

@Service(interfaceClass = DictService.class)
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {

    @Autowired
    private DictDao dictDao;

    @Override
    protected BaseDao<Dict> getEntityDao() {
        return dictDao;
    }

    /**
     * 查询数据库
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> findZnodes(Long id) {
        // 根据0，查询所有的1,根据1，找到10000
        List<Dict> list =  dictDao.findListByParentId(id);


        // 需要把数据库里面的数据变成树
//        [{ id:'011', name:'n1.n1', isParent:true}]
        List<Map<String, Object>> data = new ArrayList<>();
        for (Dict dict : list) {
            // 查询有多少个count
            Integer count =  dictDao.countIsParent(dict.getId());
            Map<String, Object> map = new HashMap<>();

            map.put("id",dict.getId());
            map.put("name",dict.getName());
            map.put("isParent",count>0?true:false);
            data.add(map);
        }
        return data;
    }

    @Override
    public List<Dict> findListByParentId(Long parentId) {
        return dictDao.findListByParentId(parentId);
    }

    @Override
    public List<Dict> findListByDictCode(String dictCode) {
        // 先根据北京，查询到dict
        Dict dict =  dictDao.getByDictCode(dictCode);
        if (null==dict)
            return null;
        return this.findListByParentId(dict.getId());
    }

    @Override
    public String getNameById(Long houseTypeId) {
        return "00000";
    }

}