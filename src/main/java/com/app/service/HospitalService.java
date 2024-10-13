package com.app.service;

import com.app.common.Result;
import com.app.entity.Hospital;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Byterain
 * @since 2024-09-19
 */
public interface HospitalService extends IService<Hospital> {
    //查询获得所有医院的方法
    public Result<List<Hospital>> getAll();

}
