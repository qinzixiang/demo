package com.qinzx.demo.refactoring.firstdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 原代码中的vector替换成了ArrayList
 * @author qinzx
 * @date 2019/12/02 10:42
 */
public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        String result = "Rental Record for " + getName() + "\n";
        for (Rental rental : rentals) {
            result += "\t" + rental.getMovie().getTitle() + "\t" + rental.getCharge() + "\n";
        }
        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getTotalFrequentRenterPoints() + " frequent renter points";
        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;
        for (Rental rental : rentals) {
            result += rental.getFrequentRenterPoints();
        }
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        for (Rental rental : rentals) {
            result += rental.getCharge();
        }
        return result;
    }

//    重构前的代码
//    private String name;
//    private List<Rental> rentals = new ArrayList<>();
//
//    public Customer(String name) {
//        name = name;
//    }
//
//    public void addRental(Rental arg) {
//        rentals.add(arg);
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String statement() {
//        double totalAmount = 0;
//        int frequestRenterPoints = 0;
//        String result = "Rental Record for " + getName() + "\n";
//        for (Rental rental : rentals) {
//            double thisAmount = 0;
//
//            //determine amounts for each line
//            switch (rental.getMovie().getPriceCode()) {
//                case Movie.REGULAR:
//                    thisAmount += 2;
//                    if (rental.getDaysRented() > 2) {
//                        thisAmount += (rental.getDaysRented() - 2) * 1.5;
//                    }
//                    break;
//                case Movie.NEW_RELEASE:
//                    thisAmount += rental.getDaysRented() * 3;
//                    break;
//                case Movie.CHILDRENS:
//                    thisAmount += 1.5;
//                    if (rental.getDaysRented() > 3) {
//                        thisAmount += (rental.getDaysRented() - 3) * 1.5;
//                    }
//                    break;
//                default:
//                    break;
//            }
//
//            // add frequent renter points
//            frequestRenterPoints++;
//            // add bonus for a two day new release rental
//            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1) {
//                frequestRenterPoints++;
//            }
//            result += "\t" + rental.getMovie().getTitle() + "\t" + thisAmount + "\n";
//            totalAmount += thisAmount;
//        }
//
//        result += "Amount owed is " + totalAmount + "\n";
//        result += "You earned " + frequestRenterPoints + " frequent renter points";
//        return result;
//    }
}