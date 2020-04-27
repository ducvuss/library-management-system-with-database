/**
 * 
 */
package lms.ui;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.stream.IntStream;

import lms.entity.BookLoan;
import lms.entity.LibraryBranch;
import lms.service.BorrowerService;
import lms.service.GeneralService;
import lms.service.LibrarianSerivce;

/**
 * @author ducba
 *
 */
public class UserInterface {

	private Scanner scanner;
	private LibrarianSerivce librarianService;
	private BorrowerService borrowerService;
	private boolean isRunning = false;
	private GeneralService generalService = new GeneralService();
	private Integer cardNo;

	/**
	 * 
	 */
	public UserInterface() {
		this.scanner = new Scanner(System.in);
	}

	public void run() {
		isRunning = true;
		while (isRunning) {
			int option = renderView("Welcome to LMS, select a number that corresponds to your current role?",
					new String[] { "Quit", "Librarian", "Administrator", "Borrower" });
			switch (option) {
			case 0:
				scanner.close();
				System.out.println("app closed");
				return;
			case 1:
				runLibrarianMode(isRunning);
				break;
			case 2:
				break;
			case 3:
				if (validateBorrower()) {
					runBorrowerMode(isRunning);
				}
				break;
			}
		}
	}

	private void runBorrowerMode(boolean isRunning) {
		System.out.println("");
		borrowerService = new BorrowerService();
		while (isRunning) {
			int option = renderView("Borrower Options:",
					new String[] { "Go back", "Check out a Book", "Return a Book" });
			switch (option) {
			case 0:
				return;
			case 1:
				runModeByRole(isRunning, "Borrower", "Enter Branch you want to check out from", true);
				System.out.println("checkout");
				break;
			case 2:
				System.out.println("return");
				runModeByRole(isRunning, "Borrower", "Enter Branch you want to return books", false);
				break;
			}
		}
	}

	private boolean validateBorrower() {
		System.out.println("Enter 0 to go back");
		System.out.print("Enter your Card Number: ");

		while (scanner.hasNextLine()) {
			scanner = new Scanner(System.in);
			while (scanner.hasNextInt()) {
				Integer input = scanner.nextInt();
				if (input == 0) {
					return false;
				}
				this.cardNo = generalService.validateCardNo(input);
				if (cardNo != null) {
					System.out.print("successfully validation");
					return true;
				}
				System.out.println("validation failed - try again");

			}
			System.out.println("invalid input - try again");
		}
		return false;
	}

