package com.kt.calendar.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kt.calendar.service.ICalendarService;
import com.kt.calendar.vo.Calendar;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class CalendarController {

	@Autowired
	ICalendarService service;
	
	@RequestMapping(value="/insertCalen", method= RequestMethod.POST)
	public void insertCalen(@RequestBody Calendar cal) {
		service.insertCalen(cal);
	}
	
	@RequestMapping(value="/selectCalen", method= RequestMethod.POST)
	public List<Calendar> selectCalen(@RequestBody Map<String, String> map) {
		return service.selectCalen(map.get("startDt"), map.get("endDt"));
	}
	
	@RequestMapping(value="/updateCalen", method= RequestMethod.PUT)
	public void updateCalen(@RequestBody Calendar cal) {
		service.updateCalen(cal);
	}
	
	@RequestMapping(value="/deleteCalen", method= RequestMethod.DELETE)
	public void deleteCalen(@RequestBody String seqNo) {
		service.deleteCalen(seqNo);
	}
	
}