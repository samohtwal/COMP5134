package ui.panel.common;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
	
	public JTextField tfId = new JTextField();
	public JPasswordField pfPassword = new JPasswordField();
	
	public LoginPanel() {
		
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		
		JLabel lbId = new JLabel("Staff ID");
		tfId.setPreferredSize(new Dimension(200, 20));
		
		JLabel lbPassword = new JLabel("Password");
		pfPassword.setPreferredSize(new Dimension(200, 20));
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// horizontal parallel group
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(lbId)
				.addComponent(lbPassword));
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(tfId)
				.addComponent(pfPassword));
		layout.setHorizontalGroup(hGroup);
		
		// vertical parallel group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lbId)
				.addComponent(tfId));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lbPassword)
				.addComponent(pfPassword));
		layout.setVerticalGroup(vGroup);
		
		panel.setLayout(layout);
		
		this.setLayout(new FlowLayout());
		this.add(panel);
		
	}

}
