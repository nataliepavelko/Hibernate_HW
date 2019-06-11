package dao;

import connector.Connector;
import entity.Customer;
import entity.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomersDao implements AbstractDao <Customer, Long> {
    @Override
    public void save(Customer customer) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public Customer getById(Long id) {
        Customer customer = null;
        Session session = Connector.getSessionFactory().openSession();
        customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List <Customer> customerList = null;
        Session session = Connector.getSessionFactory().openSession();
        customerList = session.createQuery("From Customer", Customer.class).list();
        session.close();
        return customerList;
    }

    @Override
    public void update(Customer customer) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Customer customer) {
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(customer);
        transaction.commit();
        session.close();

    }

    public void addProjectToCustomer (Project project, Customer customer){
        Session session = Connector.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        customer.getProjects().add(project);
        session.update(customer);
        transaction.commit();
        session.close();
    }
}
