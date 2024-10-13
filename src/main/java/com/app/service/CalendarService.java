package com.app.service;
import com.app.entity.response.AvailabilityVo;
public interface CalendarService {
    public AvailabilityVo getAvailability(int year, int month);
}
