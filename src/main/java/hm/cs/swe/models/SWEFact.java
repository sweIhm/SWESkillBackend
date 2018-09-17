package hm.cs.swe.models;

import java.util.Random;

public class SWEFact {

    protected String text;
    protected int year;
    protected String type;

    public SWEFact(String text, int year, String type) {
        this.text = text;
        this.year = year;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int number) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
