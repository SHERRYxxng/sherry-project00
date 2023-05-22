package sherry.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import sherry.base.BaseController;
import sherry.entity.Dict;
import sherry.result.Result;
import sherry.service.DictService;

import java.util.List;

/**
 * @Description:
 * @Author: SHERRY
 * @email: SHERRYth743779@gmail.com
 * @Date: 2023/5/24 17:51
 **/
@RestController
@RequestMapping(value="/dict")
@CrossOrigin
@SuppressWarnings({"unchecked", "rawtypes"})
public class DictController extends BaseController {

    @Reference
    private DictService dictService;

    /**
     * 根据编码获取子节点数据列表
     * @param dictCode
     * @return
     */
    @GetMapping(value = "findListByDictCode/{dictCode}")
    public Result<List<Dict>> findListByDictCode(@PathVariable String dictCode) {
        List<Dict> list = dictService.findListByDictCode(dictCode);
        return Result.ok(list);
    }

}