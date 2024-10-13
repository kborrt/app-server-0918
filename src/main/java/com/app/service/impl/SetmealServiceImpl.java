package com.app.service.impl;

import com.app.common.Result;
import com.app.entity.Checkitem;
import com.app.entity.Setmeal;
import com.app.entity.Setmealdetailed;
import com.app.entity.response.SetmealVo;
import com.app.mapper.CheckitemMapper;
import com.app.mapper.SetmealMapper;
import com.app.mapper.SetmealdetailedMapper;
import com.app.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2024-09-19
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealdetailedMapper setmealdetailedMapper;
    @Autowired
    private CheckitemMapper checkitemMapper;

    @Override
    public Result<List<SetmealVo>> getAll() {
        //存储SetmealVo实体类(包含套餐和明细)集合。
        List<SetmealVo> volist = new ArrayList<>();
        //部分1：查询套餐数据
        List<Setmeal> setmealList = setmealMapper.selectList(null);
        //将查询出 setmeal 赋值到 setmealVo 对象
        setmealList.forEach(setmeal->{
            SetmealVo vo = new SetmealVo();
            vo.setSmId(setmeal.getSmId());
            vo.setName(setmeal.getName());
            vo.setPrice(setmeal.getPrice());
            vo.setType(setmeal.getType());
        //部分2：从关联表 setmealdetailed 查询套餐所对应 检查明细id。
            QueryWrapper<Setmealdetailed> qw = new QueryWrapper<>();
            qw.eq("sm_id",setmeal.getSmId());
        //查询套餐和检查明细的关联表 ，通过sm_id 查询出该套餐检查明细 ci_id
            List<Setmealdetailed> setmealdetaileds =
                    setmealdetailedMapper.selectList(qw);
        //循环取出 集合中 某个套餐sm_id(套餐编号) 对应检查明细ci_id(检查明编号)
        //集合存储套餐对应 明细id
            List<Integer> ci_id_list = new ArrayList<>();
            setmealdetaileds.forEach(sm ->{
                ci_id_list.add(sm.getCiId());
            });
        //部分3， 查询该套餐对应检查明细
        //使用 检查明细ci_id 取查询checkItem表，查询出明细详细数据
        //selectBatchIds() 根据多个id查询多条数据
            List<Checkitem> checkitemList =
                    checkitemMapper.selectBatchIds(ci_id_list);
            vo.setCheckItemList(checkitemList);
            volist.add(vo);
        });
        return Result.ok(volist);
    }

    @Override
    public SetmealVo getSetmealWithCheckItems(Integer smId) {
        SetmealVo vo = new SetmealVo();
        vo.setSmId(smId);
        //查询体检套餐基本数据
        Setmeal setmeal = setmealMapper.selectById(smId);
        vo.setSmId(setmeal.getSmId());
        vo.setName(setmeal.getName());
        vo.setType(setmeal.getType());
        vo.setPrice(setmeal.getPrice());
        //查询体检套餐的明细
        QueryWrapper<Setmealdetailed> qw = new QueryWrapper<>();
        qw.eq("sm_id",smId);
        List<Checkitem> items = new ArrayList<>();
        List<Setmealdetailed> details = setmealdetailedMapper.selectList(qw);
        details.forEach(d->{
            Checkitem checkitem = checkitemMapper.selectById(d.getCiId());
            items.add(checkitem);
        });
        vo.setCheckItemList(items);
        return vo;
    }
}
