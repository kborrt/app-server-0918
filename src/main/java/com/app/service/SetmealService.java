package com.app.service;

import com.app.common.Result;
import com.app.entity.Setmeal;
import com.app.entity.response.SetmealVo;
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
public interface SetmealService extends IService<Setmeal> {
    public Result<List<SetmealVo>> getAll();

    //查询体检项和相关明细
    public SetmealVo getSetmealWithCheckItems(Integer smId);

}
