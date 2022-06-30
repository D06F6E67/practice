package com.lee.sharrding.config;

import com.lee.sharrding.service.TableService;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
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
        String suffix = getSuffixByYearMonth(date);
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

    private static String getSuffixByYearMonth(Date date) {
        return new SimpleDateFormat("yyyyMM").format(date);
    }

}
