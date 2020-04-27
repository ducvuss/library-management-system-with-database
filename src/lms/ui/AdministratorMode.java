package lms.ui;

import java.sql.SQLException;
import java.util.Scanner;

import lms.service.AdminService;

public class AdministratorMode {

	private Scanner scanner;
	private AdminService adminService = new AdminService();
	private String currentEntity;

	public AdministratorMode(Scanner scanner) {
		this.scanner = scanner;
		
	}

	public void runAdministratorMode(boolean isRunning) {
		String[] options = new String[] { "Go Back", "Book", "Author", "Book Loan", "Book Genres", "Borrowers",
				"Library Branches", "Publishers" };
		scanner = new Scanner("test");
		while (!scanner.hasNextInt()) {
			renderView("Welcome to Admin Menu:\nPlease select entity you want to manage:", options);
			scanner = new Scanner(System.in);
			if (scanner.hasNextInt()) {
				int input = scanner.nextInt();
				if (input < 0 || input > options.length - 1) {
					scanner = new Scanner("test");
					continue;
				}
				switch (input) {
				case 0:
					return;
				default:
					renderSubView(options[input]);
					scanner = new Scanner("test");
					break;
				}
			}
		}
	}

	public void renderSubView(String title) {
		currentEntity = title;
		String[] options = new String[] { "Quit", "Read", "Create", "Update", "Delete" };
		scanner = new Scanner("test");
		while (!scanner.hasNextInt()) {
			renderView("You are managing " + title, options);
			scanner = new Scanner(System.in);
			if (scanner.hasNextInt()) {
				int input = scanner.nextInt();
				if (input < 0 || input > options.length - 1) {
					scanner = new Scanner("test");
					continue;
				}
				switch (input) {
				case 0:
					return;
				default:
					runOperation(title, options[input], input);
					break;
				}
			}
		}

	}
	
	private void runOperation(String entity, String operation, Integer input) {
		System.out.println(operation + " " + entity);
		switch(input) {
		case 1:
			readEntity();
			break;
		case 2:
			updateEntity();
			break;
		case 3:
			createEntity();
			break;
		case 4:
			deleteEntity();
			break;
		}
	}

	private void deleteEntity() {
		// TODO Auto-generated method stub
		
	}

	private void createEntity() {
		// TODO Auto-generated method stub
		
	}

	private void updateEntity() {
		// TODO Auto-generated method stub
		
	}

	private void readEntity() {
		try {
			adminService.readTable(currentEntity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void renderView(String title, String[] options) {
		System.out.println(title);
		int count = 0;
		for (String option : options) {
			System.out.println(count++ + " - " + option);
		}
	}

}
