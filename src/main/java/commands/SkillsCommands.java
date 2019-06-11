package commands;

import dao.SkillsDao;
import entity.Skill;

import java.util.List;
import java.util.Scanner;

public class SkillsCommands implements Commands {
    private Scanner scanner = new Scanner(System.in);
    private SkillsDao skillsDao = new SkillsDao();
    private Skill skill = new Skill();

    public SkillsCommands() {
        desc();
    }

    @Override
    public void desc() {
        System.out.println("\n  -- Menu of table Skills -- \n");

        System.out.println("   Choose a command from the list, please : ");
        System.out.println("1. Create new skill ");
        System.out.println("2. Show info about skill ");
        System.out.println("3. Update info for skill ");
        System.out.println("4. Show all skills ");
        System.out.println("5. Delete skill ");
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
        System.out.println("Enter info for a new skill");
        System.out.print("Name -\t");
        String name = scanner.next();
        System.out.print("Level -\t");
        String level = scanner.next();
        skill.setName(name);
        skill.setLevel(level);
        skillsDao.save(skill);
        System.out.println("Added new skill!");
    }

    @Override
    public void getByID() {
        System.out.print("Enter id skill\t");
        long id = scanner.nextInt();
        skill = skillsDao.getById(id);
        if (skill != null) {
            System.out.println(skill);
        } else {
            System.out.println("Skill not exist");
        }
    }

    @Override
    public void update() {
        System.out.println("Enter information for skill update");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        System.out.print("Name -\t");
        String name = scanner.next();
        System.out.print("Level -\t");
        String level = scanner.next();

        skill.setName(name);
        skill.setLevel(level);
        skill.setId(id);
        if (skillsDao.getById(id) != null) {
            skillsDao.update(skill);
            System.out.println("Skill with ID " + id + " is updated");
        } else {
            System.out.println("Skill with ID " + id + " not exist");
        }
    }

    @Override
    public void showAll() {
        List<Skill> skillList = skillsDao.getAll();
        if (skillList != null) {
            skillList.forEach(skill -> System.out.println(skill));
        } else {
            System.out.println("Skills table is empty");
        }
    }

    @Override
    public void deleteByID() {
        System.out.println("Enter ID to delete skill");
        System.out.print("ID -\t");
        long id = scanner.nextInt();
        skill = skillsDao.getById(id);
        if (skill != null) {
            skillsDao.delete(skill);
            System.out.println("Skill with ID + " + id + " was deleted");
        } else {
            System.out.println("Skill not exist");
        }
    }
}
