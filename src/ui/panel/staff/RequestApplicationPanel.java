package ui.panel.staff;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RequestApplicationPanel extends JPanel {
	
	public JTextField tfStartDate = new JTextField();
	public JTextField tfEndDate = new JTextField();

	public RequestApplicationPanel() {

		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);

		JLabel lbStartDate = new JLabel("Start Date");
		tfStartDate.setPreferredSize(new Dimension(200, 20));

		JLabel lbEndDate = new JLabel("End Date");
		tfEndDate.setPreferredSize(new Dimension(200, 20));
		
		JLabel lbStartDateNote = new JLabel("Date Format: YYYYMMDD");
		lbStartDateNote.setFont(new Font(panel.getFont().getFontName(), Font.PLAIN, panel.getFont().getSize()));
		
		JLabel lbEndDateNote = new JLabel("Date Format: YYYYMMDD");
		lbEndDateNote.setFont(new Font(panel.getFont().getFontName(), Font.PLAIN, panel.getFont().getSize()));
		
		JLabel lbDummy = new JLabel();

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// horizontal parallel group
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(lbStartDate)
				.addComponent(lbDummy)
				.addComponent(lbEndDate)
				.addComponent(lbDummy));
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(tfStartDate)
				.addComponent(lbStartDateNote)
				.addComponent(tfEndDate)
				.addComponent(lbEndDateNote));
		layout.setHorizontalGroup(hGroup);

		// vertical parallel group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lbStartDate)
				.addComponent(tfStartDate));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lbDummy)
				.addComponent(lbStartDateNote));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lbEndDate)
				.addComponent(tfEndDate));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lbDummy)
				.addComponent(lbEndDateNote));
		layout.setVerticalGroup(vGroup);

		panel.setLayout(layout);

		this.setLayout(new FlowLayout());
		this.add(panel);
		
	}

}
