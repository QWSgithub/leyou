package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.SkuMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.mapper.StockMapper;
import com.leyou.item.pojo.*;
import javafx.scene.shape.Circle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper detailMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;


    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {

        //分页
        PageHelper.startPage(page,rows);
        //过滤
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //搜索字段过滤
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("title","%"+key+"%");
        }
        if (saleable != null) {
            criteria.andEqualTo("saleable",saleable);
        }

        //排序
        example.setOrderByClause("last_update_time DESC");

        //查询
        List<Spu> spus = spuMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(spus)){
            throw  new LyException(ExceptionEnum.GOODS_NOT_FOND);
        }

        loadCategoryAndBrandName(spus);

        PageInfo<Spu> info = new PageInfo<>(spus);
        return new PageResult<>(info.getTotal(),spus);
    }

    private void loadCategoryAndBrandName(List<Spu> spus) {

        for (Spu spu: spus) {
            //处理分类
            List<Category> categories = categoryService.queryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            List<String> names = categories.stream().map(Category::getName).collect(Collectors.toList());
            spu.setCname(StringUtils.join("/"));

            //处理品牌
            spu.setBname(brandService.queryById(spu.getBrandId()).getName());


        }

    }

    @Transactional
    public void saveGoods(Spu spu) {

        //新增spu
         spu.setId(null);
         spu.setCreateTime(new Date());
         spu.setLastUpdateTime(spu.getCreateTime());
         spu.setSaleable(true);
         spu.setValid(false);
        int count = spuMapper.insert(spu);
        if (count != 1){
            throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
        }
        //新增detail
        SpuDetail detail = spu.getSpuDetail();
        detail.setSpuId(spu.getId());
        detailMapper.insert(detail);

        //定义库存的集合
        List<Stock> stockList = new ArrayList<>();

        //新增sku
        List<Sku> skus = spu.getSkus();

        for (Sku sku : skus) {
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            sku.setSpuId(spu.getId());
            count = skuMapper.insert(sku);
            if (count != 1){
                throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
            }

            //新增库存
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());

            stockList.add(stock);

        }

        //批量新增库存
        stockMapper.insertList(stockList);



    }
}
