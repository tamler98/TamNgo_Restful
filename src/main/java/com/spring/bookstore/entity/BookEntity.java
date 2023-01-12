package entity;

import javax.persistence.*;

@Entity
@Table (name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column (name = "id")
    private int id;

    @Column (name = "name")
    private String name;

    @Column (name = "author")
    private String author;

    @ManyToOne
    @JoinColumn (name = "category")
    private CategoryEntity category;

    @OneToOne(cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private BookDetailsEntity bookDetails;

    public BookEntity() {

    }

    public int getId(int i) {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public BookDetailsEntity getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetailsEntity bookDetails) {
        this.bookDetails = bookDetails;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "Id=" + id +
                ", Name='" + name + '\'' +
                ", Author='" + author + '\'' +
                ", PublishDate= " + bookDetails.getPublishDate() +
                ", Price= " + bookDetails.getPrice() +
                '}';
    }
}


