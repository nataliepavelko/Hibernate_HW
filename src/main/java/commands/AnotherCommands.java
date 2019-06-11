package commands;

import dao.ProjectsDao;
import dao.SkillsDao;
import entity.Developer;
import entity.Project;
import entity.Skill;

import java.util.List;
import java.util.Scanner;

public class AnotherCommands {
    private Scanner scanner = new Scanner(System.in);
    private ProjectsDao projectsDao = new ProjectsDao();
    private Project project = new Project();
    private SkillsDao skillsDao = new SkillsDao();
    private final String skillName = "Java";
    private final String skillLevel = "Middle";

    public AnotherCommands() {
        desc();
    }

    public void desc() {
        System.out.println(" \n -- Menu with additional commands --  \n");

        System.out.println("   Choose a command from the list, please : ");
        System.out.println("1. Show the salary (amount) of all developers of a separate project ");
        System.out.println("2. Show a list of all developers of a separate project");
        System.out.println("3. Show a list of all Java developers ");
        System.out.println("4. Show a list of all Middle developers");
        System.out.println("5. Show amount of developers of all projects");
        System.out.println("m. Go to main menu ");
        System.out.println("z. Exit ");

        String command = scanner.next();
        switch (command) {
            case "1":
                salaryDevelopersOfProject();
                desc();
                break;
            case "2":
                listDevelopersOfProject();
                desc();
                break;
            case "3":
                listJavaDevelopers();
                desc();
                break;
            case "4":
                listMiddleDevelopers();
                desc();
                break;
            case "5":
                amountDevelopersOfProject();
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

    private void salaryDevelopersOfProject() {
        showProjects();
        System.out.println("Enter id project");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        project = projectsDao.getById(id);
        if (project != null) {
            int sum = project.getSumDevelopersSalary();
            System.out.println("Sum salary of developers in project with id - " + id + " is " + sum);
        }
    }

    private void listDevelopersOfProject() {
        showProjects();
        System.out.println("Enter id project");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        project = projectsDao.getById(id);
        if (project != null) {
            List<Developer> developerList = project.getDevelopers();
            developerList.forEach(developer -> System.out.println(developer));
        } else {
            System.out.println("On project with id " + id + "developers are not exist");
        }
    }

    private void listJavaDevelopers() {
        List<Skill> skillList = skillsDao.getSkillByName(skillName);
        System.out.println(" -- All Java developers -- ");
        if (skillList != null) {
            for (Skill skill : skillList) {
                System.out.println(skill.getDevelopersBySkillName());
            }
        } else {
            System.out.println("No data about " + skillName);
        }
    }

    private void listMiddleDevelopers() {
        List<Skill> skillList = skillsDao.getSkillByLevel(skillLevel);
        System.out.println(" -- All Middle developers -- ");
        if (skillList != null) {
            for (Skill skill : skillList) {
                System.out.println(skill.getDevelopersBySkillLevel());
            }
        } else {
            System.out.println("No data about " + skillLevel);
        }
    }

    private void amountDevelopersOfProject() {
        List<Project> projectList = projectsDao.getAll();
        if (projectList != null) {
            for (Project project : projectList) {
                System.out.println("Project date - " + project.getDate() +
                        ", project name - " + project.getName() +
                        ", developers amount - " + project.getDevelopers().size());
            }
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
