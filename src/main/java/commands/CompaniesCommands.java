package commands;

import dao.CompaniesDao;
import dao.ProjectsDao;
import entity.Company;
import entity.Project;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CompaniesCommands implements Commands {
    private Scanner scanner = new Scanner(System.in);
    private CompaniesDao companiesDao = new CompaniesDao();
   // private Company company = new Company();
    private ProjectsDao projectsDao = new ProjectsDao();

    public CompaniesCommands() {
        desc();
    }

    @Override
    public void desc() {
        System.out.println(" \n -- Menu of table Companies -- \n");

        System.out.println("   Choose a command from the list, please : ");
        System.out.println("1. Create new company ");
        System.out.println("2. Show info about company ");
        System.out.println("3. Update info for company ");
        System.out.println("4. Show all companies ");
        System.out.println("5. Delete company ");
        System.out.println("6. Show company by id project ");
        System.out.println("7. Show all projects of company ");
        System.out.println("8. Insert project to company ");
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
            case "6":
                getCompanyByIdProject();
                desc();
                break;
            case "7":
                getAllProjectsByCompanyId();
                desc();
                break;
            case "8":
                 insertProjectToCompany();
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

    private void getAllProjectsByCompanyId() {
        List<Company> companyList = companiesDao.getAll();
        if (companyList != null) {
            companyList.forEach(company1 -> System.out.println(company1));
        } else {
            System.out.println("Table of companies is empty");
        }
        System.out.println("Enter ID company");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        Company company = companiesDao.getById(id);
        Set<Project> projectList = company.getProjects();
        if (projectList != null) {
            projectList.forEach(project -> System.out.println(project));
        } else {
            System.out.println("There are no projects by id company " + id);
        }
    }

    @Override
    public void add() {
        System.out.println("Enter info for a new company");
        System.out.print("Name -\t");
        String name = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Address -\t");
        String address = scanner.nextLine();

        Company company = new Company();
        company.setName(name);
        company.setAddress(address);
        companiesDao.save(company);
        System.out.println("Added new company");
    }

    @Override
    public void getByID() {
        System.out.print("Enter id company -\t");
        long id = scanner.nextInt();
        Company company = companiesDao.getById(id);
        if (company != null) {
            System.out.println(company);
        } else {
            System.out.println("Company not exist");
        }
    }


    @Override
    public void update() {
        System.out.println("Enter information for company update");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        System.out.print("Name -\t");
        String name = scanner.next();
        scanner.nextLine();
        System.out.print("Address -\t");
        String address = scanner.nextLine();

        Company company = new Company();
        company.setName(name);
        company.setAddress(address);
        company.setId(id);
        if (companiesDao.getById(id) != null) {
            companiesDao.update(company);
            System.out.println("Company with id " + id + " is updated");
        } else {
            System.out.println("Company with id " + id + " not exist");
        }
    }

    @Override
    public void showAll() {
        List<Company> companyList = companiesDao.getAll();
        if (companyList != null) {
            companyList.forEach(company1 -> System.out.println(company1));
        } else {
            System.out.println("Companies table is empty");
        }
    }

    @Override
    public void deleteByID() {
        System.out.println("Enter ID to delete company ");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        Company company = companiesDao.getById(id);
        if (company != null) {
            companiesDao.delete(company);
            System.out.println("Company with ID " + id + " was deleted");
        } else {
            System.out.println("Company not exist");
        }
    }

    private void getCompanyByIdProject() {
        showProjects();
        System.out.println("Enter id project");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        Project project = projectsDao.getById(id);
        if (project != null) {
           Company company = project.getCompany();
            System.out.println(company);
        } else {
            System.out.println("Project with ID " + id + " not exist");
        }
    }

    private void insertProjectToCompany() {
        showProjects();
        System.out.println("Enter ID of project");
        long id_project = scanner.nextInt();
        Project project = projectsDao.getById(id_project);
        System.out.println("Enter ID of a company");
        long id_company = scanner.nextInt();
        Company company = companiesDao.getById(id_company);

        if (project != null && company != null) {
            companiesDao.addProjectToCompany(project, company);
            System.out.println("Added project to customer");
        } else {
            System.out.println("Company with ID " + id_company + " or project with ID " + id_project +
                    " are not exist");
        }
    }

    private void showProjects() {
        List<Project> projectList = projectsDao.getAll();
        if (projectList != null) {
            projectList.forEach(project -> System.out.println(project));
        } else {
            System.out.println("Project table is empty");
        }
    }
}
