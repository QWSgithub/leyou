package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

//    PRICE_COUNT_BE_NULL(400,"价格不能为空！"),
//    PRICE_COUNT_BE_NULL(400,"价格不能为空！"),
//    PRICE_COUNT_BE_NULL(400,"价格不能为空！"),
//    PRICE_COUNT_BE_NULL(400,"价格不能为空！"),
    PRICE_COUNT_BE_NULL(400,"价格不能为空！"),
    CATEGORY_NOT_FOUND(404,"商品分类没查到"),
    BRAND_NOT_FOUND(404,"品牌不存在")
    ;
    private int code;

    private String msg;



}
