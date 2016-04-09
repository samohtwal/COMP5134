package domain;

import java.util.Date;

public class Request {
	
	private Staff applicant;
	private Date startDate;
	private Date endDate;
	private Boolean approve;
	
	public Request(Staff applicant, Date startDate, Date endDate) {
		this.applicant = applicant;
		this.startDate = startDate;
		this.endDate = endDate;
		this.approve = null;
	}
	
	public Staff getApplicant() {
		return applicant;
	}
	
	public void setApplicant(Staff applicant) {
		this.applicant = applicant;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean IsApprove() {
		return approve;
	}
	
	public void setApprove(Boolean approve) {
		this.approve = approve;
	}

}
