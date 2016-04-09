package ui.panel.staff;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Request;
import domain.Staff;

@SuppressWarnings("serial")
public class RequestViewingPanel extends JPanel {

	public RequestViewingPanel(Staff staff, int subPanelNumber) {

		int columnNumber = 2;
		int subPanelLimit = 6;

		// sub-panel size standardization
		if (subPanelNumber < subPanelLimit) {
			int rowNumber = subPanelLimit / columnNumber;
			this.setLayout(new GridLayout(rowNumber, columnNumber));
			for (int i = 0; i < subPanelNumber; i++) {
				this.add(subPanel(staff, i));
			}
			for (int i = 0; i < subPanelLimit - subPanelNumber; i++) {
				this.add(new JPanel());
			}
		} else {
			int rowNumber = (int) Math.ceil((double) subPanelNumber / columnNumber);
			this.setLayout(new GridLayout(rowNumber, columnNumber));
			for (int i = 0; i < subPanelNumber; i++) {
				this.add(subPanel(staff, i));
			}
		}

	}
	
	private JPanel subPanel(Staff staff, int subPanelNumber) {
		//method for leave viewing sub-panel		
		
		JPanel subPanel = new JPanel();
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Request request = staff.getRequestApplicationList().get(subPanelNumber);
		
		JLabel lbStartDate = new JLabel("Start Date: " + sdf.format(request.getStartDate()));
		JLabel lbEndDate = new JLabel("End Date: " + sdf.format(request.getEndDate()));
		JLabel lbApprovalStatus = new JLabel();
		
				
		if (request.IsApprove() == null) {
			lbApprovalStatus.setText("Approval Status: PENDING FOR REVIEW");
		} else if (request.IsApprove()) {
			lbApprovalStatus.setText("Approval Status: ENDORSE");
		} else if (!request.IsApprove()) {
			lbApprovalStatus.setText("Approval Status: DECLINE");
		}
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//horizontal parallel group
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(lbStartDate)
				.addComponent(lbEndDate)
				.addComponent(lbApprovalStatus));
		layout.setHorizontalGroup(hGroup);
		
		//vertical parallel group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lbStartDate));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lbEndDate));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lbApprovalStatus));
		layout.setVerticalGroup(vGroup);
		
		panel.setLayout(layout);
		
		subPanel.setLayout(new FlowLayout());
		subPanel.add(panel);
		
		return subPanel;
		
	}
	
}
