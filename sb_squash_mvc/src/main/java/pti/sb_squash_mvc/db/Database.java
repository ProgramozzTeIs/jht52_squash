package pti.sb_squash_mvc.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import pti.sb_squash_mvc.model.Game;
import pti.sb_squash_mvc.model.Location;
import pti.sb_squash_mvc.model.User;

@Repository
public class Database {
	
	private SessionFactory sessionFactory;
	
	public Database() {
		
		Configuration config = new Configuration();
		config.configure();
		
		sessionFactory = config.buildSessionFactory();
	}
	public List<User> getAllPlayer(){
		
		List<User> players = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<User> query = session.createSelectionQuery("Select u FROM User u", User.class);
		players =query.getResultList();
		
		
		tx.commit();
		session.close();
		
		return players;
	}
	
	public List<Location> getAllLocations(){
		
		List<Location> locations = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Location> query = session.createSelectionQuery("SELECT l FROM Location l", Location.class);
		locations = query.getResultList();
		
		tx.commit();
		session.close();
		
		return locations;
	}
	
	
	public void saveGame(Game game) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(game);
		
		tx.commit();
		session.close();
	}
	
	public void savePlayer(User user) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(user);
		
		tx.commit();
		session.close();
		
	}
	
	
	public Location getLocationById(int locationId) {
		
		Location location = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		location = session.get(Location.class,locationId);
		
		tx.commit();
		session.close();
		
		
		return location;
	}
	
//	public User getPlayerByNameAndPwd(String name, String password) {
//		
//		User user = null;
//		
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
//		
//		
//		
//		tx.commit();
//		session.close();
//		
//		return user;
//		
//	}
	
}
