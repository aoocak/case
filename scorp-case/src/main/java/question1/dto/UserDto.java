package question1.dto;

public class UserDto {

    private Integer id;
    private String userName;
    private String email;
    private String fullName;
    private String profilePicture;
    private String bio;
    private Integer createdAt;

    public UserDto(Integer id, String userName, String email, String fullName, String profilePicture, String bio, Integer createdAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }
}
