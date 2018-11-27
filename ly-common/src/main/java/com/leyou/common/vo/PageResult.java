package com.leyou.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class PageResult<T> {
    private Long total;//总的条数
    private Integer totalPage;//总的页数
    private List<T> items;


    public PageResult(){

    }

    public PageResult(Long total,List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total,List<T> items,Integer totalPage){
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }
}
