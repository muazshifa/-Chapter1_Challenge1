import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Chapter1_Challenge_1_4 {

    // Note: The custom exception class is defined inline for simplicity 
    // when providing the code in one block, but should ideally be in its own file.
    public static class InvalidConfigVersionException extends Exception {
        public InvalidConfigVersionException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        
        BufferedReader reader = null; // Declare outside try for finally access

        try {
            // --- 1. Open File (Can throw FileNotFoundException) ---
            String fileName = "config.txt";
            reader = new BufferedReader(new FileReader(fileName));
            System.out.println("Successfully opened " + fileName);

            // --- 2. Read Config Version (Line 1) ---
            String versionLine = reader.readLine();
            if (versionLine == null) {
                throw new IOException("Config file is empty.");
            }
            
            // Can throw NumberFormatException
            int configVersion = Integer.parseInt(versionLine); 
            
            System.out.println("Config Version: " + configVersion);

            // --- 3. Check Version and Throw Custom Exception ---
            if (configVersion < 2) {
                // Requirement: throw custom exception
                throw new InvalidConfigVersionException("Config version too old! (Minimum version is 2)"); 
            }
            
            // --- 4. Read File Path (Line 2) ---
            String filePath = reader.readLine();
            if (filePath == null) {
                throw new IOException("Config file missing file path on line 2.");
            }
            
            System.out.println("Target File Path: " + filePath);
            
            // --- 5. Check Target File Existence and Throw IOException ---
            File targetFile = new File(filePath);
            if (!targetFile.exists()) {
                // Requirement: throw new IOException with a custom message
                throw new IOException("Target file specified in config does not exist at: " + filePath); 
            }

            System.out.println("Configuration successfully validated.");

        } catch (FileNotFoundException e) {
            // Catches if config.txt itself is missing
            System.err.println("Error: The configuration file (" + e.getMessage() + ") was not found.");
        } catch (NumberFormatException e) {
            // Catches if Line 1 contains text instead of a number
            System.err.println("Error: Could not parse config version. First line must be an integer.");
        } catch (InvalidConfigVersionException e) {
            // Catches if the version is < 2
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            // Catches general read errors, or the custom path-not-found error
            System.err.println("Error: General I/O issue occurred: " + e.getMessage());
        } finally {
            // Requirement: This block must always execute
            System.out.println("Config read attempt finished.");
            
            // Clean up the resource
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Warning: Failed to close BufferedReader.");
                }
            }
        }
    }
}