package com.app.service.impl;

import com.app.common.Result;
import com.app.entity.NurseCategory;
import com.app.entity.NurseItem;
import com.app.entity.response.NurseDetailVo;
import com.app.entity.response.NurseHomeVo;
import com.app.entity.response.NurseListVo;
import com.app.mapper.NurseCategoryMapper;
import com.app.mapper.NurseItemMapper;
import com.app.service.NurseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2024-09-24
 */
@Service
@Slf4j
public class NurseServiceImpl extends ServiceImpl<NurseItemMapper, NurseItem> implements NurseService {
    @Autowired
    private NurseItemMapper nurseItemMapper;
    @Autowired
    private NurseCategoryMapper nurseCategoryMapper;

    @Override
    public Result<List<NurseHomeVo>> getAll() {
        List<NurseHomeVo> nurseHomeVos=new ArrayList<>();
        List<NurseCategory> categories = nurseCategoryMapper.selectList(null);
        for(NurseCategory cg : categories){
            NurseHomeVo nh=new NurseHomeVo();
            nh.setNcId(cg.getNcId());
            nh.setName(cg.getName());
            QueryWrapper<NurseItem> qw = new QueryWrapper<>();
            qw.eq("nc_id",cg.getNcId());
            List<NurseItem> items = nurseItemMapper.selectList(qw);

            List<NurseHomeVo.item> itemList=new ArrayList<>();
            for (int k=0;k<3;k++){
                NurseItem it=items.get(k);
                NurseHomeVo.item i=new NurseHomeVo.item();
                i.setId(it.getNiId());
                i.setName(it.getName());
                i.setPrice(it.getPrice());
                i.setImageUrl(it.getImageurl());
                itemList.add(i);
            }
            nh.setItems(itemList);
            nurseHomeVos.add(nh);
        }
        return Result.ok(nurseHomeVos);
    }

    @Override
    public Result<NurseListVo> getList(String ncId) {
        NurseListVo nh=new NurseListVo();
        QueryWrapper<NurseItem> qw = new QueryWrapper<>();
        qw.eq("nc_id",ncId);
        List<NurseItem> items = nurseItemMapper.selectList(qw);
        List<NurseListVo.item> itemList=new ArrayList<>();
        for (NurseItem it : items){
            NurseListVo.item i=new NurseListVo.item();
            i.setId(it.getNiId());
            i.setName(it.getName());
            i.setPrice(it.getPrice());
            i.setImageUrl(it.getImageurl());
            itemList.add(i);
        }
        nh.setItems(itemList);
        return Result.ok(nh);
    }

    @Override
    public Result<NurseDetailVo> getDetail(String niId) {
        NurseItem nurseItem = nurseItemMapper.selectById(niId);
        NurseCategory nurseCategory = nurseCategoryMapper.selectById(nurseItem.getNcId());
        NurseDetailVo nurseDetailVo=new NurseDetailVo();
        NurseDetailVo.item item=new NurseDetailVo.item();
        item.setId(nurseItem.getNiId());
        item.setName(nurseItem.getName());
        item.setPrice(nurseItem.getPrice());
        item.setImageUrl(nurseItem.getImageurl());
        item.setIllustrate(nurseItem.getIllustrate());
        item.setInfo(nurseItem.getInfo());
        nurseDetailVo.setCategory(nurseCategory.getName());
        nurseDetailVo.setItem(item);
        return Result.ok(nurseDetailVo);
    }
}
