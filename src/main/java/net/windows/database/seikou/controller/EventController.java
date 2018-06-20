package net.windows.database.seikou.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.windows.database.seikou.service.EventService;
import net.windows.database.seikou.utils.Utilities;

@Controller
public class EventController {

	@Autowired
	EventService eventService;
	
	/* App URL Local : http://localhost:8080/cprs-api/api/events/
	 * */
	@RequestMapping(value = "/events", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> listAllEvents() throws IOException {
        List<Object[]> events = eventService.getEventFormInfo();
        StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        String response = " ";
        if(events.isEmpty()){
            return  new ResponseEntity<String>(HttpStatus.NO_CONTENT);//You may decide to return HttpStatus.NOT_FOUND
        }
        generator.writeStartArray();
        for (Object[] event : events){
        	//generator.writeStartArray()
        	generator.writeStartObject()
					.write("ID",(Long)event[0])
					.write("EVENT_NAME",(String)event[1])
					.write("EVENT_DESCRIPTION",(String)event[2])
					.write("ORGANIZER_NAME",(String)event[3])
					.write("VENUE_NAME",(String)event[4])
					.write("LOCATION",(String)event[5])
					.write("START_DATE",Utilities.dateTimeToString(event[6]))
					.write("END_DATE",Utilities.dateTimeToString(event[7]))
					.write("START_TIME",Utilities.dateTimeToString(event[8]))
					.write("END_TIME",Utilities.dateTimeToString(event[9]))
					.write("IMAGE1",(event[10] == null)? "null" : (String)event[10])
					.write("IMAGE2",(event[11] == null)? "null" : (String)event[11])
					.write("IMAGE3",(event[12] == null)? "null" : (String)event[12])					
					.writeEnd();
        }
      //Writes the data in the buffer to the String buffer.
        generator.writeEnd();
        generator.flush();
        response = writer.toString();
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
