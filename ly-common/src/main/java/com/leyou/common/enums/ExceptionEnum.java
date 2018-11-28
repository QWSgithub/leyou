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
    BRAND_NOT_FOUND(404,"品牌不存在"),
    BRAND_SAVE_ERROR(500,"新增品牌失败"),
    UPLOAD_FILE_ERROR(500,"文件上传失败"),
    INVALID_FILE_TYPE(400,"无效的文件类型"),
//    CATEGORY_BRAND_SAVE_ERROR(500,"新增品牌分类中间表失败"),
    ;
    private int code;

    private String msg;



}
