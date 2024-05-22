package com.msb.es.service;

import com.msb.es.entity.MsbCarInfo;
import com.msb.es.util.ESUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ESServiceImpl<T> {

    /**
     * 自己封装
     * 通过自定义@Field注解来映射mapping
     */
    @Resource
    private ESUtil esUtil;

    /**
     * ES Data
     * 自动根据实体类中的Field来映射Mappings
     */
    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    public void createIndex() {
        if (!elasticsearchOperations.indexOps(MsbCarInfo.class).exists()) {
            elasticsearchOperations.indexOps(MsbCarInfo.class).create();
            elasticsearchOperations.indexOps(MsbCarInfo.class).putMapping(elasticsearchOperations.indexOps(MsbCarInfo.class).createMapping());
        }
    }

    /**
     * 删除索引
     */
    public boolean removeIndex(String indexName){
        return esUtil.delIndex(indexName);
    }

    /**
     * 创建索引后批量插入或更新数据
     */
    public boolean addData(List<T> list) {
        if(list.size()<0){
            return true;
        }
        try {
            if(esUtil.createIndex(list.get(0).getClass()))
                return esUtil.batchSaveOrUpdate(list,true);
            else
                return false;
        } catch (Exception e) {
            log.error("############### 数据添加失败! {}", e);
        }
        return false;
    }

    /**
     * 查询
     */
    public List<MsbCarInfo> search(SearchSourceBuilder searchSourceBuilder) {
        try {
            return esUtil.search(searchSourceBuilder, MsbCarInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
