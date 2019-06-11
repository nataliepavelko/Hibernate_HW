package commands;

import dao.CustomersDao;
import entity.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomersCommands implements Commands {
    private Scanner scanner = new Scanner(System.in);
    private CustomersDao customersDao = new CustomersDao();
    private Customer customer = new Customer();

    public CustomersCommands() {
        desc();
    }

    @Override
    public void desc() {
        System.out.println(" \n -- Menu of table Customers -- \n");

        System.out.println("   Choose a command from the list, please : ");
        System.out.println("1. Create new customer ");
        System.out.println("2. Show info about customer ");
        System.out.println("3. Update info for customer ");
        System.out.println("4. Show all customers ");
        System.out.println("5. Delete customer ");
        System.out.println("m. Go to main menu ");
        System.out.println("z. Exit ");


        String command = scanner.next();
        switch (command) {
            case "1":
                add();
                desc();
                break;
            case "2":
                getByID();
                desc();
                break;
            case "3":
                update();
                desc();
                break;
            case "4":
                showAll();
                desc();
                break;
            case "5":
                deleteByID();
                desc();
                break;
            case "m":
                new MainCommands();
                break;
            case "z":
                System.out.println(" --------- Exit ---------");
                scanner.close();
                break;
            default:
                System.out.println("Unknown command. Please, try again. ");
                desc();
        }
    }

    @Override
    public void add() {
        System.out.println("Enter info for a new customer");
        System.out.print("Name -\t");
        String name = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Phone -\t");
        String phone = scanner.nextLine();
        customer.setName(name);
        customer.setPhone(phone);
        customersDao.save(customer);
        System.out.println("Added new customer");
    }

    @Override
    public void getByID() {
        System.out.print("Enter id customer -\t");
        long id = scanner.nextInt();
        customer = customersDao.getById(id);
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not exist");
        }
    }

    @Override
    public void update() {
        System.out.println("Enter information for customer update");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        System.out.println("Name -\t");
        String name = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Phone -\t");
        String phone = scanner.nextLine();

        customer.setName(name);
        customer.setPhone(phone);
        customer.setId(id);
        if (customersDao.getById(id) != null) {
            customersDao.update(customer);
            System.out.println("Customer with id " + id + " is updated");
        } else {
            System.out.println("Customer with id " + id + " not exist");
        }
    }

    @Override
    public void showAll() {
        List<Customer> customerList = customersDao.getAll();
        if (customerList != null) {
            customerList.forEach(customer1 -> System.out.println(customer1));
        } else {
            System.out.println("Customers table is empty");
        }
    }

    @Override
    public void deleteByID() {
        System.out.println("Enter ID to delete customer ");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        customer = customersDao.getById(id);
        if (customer != null) {
            customersDao.delete(customer);
            System.out.println("Customer with ID " + id + " was deleted");
        } else {
            System.out.println("Customer not exist");
        }
    }
}
