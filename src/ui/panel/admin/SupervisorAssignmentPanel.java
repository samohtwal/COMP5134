package ui.panel.admin;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

import domain.Staff;

@SuppressWarnings("serial")
public class SupervisorAssignmentPanel extends JPanel {
	
	public JComboBox<String> cbSubordinateId;
	public JComboBox<String> cbSupervisorId;
	
	public SupervisorAssignmentPanel(List<Staff> staffList) {
		
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);

		List<String> staffIdList = new ArrayList<String>();
		for (Staff staff : staffList) {
			if (!staff.getType().equals("Administrator")) {
				staffIdList.add(staff.getId());
			}
		}
		
		JLabel lbSubordinteId = new JLabel("Subordinate Staff ID");
		cbSubordinateId = new JComboBox<String>(new Vector<String>(staffIdList));
		
		JLabel lbSupervisorId = new JLabel("Supervisor Staff ID");
		cbSupervisorId = new JComboBox<String>(new Vector<String>(staffIdList));
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// horizontal parallel group
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(lbSubordinteId)
				.addComponent(lbSupervisorId));
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(cbSubordinateId)
				.addComponent(cbSupervisorId));
		layout.setHorizontalGroup(hGroup);

		// vertical parallel group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lbSubordinteId)
				.addComponent(cbSubordinateId));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lbSupervisorId)
				.addComponent(cbSupervisorId));
		layout.setVerticalGroup(vGroup);

		panel.setLayout(layout);

		this.setLayout(new FlowLayout());
		this.add(panel);
		
	}

}
