package com.example.retrofitlearning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitlearning.classes.Comment;
import com.example.retrofitlearning.classes.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //==============================================================================================

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
        Call<List<Post>> call = jsonPlaceHolderApi.getPostsWithMultipleQuery(2,"id","desc");
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

    private void getPostsWithQueryMap() {
        Map<String,String> parameters = new HashMap<>();
        parameters.put("userId","2");
        parameters.put("_sort","id");
        parameters.put("_order","desc");

        Call<List<Post>> call = jsonPlaceHolderApi.getPostsWithQueryMap(parameters);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
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
                result.setText(String.format("%s %s", getString(R.string.error), t.getMessage()));
            }
        });
    }

    private void getPostsWithQueryCheck() {
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


                    if(post.getUserId() == 1){
                        result.append("\n\n" + content);
                    } else{
                        result.setText("Error");
                        result.setTextColor(Color.RED);
                        call.cancel();
                    }
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
    //==============================================================================================

    private void createPost() {
        Post newPost = new Post(20, "Athadu", "abcd");
        Call<Post> call = jsonPlaceHolderApi.createPost(newPost);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }
                Post postResponse = response.body();
                String content = "";
                content += "Response Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title : " + postResponse.getTitle() + "\n";
                content += "Text : " + postResponse.getText() + "\n\n";

                result.append("\n\n" + content);
                Toast.makeText(MainActivity.this, "New Resource created Response code from web: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                result.setText("Error: " + t.getMessage());
            }
        });
    }

    private void postWithPutOrPatch() {
        Call<Post> call = jsonPlaceHolderApi.createPost(20, "Vishnu Simha", "abcd");

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }
                Post postResponse = response.body();
                String content = "";
                content += "Response Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title : " + postResponse.getTitle() + "\n";
                content += "Text : " + postResponse.getText() + "\n\n";

                result.append("\n\n" + content);
                Toast.makeText(MainActivity.this, "New Resource created Response code from web: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                result.setText("Error: " + t.getMessage());
            }
        });
    }

    private void createPostWithHashMap() {

        HashMap<String, String> fields = new HashMap<>();
        fields.put("userId", "20");
        fields.put("title", "Akansha");
        fields.put("text", "She is beautiful");

        Call<Post> call = jsonPlaceHolderApi.createPost(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }
                Post postResponse = response.body();
                String content = "";
                content += "Response Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title : " + postResponse.getTitle() + "\n";
                content += "Text : " + postResponse.getText() + "\n\n";

                result.append("\n\n" + content);
                Toast.makeText(MainActivity.this, "New Resource created Response code from web: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                result.setText("Error: " + t.getMessage());
            }
        });
    }

    //==============================================================================================
    private void putPost() {
        Post newPost = new Post(20, "Post", "PUT");
        Call<Post> call = jsonPlaceHolderApi.putPost(1, newPost);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }
                Post postResponse = response.body();
                String content = "";
                content += "Response Code: " + response.code() + "\n";
                assert postResponse != null;
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title : " + postResponse.getTitle() + "\n";
                content += "Text : " + postResponse.getText() + "\n\n";

                result.append("\n\n" + content);
                Toast.makeText(MainActivity.this, "New Resource created Response code from web: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                result.setText("Error: " + t.getMessage());
            }
        });
    }

    private void patchPost() {
        Post newPost = new Post(20, "Post", "Patch");
        Call<Post> call = jsonPlaceHolderApi.patchPost(1, newPost);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }
                Post postResponse = response.body();
                String content = "";
                content += "Response Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title : " + postResponse.getTitle() + "\n";
                content += "Text : " + postResponse.getText() + "\n\n";

                result.append("\n\n" + content);
                Toast.makeText(MainActivity.this, "New Resource created Response code from web: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                result.setText("Error: " + t.getMessage());
            }
        });
    }

    //Button Events=================================================================================
    public void NextScreen(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
        Toast.makeText(MainActivity.this, "Next Screen", Toast.LENGTH_SHORT).show();
    }

    public void getThePosts(View view) {
        result.setText("");
        getPosts();
        Toast.makeText(MainActivity.this, "getPosts", Toast.LENGTH_SHORT).show();
    }

    public void postsWithQuery(View view) {
        result.setText("");
        getPostsWithQueryCheck();
        Toast.makeText(MainActivity.this, "getPostsWithQuery", Toast.LENGTH_SHORT).show();
    }

    public void postsWithMultipleQuery(View view) {
        result.setText("");
        getPostsWithMultipleQuery();
        Toast.makeText(MainActivity.this, "getPostsWithMultipleQuery", Toast.LENGTH_SHORT).show();
    }

    public void createPost(View view) {
        result.setText("");
//        createPost();
//        createPostWithParameters();
//        createPostWithHashMap();
        patchPost();
        Toast.makeText(MainActivity.this, "patchPost", Toast.LENGTH_SHORT).show();
    }

    public void postWithPutOrPatch(View view) {
        result.setText("");
        putPost();
        Toast.makeText(MainActivity.this, "putPost", Toast.LENGTH_SHORT).show();
    }
}
