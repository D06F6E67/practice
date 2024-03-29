package com.lee.sharding.config;

import com.google.common.collect.Range;
import com.lee.sharding.service.TableService;
import com.lee.sharding.util.DateUtil;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 日期范围分片
 *
 * @author Lee
 */
@Component
public class DateRangeShardingAlgorithm implements RangeShardingAlgorithm<Date> {

    private static TableService tableService;

    public static void setTableService(TableService tableService) {

        DateRangeShardingAlgorithm.tableService = tableService;
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> shardingValue) {

        Collection<String> collect = new ArrayList<>();
        Range<Date> valueRange = shardingValue.getValueRange();
        String upper = "";
        if (valueRange.hasUpperBound()) {
            upper = DateUtil.getSuffixByYearMonth(valueRange.upperEndpoint());
        }

        String lower = "";
        if (valueRange.hasLowerBound()) {
            lower = DateUtil.getSuffixByYearMonth(valueRange.lowerEndpoint());
        }

        Boolean valid = "".equals(lower);
        for (String tableName : collection) {
            if (!"".equals(lower) && tableName.endsWith(lower)) {
                valid = true;
            }
            if (valid && tableService.existsTable(tableName)) {
                collect.add(tableName);
            }
            if (!"".equals(upper) && tableName.endsWith(upper)) {
                break;
            }
        }
        return collect;
    }
}