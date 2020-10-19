package com.example.retrofitlearning;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //We created this interface
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

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
}