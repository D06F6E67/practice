package com.lee.sharding.config;

import com.lee.sharding.service.TableService;
import com.lee.sharding.util.DateUtil;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

/**
 * 日期精确分片
 *
 * @author Lee
 */
@Component
public class DatePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {

    private static TableService tableService;

    public static void setTableService(TableService tableService) {

        DatePreciseShardingAlgorithm.tableService = tableService;
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> shardingValue) {

        Date date = shardingValue.getValue();
        String suffix = DateUtil.getSuffixByYearMonth(date);
        for (String tableName : collection) {
            if (tableName.endsWith(suffix)) {
                if (!tableService.existsTable(tableName)) {
                    tableService.createTable(tableName);
                }
                return tableName;
            }
        }
        throw new IllegalArgumentException("未找到匹配的数据表");
    }
}
