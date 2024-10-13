package com.app.service;

import com.app.common.Result;
import com.app.entity.NurseItem;
import com.app.entity.response.NurseDetailVo;
import com.app.entity.response.NurseHomeVo;
import com.app.entity.response.NurseListVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Byterain
 * @since 2024-09-24
 */
public interface NurseService extends IService<NurseItem> {
    //主页初始化请求
    public Result<List<NurseHomeVo>> getAll();

    //获取某个服务项中的所有服务
    public Result<NurseListVo> getList(String ncId);

    //获取服务详情
    public Result<NurseDetailVo> getDetail(String niId);

}
