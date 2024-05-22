package com.msb.es.repository;

import com.msb.es.entity.MsbCarInfo;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @date 2024/5/22 21:37
 * @author DongCL
 */
public interface MsbCarInfoRepository extends Repository<MsbCarInfo, String> {

    /**
     * 也可以用这种方式实现es查询
     */
    @Query("{\"match\": {\"name\": {\"query\": \"?0\"}}}")
    List<MsbCarInfo> findByName(String name);
}
