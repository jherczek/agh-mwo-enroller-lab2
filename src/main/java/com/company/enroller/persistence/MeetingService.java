package com.company.enroller.persistence;

import com.company.enroller.model.Meeting;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

@Component("meetingService")
public class MeetingService {

	private Session session;

	public MeetingService() {
		this.session = DatabaseConnector.getInstance().getSession();
	}

	public Collection<Meeting> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Meeting> criteria = builder.createQuery(Meeting.class);
		Root<Meeting> root = criteria.from(Meeting.class);
		criteria.select(root);
		return session.createQuery(criteria).getResultList();
	}

	public Meeting findByID(long id) {
		return session.get(Meeting.class, id);
	}

	public void add(Meeting meeting) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(meeting);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void update(Meeting meeting) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(meeting);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void delete(Meeting meeting) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(meeting);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
