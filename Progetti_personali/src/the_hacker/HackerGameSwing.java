package the_hacker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HackerGameSwing extends JFrame {
    private int currentLevel = 1;
    private final int maxLevels = 3;
    private boolean gameOver = false;
    private int timeLeft;
    private Timer timer;
    private Random random = new Random();

    // GUI components
    private JTextArea outputArea;
    private JTextField inputField;
    private JLabel timerLabel;
    private JLabel levelLabel;
    private JButton submitButton;
    private String currentChallengeType;
    private String correctAnswer;

    public HackerGameSwing() {
        // Set up the main window
        setTitle("Hacker Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create a dark, terminal-like theme
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // Output area (terminal-like display)
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.GREEN);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Input panel (bottom)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBackground(Color.BLACK);

        inputField = new JTextField();
        inputField.setBackground(Color.DARK_GRAY);
        inputField.setForeground(Color.GREEN);
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        inputField.setCaretColor(Color.GREEN);
        inputPanel.add(inputField, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.setBackground(Color.DARK_GRAY);
        submitButton.setForeground(Color.GREEN);
        submitButton.setFocusPainted(false);
        inputPanel.add(submitButton, BorderLayout.EAST);

        // Status panel (top)
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout());
        statusPanel.setBackground(Color.BLACK);

        levelLabel = new JLabel("Level: 1");
        levelLabel.setForeground(Color.GREEN);
        levelLabel.setFont(new Font("Monospaced", Font.BOLD, 14));

        timerLabel = new JLabel("Time: 30s");
        timerLabel.setForeground(Color.GREEN);
        timerLabel.setFont(new Font("Monospaced", Font.BOLD, 14));

        statusPanel.add(levelLabel);
        statusPanel.add(Box.createHorizontalStrut(20));
        statusPanel.add(timerLabel);

        mainPanel.add(statusPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // Show tutorial and start game
        showTutorial();
        submitButton.addActionListener(new SubmitActionListener());
        inputField.addActionListener(new SubmitActionListener()); // Allow Enter key to submit
    }

    // Display the game tutorial
    private void showTutorial() {
        outputArea.setText("=== Hacker Game Tutorial ===\n" +
                "Welcome, Hacker! Your mission is to infiltrate secure systems.\n" +
                "How to Play:\n" +
                "- Each level represents a system to hack.\n" +
                "- Solve puzzles like guessing passwords or codes.\n" +
                "- You have 30 seconds per level.\n" +
                "- Type your answer in the input field and click Submit or press Enter.\n" +
                "- Correct answers advance you; wrong answers or timeout end the game.\n" +
                "Tips:\n" +
                "- Read hints carefully.\n" +
                "- Stay calm; the timer is ticking!\n" +
                "Click Submit to start hacking...\n");
        correctAnswer = ""; // Empty to prevent accidental submission
    }

    // Start a level
    private void startLevel() {
        if (currentLevel > maxLevels) {
            outputArea.setText("Congratulations! You've hacked all systems!\nGame Complete!");
            inputField.setEnabled(false);
            submitButton.setEnabled(false);
            stopTimer();
            return;
        }

        levelLabel.setText("Level: " + currentLevel);
        timeLeft = 30;
        timerLabel.setText("Time: " + timeLeft + "s");
        inputField.setText("");
        inputField.setEnabled(true);
        submitButton.setEnabled(true);
        currentChallengeType = getChallengeType();
        displayChallenge();
        startTimer();
    }

    // Randomly select a challenge type
    private String getChallengeType() {
        String[] challenges = {"password", "code", "sequence"};
        return challenges[random.nextInt(challenges.length)];
    }

    // Display the challenge based on type
    private void displayChallenge() {
        switch (currentChallengeType) {
            case "password":
                String[] passwords = {"admin123", "secret42", "hackme"};
                correctAnswer = passwords[currentLevel - 1];
                outputArea.setText("=== Level " + currentLevel + ": Infiltrating System ===\n" +
                        "Challenge: Guess the password.\n" +
                        "Hint: Common passwords are often simple. Try something like 'admin123' or 'secret42'.\n" +
                        "Enter password:");
                break;
            case "code":
                correctAnswer = String.valueOf(random.nextInt(9000) + 1000); // 4-digit code
                outputArea.setText("=== Level " + currentLevel + ": Infiltrating System ===\n" +
                        "Challenge: Enter the correct 4-digit code.\n" +
                        "Hint: The code is a number between 1000 and 9999.\n" +
                        "Enter code:");
                break;
            case "sequence":
                correctAnswer = "7"; // Sequence: 1, 3, 5, next is 7
                outputArea.setText("=== Level " + currentLevel + ": Infiltrating System ===\n" +
                        "Challenge: Identify the next number in the sequence.\n" +
                        "Sequence: 1, 3, 5\n" +
                        "Enter the next number:");
                break;
        }
    }

    // Start the timer
    private void startTimer() {
        stopTimer(); // Ensure no previous timer is running
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (timeLeft > 0) {
                        timeLeft--;
                        timerLabel.setText("Time: " + timeLeft + "s");
                        if (timeLeft == 10 || timeLeft <= 5) {
                            outputArea.append("\nWarning: " + timeLeft + " seconds left!");
                        }
                    } else {
                        outputArea.setText("Time's up! System lockdown triggered. Game Over!");
                        inputField.setEnabled(false);
                        submitButton.setEnabled(false);
                        gameOver = true;
                        stopTimer();
                    }
                });
            }
        }, 1000, 1000);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    // Handle submit button or Enter key
    private class SubmitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (outputArea.getText().contains("Tutorial")) {
                startLevel();
                return;
            }

            if (gameOver || currentLevel > maxLevels) {
                return;
            }

            String userInput = inputField.getText().trim();
            if (userInput.equals(correctAnswer)) {
                stopTimer();
                outputArea.setText("Access granted! System hacked!\nMoving to next level...");
                currentLevel++;
                inputField.setEnabled(false);
                submitButton.setEnabled(false);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        SwingUtilities.invokeLater(() -> startLevel());
                    }
                }, 2000); // Delay before next level
            } else {
                stopTimer();
                outputArea.setText("Access denied! Incorrect input. Game Over!");
                inputField.setEnabled(false);
                submitButton.setEnabled(false);
                gameOver = true;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HackerGameSwing game = new HackerGameSwing();
            game.setVisible(true);
        });
    }
}