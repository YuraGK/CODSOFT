package STUDENT_MANAGEMENT_SYSTEM;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

public class Main {
	private static StudentManagementSystem studentManagementSystem = new StudentManagementSystem();
	private static DefaultListModel<String> model = new DefaultListModel<String>();
	private static JFrame frame = new JFrame("STUDENT MANAGEMENT SYSTEM");
	private static final String SAVE_FILE_NAME = "data.txt";
	public static void main(String[] args) {
		
		showWindow();

	}
	
	
	public static void showWindow() {
		
		frame.setBounds(100,100,1000,680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel statusLabel = new JLabel("STUDENT MANAGEMENT SYSTEM");
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
		
		JTextField inputRoll_numberField = new JTextField("");
		inputRoll_numberField.setBounds(260, 520, 100, 30);
		frame.getContentPane().add(inputRoll_numberField);
		inputRoll_numberField.setToolTipText("Input Roll number");
		inputRoll_numberField.setEnabled(false);
		inputRoll_numberField.setVisible(false);
		
		JTextField inputGradeField = new JTextField("");
		inputGradeField.setBounds(370, 520, 100, 30);
		frame.getContentPane().add(inputGradeField);
		inputGradeField.setToolTipText("Input Grade");
		inputGradeField.setEnabled(false);
		inputGradeField.setVisible(false);
		
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
				inputRoll_numberField.setEnabled(true);
				inputRoll_numberField.setVisible(true);
				inputGradeField.setEnabled(true);
				inputGradeField.setVisible(true);
				confirmButton.setEnabled(true);
				confirmButton.setVisible(true);
				if(!list.isSelectionEmpty()) {
					inputNameField.setText(model.getElementAt(list.getSelectedIndex()).split(" ")[0]);
					inputRoll_numberField.setText(model.getElementAt(list.getSelectedIndex()).split(" ")[1]);
					inputGradeField.setText(model.getElementAt(list.getSelectedIndex()).split(" ")[2]);
				}
		}});
		
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.getElementAt(list.getSelectedIndex());
				Student student = studentManagementSystem.findStudent(Integer.parseInt(model.getElementAt(list.getSelectedIndex()).split(" ")[1]));
				if(student!=null) {
					studentManagementSystem.removeStudent(student);
					model.removeElement(student);
					resultLabel.setText("Deleted succesfully");
					inputRoll_numberField.setEnabled(false);
					inputRoll_numberField.setVisible(false);
					inputRoll_numberField.setText(null);
					confirmButton.setEnabled(false);
					confirmButton.setVisible(false);
					model = new DefaultListModel<String>();
					int i = 0;
					for(Student s : studentManagementSystem.getStudents()) {
						model.add(i, s.getName()+" "+s.getRoll_number()+" "+s.getGrade());
						i++;
					}
					list.setModel(model);
				}else {
					resultLabel.setText("No student found for "+inputRoll_numberField.getText()+" input");
				}
				
				
				
			}});
		
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				studentManagementSystem.addStudent(new Student(inputNameField.getText(),
						Integer.parseInt(inputRoll_numberField.getText()), inputGradeField.getText()));
				model = new DefaultListModel<String>();
				int i = 0;
				for(Student s : studentManagementSystem.getStudents()) {
					model.add(i, s.getName()+" "+s.getRoll_number()+" "+s.getGrade());
					i++;
				}
				list.setModel(model);
				inputNameField.setEnabled(false);
				inputNameField.setVisible(false);
				inputNameField.setText(null);
				inputRoll_numberField.setEnabled(false);
				inputRoll_numberField.setVisible(false);
				inputRoll_numberField.setText(null);
				inputGradeField.setEnabled(false);
				inputGradeField.setVisible(false);
				inputGradeField.setText(null);
				confirmButton.setEnabled(false);
				confirmButton.setVisible(false);
				resultLabel.setText("Succesfully added/edited");
				}catch(NumberFormatException ex) {
					resultLabel.setText("Invalid input for 'Roll_number' field");
				}
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
				File file = new File("data.txt");
				try(FileReader fr = new FileReader(file);
						Scanner scan = new Scanner(fr);) {
					int i = 0;
					String temp ="";
					while (scan.hasNextLine()) {
						temp = scan.nextLine();
						studentManagementSystem.addStudent(new Student(temp.split(" ")[0],
								Integer.parseInt(temp.split(" ")[1]), temp.split(" ")[2]));
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
