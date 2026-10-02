import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ValidateCheckDigits {

    public static void main(String[] args) {
        String inputFile = "accounts.txt";
        String outputFile = "valid_accounts.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            System.out.println("--- Account Validation Results ---");

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (isValidAccount(line)) {
                    System.out.println(line + " -> VALID");
                    writer.write(line);
                    writer.newLine();
                } else {
                    System.out.println(line + " -> INVALID");
                }
            }

            System.out.println("\nValid account numbers written to " + outputFile);

        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        }
    }

    private static boolean isValidAccount(String accountStr) {
        if (accountStr.length() != 6 || !accountStr.matches("\\d{6}")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += Character.getNumericValue(accountStr.charAt(i));
        }

        int remainder = sum % 10;
        int checkDigit = Character.getNumericValue(accountStr.charAt(5));

        return remainder == checkDigit;
    }
}