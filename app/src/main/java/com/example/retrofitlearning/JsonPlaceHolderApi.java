package com.example.retrofitlearning;

import com.example.retrofitlearning.classes.Comment;
import com.example.retrofitlearning.classes.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    /**
     * Here we are running a query by passing id.
     * So that will give us particular data based on the id.
     */
    @GET("posts")
    Call<List<Post>> getPostsWithQuery(@Query("id") int id);

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
    Call<List<Post>> getPostsWithQueryMap(@QueryMap Map<String, String> parameters);

    /**
     * Here we are keeping id as placeholder and passing it in our implementation methods @Path helps us to keep that property in exact place
     * {id} for that we need to keep in flower bracket
     */
    @GET("/posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @GET
    Call<List<Comment>> getCommentsWithUrl(@Url String url);


    /**
     * This is same as the GET req in the opp direction
     */
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@Field("userId") int userId, @Field("title") String title, @Field("body") String text);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap HashMap<String, String> fields);

    /**
     * PUT Replaces a post entirely
     */
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);

    /**
     * PATCH Modifies a post based on the fields we sent means it just updates the fields it will not replace a post like PUT
     */
    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);
}
