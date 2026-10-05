package com.cs.week4;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainDriver {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Interest[] investments = new Interest[5];

		for (int i = 0; i < investments.length; i++) {

			System.out.println("\nInvestment " + (i + 1));

			try {
				investments[i] = Interest.readFromScanner(scanner);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid input: " + e.getMessage());
				i--;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input: text is not permitted. Numbers only.");
				scanner.nextLine();
				i--;
			}
		}

		System.out.println("\n========== INTEREST RESULTS ==========");

		for (int i = 0; i < investments.length; i++) {

			Interest inv = investments[i];

			BigDecimal p = inv.getPrincipal();
			BigDecimal r = inv.getRate();
			int t = inv.getTime();

			System.out.println("\nInvestment " + (i + 1) + ": P=" + p + "  R=" + r + "%  T=" + t + " years");

			System.out.println("Simple Interest   (BigDecimal): " + inv.calculateSimpleInterest(p, r, t));
			System.out.printf("Simple Interest   (Double)    : %.2f%n",
					inv.calculateSimpleInterest(p.doubleValue(), r.doubleValue(), t));

			System.out.println("Compound Interest (BigDecimal): " + inv.calculateCompoundInterest(p, r, t));
			System.out.printf("Compound Interest (Double)    : %.2f%n",
					inv.calculateCompoundInterest(p.doubleValue(), r.doubleValue(), t));
		}

		scanner.close();
	}
}