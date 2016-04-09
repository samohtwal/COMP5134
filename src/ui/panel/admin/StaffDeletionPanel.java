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
public class StaffDeletionPanel extends JPanel {
	
	public JComboBox<String> cbDeletionStaffId;

	public StaffDeletionPanel(List<Staff> staffList) {

		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		
		List<String> staffIdList = new ArrayList<String>();
		for (Staff staff : staffList) {
			if (!staff.getType().equals("Administrator")) {
				staffIdList.add(staff.getId());
			}
		}

		JLabel lbId = new JLabel("Staff ID");
		cbDeletionStaffId = new JComboBox<String>(new Vector<String>(staffIdList));

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// horizontal parallel group
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(lbId));
		hGroup.addGroup(layout.createParallelGroup().addComponent(cbDeletionStaffId));
		layout.setHorizontalGroup(hGroup);

		// vertical parallel group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lbId)
				.addComponent(cbDeletionStaffId));
		layout.setVerticalGroup(vGroup);

		panel.setLayout(layout);

		this.setLayout(new FlowLayout());
		this.add(panel);

	}

}
