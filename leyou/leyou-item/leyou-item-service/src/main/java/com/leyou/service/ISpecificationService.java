package com.leyou.service;

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
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching);

    /**
     * 根据参数查询SpecGroup
     * @param cid
     * @return
     */
    List<SpecGroup> queryGroupsWithParam(Long cid);
}
