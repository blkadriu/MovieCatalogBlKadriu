package com.example.moviecatalog;

public class Movie {
    private String poster, title, description, dateOfRelease;
    private Double rating;
    String cover;
    int id;

    public Movie(String title, String cover, String poster,
                 String description, String yearOfRelease, Double rating, int id) {
        this.title = title;
        this.cover = cover;
        this.poster = poster;
        this.description = description;
        this.dateOfRelease = yearOfRelease;
        this.rating = rating;
        this.id = id;
    }

    public String getCover(){
        return cover;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getDescription() {
        return description;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public Double getRating() {
        return rating;
    }
}
