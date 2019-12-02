package com.qinzx.demo.refactoring.firstdemo;

/**
 * @ClassName: Rental
 * @Author qinzx
 * @Date 2019/12/02 10:41
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getCharge() {
        return movie.getCharge(daysRented);

    }

    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }
}