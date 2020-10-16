package com.task.rssfeed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RssService
{
    @GET("http://www.nbg.ge/rss.php") Call<RssFeed> getFeed();
}