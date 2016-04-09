package ui.window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import ui.panel.admin.StaffAdditionPanel;
import ui.panel.admin.StaffDeletionPanel;
import ui.panel.admin.SupervisorAssignmentPanel;
import ui.panel.common.ContentPanel;
import ui.panel.common.FooterPanel;
import ui.panel.common.HeaderPanel;
import ui.panel.common.LoginPanel;
import ui.panel.staff.RequestApplicationPanel;
import ui.panel.staff.RequestHandlingPanel;
import ui.panel.staff.RequestViewingPanel;

import domain.Request;
import domain.Staff;

@SuppressWarnings("serial")
public class Window extends JFrame {

	public Window(final List<Staff> staffList) {
		// constructor for Window

		this.setTitle("eLeave System");
		this.setSize(550, 550);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		login(staffList);	// go to login panel

	}

	private void login(final List<Staff> staffList) {
		// login panel

		this.getContentPane().removeAll();

		HeaderPanel headerPanel = new HeaderPanel(null);
		this.add(headerPanel, BorderLayout.NORTH);

		FooterPanel footerPanel = new FooterPanel();
		this.add(footerPanel, BorderLayout.SOUTH);

		final LoginPanel loginPanel = new LoginPanel();
		this.add(loginPanel);

		footerPanel.btnBack.setEnabled(false);

		footerPanel.btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id = loginPanel.tfId.getText();
				String password = new String(loginPanel.pfPassword.getPassword());
				boolean validAuthen = false;

				// authentication
				for (Staff staff : staffList) {
					if (id.equals(staff.getId()) && password.equals(staff.getPassword())) {
						// valid authentication
						validAuthen = true;
						content(staffList, staff);	// go to content panel
					}
				}

				if (!validAuthen) {
					// invalid authentication
					JOptionPane.showMessageDialog(
							loginPanel,
							"Staff ID or Password is invalid.",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		this.reload();

	}

	private void content(final List<Staff> staffList, final Staff staff) {
		// content panel

		this.getContentPane().removeAll();

		HeaderPanel headerPanel = new HeaderPanel(staff);
		this.add(headerPanel, BorderLayout.NORTH);

		FooterPanel footerPanel = new FooterPanel();
		this.add(footerPanel, BorderLayout.SOUTH);

		final ContentPanel contentPanel = new ContentPanel(staffList, staff);
		this.add(contentPanel, BorderLayout.CENTER);

		footerPanel.btnConfirm.setEnabled(false);

		// administrator content panel ++
		contentPanel.btnStaffAddition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStaff(staffList, staff);
			}
		});

