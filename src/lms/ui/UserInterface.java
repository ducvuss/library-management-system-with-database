/**
 * 
 */
package lms.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lms.dao.LibraryBranchDAO;
import lms.entity.LibraryBranch;
import lms.service.LibrarianSerivce;
import lms.utils.DbConnection;

/**
 * @author ducba
 *
 */
public class UserInterface {

	private Scanner scanner;
	private String state;
	private LibrarianSerivce librarianService;
	private boolean isRunning = false;

	/**
	 * 
	 */
	public UserInterface() {
		this.scanner = new Scanner(System.in);
		this.state = "Main";
	}

	public void run() {
		isRunning = true;
		while (isRunning) {
			int option = renderView("Welcome to LMS, select a number that corresponds to your current role?",
					new String[] { "Quit", "Librarian", "Administrator", "Borrower" });
			switch (option) {
			case 0:
				System.out.println("app closed");
				return;
			case 1:
				runLibrarianMode(isRunning);
				break;
			case 2:
				break;
			case 3:
				break;
			}
		}
	}

	private void runLibrarianMode(boolean isRunning) {
		librarianService = new LibrarianSerivce();
		while (isRunning) {
			int option = renderView("Librarian Options:", new String[] { "Go back", "Enter Branch you manage",  });
			switch (option) {
			case 0:
				return;
			case 1:
				runBranchesMode(isRunning);
				break;
			}
		}
	}

	private void runBranchesMode(boolean isRunning) {
		while (isRunning) {
			int option = renderBranchesView();
			switch (option) {
			case -1:
				break;
			case 0:
				return;
			default:
				LibraryBranch branch = librarianService.getBranchById(option);
				if (branch == null) {
					System.out.println("branch not found - try again");
					break;
				}
				System.out.println("good");
				break;
			}
		}
	}

	private int renderView(String title, String[] options) {
		int selection = -1;
		while (selection == -1) {
			display(title, options);
			selection = handleInput(options.length);
		}
		return selection;

	}

	private int renderBranchesView() {
		librarianService.getBranches().forEach(System.out::println);
		System.out.println("0 - Go Back");
		System.out.print("Your selection: ");
		scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			if (scanner.hasNextInt()) {
				return scanner.nextInt();
			}
			System.out.println("input invalid - try again");
			scanner = new Scanner(System.in);
		}
		return -1;
	}

	private Integer handleBranchesInput() {
		while (true) {
			Integer input = scanner.nextInt();
			if (input == 0) {
				this.state = "Librarian";
				return null;
			} else {
				getBranchMenu(input);
			}
		}
	}

	private void getBranchMenu(Integer input) {
		LibraryBranch branch = librarianService.getBranchById(input);
		if (branch == null) {
			this.state = "Branches";
			return;
		}
		display("", new String[] { "Update the details", "Add copies of Book", "Go back" });
	}

	private void display(String title, String[] options) {
		System.out.println("");
		System.out.println(title);
		IntStream.range(0, options.length).forEach(index -> {
			System.out.println(index + "-" + options[index]);
		});
		System.out.print("Your number: ");
	}

	private int handleInput(int numOfOptions) {
		scanner = new Scanner(System.in);
		while (scanner.hasNextInt()) {
			int selection = scanner.nextInt();
			if (selection > -1 && selection < numOfOptions + 1) {
				return selection;
			}
			System.out.println("wrong option, please try again");
			return -1;
		}
		System.out.println("invalid input, please try again");
		return -1;
	}

}
