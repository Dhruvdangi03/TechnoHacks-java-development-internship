import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Scanner;

public class PasswordGenerator {
    public static String generate(int length, int complexity, int type){
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()-_=+";

        HashMap<Character, Integer> check = new HashMap<>();

        StringBuilder allCharacters = new StringBuilder();
        if (type == 1) allCharacters.append(digits);
        else if (type >= 2) allCharacters.append(upperCase).append(lowerCase);
        if (type >= 3) allCharacters.append(digits).append(specialChars);

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length;) {
            int randomIndex = random.nextInt(allCharacters.length());

            if(complexity == 2){
                if(check.getOrDefault(allCharacters.charAt(randomIndex), 0) < 3){
                    check.put(allCharacters.charAt(randomIndex), check.getOrDefault(allCharacters.charAt(randomIndex), 0) +1);
                    password.append(allCharacters.charAt(randomIndex));
                    i++;
                }
            }

            else if(complexity == 3){
                if(!check.containsKey(allCharacters.charAt(randomIndex))){
                    check.put(allCharacters.charAt(randomIndex), 1);
                    password.append(allCharacters.charAt(randomIndex));
                    i++;
                }
            }

            else {
                password.append(allCharacters.charAt(randomIndex));
                i++;
            }
        }

        return password.toString();
    }

    public static int getValidInput(String prompt, String type) {
        Scanner scanner = new Scanner(System.in);
        int input;

        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                if(type.equals("length") && (input > 30 || input < 6)) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    input = getValidInput(prompt, type);
                }
                else if(!type.equals("length") && (input > 3 || input < 1)) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    input = getValidInput(prompt, type);
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        return input;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Random Password Generator!\n");

        int length = getValidInput("Please Enter the Size of Password (6 <= size <= 30) : ", "length");

        int type = getValidInput("Enter the desired type (1 for Numeric, 2 for Characters Only, 3 for Mixed) : ", "type");

        int complexity = 0;
        if(type > 1){
            complexity = getValidInput("Enter the desired complexity (1 for low, 2 for medium, 3 for high) : ", "complexity");
        }

        String password = generate(length, complexity, type);
        System.out.println("Generated Password: " + password);

        sc.close();
    }
}