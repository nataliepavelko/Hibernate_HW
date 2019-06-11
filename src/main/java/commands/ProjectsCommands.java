package commands;

import dao.CustomersDao;
import dao.DevelopersDao;
import dao.ProjectsDao;
import entity.Company;
import entity.Customer;
import entity.Developer;
import entity.Project;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ProjectsCommands implements Commands {
    private Scanner scanner = new Scanner(System.in);
    private ProjectsDao projectDao = new ProjectsDao();
    private DevelopersDao developersDao = new DevelopersDao();
    private CustomersDao customersDao = new CustomersDao();
    private Project project = new Project();


    public ProjectsCommands() {
        desc();
    }

    @Override
    public void desc() {
        System.out.println(" \n -- Menu of table Projects -- \n");

        System.out.println("   Choose a command from the list, please : ");
        System.out.println("1. Create new project ");
        System.out.println("2. Show info about project ");
        System.out.println("3. Update info for project ");

        System.out.println("4. Show all projects ");
        System.out.println("5. Show all developers of project");
        System.out.println("6. Add developer to project");

        System.out.println("7. Show customer  by id project");
        System.out.println("8. Show company  by id project");
        System.out.println("9. Show all projects of customer");
        System.out.println("10. Insert project to customer");

        System.out.println("11. Delete project ");
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
                showAllDevelopersFromProject();
                desc();
                break;
            case "6":
                insertDeveloperInProject();
                desc();
                break;
            case "7":
                getCustomerByProjectId();
                desc();
                break;
            case "8":
                getCompanyByProjectId();
                desc();
                break;
            case "9":
                getAllProjectsByCustomerId();
                desc();
                break;
            case "10":
                insertProjectToCustomer();
                desc();
                break;
            case "11":
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
        System.out.println("Enter info for a new project");
        System.out.print("Name -\t");
        String name = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Cost -\t");
        double cost = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Date -\t");
        String dateString = scanner.nextLine();

        project.setName(name);
        project.setCost(cost);
        project.setDate(dateString);
        projectDao.save(project);

        System.out.println("Added new project!");
    }

    @Override
    public void getByID() {
        System.out.print("Enter id project -\t");
        long id = scanner.nextInt();
        project = projectDao.getById(id);

        if (project != null) {
            System.out.println(project);
        } else {
            System.out.println("Project not exist");
        }
    }

    @Override
    public void update() {
        System.out.println("Enter information for project update");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        System.out.print("Name -\t");
        String name = scanner.next();
        System.out.println("Cost -\t");
        double cost = scanner.nextDouble();

        project = new Project();
        project.setName(name);
        project.setCost(cost);
        project.setId(id);

        if (projectDao.getById(id) != null) {
            projectDao.update(project);
            System.out.println("Project with id " + id + " is updated");
        } else {
            System.out.println("Project with ID " + id + " not exist");
        }
    }

    @Override
    public void showAll() {
        List<Project> projectList = projectDao.getAll();
        if (projectList != null) {
            projectList.forEach(project -> System.out.println(project));
        } else {
            System.out.println("Projects table is empty");
        }
    }

    public void showAllDevelopersFromProject() {
        showAll();
        System.out.println("Enter project id to see all project developers.");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        project = projectDao.getById(id);

        List<Developer> developerList = project.getDevelopers();
        if (developerList != null) {
            developerList.forEach(developer -> System.out.println(developer));
        } else {
            System.out.println("Developers on project not exist");
        }
    }

    public void insertDeveloperInProject() {
        List<Developer> developerList = developersDao.getAll();
        if (developerList != null) {
            developerList.forEach(developer -> System.out.println(developer));
        } else {
            System.out.println("Developers on project not exist");
        }
        System.out.print("Enter ID of developer -\t");
        long id_developer = scanner.nextInt();
        Developer developer = developersDao.getById(id_developer);
        showAll();
        System.out.print("Enter ID of project -\t");
        long id_project = scanner.nextInt();
        project = projectDao.getById(id_project);

        if (developer != null && project != null) {
            projectDao.addDeveloperToProject(developer, project);
            System.out.println("Developer with id - " + id_developer + " added to project with id " + id_project);
        } else {
            System.out.println("Developer with id " + id_developer + " or project with id " + id_project +
                    " are not exist");
        }
    }

    @Override
    public void deleteByID() {
        System.out.println("Enter ID to delete project ");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        project = projectDao.getById(id);
        if (project != null) {
            projectDao.delete(project);
            System.out.println("Project with ID + " + id + " was deleted");
        } else {
            System.out.println("Project with ID " + id + " not exist");
        }
    }

    private void getCompanyByProjectId() {
        showAll();
        System.out.println("Enter ID project");
        System.out.println("ID -\t");
        long id = scanner.nextInt();
        project = projectDao.getById(id);
        if (project != null) {
            Company company = project.getCompany();
            System.out.println(company);
        } else {
            System.out.println("Project with ID " + id + " not exist");
        }
    }

    private void getCustomerByProjectId() {
        showAll();
        System.out.println("Enter ID project");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        project = projectDao.getById(id);
        Customer customer = project.getCustomer();
        System.out.println(customer);
    }

    private void getAllProjectsByCustomerId() {
        List<Customer> customerList = customersDao.getAll();
        if (customerList != null) {
            customerList.forEach(customer -> System.out.println(customer));
        } else {
            System.out.println("Table of customers is empty");
        }
        System.out.println("Enter ID customer");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        Customer customer = customersDao.getById(id);
        Set<Project> projectList = customer.getProjects();
        if (projectList != null) {
            projectList.forEach(project -> System.out.println(project));
        } else {
            System.out.println("There are no projects by id customer " + id);
        }
    }

    private void insertProjectToCustomer() {
        showAll();
        System.out.print("Enter ID of project -\t");
        long id_project = scanner.nextInt();
        project = projectDao.getById(id_project);

        System.out.print("Enter ID of a customer -\t");
        long id_customer = scanner.nextInt();
        Customer customer = customersDao.getById(id_customer);

        if (project != null && customer != null) {
            customersDao.addProjectToCustomer(project, customer);
            System.out.println("Added project to customer");
        } else {
            System.out.println("Customer with ID " + id_customer + " or project with ID " + id_project +
                    " are not exist");
        }
    }
}
