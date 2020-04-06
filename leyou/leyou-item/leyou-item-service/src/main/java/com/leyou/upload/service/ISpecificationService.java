package com.leyou.upload.service;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;

import java.util.List;

public interface ISpecificationService {
    /**
     * 查询组用分类id
     * @param cid
     * @return
     */
    List<SpecGroup> queryGroupsByCid(Long cid);

    /**
     * 查询组下的参数
     * @param gid
     * @return
     */
    List<SpecParam> queryParams(Long gid);
}
