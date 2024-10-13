package com.app.controller;


import com.app.common.Result;
import com.app.entity.response.SetmealVo;
import com.app.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.app.common.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Byterain
 * @since 2024-09-19
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController extends BaseController {
    @Autowired
    private SetmealService setmealService;
    @GetMapping("/getAll")
    public Result<List<SetmealVo>> getAll(){
        return setmealService.getAll();
    }

}
