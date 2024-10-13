package com.app.controller;


import com.app.common.Result;
import com.app.entity.response.NurseDetailVo;
import com.app.entity.response.NurseHomeVo;
import com.app.entity.response.NurseListVo;
import com.app.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.common.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Byterain
 * @since 2024-09-24
 */
@RestController
@RequestMapping("/nurse")
public class NurseController extends BaseController {
    @Autowired
    private NurseService nurseService;
    @GetMapping("/getAll")
    public Result<List<NurseHomeVo>> getAll(){
        return nurseService.getAll();
    }

    @GetMapping("/getList")
    public Result<NurseListVo> getList(@RequestParam("ncId") String ncId){
        return nurseService.getList(ncId);
    }
    @GetMapping("/getDetail")
    public Result<NurseDetailVo> getDetail(@RequestParam("niId") String niId){
        return nurseService.getDetail(niId);
    }




}
