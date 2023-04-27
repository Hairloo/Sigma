package edu.mirea.hairloo1x3.sigma.data.models;

public class Task {
    private String description, section, source, status, name, dif;
    public Task(String name, String description, String section, String status, String source, String dif){
        this.name = name;
        this.description = description;
        this.section = section;
        this.status = status;
        this.source = source;
        this.dif = dif;
    }

    public String getDescription() {
        return description;
    }

    public String getSection() {
        return section;
    }

    public String getSource() {
        return source;
    }

    public String getStatus() {
        return status;
    }

    public String getDif() {
        return dif;
    }

    public String getName() {
        return name;
    }
}
