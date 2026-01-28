package GroupProject;

import java.util.Scanner;

public class EmployeeSchedulingSystem {
	// Properties
	private static Scanner sc = new Scanner(System.in);
	private static String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	private static String[] shifts = {"M", "A", "E"};
	private static String[] employee_List = new String[0];
	private static int employees = employee_List.length;
	private static String[][][] mall_Schedule = new String[employees][3][6];
	
	
	public static void main(String[] args) {
		intialize_Mall_Sched();

	}
	
	private static void intialize_Mall_Sched() {
		// Days
		for (int i = 1, j = 0; i <= 7; i++, j++) {
			mall_Schedule[0][0][i] = days[j];
		}
		
		// Shifts
		for (int a = 1, b = 0; a <= 3; a++, b++) {
			mall_Schedule[0][a][b] = shifts[b];
		}
			
	}
	
	public static void _View_Employee_Sched() {

	}
	
	public static void _AssignEmployee() {
		
	}
	
	public static void _Update_Employee() {
		
	}
	
	public static void _Search_Employee() {
		
	}
	
	public static void _Exit_The_System() {
		
	}

}
