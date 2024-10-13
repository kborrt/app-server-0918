package com.app.entity.response;

import com.app.entity.Checkitem;
import com.app.entity.Setmeal;
import lombok.Data;

import java.util.List;
@Data
public class SetmealVo extends Setmeal {
    //添加套餐体检明细属性
    private List<Checkitem> checkItemList;
}

