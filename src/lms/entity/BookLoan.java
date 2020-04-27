/**
 * 
 */
package lms.entity;

import java.time.ZonedDateTime;

/**
 * @author ducba
 *
 */
public class BookLoan {
	private Integer bookId;
	private Integer branchId;
	private Integer cardNo;
	private ZonedDateTime dateOut;
	private ZonedDateTime dueDate;
	private ZonedDateTime dateIn;
	/**
	 * 
	 */
	public BookLoan() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the branchId
	 */
	public Integer getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the cardNo
	 */
	public Integer getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the dateOut
	 */
	public ZonedDateTime getDateOut() {
		return dateOut;
	}
	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(ZonedDateTime dateOut) {
		this.dateOut = dateOut;
	}
	/**
	 * @return the dueDate
	 */
	public ZonedDateTime getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(ZonedDateTime dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the dateIn
	 */
	public ZonedDateTime getDateIn() {
		return dateIn;
	}
	/**
	 * @param dateIn the dateIn to set
	 */
	public void setDateIn(ZonedDateTime dateIn) {
		this.dateIn = dateIn;
	}

}
