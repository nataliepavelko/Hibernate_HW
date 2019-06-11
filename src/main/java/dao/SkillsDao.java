package dao;

import connector.Connector;
import entity.Developer;
import entity.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SkillsDao implements AbstractDao <Skill, Long> {

    @Override
    public void save(Skill skill) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(skill);
        transaction.commit();
        session.close();
    }

    @Override
    public Skill getById(Long id) {
        Session session = Connector.getSessionFactory().openSession();
        Skill skill = session.get(Skill.class, id);
        session.close();
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        Session session = Connector.getSessionFactory().openSession();
        List <Skill> skillList = session.createQuery("From Skill", Skill.class).list();
        session.close();
        return skillList;
    }

    @Override
    public void update(Skill skill) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(skill);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Skill skill) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(skill);
        transaction.commit();
        session.close();
    }

    public void addSkillToDeveloper (Developer developer, Skill skill ){
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        developer.getSkills().add(skill);
        session.update(developer);
        transaction.commit();
        session.close();
    }
    //for java developers
    public List<Skill> getSkillByName(String skillName) {
        List<Skill> skillList;
        Session session = Connector.getSessionFactory().openSession();
        skillList = session.createQuery("FROM Skill WHERE name = '" + skillName + "'", Skill.class).list();
        session.close();
        return skillList;
    }

    // for middle developers
    public List<Skill> getSkillByLevel(String skillLevel) {
        List<Skill> skillList;
        Session session = Connector.getSessionFactory().openSession();
        skillList = session.createQuery("FROM Skill WHERE level = '" + skillLevel + "'", Skill.class).list();
        session.close();
        return skillList;
    }
}
