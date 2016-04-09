package ui.panel.common;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FooterPanel extends JPanel {
	
	public JButton btnConfirm = new JButton("Confirm");
	public JButton btnBack = new JButton("Back");
	
	public FooterPanel() {
		
		this.add(btnConfirm);
		this.add(btnBack);
		
	}
	

}
