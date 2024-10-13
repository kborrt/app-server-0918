package com.app.controller;


import com.app.common.Result;
import com.app.entity.DoctorInfo;
import com.app.entity.response.DoctorBasicVo;
import com.app.service.DoctorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.common.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Byterain
 * @since 2024-09-25
 */
@RestController
@RequestMapping("/doctor")
public class DoctorInfoController extends BaseController {
    @Autowired
    private DoctorInfoService doctorInfoService;
    @GetMapping("getAll")
    public Result<List<DoctorBasicVo>> getAll(){
        return doctorInfoService.getAll();
    }
    @GetMapping("getOne")
    public Result<DoctorInfo> getOne(@RequestParam("Id") String Id){
        return doctorInfoService.getOne(Id);
    }

}
