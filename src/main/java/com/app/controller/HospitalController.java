package com.app.controller;


import com.app.common.Result;
import com.app.entity.Hospital;
import com.app.service.HospitalService;
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
@RequestMapping("/hospital")
public class HospitalController extends BaseController {
    @Autowired
    private HospitalService hospitalService;
    @GetMapping("/getAll")
    public Result<List<Hospital>> getAll() {
        return hospitalService.getAll();
    }

}
