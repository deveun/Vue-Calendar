package com.kt.calendar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.calendar.vo.Calendar;

@Service
public class CalendarServiceImpl implements ICalendarService {

	@Autowired
	SqlSession session;
	
	@Override
	public void insertCalen(Calendar cal) {
		session.insert("Calendar.insertCalen", cal);
	}

	@Override
	public List<Calendar> selectCalen(String sta, String end) {
		Map<String, String> map = new HashMap<String, String>();
		//TODO: sta, end 값이 맞게 들어갈 수 있도록 가공 필요
		map.put("sta", sta);
		map.put("end", end);
		return session.selectList("Calendar.selectCalen", map);
	}

	@Override
	public void updateCalen(Calendar cal) {
		session.update("Calendar.updateCalen", cal);
	}

	@Override
	public void deleteCalen(String seqNo) {
		session.delete("Calendar.deleteCalen", seqNo);
	}

}
