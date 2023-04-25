package com.lee.sharding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.sharding.entity.Info;
import com.lee.sharding.entity.param.InfoParam;
import com.lee.sharding.entity.dto.InfoDTO;
import com.lee.sharding.mapper.InfoMapper;
import com.lee.sharding.service.InfoService;
import com.lee.sharding.util.DateUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Lee
 */
@Service
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements InfoService {

    @Resource
    private InfoMapper mapper;

    @Override
    public List<InfoDTO> list(InfoParam param) {

        LambdaQueryWrapper<Info> queue = new LambdaQueryWrapper<>();
        queue.ge(Info::getCreateDate, param.getStartDate());
        queue.le(Info::getCreateDate, param.getEndDate());

        List<Info> list = this.list(queue);
        List<InfoDTO> dtoList = new ArrayList<>();

        for (Info info : list) {
            InfoDTO vo = new InfoDTO();
            BeanUtils.copyProperties(info, vo);
            dtoList.add(vo);
        }

        return dtoList;
    }

    @Override
    public List<InfoDTO> listAndUser(InfoParam param) {
        return mapper.listAndUser(param);
    }

    @Override
    public boolean save(InfoDTO dto) {

        if (Objects.isNull(dto.getCreateDate())) {
            dto.setCreateDate(new Date());
        }

        Info info = new Info();
        BeanUtils.copyProperties(dto, info);

        return super.save(info);
    }

    @Override
    public InfoDTO getOne(InfoParam param) {

        LambdaQueryWrapper<Info> queue = getOneInfoQuery(param.getCreateDate(), param.getId());
        queue.last("LIMIT 1");

        Info info = this.getOne(queue);

        InfoDTO vo = new InfoDTO();
        BeanUtils.copyProperties(info, vo);

        return vo;
    }

    @Override
    public void update(InfoDTO dto) {

        LambdaQueryWrapper<Info> queue = getOneInfoQuery(dto.getCreateDate(), dto.getId());

        Info info = new Info();
        BeanUtils.copyProperties(dto, info);

        this.update(info, queue);
    }

    @NotNull
    private static LambdaQueryWrapper<Info> getOneInfoQuery(Date createDate, Long InfoId) {
        LambdaQueryWrapper<Info> queue = new LambdaQueryWrapper<>();
        queue.gt(Info::getCreateDate, DateUtil.getMonthFirstDay(createDate));
        queue.le(Info::getCreateDate, DateUtil.getMonthLastDay(createDate));
        queue.eq(Info::getId, InfoId);
        return queue;
    }
}
