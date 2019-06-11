package dao;

import connector.Connector;
import entity.Developer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DevelopersDao implements AbstractDao <Developer, Long>{

    @Override
    public void save(Developer developer) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(developer);
        transaction.commit();
        session.close();
    }

    @Override
    public Developer getById(Long id) {
        Developer developer = null;
        Session session = Connector.getSessionFactory().openSession();
        developer = session.get(Developer.class, id);
        session.close();
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        List <Developer> developerList;
        Session session = Connector.getSessionFactory().openSession();
        developerList = session.createQuery("From Developer", Developer.class).list();
        session.close();
        return developerList;
    }

    @Override
    public void update(Developer developer) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(developer);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Developer developer) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        //developer.getId();
        session.delete(developer);
        transaction.commit();
        session.close();
    }
}
