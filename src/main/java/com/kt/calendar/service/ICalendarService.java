package com.kt.calendar.service;

import java.util.List;

import com.kt.calendar.vo.Calendar;

public interface ICalendarService {

	void insertCalen(Calendar cal);

	List<Calendar> selectCalen(String startDt, String endDt);

	void updateCalen(Calendar cal);

	void deleteCalen(String seqNo);
}
