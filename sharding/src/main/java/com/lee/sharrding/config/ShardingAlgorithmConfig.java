package com.lee.sharrding.config;

import com.lee.sharrding.service.TableService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

/**
 * sharding分片算法配置
 *
 * @author Lee
 */
@Order(1)
@Configuration
public class ShardingAlgorithmConfig {

    @Resource
    private TableService tableService;

    @Bean
    public void config() {
        DatePreciseShardingAlgorithm.setTableService(tableService);
        DateRangeShardingAlgorithm.setTableService(tableService);
    }
}
