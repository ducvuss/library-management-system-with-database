/**
 * 
 */
package lms.entity;

import java.time.LocalDateTime;

/**
 * @author ducba
 *
 */
public class BookLoan implements StringFormattable {
	private Integer bookId;
	private Integer branchId;
	private Integer cardNo;
	private LocalDateTime dateOut;
	private LocalDateTime dueDate;
	private LocalDateTime dateIn;
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
	public LocalDateTime getDateOut() {
		return dateOut;
	}
	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(LocalDateTime dateOut) {
		this.dateOut = dateOut;
	}
	/**
	 * @return the dueDate
	 */
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the dateIn
	 */
	public LocalDateTime getDateIn() {
		return dateIn;
	}
	/**
	 * @param dateIn the dateIn to set
	 */
	public void setDateIn(LocalDateTime dateIn) {
		this.dateIn = dateIn;
	}
	public String toRowString() {
		return stringify(bookId.toString(), branchId.toString(), cardNo.toString(), dateOut.toString(), dueDate.toString(), dateIn.toString());
	}

}
