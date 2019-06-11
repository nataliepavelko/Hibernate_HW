package commands;

import java.util.Scanner;

public class MainCommands {
    private Scanner scanner = new Scanner(System.in);

    public MainCommands() {
        desc();
    }

    private void desc() {
        System.out.println(" --------- Main menu --------- \n");

        System.out.println("Choose a command from the list, please : ");
        System.out.println("1. Go to table Developers ");
        System.out.println("2. Go to table Companies ");
        System.out.println("3. Go to table Customers ");
        System.out.println("4. Go to table Projects ");
        System.out.println("5. Go to table Skills ");
        System.out.println("6. Go to another commands ");
        System.out.println("z. Exit ");


        String command = scanner.next();
        switch (command) {
            case "1":
                new DevelopersCommands();
                break;
            case "2":
                new CompaniesCommands();
                break;
            case "3":
                new CustomersCommands();
                break;
            case "4":
                new ProjectsCommands();
                break;
            case "5":
                new SkillsCommands();
                break;
            case "6":
                new AnotherCommands();
                break;
            case "z":
                System.out.println(" --------- Exit ---------");
                scanner.close();
                break;
            default:
                System.out.println("Unknown command. Please try again. ");
                desc();
        }
    }
}
