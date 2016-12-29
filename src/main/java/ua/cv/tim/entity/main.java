package main.java.ua.cv.travian.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class main {
	public static void main(String[] args){
		
		Player player1 = new Player();
		player1.setLogin("login_player");
		player1.setPassword("password");
		player1.setRace(Race.ROMANS);
		
		Village village1 = new Village();
		village1.setxCoord((short) 95);
		village1.setyCoord((short) 57);
		village1.setName("New Village");
		
		Army army1 = new Army();
		army1.setType(UnitType.Axeman);
		army1.setOwnUnit(true);
		army1.setCount(15);
		
		village1.getArmies().add(army1);
		player1.getVillages().add(village1);
		
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();	
		session.save(army1);
		session.save(village1);
		session.save(player1);
		session.getTransaction().commit();
		session.close();
	}
}
