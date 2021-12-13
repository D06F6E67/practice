package com.lee.mybatis.mapper;

import com.lee.mybatis.domain.Country;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 国家
 */
@Mapper
public interface CountryMapper {

    List<Country> selectAll();
}
