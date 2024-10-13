package com.app.service.impl;
import com.app.entity.response.AvailabilityVo;
import com.app.service.CalendarService;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarServiceImpl  implements CalendarService {
    //获得万年历数据
    //参数： 年份   月分
    @Override
    public AvailabilityVo getAvailability(int year, int month) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        DayOfWeek firstDayOfWeek = firstDayOfMonth.getDayOfWeek();
        int daysInMonth = firstDayOfMonth.lengthOfMonth();

        // 计算需要填充的空白天数
        int daysToPrepend = firstDayOfWeek.getValue() % 7;

        // 创建一个用于存储所有日期的列表，包括空白天和实际日期
        List<AvailabilityVo.DayAvailability> days = new ArrayList<>();

        // 填充前面的空白天数
        for (int i = 1; i <= daysToPrepend; i++) {
            AvailabilityVo.DayAvailability emptyDay = new AvailabilityVo.DayAvailability();
            emptyDay.setDate(null); // 空白天设置日期为null
            emptyDay.setOpen(false);
            emptyDay.setRemainingSlots(0);
            days.add(emptyDay);
        }

        // 填充实际的日期数据
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);
            boolean available = false; // 示例：随机设定是否可预约
            int remainingSlots = 0;

            AvailabilityVo.DayAvailability dayAvailability = new AvailabilityVo.DayAvailability();
            dayAvailability.setDate(date);
            dayAvailability.setOpen(available);
            dayAvailability.setRemainingSlots(remainingSlots);

            days.add(dayAvailability);
        }

        AvailabilityVo availability = new AvailabilityVo();
        availability.setYear(year);
        availability.setMonth(month);
        availability.setDays(days);

        return availability;
    }
}
