package com.example.retrofitlearning;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitlearning.classes.Comment;
import com.example.retrofitlearning.classes.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MainActivity
 */

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //We created this interface. we are creating an object for that with the help of retrofit
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

//        getPosts();
//        getPostsWithQuery();
        getPostsWithMultipleQuery();
//        getComments();
    }


    private void getComments() {
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(4);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for (Comment comment : comments) {
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "postId: " + comment.getPostId() + "\n";
                    content += "NAME: " + comment.getName() + "\n";
                    content += "Email : " + comment.getEmail() + "\n";
//                    content += "Text : " + comment.getText() + "\n\n";

                    result.append("\n\n" + content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                result.setText("Error: " + t.getMessage());
            }
        });
    }


    private void getPosts() {
        //Here we are starting a call for getting posts from endpoint posts. A post contains {id, user id, title, text}
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        // This happens in background{retrofit helps us to run this in background}
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                // This runs when we get a response from server, it doesn't mean successful, it can be not found response also 404.

                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                assert posts != null;
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title : " + post.getTitle() + "\n";
                    content += "Text : " + post.getText() + "\n\n";

                    result.append("\n\n" + content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
//               This executes on failure of call. We can display an error msg here or cancel the call.
                result.setText(String.format("%s %s", getString(R.string.error), t.getMessage()));
//                call.cancel();
            }
        });
    }


    private void getPostsWithQuery() {
        //Here we are starting a call for getting posts from endpoint posts WITH A QUERY . A post contains {id, user id, title, text}
        //sending user ID as a parameter to QUERY and get particular data
        Call<List<Post>> call = jsonPlaceHolderApi.getPostsWithQuery(10);
        // This happens in background{retrofit helps us to run this in background}
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                // This runs when we get a response from server, it doesn't mean successful, it can be not found response also 404.

                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                assert posts != null;
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title : " + post.getTitle() + "\n";
                    content += "Text : " + post.getText() + "\n\n";

                    result.append("\n\n" + content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
//               This executes on failure of call. We can display an error msg here or cancel the call.
                result.setText(String.format("%s %s", getString(R.string.error), t.getMessage()));
//                call.cancel();
            }
        });
    }

    private void getPostsWithMultipleQuery() {
        //Here we are starting a call for getting posts from endpoint posts WITH A QUERY . A post contains {id, user id, title, text}
        //sending user ID as a parameter to QUERY and get particular data
//        asec/desc for acending/descending order of sorting. For sort parameter we should send based on what you need to sort
        Call<List<Post>> call = jsonPlaceHolderApi.getPostsWithMultipleQuery(1,"id","desc");
        // This happens in background{retrofit helps us to run this in background}
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                // This runs when we get a response from server, it doesn't mean successful, it can be not found response also 404.

                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                assert posts != null;
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title : " + post.getTitle() + "\n";
//                    content += "Text : " + post.getText() + "\n\n";

                    result.append("\n\n" + content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
//               This executes on failure of call. We can display an error msg here or cancel the call.
                result.setText(String.format("%s %s", getString(R.string.error), t.getMessage()));
//                call.cancel();
            }
        });
    }
}