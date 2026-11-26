import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class ThreadCount {
    private final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        new ThreadCount().run();
    }

    private void run(){
        int choice;
        do {
            displayMenu();
            choice = readMenuChoice();
            handleChoice(choice);
        } while (choice != 4);
        scanner.close();
    }

    public void displayMenu(){
        System.out.println("\n~~~~ Yearn Console ~~~~");
        System.out.println("1. Overview Materials");
        System.out.println("2. Available Crafts");
        System.out.println("3. Current Projects");
        System.out.println("4. Exit");
    }

    private int readMenuChoice() {
        while (true){
            System.out.print("Choose Action: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <=4){
                    return choice;
                }
                System.out.println("Invalid input. Please choose between 1-4 and try again.");
            } catch (InputMismatchException e){
                System.out.println("Invalid input. Please input only numbers between 1-4 and try again.");
                scanner.nextLine();
            } catch (NoSuchElementException e){
                return 4;
            }
        }
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> System.out.println("\n[Overview Materials]");
            case 2 -> System.out.println("\n[Available Crafts]");
            case 3 -> System.out.println("\n[Current Projects]");
            case 4 -> System.out.println("\nGoodbye!");
        }
    }
}
