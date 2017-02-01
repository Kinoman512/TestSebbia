package sebbia.ru.testsebbia.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sebbia.ru.testsebbia.MyFragmentManager;
import sebbia.ru.testsebbia.MyListner;
import sebbia.ru.testsebbia.NetManager;
import sebbia.ru.testsebbia.R;
import sebbia.ru.testsebbia.model.New;
import sebbia.ru.testsebbia.view.fragment.DetailedNewsFragment;

/**
 * Created by User on 01.02.2017.
 */

public class NewsAdapter extends BaseAdapter {

    Context mContext;
    List<New> listNews;

    public NewsAdapter(Context mContext, List<New> listNews ) {
        this.mContext = mContext;
        this.listNews = listNews;
    }


    @Override
    public int getCount() {
        return listNews.size();
    }

    @Override
    public Object getItem(int position) {
        return listNews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;// lcw.get(position).getId();
    }

    @Override
    public View getView(int position, View someView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        if (someView == null) {
            someView = inflater.inflate(R.layout.item_news, parent, false);
            TextView news_title = (TextView) someView.findViewById(R.id.news_title);
            TextView news_date = (TextView) someView.findViewById(R.id.news_date);
            TextView news_desc = (TextView) someView.findViewById(R.id.news_desc);

            final New news = listNews.get(position);

            news_title.setText(news.getTitle());
            news_date.setText(news.getDate());
            news_desc.setText(news.getShortDesc());

            //catText.setText(cat.getName());

            someView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // MyFragmentManager.replaceFragment( new NewsFragment());


                    NetManager.getDetailedNews(Integer.parseInt(news.getId()), new MyListner() {
                        @Override
                        public void onSucceces(Object obj) {

                            ArrayList<New> list = (ArrayList<New>) obj;

                            DetailedNewsFragment newFragment = new DetailedNewsFragment();
                            Bundle args = new Bundle();
                            args.putSerializable("list", list);
                            newFragment.setArguments(args);

                            MyFragmentManager.replaceFragment(newFragment);
                        }
                    });
                }
            });

        }


        return someView;


    }
}