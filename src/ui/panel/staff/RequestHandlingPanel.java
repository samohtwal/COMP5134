package ui.panel.staff;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import domain.Request;
import domain.Staff;

@SuppressWarnings("serial")
public class RequestHandlingPanel extends JPanel {
	
	public List<JRadioButton> rbListEndorse = new ArrayList<JRadioButton>();
	public List<JRadioButton> rbListDecline = new ArrayList<JRadioButton>();
	
	public RequestHandlingPanel(Staff staff, int subPanelNumber) {
		
		int columnNumber = 2;
		int subPanelLimit = 4;

		// sub-panel size standardization
		if (subPanelNumber < subPanelLimit) {
			int rowNumber = subPanelLimit / columnNumber;
			this.setLayout(new GridLayout(rowNumber, columnNumber));
			for (int i = 0; i < subPanelNumber; i++) {
				this.rbListEndorse.add(new JRadioButton("ENDORSE"));
				this.rbListDecline.add(new JRadioButton("DECLINE"));
				this.subPanel(staff, i);
				this.add(subPanel(staff, i));
			}
			for (int i = 0; i < subPanelLimit - subPanelNumber; i++) {
				this.add(new JPanel());
			}
		} else {
			int rowNumber = (int) Math.ceil((double) subPanelNumber / columnNumber);
			this.setLayout(new GridLayout(rowNumber, columnNumber));
			for (int i = 0; i < subPanelNumber; i++) {
				this.rbListEndorse.add(new JRadioButton("ENDORSE"));
				this.rbListDecline.add(new JRadioButton("DECLINE"));
				this.subPanel(staff, i);
				this.add(subPanel(staff, i));
			}
		}
		
	}
	
	private JPanel subPanel(Staff staff, int subPanelNumber) {
		// method for sub-panel
		
		JPanel subPanel = new JPanel();
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Request request = staff.getRequestHandlingList().get(subPanelNumber);
		
		JLabel lbApplicantId = new JLabel("Applicant Staff ID: " + request.getApplicant().getId());
		JLabel lbStartDate = new JLabel("Start Date: " + sdf.format(request.getStartDate()));
		JLabel lbEndDate = new JLabel("End Date: " + sdf.format(request.getEndDate()));
		
		JRadioButton rbEndorse = this.rbListEndorse.get(subPanelNumber);
		JRadioButton rbDecline = this.rbListDecline.get(subPanelNumber);
		ButtonGroup btngrp  = new ButtonGroup();
		btngrp.add(rbEndorse);
		btngrp.add(rbDecline);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// horizontal parallel group
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(lbApplicantId)
				.addComponent(lbStartDate)
				.addComponent(lbEndDate)
				.addComponent(rbEndorse)
				.addComponent(rbDecline));
		layout.setHorizontalGroup(hGroup);
		
		// vertical parallel group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lbApplicantId));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lbStartDate));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lbEndDate));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(rbEndorse));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(rbDecline));
		layout.setVerticalGroup(vGroup);

		panel.setLayout(layout);

		subPanel.setLayout(new FlowLayout());
		subPanel.add(panel);
		
		return subPanel;		
		
	}

}
