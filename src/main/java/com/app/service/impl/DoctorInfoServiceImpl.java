package com.app.service.impl;

import com.app.common.Result;
import com.app.entity.DoctorInfo;
import com.app.entity.response.DoctorBasicVo;
import com.app.mapper.DoctorInfoMapper;
import com.app.service.DoctorInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Byterain
 * @since 2024-09-25
 */
@Service
public class DoctorInfoServiceImpl extends ServiceImpl<DoctorInfoMapper, DoctorInfo> implements DoctorInfoService {
    @Autowired
    private DoctorInfoMapper doctorInfoMapper;
    @Override
    public Result<List<DoctorBasicVo>> getAll() {
        List<DoctorBasicVo> doctorBasicVos = new ArrayList<>();
        List<DoctorInfo> doctorInfos = doctorInfoMapper.selectList(null);
        for (DoctorInfo doctorInfo : doctorInfos) {
            DoctorBasicVo doctorBasicVo = new DoctorBasicVo();
            doctorBasicVo.setId(doctorInfo.getId());
            doctorBasicVo.setName(doctorInfo.getName());
            doctorBasicVo.setJob(doctorInfo.getJob());
            doctorBasicVo.setPic(doctorInfo.getPic());
            doctorBasicVos.add(doctorBasicVo);
        }
        return Result.ok(doctorBasicVos);
    }

    @Override
    public Result<DoctorInfo> getOne(String Id) {
        DoctorInfo doctorInfo = doctorInfoMapper.selectById(Id);
        return Result.ok(doctorInfo);
    }

}
