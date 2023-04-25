package com.lee.sharding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.sharding.entity.Info;
import com.lee.sharding.entity.param.InfoParam;
import com.lee.sharding.entity.dto.InfoDTO;

import java.util.List;

/**
 * @author Lee
 */
public interface InfoService extends IService<Info> {

    /**
     * 列表
     *
     * @param param 参数
     * @return 结果
     */
    List<InfoDTO> list(InfoParam param);

    /**
     * 关联查询
     *
     * @param param 参数
     * @return 结果
     */
    List<InfoDTO> listAndUser(InfoParam param);

    /**
     * 保存
     *
     * @param dto 保存信息
     * @return 结果
     */
    boolean save(InfoDTO dto);

    /**
     * 获取一个
     *
     * @param param ID和时间
     * @return 用户信息
     */
    InfoDTO getOne(InfoParam param);

    /**
     * 修改
     *
     * @param user 信息
     */
    void update(InfoDTO user);
}
