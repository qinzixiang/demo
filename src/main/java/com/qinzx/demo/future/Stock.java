package com.qinzx.demo.future;

/**
 *
 * @author zhhe.me@gmail.com
 * @since 9/9/2018
 */
class Stock {
    String repo;
    int count;

    public Stock(String repo, int count) {
        this.repo = repo;
        this.count = count;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Stock{");
        sb.append("repo='").append(repo).append('\'');
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}