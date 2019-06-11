package dao;

import connector.Connector;
import entity.Developer;
import entity.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProjectsDao implements AbstractDao <Project, Long> {

    @Override
    public void save(Project project) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(project);
        transaction.commit();
        session.close();
    }

    @Override
    public Project getById(Long id) {
        Project project = null;
        Session session = Connector.getSessionFactory().openSession();
        project = session.get(Project.class, id);
        session.close();
        return project;
    }

    @Override
    public List<Project> getAll() {
        List <Project> projectList = null;
        Session session = Connector.getSessionFactory().openSession();
        projectList = session.createQuery("FROM Project", Project.class).list();
        return projectList;
    }

    @Override
    public void update(Project project) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(project);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Project project) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session);
        transaction.commit();
        session.close();
    }
    public void addDeveloperToProject(Developer developer, Project project) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        developer.getProjects().add(project);
        session.update(developer);
        transaction.commit();
        session.close();
    }
}
