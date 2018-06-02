package edu.wvup.acottri9.xmlparsing;

public class Item
{
    private String title;

    private String link;


    public Item(String title, String link)
    {
        this.title = title;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString()
    {
        return title + " ; " + link;
    }
}
