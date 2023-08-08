package NUMBER_GAME;


import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Main {
	static Random random = new Random();
	static int randomNumToGuess;
	static final int MINIMAL_NUMBER = 1;
	static final int MAXIMAL_NUMBER = 100;
	static final int MAX_NUMBER_OF_ATTEMPTS = 10;
	static int numberOfAttempts;
	

	public static void main(String[] args) {
		showWindow();

	}
	
	public static void showWindow() {
		JFrame frame = new JFrame("NUMBER_GAME");
		frame.setBounds(100,100,1000,680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextField inputTextField = new JTextField("");
		inputTextField.setBounds(450, 400, 100, 30);
		frame.getContentPane().add(inputTextField);
		inputTextField.setEnabled(false);
		
		JLabel statusLabel = new JLabel("Start a game!");
		statusLabel.setBounds(462, 350, 200, 30);
		frame.getContentPane().add(statusLabel);
		
		JLabel attemptsLabel = new JLabel("");
		attemptsLabel.setBounds(462, 450, 200, 30);
		frame.getContentPane().add(attemptsLabel);
		
		JButton playButton = new JButton("Play");
		playButton.setBounds(400, 500, 100, 30);
		
		JButton guessButton = new JButton("Guess");
		guessButton.setBounds(500, 500, 100, 30);
		
		guessButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					int guess = Integer.parseInt(inputTextField.getText());
					
					if(guess == randomNumToGuess) {
						statusLabel.setText("You won!");
						inputTextField.setEnabled(false);
						guessButton.setEnabled(false);
						playButton.setEnabled(true);
						attemptsLabel.setText("Attempts used: " + numberOfAttempts);
					}else {
						if(numberOfAttempts == MAX_NUMBER_OF_ATTEMPTS) {
							statusLabel.setText("You LOST!");
							inputTextField.setEnabled(false);
							guessButton.setEnabled(false);
							playButton.setEnabled(true);
							attemptsLabel.setText("Attempts used: " + numberOfAttempts);
						}else {
							if(guess > randomNumToGuess) {
								statusLabel.setText("The input is too big");
							}else{
								statusLabel.setText("The input is too small");
							}
							numberOfAttempts++;
							attemptsLabel.setText("Attempt number: " + numberOfAttempts+" out of "+MAX_NUMBER_OF_ATTEMPTS);
						}
						
					}
					
				}catch(NumberFormatException ex) {
					statusLabel.setText("Ooops... invalid input");
				}
				
				
			}
			
		});
		
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputTextField.setEnabled(true);
				guessButton.setEnabled(true);
				inputTextField.setText(null);
				numberOfAttempts = 1;
				statusLabel.setText("Guess the number from 1 to 100");
				attemptsLabel.setText("Attempt number: " + numberOfAttempts);
				randomNumToGuess = random.nextInt(MAXIMAL_NUMBER - MINIMAL_NUMBER) + MINIMAL_NUMBER;
				playButton.setEnabled(false);
			}
			
		});
		
		frame.getContentPane().add(playButton);
		frame.getContentPane().add(guessButton);
		guessButton.setEnabled(false);
		
		frame.setVisible(true);
	}

}
