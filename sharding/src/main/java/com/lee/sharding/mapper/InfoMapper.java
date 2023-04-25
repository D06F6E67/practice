package com.lee.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.sharding.entity.Info;
import com.lee.sharding.entity.param.InfoParam;
import com.lee.sharding.entity.dto.InfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lee
 */
@Mapper
public interface InfoMapper extends BaseMapper<Info> {

    List<InfoDTO> listAndUser(InfoParam param);
}

