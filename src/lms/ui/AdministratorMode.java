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
		String[] options = new String[] { "Go Back", "Book", "Author", "Book Authors", "Book Loan", "Book Genres",
				"Borrowers", "Library Branches", "Publishers", "Override due date", "Terminal" };
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
				case 9:
					runDueDateOverriding();
					break;
				case 10:
					runTerminal();
					break;
				default:
					renderSubView(options[input]);
					scanner = new Scanner("test");
					break;
				}
			}
			scanner = new Scanner("test");
		}
	}

	private void runDueDateOverriding() {
		
		scanner = new Scanner("");
		while (!scanner.hasNext()) {
			System.out.println("Please enter the new date in the format YYYY-d-M H-m-s, bookId, branchId, and cardNo SEPERATED by single spaces: ");
			scanner = new Scanner(System.in);
			if (scanner.hasNextLine()) {
				
				String commands = scanner.nextLine();
				System.out.println();
				try {
					if (adminService.updateBookLoanDueDate(commands.split(" "))) {
						System.out.println("successfully executed");
						return;
					}
					scanner = new Scanner("");
				} catch (Exception e) {

					scanner = new Scanner("");
				}
				System.out.println("failed execution - try again");
			}
		}
		
	}

	private void runTerminal() {
		
		scanner = new Scanner("");
		while (!scanner.hasNext()) {
			System.out.println("Please enter your command: ");
			scanner = new Scanner(System.in);
			if (scanner.hasNextLine()) {
				
				String commands = scanner.nextLine();
				System.out.println(commands);
				try {
					if (adminService.execute(commands)) {
						System.out.println("successfully executed");
						return;
					}
					scanner = new Scanner("");
				} catch (Exception e) {
					scanner = new Scanner("");
				}
				System.out.println("failed execution - try again");
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
		switch (input) {
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

	}

	private void createEntity() {
		// TODO Auto-generated method stub

	}

	private void updateEntity() {
	}

	private void readEntity() {
		try {
			adminService.readTable(currentEntity).forEach(System.out::println);
			;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readEntityByPk(Object[] keys) {
		adminService.readTable(currentEntity, keys).forEach(System.out::println);

	}

	public void renderView(String title, String[] options) {
		System.out.println(title);
		int count = 0;
		for (String option : options) {
			System.out.println(count++ + " - " + option);
		}
	}

}
