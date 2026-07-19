package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.UK);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/yyyy");
		
		
		System.out.print("Enter department's name: ");
		String department = sc.nextLine();
		
		System.out.println("Enter worker data: ");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Level: ");
		String level = sc.nextLine();
		System.out.print("Base salary: ");
		Double baseSalary = sc.nextDouble();
		
		Worker worker = new Worker(name, WorkerLevel.valueOf(level), baseSalary, new Department(department));
		
		System.out.print("How many contracts to this worker: ");
		int n = sc.nextInt();
		sc.nextLine();
		for(int i = 0 ; i < n ; i++) {
			System.out.println("Enter the contract#" + (i + 1) + " data: ");
			System.out.print("Data (DD/MM/YYYY): ");
			Date stringData = sdf1.parse(sc.next());
			System.out.print("Value per hour: ");
			Double valuePerHour = sc.nextDouble();
			System.out.print("Duration (Hours): ");
			Integer hours = sc.nextInt();
			HourContract hourcontract = new HourContract(stringData, valuePerHour, hours);
			worker.addContratc(hourcontract);
		}
		System.out.print("Enter month and year to calculate income(MM/YYYY): ");
		String incomeData = sc.next();
		int month = Integer.parseInt(incomeData.substring(0, 2));
		int year = Integer.parseInt(incomeData.substring(3));
		worker.income(month,year);
		
		System.out.print(worker);
		System.out.println(", income for " + incomeData + ": " + String.format("%.2f",worker.income(year, month)));
		
		sc.close();
	}

}
