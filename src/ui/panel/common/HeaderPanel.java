package ui.panel.common;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Staff;

@SuppressWarnings("serial")
public class HeaderPanel extends JPanel {
	
	private String header = "HR Portal eLeave System";
	
	public HeaderPanel(Staff staff) {
		
		if (staff == null) {
			
			this.setLayout(new GridLayout(2, 1));
			
			JLabel lbHeader = new JLabel(header, JLabel.CENTER);
			this.add(lbHeader);
			
		} else if (staff != null) {
			
			this.setLayout(new GridLayout(5, 1));
			
			String id = staff.getId();
			String name = staff.getName();
			String type = staff.getType();
			
			JLabel lbHeader = new JLabel(header, JLabel.CENTER);
			this.add(lbHeader);
			
			JLabel lbId = new JLabel("Staff ID: " + id, JLabel.LEFT);
			this.add(lbId);
			
			JLabel lbName = new JLabel("Staff Name: " + name, JLabel.LEFT);
			this.add(lbName);
			
			JLabel lbType = new JLabel("Staff Type: " + type, JLabel.LEFT);
			this.add(lbType);
			
		}

		

	}

}
