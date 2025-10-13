import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

/**
 * BouncingTextApplet: A simple demonstration of a deprecated Java Applet
 * that uses a Thread to create a basic animation.
 *
 * NOTE: This uses deprecated APIs as required by the 'Applet Archaeologist' challenge.
 */
public class BouncingTextApplet extends Applet implements Runnable {

    // --- State Variables ---
    private Thread animationThread; // The thread for the animation [cite: 7]
    private String textToDisplay = "My Name (Mohammed Oumare)"; // Initialize String with your name [cite: 6]
    private int xCoordinate = 0; // Current x-coordinate for the text
    private int yCoordinate; // Will be calculated in init()
    private int xSpeed = 5; // Speed/step size for the bounce

    // --- Applet Lifecycle Methods ---

    /**
     * Called by the browser or appletviewer when the applet is first loaded.
     * Use to initialize variables and set initial state.
     */
    @Override
    public void init() {
        // Set the applet's size and background color [cite: 6]
        this.setSize(400, 200);
        this.setBackground(Color.DARK_GRAY);

        // Set an arbitrary font for better visibility
        this.setFont(new Font("SansSerif", Font.BOLD, 18));

        // Center the text vertically. Assuming font metrics are not needed for a simple challenge.
        // A rough center is half the height.
        yCoordinate = this.getHeight() / 2;
    }

    /**
     * Called by the browser/appletviewer after init() and every time the user
     * navigates back to the page containing the applet.
     * This is where the thread is created and started for the animation[cite: 7].
     */
    @Override
    public void start() {
        if (animationThread == null) {
            // Create and start a new thread [cite: 7]
            animationThread = new Thread(this);
            animationThread.start();
        }
    }

    /**
     * Called by the browser/appletviewer when the user leaves the page.
     * Used to stop the thread and pause the animation[cite: 8].
     */
    @Override
    public void stop() {
        // Stop the thread [cite: 8]
        if (animationThread != null) {
            // Setting the thread reference to null will allow the thread's run() method
            // to exit its loop naturally when animationThread != this condition is used.
            // For this simple challenge, we'll stop the thread directly as per older practice.
            animationThread = null;
        }
    }

    // --- Animation Logic (Runnable Interface) ---

    /**
     * The thread's code. This contains the animation logic[cite: 9].
     */
    @Override
    public void run() {
        // Use a while loop to forever update the animation [cite: 9]
        // The condition ensures the thread stops gracefully when stop() is called.
        Thread currentThread = Thread.currentThread();
        while (currentThread == animationThread) {
            try {
                // Update the x-coordinate of your text [cite: 10]
                xCoordinate += xSpeed;

                // If the text hits the right edge, reset its position [cite: 11]
                // A better implementation would reverse direction, but the challenge asks to "reset its position."
                if (xCoordinate > this.getWidth()) {
                    xCoordinate = -getFontMetrics(getFont()).stringWidth(textToDisplay); // Reset to off-screen left
                }

                // Call repaint() to request the appletviewer/browser to redraw the screen [cite: 12]
                repaint();

                // Pause the animation for 100 milliseconds [cite: 13]
                Thread.sleep(100);

            } catch (InterruptedException e) {
                // Good practice to handle exceptions, even if simple
                System.err.println("Animation thread interrupted: " + e.getMessage());
                // Exit the loop upon interruption
                return;
            }
        }
    }

    // --- Drawing Method ---

    /**
     * Called every time repaint() is called. This handles the actual drawing.
     */
    @Override
    public void paint(Graphics g) {
        // Set the color for the text
        g.setColor(Color.WHITE);

        // Use g.drawString() to draw your text at the current x-coordinate [cite: 14]
        g.drawString(textToDisplay, xCoordinate, yCoordinate);
    }
}