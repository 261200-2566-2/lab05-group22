import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Wizard_character wizard = new Wizard_character("Gandalf", 5, 20);
        Warrior_character warrior = new Warrior_character("Aragorn", 5, 25);

        boolean exit = false;

        while (!exit) {
            System.out.println("-----------------------");
            System.out.println("Choose an action:");
            System.out.println("1. Show Wizard Stats");
            System.out.println("2. Show Warrior Stats");
            System.out.println("3. Wizard attacks Warrior");
            System.out.println("4. Warrior attacks Wizard");
            System.out.println("5. Wizard casts a spell");
            System.out.println("6. Wizard heals");
            System.out.println("7. Warrior boosts speed");
            System.out.println("8. Exit");
            System.out.println("-----------------------");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    wizard.ShowStat();
                    break;
                case 2:
                    warrior.ShowStat();
                    break;
                case 3:
                    warrior.beAttacked(wizard.attack());
                    System.out.println("Wizard attacks Warrior!");
                    break;
                case 4:
                    wizard.beAttacked(warrior.attack());
                    System.out.println("Warrior attacks Wizard!");
                    break;
                case 5:
                    wizard.castSpell();
                    break;
                case 6:
                    wizard.healing();
                    break;
                case 7:
                    warrior.BoostSpeed();
                    System.out.println("Warrior boosts speed!");
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        }

        System.out.println("Exiting the program.");
    }
}
