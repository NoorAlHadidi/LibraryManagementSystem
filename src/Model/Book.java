package Model;
import java.sql.Date;

public class Book {
    private int id;
    private String title;
    private int authorID;
    private String genre;
    private Date published;

    public Book(int id, String title, int authorID, String genre, Date published) {
        this.id = id;
        this.title = title;
        this.authorID = authorID;
        this.genre = genre;
        this.published = published;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }
}
