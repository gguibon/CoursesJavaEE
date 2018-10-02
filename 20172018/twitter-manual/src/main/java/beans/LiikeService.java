package beans;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.hibernate.Query;
import org.hibernate.Session;

import database.HibernateUtil;
import launch.Main;

public class LiikeService {

	private HibernateUtil hibernateUtil = Main.hibernateUtil;
	private Session session = hibernateUtil.getSession();

	public LiikeService() {

		try {
			checkData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addLiike(Liike like) {
		session.beginTransaction();
		session.save(like);
		session.getTransaction().commit();
		session.flush();
	}

	@SuppressWarnings("unchecked")
	public Boolean liikeExists(String id, String liike_author) {
		session.beginTransaction();
		List<Liike> liikesDB = new ArrayList<Liike>(session
				.createQuery("from beans.Liike where id = '" + id + "' AND liike_author = '" + liike_author + "'")
				.list());
		session.getTransaction().commit();
		session.flush();
		return liikesDB.isEmpty();
	}

	@SuppressWarnings("unchecked")
	public void toggleLiike(Message msn, String id, String liike_author) {
		session.beginTransaction();

		Query query = session
				.createQuery("from beans.Liike L " + "where L.message.id = :id AND L.liike_author = :liike_author ");
		query.setParameter("id", id);
		query.setParameter("liike_author", liike_author);
		List<Liike> liikesDB = new ArrayList<Liike>(query.list());

		if (liikesDB.isEmpty()) {
			Liike like = new Liike();
			like.setLiike_author(liike_author);
			like.setMessage(msn);
			msn.getLikes().add(like);
			session.save(like);
		} else {
			Boolean deleted = deleteById(Liike.class, liikesDB.get(0).getLiike_id(), session);
			if (deleted)
				msn.getLikes().remove(liikesDB.get(0));
		}
		session.getTransaction().commit();
		session.flush();
	}

	private boolean deleteById(Class<?> type, Long id, Session session) {
		Object persistentInstance = session.load(type, id);
		if (persistentInstance != null) {
			session.delete(persistentInstance);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public JsonArrayBuilder getLiikesJson() {
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		session.beginTransaction();
		List<Liike> listLikes = new ArrayList<Liike>(session.createQuery("from beans.Liike").list());
		session.getTransaction().commit();
		session.flush();
		for (Liike like : listLikes) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("like_id", like.getLiike_id().toString());
			objectBuilder.add("liike_author", like.getLiike_author());
			arrayBuilder.add(objectBuilder);
		}

		return arrayBuilder;
	}

	public void checkData() throws Exception {
		hibernateUtil.checkData("select * from liike");
	}

}
