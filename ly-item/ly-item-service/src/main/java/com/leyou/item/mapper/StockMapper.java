package com.leyou.item.mapper;

import com.leyou.common.mapper.BaseMapper;
import com.leyou.item.pojo.Stock;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
//@RegisterMapper
public interface StockMapper extends BaseMapper<Stock> {
}


