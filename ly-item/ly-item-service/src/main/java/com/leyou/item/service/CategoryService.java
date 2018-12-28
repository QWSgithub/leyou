package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     *
     * @param pid
     * @return
     */
    public List<Category> queryCategoryListByPid(Long pid) {

        Category t = new Category();
        t.setParentId(pid);
        List<Category> list = categoryMapper.select(t);
        if (CollectionUtils.isEmpty(list)){
            throw  new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }

        System.out.println(list);

        return list;
    }

    public List<Category> queryByIds(List<Long> ids){


        List<Category> list = categoryMapper.selectByIdList(ids);

        if (CollectionUtils.isEmpty(list)){
            throw  new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }

}
