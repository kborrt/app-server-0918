package com.app.entity.response;
import com.app.entity.Hospital;
import com.app.entity.Orders;
import com.app.entity.Users;
import lombok.Data;
//基本数据是和Orders实体类是一样的
@Data
public class OrderDetailVo extends Orders {
    private Users users;
    private Hospital hospital;
    private SetmealVo setmealVo;
}
