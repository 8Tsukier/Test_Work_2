package ua.telesens.io;


public class RailwayStation {
    private String title;
    private String directorate;

    public RailwayStation(String title, String directorate) {
        this.title = title;
        this.directorate = directorate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirectorate() {
        return directorate;
    }

    public void setDirectorate(String directorate) {
        this.directorate = directorate;
    }

    @Override
    public String toString() {
        return title;
    }

    public String fullInfo() {
        return "Станция " + title + ". " + directorate + " ж.д.";
    }
}

