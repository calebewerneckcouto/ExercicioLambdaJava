package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Product;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		String path = "C:\\Users\\WINDOWS 11\\eclipse-workspace\\ExercicioLambdaJava\\src\\lista.txt";

		try (BufferedReader bf = new BufferedReader(new FileReader(path))) {

			List<Product> list = new ArrayList<Product>();

			String line = bf.readLine();

			while (line != null) {
				String[] vect = line.split(",");
				list.add(new Product(vect[0], Double.parseDouble(vect[1])));
				line = bf.readLine();

			}

			double avg = list.stream().map(x -> x.getPrice()).reduce(0.0, (x, y) -> x + y) / list.size();//reduce faz a soma dos elementos
			
			System.out.println("Average price: " + String.format("%.2f", avg));
			
			Comparator<String> comp = (s1,s2)-> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> name = list.stream().filter(x->x.getPrice()<avg).map(x->x.getName()).sorted(comp.reversed()).collect(Collectors.toList());

			name.forEach(System.out::println);
			
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}

		sc.close();

	}

}
