package com.example.worldskills;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AnalyzeFragment extends Fragment { ;
    public static ArrayList<NewsModel> news = new ArrayList<>();
    public static ArrayList<CatalogItem> catalog = new ArrayList<>();
    RecyclerView newsList, categoriesList, catalogList;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    LinearLayoutManager HorizontalLayoutNews, HorizontalLayoutCategories;
    NewsListAdapter newsAdapter;
    CategoriesAdapter categoriesAdapter;
    CatalogAdapter catalogAdapter;

    public AnalyzeFragment() {

    }

    public void updateNews() {
        Thread t = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                news = GetNewsTask.serializeObjects(GetNewsTask.getObjects());
            }

            @Override
            public synchronized void start() {
                super.start();
            }
        };
        t.start();
    }

    public ArrayList<CatalogItem> getCatalogItems() {
        Thread t = new Thread() {
            @Override
            public void run() {
                catalog = getCatalogTask.serializeItems(getCatalogTask.getCatalogItems());
            }
        };
        t.start();
        return catalog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analyze, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateNews();
        Log.d("NEWS", String.valueOf(news.size()));
        newsList = view.findViewById(R.id.newsList);
        RecyclerViewLayoutManager = new LinearLayoutManager(getContext());
        newsList.setLayoutManager(RecyclerViewLayoutManager);
        newsAdapter = new NewsListAdapter(news);
        categoriesAdapter = new CategoriesAdapter();
        HorizontalLayoutNews = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        HorizontalLayoutCategories = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        newsList.setLayoutManager(HorizontalLayoutNews);
        newsList.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
        categoriesList = view.findViewById(R.id.categoriesList);
        categoriesList.setLayoutManager(RecyclerViewLayoutManager);
        categoriesList.setLayoutManager(HorizontalLayoutCategories);
        categoriesList.setAdapter(categoriesAdapter);
        catalogList = view.findViewById(R.id.catalogList);
        getCatalogItems();
        catalogAdapter = new CatalogAdapter(catalog);
        catalogList.setLayoutManager(RecyclerViewLayoutManager);
        catalogList.setAdapter(catalogAdapter);
        catalogAdapter.notifyDataSetChanged();
    }
}