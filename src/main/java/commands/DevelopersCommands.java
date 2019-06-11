package commands;

import dao.DevelopersDao;
import dao.SkillsDao;
import entity.Developer;
import entity.Skill;

import java.util.List;
import java.util.Scanner;

public class DevelopersCommands implements Commands {
   private Scanner scanner = new Scanner(System.in);
   private DevelopersDao developersDao = new DevelopersDao();
   private SkillsDao skillsDao = new SkillsDao();
   private Developer developer = new Developer();

    public DevelopersCommands() {
        desc();
    }

    @Override
    public void desc() {
        System.out.println(" \n -- Menu of table Developers -- \n");

        System.out.println("   Choose a command from the list, please : ");
        System.out.println("1. Create new developer ");
        System.out.println("2. Show info about developer ");
        System.out.println("3. Update info for developer ");
        System.out.println("4. Show all developers ");
        System.out.println("5. Delete developer ");

        System.out.println("6. Show all skills of developer ");
        System.out.println("7. Insert skills to developer");
        System.out.println("m. Go to main menu ");
        System.out.println("z. Exit");


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
                showAllSkillsOfDeveloper();
                desc();
                break;
            case "7":
                insertSkillToDeveloper();
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
        System.out.println("Enter info for a new developer");
        System.out.print("Name -\t");
        String name = scanner.next();
        System.out.print("Surname -\t");
        String surname = scanner.next();
        System.out.print("Sex -\t");
        String sex = scanner.next();
        System.out.print("Salary -\t");
        int salary = scanner.nextInt();

        developer.setName(name);
        developer.setSurname(surname);
        developer.setSex(sex);
        developer.setSalary(salary);
        developersDao.save(developer);

        System.out.println("Added new developer!");
    }

    @Override
    public void getByID() {
        System.out.print("Enter id developer \t");
        long id = scanner.nextInt();
        developer = developersDao.getById(id);

        if (developer != null) {
            System.out.println(developer);
        } else {
            System.out.println("Developer with id " + id + " not exist");
        }
    }

    @Override
    public void update() {
        System.out.println("Enter information for  developer update");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        System.out.print("Name -\t");
        String name = scanner.next();
        System.out.print("Surname -\t");
        String surname = scanner.next();
        System.out.print("Sex -\t");
        String sex = scanner.next();
        System.out.print("Salary -\t");
        int salary = scanner.nextInt();

        developer.setName(name);
        developer.setSurname(surname);
        developer.setSex(sex);
        developer.setSalary(salary);
        developer.setId(id);
        if (developersDao.getById(id) != null) {
            developersDao.update(developer);
            System.out.println("Developer with id " + id + " is updated");
        } else {
            System.out.println("Developer with ID " + id + " not exist");
        }
    }

    @Override
    public void showAll() {
        List<Developer> developerList = developersDao.getAll();
        if (developerList != null) {
            developerList.forEach(developer -> { System.out.println(developer); });
        } else {
            System.out.println("Developers table is empty");
        }
    }

    @Override
    public void deleteByID() {
        System.out.println("Enter ID to delete developer ");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        Developer developer = developersDao.getById(id);
        if (developer != null) {
            developersDao.delete(developer);
            System.out.println("Developer with ID " + id + " was deleted");
        } else {
            System.out.println("Developer with ID " + id + " not exist");
        }
    }

    private void showAllSkillsOfDeveloper() {
        showAll();
        System.out.println("Enter id developer");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        Developer developer = developersDao.getById(id);
        List<Skill> skillList = developer.getSkillsOfDeveloper();
        if (skillList != null) {
            skillList.forEach(skill -> {
                System.out.println(skill);
            });
        } else {
            System.out.println("There are no skills by id developer " + id);
        }
    }

    private void insertSkillToDeveloper() {
        System.out.print("Enter id developer -\t");
        long id_developer = scanner.nextInt();
        Developer developer = developersDao.getById(id_developer);
        if (developer != null) {
            showSkills();
            System.out.print("Enter id skill -\t");
            long id_skill = scanner.nextInt();
            Skill skill = skillsDao.getById(id_skill);
            if (skill != null){
                skillsDao.addSkillToDeveloper(developer, skill);
                System.out.println("Added skill to developer");
            }else {
                System.out.println("Skill not exist");
            }
        }else {
            System.out.println("Developer with ID " + id_developer + " not exist");
        }
    }

    public void showSkills() {
        List<Skill> skillList = skillsDao.getAll();
        if (skillList != null) {
            skillList.forEach(skill -> { System.out.println(skill); });
        } else {
            System.out.println("Skills table is empty");
        }
    }
}
