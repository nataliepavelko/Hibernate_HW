package dao;

import connector.Connector;
import entity.Company;
import entity.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CompaniesDao implements AbstractDao <Company, Long> {
    @Override
    public void save(Company company) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(company);
        transaction.commit();
        session.close();
    }

    @Override
    public Company getById(Long id) {
        Company company = null;
        Session session = Connector.getSessionFactory().openSession();
        company = session.get(Company.class, id);
        session.close();
        return company;
    }

    @Override
    public List<Company> getAll() {
        List <Company> companyList = null;
        Session session = Connector.getSessionFactory().openSession();
        companyList = session.createQuery("From Company", Company.class).list();
        session.close();
        return companyList;
    }

    @Override
    public void update(Company company) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(company);
        transaction.commit();
        session.close();

    }

    @Override
    public void delete(Company company) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(company);
        transaction.commit();
        session.close();

    }

    public void addProjectToCompany(Project project, Company company){
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        company.getProjects().add(project);
        session.update(company);
        transaction.commit();
        session.close();
    }
}
