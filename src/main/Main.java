package main;

import java.util.ArrayList;
import java.util.List;

import ui.window.Window;

import domain.Staff;

public class Main {

	public static void main(String[] args) {
		
		List<Staff> staffList = generateStaff();
		new Window(staffList);

	}
	
	public static List<Staff> generateStaff() {
		
		// instantiation of dummy staff is for demonstration purpose only
		// only administrator is needed to be instantiated here in real situation
		
		List<Staff> staffList = new ArrayList<Staff>();
		
		final String GENERAL_STAFF = "General Staff";
		final String DIRECTOR = "Director";
		final String ADMINISTRATOR = "Administrator";
		
		// instantiation of administrator ++
		Staff demoStaff00 = new Staff("admin", "admin", "admin", ADMINISTRATOR);
		staffList.add(demoStaff00);
		// instantiation of administrator --		
		
		// instantiation of director ++
		Staff demoStaff01 = new Staff("01", "Staff 01", "abcd1234", DIRECTOR);
		staffList.add(demoStaff01);
		// instantiation of director --

		// instantiation of general staff ++
		Staff demoStaff02 = new Staff("02", "Staff 02", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff02);

		Staff demoStaff03 = new Staff("03", "Staff 03", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff03);

		Staff demoStaff04 = new Staff("04", "Staff 04", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff04);

		Staff demoStaff05 = new Staff("05", "Staff 05", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff05);

		Staff demoStaff06 = new Staff("06", "Staff 06", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff06);

		Staff demoStaff07 = new Staff("07", "Staff 07", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff07);

		Staff demoStaff08 = new Staff("08", "Staff 08", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff08);

		Staff demoStaff09 = new Staff("09", "Staff 09", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff09);

		Staff demoStaff10 = new Staff("10", "Staff 10", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff10);

		Staff demoStaff11 = new Staff("11", "Staff 11", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff11);

		Staff demoStaff12 = new Staff("12", "Staff 12", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff12);

		Staff demoStaff13 = new Staff("13", "Staff 13", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff13);

		Staff demoStaff14 = new Staff("14", "Staff 14", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff14);

		Staff demoStaff15 = new Staff("15", "Staff 15", "abcd1234", GENERAL_STAFF);
		staffList.add(demoStaff15);
		// instantiation of general staff --
		
		// supervisor assignment ++
		demoStaff02.setSupervisor(demoStaff01);
		demoStaff03.setSupervisor(demoStaff01);

		demoStaff04.setSupervisor(demoStaff02);
		demoStaff05.setSupervisor(demoStaff02);

		demoStaff06.setSupervisor(demoStaff03);
		demoStaff07.setSupervisor(demoStaff03);

		demoStaff08.setSupervisor(demoStaff04);
		demoStaff09.setSupervisor(demoStaff04);

		demoStaff10.setSupervisor(demoStaff05);
		demoStaff11.setSupervisor(demoStaff05);

		demoStaff12.setSupervisor(demoStaff06);
		demoStaff13.setSupervisor(demoStaff06);

		demoStaff14.setSupervisor(demoStaff07);
		demoStaff15.setSupervisor(demoStaff07);
		// supervisor assignment --
		
		return staffList;
		
	}

}
