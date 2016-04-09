package ui.panel.admin;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class StaffAdditionPanel extends JPanel {
	
	public JTextField tfId = new JTextField();
	public JTextField tfName = new JTextField();
	public JPasswordField pfPassword = new JPasswordField();
	public JComboBox<String> cbType;
	
	public StaffAdditionPanel() {
		
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		
		String DIRECTOR = "Director";
		String GENERAL_STAFF = "General Staff";
		
		JLabel lbId = new JLabel("Staff ID");
		tfId.setPreferredSize(new Dimension(200, 20));
		
		JLabel lbName = new JLabel("Name");
		tfName.setPreferredSize(new Dimension(200, 20));
		
		JLabel lbPassword = new JLabel("Password");
		pfPassword.setPreferredSize(new Dimension(200, 20));
		
		JLabel lbType = new JLabel("Staff Type");
		cbType = new JComboBox<String>(new Vector<String>());
		cbType.addItem(DIRECTOR);
		cbType.addItem(GENERAL_STAFF);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// horizontal parallel group
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(lbId)
				.addComponent(lbName)
				.addComponent(lbPassword)
				.addComponent(lbType));
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(tfId)
				.addComponent(tfName)
				.addComponent(pfPassword)
				.addComponent(cbType));
		layout.setHorizontalGroup(hGroup);
		
		// vertical parallel group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lbId)
				.addComponent(tfId));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lbName)
				.addComponent(tfName));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lbPassword)
				.addComponent(pfPassword));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lbType)
				.addComponent(cbType));
		layout.setVerticalGroup(vGroup);
		
		panel.setLayout(layout);
		
		this.setLayout(new FlowLayout());
		this.add(panel);
		
	}

}
