public class Chapter1_Challenge_1_2 {

    public static void main(String[] args) {
        String[] winningNumbers = {"12-34-56-78-90", "33-44-11-66-22", "01-02-03-04-05"};
        
        // Variables to track the winner
        double highestAverage = -1.0;
        String winningTicket = "";

        // Outer loop: Use a for-each loop to iterate over each lottery ticket (String)
        for (String ticket : winningNumbers) {
            System.out.println("Analyzing: " + ticket);
            
            // 1. Remove the dashes to form one continuous string
            String continuousDigits = ticket.replace("-", "");
            
            // 2. Create a new array of integer values from this string (by character)
            char[] digitChars = continuousDigits.toCharArray();
            
            int digitSum = 0;
            
            // Inner loop: Use a standard for loop to process the digits
            for (int i = 0; i < digitChars.length; i++) {
                // Convert char to its integer value and calculate sum
                int digit = Character.getNumericValue(digitChars[i]);
                digitSum += digit;
            }
            
            // 3. Calculate the average of the digits
            double digitAverage = (double) digitSum / digitChars.length;
            
            // Print analysis for the current ticket
            System.out.printf("Digit Sum: %d, Digit Average: %.1f%n", digitSum, digitAverage);

            // 4. Find which winning number has the highest average
            if (digitAverage > highestAverage) {
                highestAverage = digitAverage;
                winningTicket = ticket;
            }
        }

        // Final announcement
        System.out.printf("%nThe winning number with the highest average is: %s with an average of %.1f%n", 
                          winningTicket, highestAverage);
    }
}