package com.app.mapper;

import com.app.entity.NurseOrder;
import com.app.entity.response.OrderOfSetmealVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Byterain
 * @since 2024-09-24
 */
@Repository
public interface NurseOrderMapper extends BaseMapper<NurseOrder> {

}
