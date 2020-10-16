package com.task.rssfeed;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static RssFeed html;
    RecyclerView recyclerView;
    TableLayout tableLayout;

    private List<Items> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {
            rssFeed();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ItemsAdapter extends RecyclerView.Adapter<UserViewHolder> {
        private List<Items> itemsList;
        public ItemsAdapter(List<Items> items) {
            super();
            this.itemsList = items;
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UserViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            holder.bind(this.itemsList.get(position));
        }

        @Override
        public int getItemCount() {
            return this.itemsList.size();
        }
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;

        public UserViewHolder(ViewGroup container) {
            super(LayoutInflater.from(MainActivity.this).inflate(R.layout.row, container, false));
            textView1 = (TextView) itemView.findViewById(R.id.textView1);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
            textView3 = (TextView) itemView.findViewById(R.id.textView3);
            textView4 = (TextView) itemView.findViewById(R.id.textView4);
        }
        public void bind(Items item)
        {
            textView1.setText(item.getItem1());
            textView2.setText(item.getItem2());
            textView3.setText(item.getItem3());
            textView4.setText(item.getItem4());
        }
    }

    private static final String BASE_URL = "https://howtodoinjava.com/";

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create());

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    @SuppressLint("WrongViewCast")
    public void rssFeed() throws IOException {
        httpClient.addInterceptor(loggingInterceptor);
        builder.client(httpClient.build());

        Retrofit retrofit = builder.build();

        RssService rssService = retrofit.create(RssService.class);

        Call<RssFeed> callAsync = rssService.getFeed();

        callAsync.enqueue(new Callback<RssFeed>() {
            @Override
            public void onResponse(Call<RssFeed> call, Response<RssFeed> response) {
                if (response.isSuccessful()) {
                    RssFeed apiResponse = response.body();
                    // API response
                    html = apiResponse;
                    ArrayList<ArrayList> list;
                    try {
                        list = toList(html.channel.item.get(0).getDescription());

                        itemsList = new ArrayList<>();

                        for (int i = 0; i < list.size(); i++) {
                            //TableRow row = new TableRow(MainActivity.this);

                            Items item = new Items();
                            item.setItem1((String) list.get(i).get(0));
                            item.setItem2((String) list.get(i).get(1));
                            item.setItem3((String) list.get(i).get(2));
                            item.setItem4((String) list.get(i).get(3));
                            itemsList.add(item);

//                            for (int j = list.get(i).size() - 1; j >= 0; j--) {
//
//
//                                TextView tv = new TextView(MainActivity.this);
//                                tv.setText((CharSequence) list.get(i).get(j));
//                                row.addView(tv);
//                                Log.e("view", String.valueOf(list.get(i).get(j)));
//                            }
//                            tableLayout.addView(row);
                        }
                        recyclerView.setAdapter(new ItemsAdapter(itemsList));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Request Error :: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RssFeed> call, Throwable t) {
                if (call.isCanceled()) {
                    System.out.println("Call was cancelled forcefully");
                } else {
                    System.out.println("Network Error :: " + t.getLocalizedMessage());
                }
            }
        });
    }

    //}
    public ArrayList toList(String HTMLTable) throws IOException {
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>(2);

        Document doc = Jsoup.parse(HTMLTable);
        Elements tableElements = doc.select("table");

        Elements tableRowElements = tableElements.select(":not(thead) tr");

        for (int i = 0; i < tableRowElements.size(); i++) {
            Element row = tableRowElements.get(i);

            ArrayList<String> td = new ArrayList<String>();
            Elements rowItems = row.select("td");

            for (int j = 0; j < rowItems.size(); j++) {
                if (j == 3) continue;
                td.add(rowItems.get(j).text());
            }

            list.add(td);
        }
        return list;
    }
}

class Items {
    private String item1;
    private String item2;
    private String item3;
    private String item4;

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }
}