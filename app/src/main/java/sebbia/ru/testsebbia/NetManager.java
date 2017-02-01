package sebbia.ru.testsebbia;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import sebbia.ru.testsebbia.model.Categorie;
import sebbia.ru.testsebbia.model.New;

/**
 * Created by User on 01.02.2017.
 */

public class NetManager {


    public static void getCategories(final MyListner listner){

        final AsyncHttpClient client = new AsyncHttpClient();
        final String url = "http://testtask.sebbia.com/v1/news/categories";

        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                Util.showMassage("Ошибка при загрузке категорий");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {


                if(statusCode != 200){
                    Util.showMassage("Ошибка " +statusCode+ "при загрузке категорий");
                }

                JSONObject sobj = null;
                try {
                    sobj = new JSONObject(responseString);

                    JSONArray list = sobj.getJSONArray("list");
                    if (list.length() == 0) {
                        //нет данных
                        Util.showMassage("Нет категорий");
                        return;
                    }

                    List<Categorie> listCats = new ArrayList<Categorie>();

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject item = list.getJSONObject(i);
                        String id = item.getString("id");
                        String name = item.getString("name");
                        Categorie cat = new Categorie();

                        cat.setId(Integer.valueOf(id));
                        cat.setName(name);

                        listCats.add(cat);
                    }


                    listner.onSucceces(listCats);

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
            });



    }



    public static void getNewsByCat(int idCat,int page, final MyListner listner){


        final AsyncHttpClient client = new AsyncHttpClient();
        final String url = "http://testtask.sebbia.com/v1/news/categories/" + idCat + "/news?page=" +  page;

        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                Util.showMassage("Ошибка при загрузке новостей из категории");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {


                if(statusCode != 200){
                    Util.showMassage("Ошибка " +statusCode+ " при загрузке новостей из категории");
                }

                JSONObject sobj = null;
                try {
                    sobj = new JSONObject(responseString);
                    List<New> listNews = new ArrayList<New>();

                    JSONArray list = sobj.getJSONArray("list");

                    if (list.length() == 0) {
                        listner.onSucceces(listNews);
                        return;
                    }


                    for (int i = 0; i < list.length(); i++) {


                        JSONObject item = list.getJSONObject(i);

                        String id = item.getString("id");
                        String title = item.getString("title");
                        String date = item.getString("date");
                        String shortDescription = item.getString("shortDescription");

                        New news  = new New();

                        news.setId(id);
                        news.setTitle(title);
                        news.setDate(date);
                        news.setShortDesc(shortDescription);

                        listNews.add(news);

                    }

                    listner.onSucceces(listNews);



                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });
    }




    public static void getDetailedNews(int idNew, final MyListner listner){


        final AsyncHttpClient client = new AsyncHttpClient();
        final String url = "http://testtask.sebbia.com/v1/news/details?id=" + idNew;

        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                Util.showMassage("Ошибка при загрузке полной новости");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {


                if(statusCode != 200){
                    Util.showMassage("Ошибка " +statusCode+ " при загрузке полной новости");
                }

                JSONObject sobj = null;
                try {
                    sobj = new JSONObject(responseString);
                    List<New> listNews = new ArrayList<New>();

                    JSONObject item  = sobj.getJSONObject("news");




                        String id = item.getString("id");
                        String title = item.getString("title");
                        String date = item.getString("date");
                        String shortDescription = item.getString("shortDescription");
                        String fullDescription = item.getString("fullDescription");

                        New news  = new New();

                        news.setId(id);
                        news.setTitle(title);
                        news.setDate(date);
                        news.setShortDesc(shortDescription);
                        news.setFullDesc(fullDescription);

                        listNews.add(news);



                    listner.onSucceces(listNews);



                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });
    }


}
