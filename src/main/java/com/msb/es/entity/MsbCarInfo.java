package com.msb.es.entity;

import com.msb.es.dto.Document;
import com.msb.es.dto.EsDataId;
import com.msb.es.dto.Field;
import com.msb.es.dto.enums.AnalyzerType;
import lombok.Data;
import com.msb.es.dto.enums.FieldType;

import java.io.Serializable;

@Document(indexName = "msb_car_info")
@Data
public class MsbCarInfo implements Serializable {
    @EsDataId
    @Field(type = FieldType.KEYWORD)
    private Integer id;
    @Field(type = FieldType.INTEGER)
    private Integer uniacid;
    @Field(type = FieldType.INTEGER)
    private Integer brand_id;
    @Field(type = FieldType.TEXT, analyzer = AnalyzerType.STANDARD)
    private String name;
    @Field(type = FieldType.TEXT)
    private String pic_url;
    @Field(type = FieldType.INTEGER)
    private Integer status;
    @Field(type = FieldType.INTEGER)
    private Integer sort;
    @Field(type = FieldType.DATE)
    private Integer create_time;
}
