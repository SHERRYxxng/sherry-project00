package sherry.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sherry.base.BaseDao;
import sherry.base.BaseServiceImpl;
import sherry.dao.DictDao;
import sherry.dao.HouseDao;
import sherry.entity.House;
import sherry.service.DictService;
import sherry.service.HouseService;
import sherry.vo.HouseQueryVo;
import sherry.vo.HouseVo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/23 19:26
 **/
@Transactional
@Service(interfaceClass = HouseService.class)
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {

    @Autowired
    private HouseDao houseDao;
    @Autowired
    private DictDao dictDao;
    @Autowired
    private DictService dictService;
    @Override
    protected BaseDao<House> getEntityDao() {
        return houseDao;
    }

    @Override
    public void publish(Long id, Integer status) {
        House house = new House();
        house.setId(id);
        house.setStatus(status);
        houseDao.update(house);
    }
    @Override
    public House getById(Serializable id) {
        House house = houseDao.getById(id);
        if(null == house) return null;

        //户型：
        String houseTypeName = dictService.getNameById(house.getHouseTypeId());
        //楼层
        String floorName = dictService.getNameById(house.getFloorId());
        //建筑结构：
        String buildStructureName = dictService.getNameById(house.getBuildStructureId());
        //朝向：
        String directionName = dictService.getNameById(house.getDirectionId());
        //装修情况：
        String decorationName = dictService.getNameById(house.getDecorationId());
        //房屋用途：
        String houseUseName = dictService.getNameById(house.getHouseUseId());
        house.setHouseTypeName(houseTypeName);
        house.setFloorName(floorName);
        house.setBuildStructureName(buildStructureName);
        house.setDirectionName(directionName);
        house.setDecorationName(decorationName);
        house.setHouseUseName(houseUseName);
        return house;
    }
    @Override
    public PageInfo<HouseVo> findListPage(int pageNum, int pageSize, HouseQueryVo houseQueryVo) {
        PageHelper.startPage(pageNum, pageSize);
        Page<HouseVo> page = houseDao.findListPage(houseQueryVo);
        List<HouseVo> list = page.getResult();
        for(HouseVo houseVo : list) {
            //户型：
            String houseTypeName = dictDao.getNameById(houseVo.getHouseTypeId());
            //楼层
            String floorName = dictDao.getNameById(houseVo.getFloorId());
            //朝向：
            String directionName = dictDao.getNameById(houseVo.getDirectionId());
            houseVo.setHouseTypeName(houseTypeName);
            houseVo.setFloorName(floorName);
            houseVo.setDirectionName(directionName);
        }
        return new PageInfo<HouseVo>(page, 10);
    }
}
