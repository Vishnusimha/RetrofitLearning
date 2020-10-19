package com.example.retrofitlearning;

import com.example.retrofitlearning.classes.Comment;
import com.example.retrofitlearning.classes.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    /**
     * Here we are running a query by passing id.
     * So that will give us particular data based on the id.
     */
    @GET("posts")
    Call<List<Post>> getPostsWithQuery(
            @Query("id") int id);

    /**
     * Here we are running a query by passing multiple parameters to sort the data.
     * So that will give us particular data based on that.
     *
     * Note: we are using Integer instead of int because its nullable means we can pass null also.
     */
    @GET("posts")
    Call<List<Post>> getPostsWithMultipleQuery(@Query("userId") Integer userID, @Query("_sort") String sort, @Query("_order") String order);


    /**
     * Here we are running a query by passing multiple parameters to sort the data.
     * So that will give us particular data based on that.
     *
     * Note: we are using Integer instead of int because its nullable means we can pass null also.
     */
    @GET("posts")
    Call<List<Post>> getPostsWithQueryMap(@QueryMap Map<String,String> parameters);

    /**
     * Here we are keeping id as placeholder and passing it in our implementation methods @Path helps us to keep that property in exact place
     * {id} for that we need to keep in flower bracket
     */
    @GET("/posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

}
