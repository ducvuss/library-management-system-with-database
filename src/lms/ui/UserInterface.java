/**
 * 
 */
package lms.ui;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

	public void getScreenByState() {
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

	private void handleLibrarianInput() {
		while (true) {
			Integer input = scanner.nextInt();
			switch (input) {
			case 1:
				this.state = "Branches";
				return;
			case 2:
				this.state = "Main";
				return;
			}
		}
	}

	public void display(String title, String[] options) {
		System.out.println("");
		System.out.println(title);
		IntStream.range(0, options.length).forEach(index -> {
			System.out.println((index + 1) + "-" + options[index]);
		});
		System.out.print("Your number: ");
	}

	public void switchStateFromMain() {
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
