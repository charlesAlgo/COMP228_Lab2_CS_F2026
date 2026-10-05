package com.cs.week4;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Interest {

	private BigDecimal principal;
	private BigDecimal rate;
	private int time;

	// SI = P*R*T / 100
	// CI = P*(1 + R/100)^T - P

	// Constructor validates inputs
	public Interest(BigDecimal principal, BigDecimal rate, int time) {

		if (principal.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Principal must be greater than zero.");
		}
		if (principal.scale() <= 0) {
			throw new IllegalArgumentException("Principal must be a decimal, not an integer.");
		}
		if (rate.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Rate cannot be negative.");
		}
		if (rate.scale() <= 0) {
			throw new IllegalArgumentException("Rate must be a decimal, not an integer.");
		}
		if (time <= 0) {
			throw new IllegalArgumentException("Time must be greater than zero.");
		}

		this.principal = principal;
		this.rate = rate;
		this.time = time;
	}

	// Method 1 - Simple Interest using BigDecimal
	public BigDecimal calculateSimpleInterest(BigDecimal p, BigDecimal r, int t) {
		return p.multiply(r)
				.multiply(new BigDecimal(t))
				.divide(new BigDecimal("100"))
				.setScale(2, RoundingMode.HALF_UP);
	}

	// Method 2 - Simple Interest using Double - method overloading
	public double calculateSimpleInterest(double p, double r, int t) {
		return p * r * t / 100;
	}

	// Method 3 - Compound Interest using BigDecimal
	public BigDecimal calculateCompoundInterest(BigDecimal p, BigDecimal r, int t) {
		BigDecimal base = BigDecimal.ONE.add(r.divide(new BigDecimal("100")));
		BigDecimal amount = p.multiply(base.pow(t));
		return amount.subtract(p).setScale(2, RoundingMode.HALF_UP);
	}

	// Method 4 - Compound Interest using Double - method overloading
	public double calculateCompoundInterest(double p, double r, int t) {
		return p * Math.pow(1 + r / 100, t) - p;
	}

	// Method 5 - reads one investment using the Scanner library
	public static Interest readFromScanner(Scanner scanner) {

		System.out.print("Enter Principal: ");
		BigDecimal principal = scanner.nextBigDecimal();

		System.out.print("Enter Interest Rate (%): ");
		BigDecimal rate = scanner.nextBigDecimal();

		System.out.print("Enter Time (years): ");
		int time = scanner.nextInt();

		return new Interest(principal, rate, time);
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public int getTime() {
		return time;
	}
}