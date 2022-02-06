package question2;

import question1.model.Post;

import java.util.*;

public class Question2 {

    public static void main( String[] args ) {
        Question2 question2 = new Question2();

        List<List<Post>> listOfPostLists = getListOfPostLists();
        List<Post> mergedList = question2.getMergedList(listOfPostLists);

        mergedList.forEach(l -> System.out.print(l.getCreatedAt() + " "));
        System.out.println();
    }

    public List<Post> getMergedList(List<List<Post>> listOfPostLists) {
        BinarySearchTree bts = new BinarySearchTree();
        List<Post> mergedList = new ArrayList<>();

        listOfPostLists.forEach(bts::insertList);
        bts.traverseInorder(bts.root, mergedList);

        return mergedList;
    }


    private static List<List<Post>> getListOfPostLists() {
        Post post = new Post();
        post.setId(1);
        post.setCreatedAt(1);

        Post post2 = new Post();
        post2.setId(2);
        post2.setCreatedAt(2);

        Post post3 = new Post();
        post3.setId(3);
        post3.setCreatedAt(3);

        Post post4 = new Post();
        post4.setId(4);
        post4.setCreatedAt(4);

        Post post5 = new Post();
        post5.setId(5);
        post5.setCreatedAt(5);

        Post post6 = new Post();
        post6.setId(6);
        post6.setCreatedAt(6);

        Post post7 = new Post();
        post7.setId(7);
        post7.setCreatedAt(7);

        Post post8 = new Post();
        post8.setId(8);
        post8.setCreatedAt(8);

        Post post9 = new Post();
        post9.setId(9);
        post9.setCreatedAt(9);

        Post post10 = new Post();
        post10.setId(10);
        post10.setCreatedAt(10);

        List<Post> postList = Arrays.asList(post, post3, post10);
        List<Post> postList2 = Arrays.asList(post2, post5, post8);
        List<Post> postList3 = Arrays.asList(post4, post7, post9);

        return Arrays.asList(postList, postList2, postList3);
    }

}
