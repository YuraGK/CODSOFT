package ADDRESS_BOOK_SYSTEM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;




public class Main {

	private static JFrame frame = new JFrame("ADDRESS_BOOK_SYSTEM");
	private static final String SAVE_FILE_NAME = "data.txt";
	private static AddressBook addressBook = new AddressBook();
	private static DefaultListModel<String> model = new DefaultListModel<String>();

	public static void main(String[] args) {
		showWindow();

	}
	
	public static void showWindow() {
		frame.setBounds(100,100,1000,680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel statusLabel = new JLabel("ADDRESS BOOK SYSTEM");
		statusLabel.setBounds(162, 50, 300, 30);
		frame.getContentPane().add(statusLabel);
		
		JLabel resultLabel = new JLabel(" ");
		resultLabel.setBounds(162, 70, 300, 30);
		frame.getContentPane().add(resultLabel);
		
		JButton addButton = new JButton("Add/Edit");
		addButton.setBounds(50, 250, 100, 30);
		frame.getContentPane().add(addButton);
		
		JButton deleteButton = new JButton("Remove");
		deleteButton.setBounds(50, 300, 100, 30);
		frame.getContentPane().add(deleteButton);
		
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(50, 350, 100, 30);
		frame.getContentPane().add(saveButton);
		
		JButton downloadButton = new JButton("Load");
		downloadButton.setBounds(50, 400, 100, 30);
		frame.getContentPane().add(downloadButton);
		
		JList<String> list = new JList<String>(model);
		list.setBounds(150, 100, 800, 400);
		frame.getContentPane().add(list);
		
		JTextField inputNameField = new JTextField("");
		inputNameField.setBounds(150, 520, 100, 30);
		frame.getContentPane().add(inputNameField);
		inputNameField.setToolTipText("Input Name");
		inputNameField.setEnabled(false);
		inputNameField.setVisible(false);
		
		JTextField inputPhone_numberField = new JTextField("");
		inputPhone_numberField.setBounds(260, 520, 100, 30);
		frame.getContentPane().add(inputPhone_numberField);
		inputPhone_numberField.setToolTipText("Input Phone number");
		inputPhone_numberField.setEnabled(false);
		inputPhone_numberField.setVisible(false);
		
		JTextField inputEmailField = new JTextField("");
		inputEmailField.setBounds(370, 520, 100, 30);
		frame.getContentPane().add(inputEmailField);
		inputEmailField.setToolTipText("Input Email");
		inputEmailField.setEnabled(false);
		inputEmailField.setVisible(false);
		
		JButton confirmButton = new JButton("confirm");
		confirmButton.setBounds(480, 520, 100, 30);
		frame.getContentPane().add(confirmButton);
		confirmButton.setEnabled(false);
		confirmButton.setVisible(false);
		
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputNameField.setEnabled(true);
				inputNameField.setVisible(true);
				inputPhone_numberField.setEnabled(true);
				inputPhone_numberField.setVisible(true);
				inputEmailField.setEnabled(true);
				inputEmailField.setVisible(true);
				confirmButton.setEnabled(true);
				confirmButton.setVisible(true);
				if(!list.isSelectionEmpty()) {
					inputNameField.setText(model.getElementAt(list.getSelectedIndex()).split(" ")[0]);
					inputPhone_numberField.setText(model.getElementAt(list.getSelectedIndex()).split(" ")[1]);
					inputEmailField.setText(model.getElementAt(list.getSelectedIndex()).split(" ")[2]);
				}
		}});
		
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.getElementAt(list.getSelectedIndex());
				Contact contact = addressBook.findContact(model.getElementAt(list.getSelectedIndex()).split(" ")[0]);
				if(contact!=null) {
					addressBook.removeContact(contact);
					model.removeElement(contact);
					resultLabel.setText("Deleted succesfully");
					inputPhone_numberField.setEnabled(false);
					inputPhone_numberField.setVisible(false);
					inputPhone_numberField.setText(null);
					confirmButton.setEnabled(false);
					confirmButton.setVisible(false);
					model = new DefaultListModel<String>();
					int i = 0;
					for(Contact s : addressBook.getContacts()) {
						model.add(i, s.getName()+" "+s.getPhoneNumber()+" "+s.getEmail());
						i++;
					}
					list.setModel(model);
				}else {
					resultLabel.setText("No student found for "+inputPhone_numberField.getText()+" input");
				}
				
				
				
			}});
		
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String phonePatterns 
			      = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" 
			      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" 
			      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
				
				Pattern phonePattern = Pattern.compile(phonePatterns);
				Pattern emailPattern = Pattern.compile("^(.+)@(\\S+)$");
				
				Matcher phoneMatcher = phonePattern.matcher(inputPhone_numberField.getText());
				Matcher emailMatcher = emailPattern.matcher(inputEmailField.getText());
				
				if(inputPhone_numberField.getText().equals("")&& inputEmailField.getText().equals("")) {
					resultLabel.setText("Either Phone number or Email should be inserted");
					
				}
				else {
					if(!inputPhone_numberField.getText().equals("") && !phoneMatcher.matches()) {
						resultLabel.setText("Invalid input for 'Phone_number' field");
						return;
					}
					if(!inputEmailField.getText().equals("") && !emailMatcher.matches()) {
						resultLabel.setText("Invalid input for 'Email' field");
						return;
					}
				try {
					addressBook.addContact(new Contact(inputNameField.getText(),
						inputPhone_numberField.getText(), inputEmailField.getText()));
				model = new DefaultListModel<String>();
				int i = 0;
				for(Contact s : addressBook.getContacts()) {
					model.add(i, s.getName()+" "+s.getPhoneNumber()+" "+s.getEmail());
					i++;
				}
				list.setModel(model);
				inputNameField.setEnabled(false);
				inputNameField.setVisible(false);
				inputNameField.setText(null);
				inputPhone_numberField.setEnabled(false);
				inputPhone_numberField.setVisible(false);
				inputPhone_numberField.setText(null);
				inputEmailField.setEnabled(false);
				inputEmailField.setVisible(false);
				inputEmailField.setText(null);
				confirmButton.setEnabled(false);
				confirmButton.setVisible(false);
				resultLabel.setText("Succesfully added/edited");
				}catch(NumberFormatException ex) {
					resultLabel.setText("Invalid input for 'Phone_number' field");
				}}
			}});
		
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = "";
				File file = new File(SAVE_FILE_NAME);
				
				for(Object tex : model.toArray()) {
					text+=tex+"\n";
				}
				
				try(FileWriter fr = new FileWriter(file)) {
					fr.append(text);
					fr.flush();
					resultLabel.setText("Saved succesfully to file: "+SAVE_FILE_NAME);
				} catch (IOException e1) {
					resultLabel.setText("Error while saving");
					e1.printStackTrace();
				}
				
				
			}
			});
		
		downloadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model = new DefaultListModel<String>();
				File file = new File(SAVE_FILE_NAME);
				try(FileReader fr = new FileReader(file);
						Scanner scan = new Scanner(fr);) {
					int i = 0;
					String temp ="";
					while (scan.hasNextLine()) {
						temp = scan.nextLine();
						addressBook.addContact(new Contact(temp.split(" ")[0],
								temp.split(" ")[1], temp.split(" ")[2]));
						model.add(i, temp);
						
						i++;
					}
					
					
					list.setModel(model);
					
					
					resultLabel.setText("Downloaded succesfully from file: "+SAVE_FILE_NAME);
				} catch (IOException e1) {
					e1.printStackTrace();
					resultLabel.setText("Error while downloading");
				}
				
				
			}
			});
		
		
		frame.setVisible(true);
	}

}
