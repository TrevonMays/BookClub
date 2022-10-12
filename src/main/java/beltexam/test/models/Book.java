package beltexam.test.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 200, message = "Minimum of three characters required")
    private String title;
    @NotNull
    @Size(min = 3, max = 200, message = "Minimum of three characters required")
    private String author;
    @NotNull
    @Size(min = 3, max = 200, message = "Minimum of three characters required")
    private String myThoughts;

    //RELATIONSHIP

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="user_id")
    private User user;
    /////////////////////////////////!NO ARGUMENT CONSTRUCTORS!////////////////////////////////////////////////////////
    public Book(){}
    /////////////////////////////////!GETTERS AND SETTERS!////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMyThoughts() {
        return myThoughts;
    }

    public void setMyThoughts(String myThoughts) {
        this.myThoughts = myThoughts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
