package WORD_COUNTER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main {

	public static void main(String[] args) {
		showWindow();

	}
	
	public static long wordCounter(String text) {
		
		return Arrays.asList(text.split(" "))
			      .stream().filter(n -> !n.equals("")&&!n.equals(" ")&&!n.equals(null))
			      .count();
		
	}
	
	public static void showWindow() {
		JFrame frame = new JFrame("WORD COUNTER");
		frame.setBounds(100,100,1000,680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		JTextField inputTextField = new JTextField("");
		inputTextField.setBounds(150, 120, 100, 30);
		frame.getContentPane().add(inputTextField);
		
		JLabel statusLabel = new JLabel("Input text of .txt file");
		statusLabel.setBounds(162, 50, 300, 30);
		frame.getContentPane().add(statusLabel);
		
		JLabel resultLabel = new JLabel(" ");
		resultLabel.setBounds(162, 70, 300, 30);
		frame.getContentPane().add(resultLabel);
		
		JButton showFileFieldButton = new JButton("Use file");
		showFileFieldButton.setBounds(50, 200, 100, 30);
		frame.getContentPane().add(showFileFieldButton);
		
		JButton playButton = new JButton("Commence");
		playButton.setBounds(50, 250, 100, 30);
		frame.getContentPane().add(playButton);
		
		
		JFileChooser fileField = new JFileChooser();
		fileField.setBounds(150, 150, 750, 400);
		frame.getContentPane().add(fileField);
		fileField.setVisible(false);
		fileField.setEnabled(false);
		
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String text = "";
				if(inputTextField.isEnabled()) {
					text = inputTextField.getText();
					
				}else {
					File file = fileField.getSelectedFile();
					try(FileReader fr = new FileReader(file);
							Scanner scan = new Scanner(fr);) {
						while (scan.hasNextLine()) {
							text += scan.nextLine();
						}
						
								
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if(text=="") {
					return;
				}
				
				long uniqueNum = Arrays.asList(text.split(" "))
					      .stream().filter(n -> !n.equals("")&&!n.equals(" ")&&!n.equals(null))
					      .distinct()
					      .count();
				statusLabel.setText("The amount of words in this text is: " + wordCounter(text));
				resultLabel.setText("The amount of unique words is: " + uniqueNum);
				fileField.setEnabled(false);
				fileField.setVisible(false);
				inputTextField.setEnabled(true);
				inputTextField.setVisible(true);
				showFileFieldButton.setText("Use file");
			}
			
		});
		
		showFileFieldButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(inputTextField.isEnabled()) {
					inputTextField.setEnabled(false);
					inputTextField.setVisible(false);
					inputTextField.setText(null);
					fileField.setEnabled(true);
					fileField.setVisible(true);
					showFileFieldButton.setText("Use text");
				}else {
					inputTextField.setEnabled(true);
					inputTextField.setVisible(true);
					fileField.setEnabled(false);
					fileField.setVisible(false);
					fileField.cancelSelection();
					showFileFieldButton.setText("Use file");
				}
				
			}
			
		});
		
		
		frame.setVisible(true);
	}

}