	private void runModeByRole(boolean isRunning, String role, String optionName, boolean checkoutMode) {
		borrowerService = new BorrowerService();
		while (isRunning) {
			int option = renderView(role + " Options:", new String[] { "Go back", optionName });
			switch (option) {
			case 0:
				return;
			case 1:
				runBranchesModeBorrower(isRunning, checkoutMode);
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

	private void runBranchesModeBorrower(boolean isRunning, boolean checkoutMode) {
		while (isRunning) {
			int option = renderBranchesView();
			switch (option) {
			case -1:
				break;
			case 0:
				return;
			default:
				LibraryBranch branch = generalService.getBranchById(option);
				if (branch == null) {
					System.out.println("branch not found - try again");
					break;
				}
				if (checkoutMode) {
					runSubModeByRole(branch.getBranchId(),
							new String[] { "Go back", "select the Book you want to check out" }, checkoutMode);
				} else {
					runSubModeByRole(branch.getBranchId(),
							new String[] { "Go back", "select the Book you want to check in" }, checkoutMode);
				}
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
				runBranchMode(branch.getBranchId());
				break;
			}
		}
	}

	private void runSubModeByRole(Integer branchId, String[] strings, boolean checkoutMode) {
		while (isRunning) {
			LibraryBranch branch = generalService.getBranchById(branchId);
			int option = renderView("Current Record: " + branch.toRowString(), strings);
			switch (option) {
			case 0:
				return;
			case 1:
				runBookMode(branch, checkoutMode);
				break;
			}
		}
	}

	private void runBookMode(LibraryBranch branch, boolean checkoutMode) {
		System.out.println("");
		System.out.println("0 - Go back");
		System.out.println(checkoutMode ? "Select the book you want to checkout" : "Select the book you want to return");
		borrowerService.getAvailableBooksByBranch(branch.getBranchId()).forEach(System.out::println);
		System.out.print("Your selection: ");
		while (!scanner.hasNextInt()) {
			System.out.println("invalid input - try again");
			scanner = new Scanner(System.in);
		}
		Integer bookId = null;
		Integer noOfCopies = null;
		while (scanner.hasNextInt()) {
			bookId = scanner.nextInt();
			if (bookId == 0) {
				return;
			} else {
				noOfCopies = generalService.getBookCopiesByBranch(bookId, branch.getBranchId());
				if (noOfCopies != null) {
					try {
						BookLoan bookLoan =	borrowerService.getBookLoan(bookId, branch.getBranchId(), cardNo);
						if (bookLoan != null && checkoutMode) {
							System.out.println("You aleady checked out this book and haven't returned it");
							return;
						}
						
						if (bookLoan == null && !checkoutMode ) {
							System.out.println("You haven't checked out this book");
							return;
						}
					} catch (SQLException e) {
						return;
					}
					break;
				}

				
				System.out.println("book not found - please try again");
				return;
			}
		}

		BookLoan loan = new BookLoan();
		loan.setBookId(bookId);
		loan.setBranchId(branch.getBranchId());
		loan.setCardNo(cardNo);
		if (noOfCopies > 0) {
			System.out.println(noOfCopies);
			try {
				if (checkoutMode) {
					borrowerService.checkOutBook(bookId, branch.getBranchId(), noOfCopies, loan);
				} else {
					borrowerService.checkInBook(bookId, branch.getBranchId(), noOfCopies, loan);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("successfully updated");
	}

	private void runBranchMode(Integer branchId) {
		while (isRunning) {
			LibraryBranch branch = librarianService.getBranchById(branchId);
			int option = renderView("Current Branch: " + branch.toRowString(),
					new String[] { "Go back", "Update branch details.", "Add copies of book." });
			switch (option) {
			case 0:
				return;
			case 1:
				runBranchEditMode(branch);
				break;
			case 2:
				runBookCopiesMode(branch);
				break;
			}
		}
	}

	private void runBookCopiesMode(LibraryBranch branch) {
		System.out.println("");
		System.out.println("0 - Go back");
		System.out.println("Select the book you want to add copies");
		librarianService.getBooksByBranchId(branch.getBranchId()).forEach(System.out::println);
		System.out.print("Your selection: ");
		while (!scanner.hasNextInt()) {
			System.out.println("invalid input - try again");
			scanner = new Scanner(System.in);
		}
		Integer bookId = null;
		Integer noOfCopies = null;
		while (scanner.hasNextInt()) {
			bookId = scanner.nextInt();
			if (bookId == 0) {
				return;
			} else {
				noOfCopies = librarianService.getBookCopiesByBranch(bookId, branch.getBranchId());
				if (noOfCopies != null) {
					break;
				}
				System.out.println("book not found - please try again");
				return;
			}
		}

		System.out.println("Current number of copies: " + noOfCopies);
		System.out.print("New number of copies: ");
		while (!scanner.hasNextInt()) {
			System.out.println("invalid input - try again");
			scanner = new Scanner(System.in);
		}
		try {
			librarianService.setBookCopiesByBranch(bookId, branch.getBranchId(), scanner.nextInt());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void runBranchEditMode(LibraryBranch branch) {
		System.out.println("");
		System.out.println("You are editting " + branch.toRowString());
		System.out.println("Enter 'quit' at any prompt to cancel the operation.");
		System.out.println("Enter 'n/a' for no change.");
		System.out.print("New Branch Name: ");
		scanner = new Scanner(System.in);
		String branchName = scanner.nextLine();
		switch (branchName.trim()) {
		case "quit":
			return;
		case "n/a":
			break;
		case "":
			break;
		default:
			branch.setBranchName(branchName);
		}

		System.out.print("\nNew Branch Address: ");
		scanner = new Scanner(System.in);
		String branchAddress = scanner.nextLine();
		switch (branchAddress.trim()) {
		case "quit":
			return;
		case "n/a":
			break;
		case "":
			break;
		default:
			branch.setBranchAddress(branchAddress);
		}
		try {
			librarianService.update(branch);
			System.out.println("successfully update information of this library branch");
		} catch (SQLException e) {
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
		generalService.getBranches().forEach(System.out::println);
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