		contentPanel.btnAssignSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assignSupervisor(staffList, staff);
			}
		});

		contentPanel.btnStaffDeletion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteStaff(staffList, staff);
			}
		});
		// administrator content panel --

		// staff content panel ++
		contentPanel.btnRequestApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				applyRequest(staffList, staff);
			}
		});

		contentPanel.btnRequestViewing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewRequest(staffList, staff);
			}
		});

		contentPanel.btnRequestHandling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleRequest(staffList, staff);
			}
		});
		// staff content panel --

		footerPanel.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login(staffList);	// go to login panel
			}
		});

		this.reload();
	}

	private void addStaff(final List<Staff> staffList, final Staff staff) {
		// staff addition panel

		this.getContentPane().removeAll();

		HeaderPanel headerPanel = new HeaderPanel(staff);
		this.add(headerPanel, BorderLayout.NORTH);

		FooterPanel footerPanel = new FooterPanel();
		this.add(footerPanel, BorderLayout.SOUTH);

		final StaffAdditionPanel staffAdditionPanel = new StaffAdditionPanel();
		this.add(staffAdditionPanel, BorderLayout.CENTER);

		footerPanel.btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final String id = staffAdditionPanel.tfId.getText();
				final String name = staffAdditionPanel.tfName.getText();
				final String password = new String(staffAdditionPanel.pfPassword.getPassword());
				final String type = staffAdditionPanel.cbType.getSelectedItem().toString();

				boolean validAdditionStaff = true;

				final String DIRECTOR = "Director";
				final String GENERAL_STAFF = "General Staff";

				Staff additionStaff = null;
				boolean existDirector = false;

				if (!id.isEmpty() && !name.isEmpty() && !password.isEmpty()) {
					// sufficient information

					for (Staff staff : staffList) {
						if (staff.getId().equals(id)) {
							validAdditionStaff = false;
						}
						if (staff.getType().equals(DIRECTOR)) {
							existDirector = true;
						}
					}

					if (validAdditionStaff) {

						if (type.equals(DIRECTOR)) {
							// director
							if (!existDirector) {
								additionStaff = new Staff(id, name, password, DIRECTOR);
								staffList.add(additionStaff);
								content(staffList, staff);	//go to content panel
							} else {
								// more than 1 staff is not allowed
								JOptionPane.showMessageDialog(
										staffAdditionPanel,
										"Director is already exist.",
										"Warning",
										JOptionPane.WARNING_MESSAGE);
							}
						} else if (type.equals(GENERAL_STAFF)) {
							// general staff
							additionStaff = new Staff(id, name, password, GENERAL_STAFF);
							staffList.add(additionStaff);
							assignSupervisor(staffList, staff);	// go to supervisor assignment panel
						}

					} else {
						// duplication staff id
						JOptionPane.showMessageDialog(
								staffAdditionPanel,
								"Staff ID is duplicated with existing Staff.",
								"Warning",
								JOptionPane.WARNING_MESSAGE);
					}

				} else {
					// insufficient information
					JOptionPane.showMessageDialog(
							staffAdditionPanel,
							"Staff ID or Name or Password is missing.",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		footerPanel.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content(staffList, staff);	// go to content panel
			}
		});

		this.reload();

	}

	private void assignSupervisor(final List<Staff> staffList, final Staff staff) {
		// supervisor assignment panel

		this.getContentPane().removeAll();

		HeaderPanel headerPanel = new HeaderPanel(staff);
		this.add(headerPanel, BorderLayout.NORTH);

		FooterPanel footerPanel = new FooterPanel();
		this.add(footerPanel, BorderLayout.SOUTH);

		final SupervisorAssignmentPanel supervisorAssignmentPanel = new SupervisorAssignmentPanel(staffList);
		this.add(supervisorAssignmentPanel, BorderLayout.CENTER);

		footerPanel.btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String subordinateStaffId = supervisorAssignmentPanel.cbSubordinateId.getSelectedItem().toString();
				String supervisorStaffId = supervisorAssignmentPanel.cbSupervisorId.getSelectedItem().toString();

				Staff subordinate = null;
				Staff supervisor = null;

				boolean validSubordinate = true;
				final String DIRECTOR = "Director";


				for (Staff staff : staffList) {
					if (staff.getId().equals(subordinateStaffId) 
							&& staff.getType().equals(DIRECTOR)) {
						validSubordinate = false;
					} else {
						if (staff.getId().equals(subordinateStaffId)) {
							subordinate = staff;
						}
						if (staff.getId().equals(supervisorStaffId)) {
							supervisor = staff;
						}
					}
				}

				if (!subordinateStaffId.equals(supervisorStaffId)
						&& validSubordinate) {
					subordinate.setSupervisor(supervisor);
					content(staffList, staff);	// go to content panel
				} else if (subordinateStaffId.equals(supervisorStaffId)) {
					// duplicate staff id for subordinate and supervisor
					JOptionPane.showMessageDialog(
							supervisorAssignmentPanel,
							"Duplicate Input for Subordinate and Supervisor Staff ID",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
				} else if (!validSubordinate) {
					// subordinate staff type is director
					JOptionPane.showMessageDialog(
							supervisorAssignmentPanel,
							"Subordinate Staff Type cannot be Director",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		footerPanel.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content(staffList, staff);	// go to content panel
			}
		});

		this.reload();

	}

	private void deleteStaff(final List<Staff> staffList, final Staff staff) {
		// staff deletion panel
		
		this.getContentPane().removeAll();

		HeaderPanel headerPanel = new HeaderPanel(staff);
		this.add(headerPanel, BorderLayout.NORTH);

		FooterPanel footerPanel = new FooterPanel();
		this.add(footerPanel, BorderLayout.SOUTH);

		final StaffDeletionPanel staffDeletionPanel = new StaffDeletionPanel(staffList);
		this.add(staffDeletionPanel, BorderLayout.CENTER);

		footerPanel.btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String deletionStaffId = staffDeletionPanel.cbDeletionStaffId.getSelectedItem().toString();
				Staff deletionStaff = null;
				boolean isDirector = false;

				List<Request> discardRequestList = new ArrayList<Request>();
				List<Staff> subordinateList = new ArrayList<Staff>();

				for (Staff staff : staffList) {
					if (staff.getId().equals(deletionStaffId)) {
						deletionStaff = staff;
						if (deletionStaff.getSupervisor() == null) {
							isDirector = true;
						}
					}
				}

				for (Staff staff : staffList) {
					if (staff.getSupervisor() != null
							&& staff.getSupervisor().equals(deletionStaff)) {
						subordinateList.add(staff);
					}
				}

				if (!isDirector) {
					// deletion staff is not director

					// apply all leave request pending to be handled by deletion staff
					if (deletionStaff.getRequestHandlingList() != null) {
						for (Request request : deletionStaff.getRequestHandlingList()) {
							deletionStaff.applyRequest(request);
						}
					}

					// remove leave request applied by deletion staff
					for (Staff staff : staffList) {
						if (staff.getRequestApplicationList() != null) {
							for (Request request : staff.getRequestHandlingList()) {
								if (request.getApplicant().equals(deletionStaff)) {
									discardRequestList.add(request);
								}
							}
							for (Request request : discardRequestList) {
								staff.removeRequestApplicationList(request);
							}
							discardRequestList.clear();
						}
					}

					// auto re-set supervisor for subordinate of deletion staff
					for (Staff staff : staffList) {
						if (staff.getSupervisor() != null
								&& staff.getSupervisor().equals(deletionStaff)) {
							staff.setSupervisor(deletionStaff.getSupervisor());
						}
					}

					// remove deletion staff
					if (deletionStaff.getRequestApplicationList() != null) {
						deletionStaff.getRequestApplicationList().clear();
					}
					if (deletionStaff.getRequestHandlingList() != null) {
						deletionStaff.getRequestHandlingList().clear();
					}
					staffList.remove(deletionStaff);

					content(staffList, staff);	// go to content panel

				} else {
					// deletion staff is director
					JOptionPane.showMessageDialog(
							staffDeletionPanel,
							"Director cannot be deleted.\n",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		footerPanel.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content(staffList, staff);	//go to content panel
			}
		});

		this.reload();

	}

	private void applyRequest(final List<Staff> staffList, final Staff staff) {
		// leave request application panel

		this.getContentPane().removeAll();

		HeaderPanel headerPanel = new HeaderPanel(staff);
		this.add(headerPanel, BorderLayout.NORTH);

		FooterPanel footerPanel = new FooterPanel();
		this.add(footerPanel, BorderLayout.SOUTH);

		final RequestApplicationPanel leaveApplicationPanel = new RequestApplicationPanel();
		this.add(leaveApplicationPanel, BorderLayout.CENTER);

		footerPanel.btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					Date startDate = sdf.parse(leaveApplicationPanel.tfStartDate.getText());
					Date endDate = sdf.parse(leaveApplicationPanel.tfEndDate.getText());

					if (leaveApplicationPanel.tfStartDate.getText().equals(sdf.format(startDate))
							&& leaveApplicationPanel.tfEndDate.getText().equals(sdf.format(endDate))) {

						if (!startDate.after(endDate)) {
							// start date on or before end date

							Request request = new Request(staff, startDate, endDate);
							staff.addRequestAppliactionList(request);
							staff.applyRequest(request);

							content(staffList, staff);	// go to content panel

						} else {
							// start date after end date
							JOptionPane.showMessageDialog(
									leaveApplicationPanel,
									"Start Date Before End Date",
									"Warning",
									JOptionPane.WARNING_MESSAGE);
						}

					} else {
						// invalid date
						JOptionPane.showMessageDialog(
								leaveApplicationPanel,
								"Invalid Date",
								"Warning",
								JOptionPane.WARNING_MESSAGE);
					}

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					// invalid date
					JOptionPane.showMessageDialog(
							leaveApplicationPanel,
							"Invalid Date",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		footerPanel.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content(staffList, staff);	// go to content panel
			}
		});

		this.reload();

	}

	private void viewRequest(final List<Staff> staffList, final Staff staff) {
		// leave request viewing panel

		this.getContentPane().removeAll();

		HeaderPanel headerPanel = new HeaderPanel(staff);
		this.add(headerPanel, BorderLayout.NORTH);

		FooterPanel footerPanel = new FooterPanel();
		this.add(footerPanel, BorderLayout.SOUTH);

		RequestViewingPanel leaveViewingPanel = new RequestViewingPanel(staff, staff.getRequestApplicationList().size());
		JScrollPane scrollPane = new JScrollPane(leaveViewingPanel);
		this.add(scrollPane, BorderLayout.CENTER);

		footerPanel.btnConfirm.setEnabled(false);

		footerPanel.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content(staffList, staff);	// go to content panel
			}
		});

		this.reload();

	}

	private void handleRequest(final List<Staff> staffList, final Staff staff) {
		// leave request handling panel

		this.getContentPane().removeAll();

		HeaderPanel headerPanel = new HeaderPanel(staff);
		this.add(headerPanel, BorderLayout.NORTH);

		FooterPanel footerPanel = new FooterPanel();
		this.add(footerPanel, BorderLayout.SOUTH);

		final RequestHandlingPanel leaveHandlingPanel = new RequestHandlingPanel(staff, staff.getRequestHandlingList().size());
		JScrollPane scrollPane = new JScrollPane(leaveHandlingPanel);
		this.add(scrollPane, BorderLayout.CENTER);

		footerPanel.btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Request> discardRequestList = new ArrayList<Request>();
				int i = 0;

				for (Request request : staff.getRequestHandlingList()) {

					Boolean isApprove = null;

					if (leaveHandlingPanel.rbListEndorse.get(i).isSelected()
							&& !leaveHandlingPanel.rbListDecline.get(i).isSelected()) {
						isApprove = true;
						discardRequestList.add(request);
					}else if (!leaveHandlingPanel.rbListEndorse.get(i).isSelected()
							&& leaveHandlingPanel.rbListDecline.get(i).isSelected()) {
						isApprove = false;
						discardRequestList.add(request);
					}

					if (isApprove != null) {
						staff.handleRequest(request, isApprove);
					}

					i ++;

				}

				for (Request request : discardRequestList) {
					staff.removeRequestHandlingList(request);
				}

				content(staffList, staff);	// go to content panel

			}
		});

		footerPanel.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content(staffList, staff);	// go to content panel
			}
		});

		this.reload();

	}

	private void reload() {

		this.revalidate();
		this.repaint();
		this.setVisible(true);

	}

}
