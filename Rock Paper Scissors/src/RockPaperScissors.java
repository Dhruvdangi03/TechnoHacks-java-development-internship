import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors{

    public static void playGame(String name, int player, int computer){
        int move = getValidInput("Enter 1 for Rock, 2 for Paper, 3 for Scissors : ");

        System.out.println("Press 0 if you want to quit!");

        Random random = new Random();
        int pcmove = random.nextInt(1, 3);

        if(pcmove == 1 && move == 1) System.out.println("Rock vs Rock = Draw 🤨");
        else if(pcmove == 1 && move == 2) {
            System.out.println("Paper beats the Rock. You Win ✨🎉");
            player++;
        }
        else if(pcmove == 1 && move == 3) {
            System.out.println("Rock beats the Scissors. You Lost 😓😞");
            computer++;
        }
        else if(pcmove == 2 && move == 1) {
            System.out.println("Paper beats the rock. You Lost 😓😞");
            computer++;
        }
        else if(pcmove == 2 && move == 2) System.out.println("Paper vs Paper = Draw 🤨");
        else if(pcmove == 2 && move == 3) {
            System.out.println("Scissors beats the Paper. You Win ✨🎉");
            player++;
        }
        else if(pcmove == 3 && move == 1) {
            System.out.println("Rock beats the Scissors. You Win ✨🎉");
            player++;
        }
        else if(pcmove == 3 && move == 2) {
            System.out.println("Scissors beats the Paper. You lost 😓😞");
            computer++;
        }
        else if(pcmove == 3 && move == 3) System.out.println("Scissors vs Scissors = Draw 🤨");

        System.out.println(name + "'s Score => " + player + "\n" + "Computer's Score => " + computer);
        if(move == 0) return;

        playGame(name, player, computer);
    }

    public static int getValidInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int input;

        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                if(input > 3 || input < 0) {
                    System.out.println("Invalid input. Please enter a valid Move.");
                    input = getValidInput(prompt);
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid Move.");
                scanner.nextLine();
            }
        }

        return input;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Your Name : ");
        String name = sc.next();

        playGame(name , 0, 0);
    }
}