package com.lee.mybatis;

import com.lee.mybatis.domain.Country;
import com.lee.mybatis.mapper.CountryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MybatisApplicationTest {

    @Resource
    private CountryMapper countryMapper;

    @Test
    public void testSelectAll() {
        List<Country> countries = countryMapper.selectAll();
        countries.forEach(country -> {
            System.out.println(country.toString());
        });
    }

}
