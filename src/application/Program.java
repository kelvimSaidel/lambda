package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		
		Locale.setDefault(Locale.US);
		
		Scanner read = new Scanner(System.in);
		
		System.out.println("Enter full file path");
		String path = "C:\\temp\\in.txt";
		
		
		try (BufferedReader bf = new BufferedReader(new FileReader(path))){
			
			List<Employee> datasEmp = new ArrayList<>();
					
			String line = bf.readLine();
			while (line != null) {
				String[] vct = line.split(",");
				datasEmp.add(new Employee(vct[0],vct[1],Double.parseDouble(vct[2])));
				line = bf.readLine();

			}
			
			System.out.println("Enter salary");
			Double salary = read.nextDouble();
			
			List<String> ListEmail = datasEmp.stream().filter(x -> x.getSalary() > salary)
					.map(x -> x.getEmail())
					.sorted()
					.collect(Collectors.toList());;
					
			System.out.println("Email of people whose salary is more than "+ String.format("%.2f", salary)+ ":");
			ListEmail.forEach(System.out::println);
			
			double sum = datasEmp.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x, y) -> x + y);		
			
			System.out.println("Sum of salary from people whose name Start with 'M': "+ sum);
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String>  biggestSalary = datasEmp.stream().filter(x -> x.getSalary() > 2000.00).map(p -> p.getEmail())
					.sorted(comp.reversed()).collect(Collectors.toList());
			
			
			
		}catch  (IOException e) {
			
			System.out.println("Error: " + e.getMessage());

			
		}
		
		read.close();
		
			
			

}

}
