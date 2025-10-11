import java.util.Scanner;

public class Chapter1_Challenge_1_1 {

    public static void main(String[] args) {
        // --- 1. Input Setup ---
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a single positive integer (e.g., 13579): ");
        int fullMessage = scanner.nextInt();
        scanner.close();

        // --- 2. Calculation using only Variables & Operators ---
        
        // Find the number of digits (n) and the divisor for the first digit (10^(n-1))
        // Math.log10(13579) is approx 4.13, so (int) is 4.
        int numDigits = (int) Math.log10(fullMessage) + 1;
        // The divisor is 10^(n-1). For 5 digits (n=5), power is 4.
        int firstDigitDivisor = (int) Math.pow(10, numDigits - 1); 

        // 1. Extract the first digit (1)
        int firstDigit = fullMessage / firstDigitDivisor; 
        
        // 2. Extract the last digit (9)
        int lastDigit = fullMessage % 10; 

        // 3. Find the product of the first and last digit (1 * 9 = 9)
        int product = firstDigit * lastDigit; 

        // 4. Extract the second-last digit (7)
        int secondLastDigit = (fullMessage % 100) / 10; 

        // 5. Extract the second digit (3)
        // a) Remove the first digit: 13579 - (1 * 10000) = 3579
        int afterFirstDigitRemoved = fullMessage % firstDigitDivisor;
        // b) Find the divisor to isolate the second digit: 10^(n-2). For 5 digits (n=5), 10^3 = 1000.
        int secondDigitDivisor = (int) Math.pow(10, numDigits - 2); 
        // c) Isolate the second digit: 3579 / 1000 = 3
        int secondDigit = afterFirstDigitRemoved / secondDigitDivisor; 
        
        // 6. Find the sum of the second digit and the second-last digit (3 + 7 = 10)
        int sum = secondDigit + secondLastDigit; 

        // 7. Create a final code by concatenating the product and the sum
        // Type conversion (integer to string) is necessary for concatenation
        String finalCode = "" + product + sum;

        // --- 3. Output ---
        System.out.println("The decrypted code is: " + finalCode);
    }
}
