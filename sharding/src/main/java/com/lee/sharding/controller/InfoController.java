package com.lee.sharding.controller;

import com.lee.sharding.ExamineDate;
import com.lee.sharding.entity.param.InfoParam;
import com.lee.sharding.entity.dto.InfoDTO;
import com.lee.sharding.service.InfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lee
 */
@RestController
@RequestMapping("/info")
public class InfoController {

    @Resource
    private InfoService infoService;

    @ExamineDate
    @GetMapping("/list")
    public List<InfoDTO> list(InfoParam param) {
        return infoService.list(param);
    }

    @ExamineDate
    @GetMapping("/listAndUser")
    public List<InfoDTO> listAndUser(InfoParam param) {
        return infoService.listAndUser(param);
    }

    @ExamineDate
    @PutMapping
    public void save(@RequestBody InfoDTO user) {
        infoService.save(user);
    }

    @ExamineDate
    @GetMapping
    public InfoDTO get(InfoParam param) {
        return infoService.getOne(param);
    }

    @ExamineDate
    @PostMapping
    public void update(@RequestBody InfoDTO user) {
        infoService.update(user);
    }
}
