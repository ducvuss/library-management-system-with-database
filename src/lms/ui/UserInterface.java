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

	/**
	 * 
	 */
	public UserInterface() {
		this.scanner = new Scanner(System.in);
		this.state = "Main";
	}

	public void run() {
		while (state != null) {
			getRouteByState();
		}
	}

	private void getRouteByState() {
		switch (state) {
		case "Main":
			getMainView();
			break;
		case "Librarian":
			getLibrarianView();
			break;
		}

	}

	private void getLibrarianView() {
		display("Librarian Options:", new String[] { "Enter Branch you manage", "Go back" });
		handleLibrarianInput();
	}

	private void getMainView() {
		display("Welcome to LMS, select a number that corresponds to your current role?",
				new String[] { "Librarian", "Administrator", "Borrower" });
		handleMainInput();
	}

	private void handleLibrarianInput() {
		while (true) {
			Integer input = scanner.nextInt();
			switch (input) {
			case 1:
				this.state = "Branches";
				getBranchesView();
				handleBranchesInput();
				return;
			case 2:
				this.state = "Main";
				return;
			}
		}
	}

	private void getBranchesView() {
		this.librarianService = new LibrarianSerivce();
		librarianService.getBranches().forEach(System.out::println);
		System.out.println("0 - Go Back");
		System.out.print("Your selection: ");
	}

	private Integer handleBranchesInput() {
		while (true) {
			Integer input = scanner.nextInt();
			if (input == 0) {
				this.state = "Librarian";
				return null;
			}
			else {
				getBranchMenu(input);
			}
		}
	}

	private void getBranchMenu(Integer input) {
		LibraryBranch branch = librarianService.getBranchById(input);
		display("", new String[] { "Update the details", "Add copies of Book", "Go back"});
	}

	private void display(String title, String[] options) {
		System.out.println("");
		System.out.println(title);
		IntStream.range(0, options.length).forEach(index -> {
			System.out.println((index + 1) + "-" + options[index]);
		});
		System.out.print("Your number: ");
	}

	private void handleMainInput() {
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
