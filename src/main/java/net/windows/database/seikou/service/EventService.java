package net.windows.database.seikou.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.windows.database.seikou.dao.EventDAO;



@Service("eventService")
public class EventService {

	@Autowired
	 private EventDAO eventDAO;
	
	@Transactional  
	 public List<Object[]> getEventFormInfo() throws IOException {  
	  return eventDAO.getEventFormInfo();  
	 } 

	
	
}
