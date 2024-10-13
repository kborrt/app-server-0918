package com.app.service.impl;

import com.app.common.Result;
import com.app.entity.Hospital;
import com.app.mapper.HospitalMapper;
import com.app.service.HospitalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Byterain
 * @since 2024-09-19
 */
@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital> implements HospitalService {
    @Autowired
    private HospitalMapper hospitalMapper;
    @Override
    public Result<List<Hospital>> getAll() {
        List<Hospital> hospitals = hospitalMapper.selectList(null);
        return Result.ok(hospitals);
    }
}
