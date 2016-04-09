This file is to provide guildance for reading source code and executing program for COMP5134 project.

1. How to read source code?

		The source code is put in the following folder:
		
		I.		domain folder
						path: eLeaveSystem\src\domain
						file:	(i)		Staff.java
								(ii)	Request.java
				
		II.		ui folder
						path: eLeaveSystem\src\ui
					
							window folder
								path: eLeaveSystem\src\ui\window
								file:	(i)	Window.java
						
						
							panel folder
								path: eLeaveSystem\src\ui\panel
							
									admin folder
									path: eLeaveSystem\src\ui\panel\admin
									file:	(i)		StaffAdditionPanel.java
											(ii)	SupervisorAssignmentPanel.java
											(iii) 	StaffDeletionPanel.java
													
									staff folder
									path: eLeaveSystem\src\ui\panel\staff
									file:	(i)		RequestApplicationPanel.java
											(ii)	RequestViweingPanel.java
											(iii)	RequestHandlingPanel.java
												
									common folder
									path: eLeaveSystem\src\ui\panel\common
									file:	(i) 	LoginPanel.java
											(ii)	ContentPanel.java
											(iii)	HeaderPanel.java
											(iv)	FooterPanel.java
						
					
		III.	main folder:
					path: eLeaveSystem\src\main
					file:	(i)		Main.java
					

		The source code can be read in terms of functionality.  The key class and method is listed below:
		
		I.		New/Delete Staff
		
					New Staff:
		
					(i)		Class: Window
							Method: private void addStaff(final List<Staff> staffList, final Staff staff)
								
					(ii)	Class: StaffAdditionPanel
							Method: public StaffAdditionPanel()
								
					(iii)	Class: Staff
							Method: public Staff(String id, String name, String password, String type)
								
					Delete Staff:
					
					(i)	Class: Window
						Method: private void deleteStaff(final List<Staff> staffList, final Staff staff)
								
					(ii)	Class: StaffDeletionPanel()
							Method: public StaffDeletionPanel(List<Staff> staffList)
								
					(iii)	Class: Staff
							Method: public void removeRequestApplicationList(Request request)
							Method: public void removeRequestHandlingList(Request request)
							Method: public void setsupervisor(Staff supervisor)
							Method: public void applyRequest(Request request)
								
		II.		Assign a staf S as another staff T's supervisor
		
					(i)		Class: Window
							Method: assignSupervisor(final List<Staff> staffList, final Staff staff)
							
					(ii)	Class: SupervisorAssignmentPanel
							Method: public SupervisorAssignmentPanel(List<Staff> staffList)
								
					(iii)	Class: Staff
							Method: public void setSupervisor(Staff supervisor)

		III.	Only one staff is allowed to have no supervisor (which we call it as the 'diretor')
		
					(i)		Class: Window
							Method: private void addStaff(final List<Staff> staffList, final Staff staff)
								
					(ii)	Class: StaffAdditionPanel
							Method: public StaffAdditionPanel()
			
		IV.		A staff can supervise multiple staff but each staff has only one supervisor
		
					(i)		Class: Window
							Method: private void assignSupervisor(final List<Staff> staffList, final Staff staff)
							
					(ii)	Class: SupervisorAssignmentPanel
							Method: public SupervisorAssignmentPanel(List<Staff> staffList)
								
					(iii)	Class: Staff
							Method: public void setSupervisor(Staff supervisor)
								
		V.		Any staff	(except the director) can use the system to apply for a leave from a date X to date Y
		
					Leave Request Application:			
					
					(i)		Class: Window
							Method: private void applyRequest(final List<Staff> staffList, final Staff staff)
					
					(ii)	Class: RequestApplicationPanel
							Method: public RequestApplicationPanel()
								
					(iii)	Class: Staff
							Method: public void applyRequest(Request request)
								
					Leave Request Handling:
					
					(i)		Class: Window
							Method: private void handleRequest(final List<Staff> staffList, final Staff staff)
								
					(ii)	Class: RequestHandlingPanel
							Method: public RequestHandlingPanel(Staff staff, int subPanelNumber)
								
					(iii)	Class: Staff
							Method: public void handleRequest(Request request, Boolean isApprove)
							Method: public void applyRequest(Request request)
								
					Leave Request Viewing:
					
					(i)		Class: Window
							Method: private void viewRequest(final List<Staff> staffList, final Staff staff)
								
					(ii)	Class: RequestViewingPanel
							Method: public RequestViewingPanel(Staff staff, int subPanelNumber)
								
		N.B. Chain-of-responsibility Design Pattern
					
					Class: Staff
					Method: public void applyRequest(Request request)
					Method: public void handleRequest(Request request, Boolean isApprove)

2. How to run program?

		The program can be run by executing the following jar file:
		file name: eLeaveSystem.jar
		file path: eLeaveSystem\dist
		executing command: java -jar eLeaveSystem\dist\eLeaveSystem.jar