/**
 * 
 */
package lms.entity;

/**
 * @author ducba
 *
 */
public class Publisher {
	private Integer publisherId;
	private String publisherName;
	
	public Publisher() {
	}

	public Publisher(String publisherName) {
		this.publisherName = publisherName;
	}

	/**
	 * @return the publisherId
	 */
	public Integer getPublisherId() {
		return publisherId;
	}

	/**
	 * @param publisherId the publisherId to set
	 */
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	/**
	 * @return the publisherName
	 */
	public String getPublisherName() {
		return publisherName;
	}

	/**
	 * @param publisherName the publisherName to set
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

}
