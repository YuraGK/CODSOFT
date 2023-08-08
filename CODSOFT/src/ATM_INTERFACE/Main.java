package ATM_INTERFACE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main {
	static ATMimpl atm;

	public static void main(String[] args) {
		atm = new ATMimpl("User","cool@pass123",5000);
		showWindow();

	}
	
	public static void showWindow() {
		JFrame frame = new JFrame("ATM_INTERFACE");
		frame.setBounds(100,100,1000,680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextField inputTextField = new JTextField("");
		inputTextField.setBounds(262, 200, 100, 30);
		frame.getContentPane().add(inputTextField);
		inputTextField.setEnabled(false);
		inputTextField.setVisible(false);
		
		JLabel statusLabel = new JLabel("");
		statusLabel.setBounds(262, 150, 430, 30);
		frame.getContentPane().add(statusLabel);
		
		JLabel attemptsLabel = new JLabel("ATM INTERFACE");
		attemptsLabel.setBounds(262, 100, 400, 30);
		frame.getContentPane().add(attemptsLabel);
		
		JButton withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(100, 100, 120, 30);
		frame.getContentPane().add(withdrawButton);
		
		JButton depositButton = new JButton("Deposit");
		depositButton.setBounds(100, 150, 120, 30);
		frame.getContentPane().add(depositButton);
		
		JButton checkButton = new JButton("Check balance");
		checkButton.setBounds(100, 200, 120, 30);
		frame.getContentPane().add(checkButton);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBounds(262, 250, 120, 30);
		frame.getContentPane().add(confirmButton);
		confirmButton.setEnabled(false);
		confirmButton.setVisible(false);
		
		withdrawButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("Input sum to withdraw");
				inputTextField.setText(null);
				confirmButton.setEnabled(true);
				confirmButton.setVisible(true);
				inputTextField.setEnabled(true);
				inputTextField.setVisible(true);
				confirmButton.setText("Withdraw sum");
				for(ActionListener al : confirmButton.getActionListeners()) {
					confirmButton.removeActionListener(al);
				}
				
				confirmButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							double sum = Double.parseDouble(inputTextField.getText());
							if(sum==(double)0) {
								throw new NullPointerException();
							}
							
							if(atm.withdraw(sum)) {
								statusLabel.setText("Transaction succeded.");
								confirmButton.setEnabled(false);
								confirmButton.setVisible(false);
								inputTextField.setEnabled(false);
								inputTextField.setVisible(false);
							}else {
								statusLabel.setText("Transaction failed. Requested sum is bigger than this account`s balance"); 
							}
							
						}catch(NullPointerException ex1) {
							statusLabel.setText("Error. Input field is empty.");
						}catch(NumberFormatException ex2) {
							statusLabel.setText("Error. Input value is not a number.");
						}
						
						
						
					}});
			}
			
		});
		
		depositButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("Input sum to deposit");
				inputTextField.setText(null);
				confirmButton.setEnabled(true);
				confirmButton.setVisible(true);
				inputTextField.setEnabled(true);
				inputTextField.setVisible(true);
				confirmButton.setText("Deposit sum");
				for(ActionListener al : confirmButton.getActionListeners()) {
					confirmButton.removeActionListener(al);
				}
				
				confirmButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							double sum = Double.parseDouble(inputTextField.getText());
							if(sum==(double)0) {
								throw new NullPointerException();
							}
							
							atm.deposit(sum);
							statusLabel.setText("Transaction succeded.");
							confirmButton.setEnabled(false);
							confirmButton.setVisible(false);
							inputTextField.setEnabled(false);
							inputTextField.setVisible(false);
							
							
						}catch(NullPointerException ex1) {
							statusLabel.setText("Error. Input field is empty.");
						}catch(NumberFormatException ex2) {
							statusLabel.setText("Error. Input value is not a number.");
						}
						
						
						
					}});
			}
			
		});
		
		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("Account`s current balance: "+atm.checkBalance());
			}
			
		});
		
		
		frame.setVisible(true);
	}

}
