package question1.model;

public class Post {

    private Integer id;
    private String description;
    private User owner;
    private Integer image;
    private Integer createdAt;
    private Boolean liked;

    public Post() {
    }

    public Post(Integer id, String description, User owner, Integer image, Integer createdAt) {
        this.id = id;
        this.description = description;
        this.owner = owner;
        this.image = image;
        this.createdAt = createdAt;
    }

    public Post(Integer id, String description, User owner, Integer image, Integer createdAt, Boolean liked) {
        this.id = id;
        this.description = description;
        this.owner = owner;
        this.image = image;
        this.createdAt = createdAt;
        this.liked = liked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }
}
