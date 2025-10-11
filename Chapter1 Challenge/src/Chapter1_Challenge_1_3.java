import java.util.Random;
import java.util.Scanner;

public class Chapter1_Challenge_1_3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int health = 100;
        int maxRooms = 5;
        boolean defeated = false;

        System.out.println("Starting Dungeon Crawl with " + health + " health!");

        // Loop 5 times for 5 rooms
        for (int room = 1; room <= maxRooms; room++) {
            System.out.printf("%nEntering room %d...%n", room);
            
            // Generate a random event (1: Trap, 2: Potion, 3: Monster)
            int event = rand.nextInt(3) + 1; 

            switch (event) {
                case 1: // Trap
                    int trapDamage = 20;
                    health -= trapDamage;
                    System.out.println("A trap sprung! You lost " + trapDamage + " health.");
                    break;
                
                case 2: // Healing Potion
                    int healAmount = 15;
                    health = Math.min(health + healAmount, 100); // Health capped at 100
                    System.out.println("You found a healing potion! Health is now " + health + (health == 100 ? " (capped)." : "."));
                    break;

                case 3: // Monster Battle
                    int monsterNumber = rand.nextInt(5) + 1; // Monster number is 1-5
                    int playerGuess;
                    
                    System.out.println("A monster appears! Guess a number (1-5) to defeat it:");

                    // Do-while loop for the guessing game
                    do {
                        System.out.print("Guess: ");
                        if (scanner.hasNextInt()) {
                            playerGuess = scanner.nextInt();
                            if (playerGuess != monsterNumber) {
                                System.out.println("Wrong! Try again.");
                            }
                        } else {
                            // Handle non-integer input to prevent crash
                            System.out.println("Invalid input. Guess a number (1-5).");
                            scanner.next(); // Consume invalid input
                            playerGuess = 0; // Keep the loop going
                        }
                    } while (playerGuess != monsterNumber);
                    
                    System.out.println("You defeated the monster!");
                    break;
            } // end switch

            // Check health after the event
            if (health <= 0) {
                System.out.printf("Health is now %d. You have been defeated in room %d.%n", health, room);
                defeated = true;
                break; // Exit the for loop early (Requirement: break statement)
            }
            
            System.out.println("Current Health: " + health);
        } // end for loop

        // Final result announcement
        if (!defeated) {
            System.out.printf("%n--- VICTORY ---%n", health);
            System.out.printf("You cleared the dungeon! Victorious with %d health!%n", health);
        }
        
        scanner.close();
    }
}
