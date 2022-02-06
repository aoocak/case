package question1;

import question1.dto.PostDto;
import question1.dto.UserDto;
import question1.model.Post;
import question1.model.User;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Question1 {
    public static void main( String[] args ) {
        List<Integer> postIds = Arrays.asList(1,6,2,3,4,5);
        List<Post> posts = getPosts(2, postIds);

        posts.forEach(post -> {
            System.out.print(post == null ? "null, " : post.getId() + ", ");
        });
    }

    public static List<Post> getPosts(Integer requestedUserId, List<Integer> postIds) {
        Question1 question1 = new Question1();

        Map<Integer, PostDto> postDtoMap = question1.getPostsById(postIds);
        List<Integer> userIds = postDtoMap.values().stream().map(PostDto::getUserId).collect(Collectors.toList());
        Map<Integer, UserDto> userDtoMap = question1.getUserDtoMap(userIds);
        List<Integer> followingUsersIds = question1.getFollowingUserIds(requestedUserId, userIds);
        List<Integer> likedPostsIds = question1.getLikedPosts(requestedUserId, postIds);

        List<Post> posts = postIds.stream().map(p -> {
            PostDto postDto = postDtoMap.get(p);

            if (postDto == null) {
                return null;
            }

            UserDto userDto = userDtoMap.get(postDto.getUserId());

            Boolean isFollowing = followingUsersIds.contains(userDto.getId());
            User user = convertToUser(userDto, isFollowing);

            Boolean isLiked = likedPostsIds.contains(postDto.getId());
            Post post = convertToPost(postDto, user, isLiked);

            return post;
        }).collect(Collectors.toList());

        return posts;
    }

    private static Post convertToPost(PostDto postDto, User user, Boolean isLikes) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setDescription(postDto.getDescription());
        post.setImage(postDto.getImage());
        post.setCreatedAt(postDto.getCreatedAt());
        post.setOwner(user);
        post.setCreatedAt(postDto.getCreatedAt());
        post.setLiked(isLikes);
        return post;
    }

    private static User convertToUser(UserDto userDto, Boolean isFollowing) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUserName());
        user.setFullName(userDto.getFullName());
        user.setFollowed(isFollowing);
        return user;
    }

    public Map<Integer, PostDto> getPostsById(List<Integer> postIds) {
        String sql = String.format("SELECT id, description, user_id, image, created_at  FROM post WHERE id IN (%s) LIMIT "
                        + postIds.size(), postIds.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")));


        Map<Integer, PostDto> postDtoMap = new HashMap<>();
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while(rs.next()) {
                PostDto postDto = new PostDto(rs.getInt("id"),
                        rs.getString("description"),
                        rs.getInt("user_id"),
                        rs.getInt("image"),
                        rs.getInt("created_at"));
                    postDtoMap.put(postDto.getId(), postDto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return postDtoMap;
    }

    public Map<Integer, UserDto> getUserDtoMap(List<Integer> userIds) {
        String sql = String.format("SELECT id, username, email, full_name, profile_picture, bio, created_at FROM user WHERE id IN (%s)",
                userIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));

        Map<Integer, UserDto> userDtoMap = new HashMap<>();
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                UserDto userDto = new UserDto(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("profile_picture"),
                        rs.getString("bio"),
                        rs.getInt("created_at"));
                userDtoMap.put(userDto.getId(), userDto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return userDtoMap;

    }

    public List<Integer> getFollowingUserIds(Integer requestedUserId, List<Integer> followingIdList) {
        String sql = String.format("SELECT follower_id, created_at FROM follow f WHERE follower_id = "
                + requestedUserId + " AND following_id IN (%s)", followingIdList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));

        List<Integer> followingUserIds = new ArrayList<>();
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                followingUserIds.add(rs.getInt("follower_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return followingUserIds;
    }

    public List<Integer> getLikedPosts(Integer requestedUserId, List<Integer> postIds) {
        String sql = String.format("SELECT post_id FROM like WHERE user_id = " + requestedUserId
                + " AND user_id IN (%s)" , postIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));

        List<Integer> likedPosts = new ArrayList<>();
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                likedPosts.add(rs.getInt("post_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return likedPosts;
    }

    private Connection connect() {
        String url = "jdbc:sqlite:identifier.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
