package net.windows.database.seikou.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	/*
	 * @SuppressWarnings("unchecked") public List<Event> getEventFormInfo() {
	 * Session session = this.sessionFactory.getCurrentSession(); List<Event>
	 * eventList = new ArrayList<>(); eventList = session.createQuery(
	 * "select distinct ev " + "from Event ev " + " join fetch ev.organizer o "
	 * + " join fetch  ev.venue v" ).list(); return eventList; }
	 */

	@SuppressWarnings("unchecked")
	public List<Object[]> getEventFormInfo() throws IOException {
		Session session = this.sessionFactory.getCurrentSession();
		List<Object[]> eventObjList = new ArrayList<>();
		SQLQuery query = session.createSQLQuery("SELECT TOP 20 EVENTS.ID, EVENTS.NAME AS EVENT_NAME, EVENTS.DESCRIPTION AS EVENT_DESCRIPTION, ORGANIZERS.NAME AS ORGANIZER_NAME, VENUE.NAME AS VENUE_NAME, CONCAT(VENUE.[ADDRESS_1], ', ', VENUE.CITY, ' ', VENUE.STATE, ' ', VENUE.ZIP) AS [LOCATION], START_DATE, END_DATE, START_TIME, END_TIME, CASE WHEN IMAGE1 IS NULL THEN 'http://www.hotel-r.net/im/destination/paris-france-2.jpg' ELSE IMAGE1 END AS IMAGE1, IMAGE2, IMAGE3 "
						+ "FROM EVENTS " + "INNER JOIN ORGANIZERS ON ORGANIZERS.ID = EVENTS.ORGANIZER "
						+ "INNER JOIN VENUE ON VENUE.ID = EVENTS.VENUE "
						+ "WHERE START_DATE >= DATEADD(day, DATEDIFF(day, 0, GETDATE()), 0) "
						+ "ORDER BY START_DATE, START_TIME ASC")
				.addScalar("ID", LongType.INSTANCE)
				.addScalar("EVENT_NAME", StringType.INSTANCE)
				.addScalar("EVENT_DESCRIPTION",StringType.INSTANCE)
				.addScalar("ORGANIZER_NAME",StringType.INSTANCE)
				.addScalar("VENUE_NAME",StringType.INSTANCE)
				.addScalar("LOCATION",StringType.INSTANCE)
				.addScalar("START_DATE",DateType.INSTANCE)
				.addScalar("END_DATE",DateType.INSTANCE)
				.addScalar("START_TIME",TimeType.INSTANCE)
				.addScalar("END_TIME",TimeType.INSTANCE)
				.addScalar("IMAGE1",StringType.INSTANCE)
				.addScalar("IMAGE2",StringType.INSTANCE)
				.addScalar("IMAGE3",StringType.INSTANCE);
		
		eventObjList = query.list();
		return eventObjList;
	}

}
