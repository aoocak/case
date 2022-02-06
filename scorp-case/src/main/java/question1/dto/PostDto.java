package question1.dto;

public class PostDto {

    private Integer id;
    private String description;
    private Integer userId;
    private Integer image;
    private Integer createdAt;

    public PostDto(Integer id, String description, Integer userId, Integer image, Integer createdAt) {
        this.id = id;
        this.description = description;
        this.userId = userId;
        this.image = image;
        this.createdAt = createdAt;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
