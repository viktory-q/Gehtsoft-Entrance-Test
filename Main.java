import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1":
                        handleCaesarEncryption(scanner);
                        break;
                    case "2":
                        handleCaesarDecryption(scanner);
                        break;
                    case "3":
                        handleExpressionEvaluation(scanner);
                        break;
                    case "4":
                        System.out.println("Exiting program...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter 1-4.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.print("\nContinue? (y/n): ");
            if (!scanner.nextLine().trim().toLowerCase().equals("y")) {
                System.out.println("Exiting program...");
                scanner.close();
                break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nWelcome to Gehtsoft Technical Assessment");
        System.out.println("Please choose an option:");
        System.out.println("1. Caesar Cipher Encryption");
        System.out.println("2. Caesar Cipher Decryption");
        System.out.println("3. Arithmetic Expression Evaluation");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleCaesarEncryption(Scanner scanner) throws IOException {
        System.out.print("Enter 'c' for console input or 'f' for file input: ");
        String inputType = scanner.nextLine().trim().toLowerCase();
        String text;
        if (inputType.equals("f")) {
            System.out.print("Enter file path: ");
            String filePath = scanner.nextLine().trim();
            text = readFile(filePath);
        } else {
            System.out.print("Enter text to encrypt: ");
            text = scanner.nextLine();
        }
        System.out.print("Enter shift value: ");
        int shift = Integer.parseInt(scanner.nextLine().trim());
        String result = CaesarCipher.encrypt(text, shift);
        System.out.println("Result: " + result);
    }

    private static void handleCaesarDecryption(Scanner scanner) throws IOException {
        System.out.print("Enter 'c' for console input or 'f' for file input: ");
        String inputType = scanner.nextLine().trim().toLowerCase();
        String text;
        if (inputType.equals("f")) {
            System.out.print("Enter file path: ");
            String filePath = scanner.nextLine().trim();
            text = readFile(filePath);
        } else {
            System.out.print("Enter text to decrypt: ");
            text = scanner.nextLine();
        }
        System.out.print("Enter shift value (or '0' to try without shift): ");
        int shift = Integer.parseInt(scanner.nextLine().trim());
        String result;
        if (shift == 0) {
            result = CaesarCipher.decryptWithoutShift(text);
        } else {
            result = CaesarCipher.decrypt(text, shift);
        }
        System.out.println("Result: " + result);
    }

    private static void handleExpressionEvaluation(Scanner scanner) {
        System.out.print("Enter arithmetic expression: ");
        String expression = scanner.nextLine().trim();
        try {
            double result = ExpressionEvaluator.evaluate(expression);
            System.out.println("Result: " + (result == (int) result ? (int) result : result));
        } catch (Exception e) {
            System.out.println("Error evaluating expression: " + e.getMessage());
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString().trim();
    }
}