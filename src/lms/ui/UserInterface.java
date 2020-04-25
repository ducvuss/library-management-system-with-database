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
import lms.utils.DbConnection;

/**
 * @author ducba
 *
 */
public class UserInterface {

	private Scanner scanner;
	private String state;

	/**
	 * 
	 */
	public UserInterface() {
		this.scanner = new Scanner(System.in);
		this.state = "Main";
	}

	public void run() {
		while (state != null) {
			getScreenByState();
		}
	}

	private void getScreenByState() {
//		System.out.println(state);
		switch (state) {
		case "Main":
			display("Welcome to LMS, select a number that corresponds to your current role?",
					new String[] { "Librarian", "Administrator", "Borrower" });
			switchStateFromMain();
			break;
		case "Librarian":
			display("Librarian Options:", new String[] { "Enter Branch you manage", "Go back" });
			handleLibrarianInput();
			break;
		}

	}

	private void getBranchesMenu() {
		try (Connection sqlConnection = new DbConnection().getConnection()) {
			LibraryBranchDAO branchDAO = new LibraryBranchDAO(sqlConnection);

			branchDAO.get().stream().forEach(branch -> {
				System.out.println(String.format("%d - %s - %s", branch.getBranchId(), branch.getBranchName(),
						branch.getBranchAddress()));
			});
		} catch (SQLException e) {
			System.out.println("can't connect to the database");
			e.printStackTrace();
		}
		
		System.out.println("0 - Go Back");
		System.out.print("Your selection: ");
	}

	private void handleLibrarianInput() {
		while (true) {
			Integer input = scanner.nextInt();
			switch (input) {
			case 1:
				this.state = "Branches";
				getBranchesMenu();
				handleBranchesSelection();
				return;
			case 2:
				this.state = "Main";
				return;
			}
		}
	}

	private void handleBranchesSelection() {
		while (true) {
			Integer input = scanner.nextInt();
			if (input == 0) {
				this.state = "Librarian";
				return;
			}
		}
	}

	private void display(String title, String[] options) {
		System.out.println("");
		System.out.println(title);
		IntStream.range(0, options.length).forEach(index -> {
			System.out.println((index + 1) + "-" + options[index]);
		});
		System.out.print("Your number: ");
	}

	private void switchStateFromMain() {
		while (true) {
			Integer input = scanner.nextInt();
			switch (input) {
			case 1:
				this.state = "Librarian";
				return;
			case 2:
				this.state = "Administrator";
				return;
			case 3:
				this.state = "Borrower";
				return;
			}
		}
	}

}
