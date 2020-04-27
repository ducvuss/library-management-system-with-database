package lms.ui;

import java.util.Scanner;
import java.util.stream.Stream;

public class AdministratorMode {

	private Scanner scanner;

	public AdministratorMode(Scanner scanner) {
		this.scanner = scanner;
	}

	public void runAdministratorMode(boolean isRunning) {
		renderView("Welcome to Admin Menu:\nPlease select entity you want to manage:", new String[] { "Book", "Author",
				"Book Loan", "Book Genres", "Borrowers", "Library Branches", "Publishers" });
	}

	private void handleInput(String[] options) {
		while (!scanner.hasNextInt()) {
			System.out.println("invalid input");
			scanner = new Scanner(System.in);
		}

		int input = scanner.nextInt();

		if (input == 0) {
			return;
		}

		if (input < 0 || input > options.length) {
			System.out.println("invalid option");
		}

		switch (input) {
		case 1:
			renderSubView("Book");
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		default:
			break;
		}
	}

	public void renderSubView(String title) {
		renderView(title, new String[] { "Read", "Create", "Update", "Delete" });
		System.out.println(title);
		while (!scanner.hasNextInt()) {
			System.out.println("invalid input");
			scanner = new Scanner(System.in);
		}

		int input = scanner.nextInt();

		if (input == 0) {
			return;
		}

		if (input < 0 || input > 4) {
			System.out.println("invalid option");
		}

		switch (input) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			break;
		}

	}

	public void renderView(String title, String[] options) {
		System.out.println(title);
		int count = 1;
		System.out.println("0 - Go Back ");
		for (String option : options) {
			System.out.println(count++ + " - " + option);
		}
		handleInput(options);
	}

}
