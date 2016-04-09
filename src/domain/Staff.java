package domain;

import java.util.ArrayList;
import java.util.List;

public class Staff {

	private String id;
	private String name;
	private String password;
	private String type;
	private Staff supervisor;
	private List<Request> requestApplicationList;
	private List<Request> requestHandlingList;

	public Staff(String id, String name, String password, String type) {

		final String GENERAL_STAFF = "General Staff";
		final String DIRECTOR = "Director";
		final String ADMINISTRATOR = "Administrator";

		this.id = id;
		this.name = name;
		this.password = password;
		this.type = type;
		this.supervisor = null;

		if (type.equals(GENERAL_STAFF)) {
			this.requestApplicationList = new ArrayList<Request>();
			this.requestHandlingList = new ArrayList<Request>();
		} else if (type.equals(DIRECTOR)) {
			this.requestApplicationList = null;
			this.requestHandlingList = new ArrayList<Request>();
		} else if (type.equals(ADMINISTRATOR)) {
			this.requestApplicationList = null;
			this.requestHandlingList = null;
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Staff getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Staff supervisor) {
		this.supervisor = supervisor;
	}

	public List<Request> getRequestApplicationList() {
		return requestApplicationList;
	}

	protected void setRequestApplicationList(List<Request> requestApplicationList) {
		this.requestApplicationList = requestApplicationList;
	}

	public List<Request> getRequestHandlingList() {
		return requestHandlingList;
	}

	protected void setRequestHandlingList(List<Request> requestHandlingList) {
		this.requestHandlingList = requestHandlingList;
	}

	public void addRequestAppliactionList(Request request) {
		this.getRequestApplicationList().add(request);
	}

	public void removeRequestApplicationList(Request request) {
		this.getRequestApplicationList().remove(request);
	}

	public void addRequestHandlingList(Request request) {
		this.getRequestHandlingList().add(request);
	}

	public void removeRequestHandlingList(Request request) {
		this.getRequestHandlingList().remove(request);
	}

	public void applyRequest(Request request) {
		this.getSupervisor().addRequestHandlingList(request);		
	}

	public void handleRequest(Request request, Boolean isApprove) {

		if (isApprove) {
			if (this.getSupervisor() == null) {
				request.setApprove(true);
			} else {
				this.applyRequest(request);
			}
		} else if (!isApprove) {
			request.setApprove(false);
		}
		
	}

}
