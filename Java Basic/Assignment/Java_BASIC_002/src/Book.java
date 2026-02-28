public class Book {
    private int id;
    private String title;
    private final String[] author = new String[2];
    private int publishedYear;
    private Status status;

    public Book() {}

    public Book(int id, String title, String authorName, String activeYear, int publishedYear, Status status) {
        this.id = id;
        this.title = title;
        this.author[0] = authorName;
        this.author[1] = activeYear;
        this.publishedYear = publishedYear;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return author[0];
    }

    public void setAuthor(String author) {
        this.author[0] = author;
    }

    public String getActiveYear() {
        return author[1];
    }

    public void setActiveYear(String activeYear) {
        this.author[1] = activeYear;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
