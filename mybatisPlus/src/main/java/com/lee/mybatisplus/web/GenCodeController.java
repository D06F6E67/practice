package com.lee.mybatisplus.web;

import com.lee.mybatisplus.domain.request.GenCodeRequest;
import com.lee.mybatisplus.service.GenCodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 生成代码
 */
@RestController
@RequestMapping("/gen")
public class GenCodeController {

    @Resource
    private GenCodeService genCodeService;

    @PostMapping("/")
    public void genCode(GenCodeRequest genCodeRequest) {
        genCodeService.genCode(genCodeRequest);
    }

}
