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
			int option = renderView("Librarian Options:", new String[] { "Go back", "Enter Branch you manage", });
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
				runBranchMode(branch);
//				runBranchEditMode(branch);
				break;
			}
		}
	}

	private void runBranchMode(LibraryBranch branch) {
		while (isRunning) {
			int option = renderView("Current Record: " + branch.toRowString(), new String[] { "Go back", "Update branch details.", "Add copies of book." });
			switch (option) {
			case 0:
				return;
			case 1:
				runBranchEditMode(branch);
				break;
			}
		}
	}

	private void runBranchEditMode(LibraryBranch branch) {
		System.out.println("You are editting " + branch.toRowString());
		System.out.println("Enter 'quit' at any prompt to cancel the operation.");
		System.out.println("Enter 'n/a' for no change.");
		System.out.print("New Branch Name: ");
		scanner = new Scanner(System.in);
		String branchName = scanner.nextLine();
		switch (branchName) {
		case "quit":
			return;
		case "n/a":
			break;
		default:
			branch.setBranchName(branchName);
		}
		System.out.print("\nNew Branch Address: ");
		scanner = new Scanner(System.in);
		String branchAddress = scanner.nextLine();
		switch (branchAddress) {
		case "quit":
			return;
		case "n/a":
			break;
		default:
			branch.setBranchAddress(branchAddress);
			System.out.println("successfully update information of this library branch");
		}
		try {
			librarianService.update(branch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to update information of this library branch");
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
		while (scanner.hasNext()) {
			if (scanner.hasNextInt()) {
				return scanner.nextInt();
			}
			System.out.println("input invalid - try again");
			scanner = new Scanner(System.in);
		}
		return -1;
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
