package com.app.service;

import com.app.common.Result;
import com.app.entity.DoctorInfo;
import com.app.entity.response.DoctorBasicVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Byterain
 * @since 2024-09-25
 */
public interface DoctorInfoService extends IService<DoctorInfo> {
    //获取推荐医生基本信息
    public Result<List<DoctorBasicVo>> getAll();

    //获取医生详细信息
    public Result<DoctorInfo> getOne(String Id);

}
