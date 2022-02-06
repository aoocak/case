package question2;

import question1.model.Post;

import java.util.List;
import java.util.Objects;

public class BinarySearchTree {

    Node root;

    static class Node {
        Post post;
        Node left, right;

        public Node(Post post) {
            this.post = post;
            left = right = null;
        }
    }

    void insertList(List<Post> posts) {
        for (Post post : posts)
        root = insertPost(root, post);
    }

    Node insertPost(Node root, Post post) {
        if (root == null) {
            root = new Node(post);
            return root;
        }

        if (post.getCreatedAt() > root.post.getCreatedAt()) {
            root.left = insertPost(root.left, post);
        } else if (Objects.equals(post.getCreatedAt(), root.post.getCreatedAt())) {
            if (post.getId() > root.post.getId()) {
                root.left = insertPost(root.left, post);
            } else if (post.getId() < root.post.getId()){
                root.right = insertPost(root.right, post);
            }
        } else if (post.getCreatedAt() < root.post.getCreatedAt()) {
            root.right = insertPost(root.right, post);
        }

        return root;
    }

    void traverseInorder(Node root, List<Post> mergedList) {
        if (root != null) {
            traverseInorder(root.left, mergedList);
            mergedList.add(root.post);
            traverseInorder(root.right, mergedList);
        }
    }
}
