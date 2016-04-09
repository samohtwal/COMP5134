package ui.panel.common;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import domain.Staff;

@SuppressWarnings("serial")
public class ContentPanel extends JPanel {

	public JButton btnStaffAddition = new JButton("Add Staff");
	public JButton btnAssignSupervisor = new JButton("Assign Supervisor");
	public JButton btnStaffDeletion = new JButton("Delete Staff");
	public JButton btnRequestApplication = new JButton("Apply Leave Rquest");
	public JButton btnRequestViewing = new JButton("View Leave Request");
	public JButton btnRequestHandling = new JButton("Handle Leave Request");
	
	public ContentPanel(List<Staff> staffList, Staff staff) {

		final String ADMINISTRATOR = "Administrator";
		
		if (staff.getType().equals(ADMINISTRATOR)) {
			
			int staffNum = 0;
			
			for (Staff tempStaff : staffList) {
				if (!tempStaff.getType().equals(ADMINISTRATOR)) {
					staffNum ++;
				}
			}
			
			if (staffNum == 0) {
				btnAssignSupervisor.setEnabled(false);
				btnStaffDeletion.setEnabled(false);
			}
			
			this.add(btnStaffAddition);
			this.add(btnAssignSupervisor);
			this.add(btnStaffDeletion);
			
		} else {
			
			// button authority control mechanism
			if (staff.getSupervisor() == null) {
				// director
				btnRequestApplication.setEnabled(false);
				btnRequestViewing.setEnabled(false);
			} else {	
				// general staff
				btnRequestHandling.setEnabled(false);
				for (Staff tempStaff : staffList) {
					if (tempStaff.getSupervisor() != null
							&& tempStaff.getSupervisor().equals(staff)) {
						btnRequestHandling.setEnabled(true);
					}
				}
				
			}
			
			this.add(btnRequestApplication);
			this.add(btnRequestViewing);
			this.add(btnRequestHandling);
			
		}
		
	}

}
