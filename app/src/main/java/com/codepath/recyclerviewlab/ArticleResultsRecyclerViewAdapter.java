package com.codepath.recyclerviewlab;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.recyclerviewlab.models.Article;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleResultsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_article_result, parent, false);
        return new ArticleViewHolder(view);
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        final TextView headlineView;
        final TextView snippetView;
        final TextView dateView;

        @RequiresApi(api = Build.VERSION_CODES.N)
        ArticleViewHolder(View view) {
            super(view);
            dateView = view.findViewById(R.id.article_pub_date);
            headlineView = view.findViewById(R.id.article_headline);
            snippetView = view.findViewById(R.id.article_snippet);

            SimpleDateFormat sdf;
            sdf = new SimpleDateFormat("yyyy/MM/dd");
            String currentDateandTime = sdf.format(new Date());
            dateView.setText(currentDateandTime);
        }
    }

    private final List<Article> articleList = new ArrayList<>();

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Article article = articleList.get(position);
        ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
        articleViewHolder.headlineView.setText(article.headline.main);
        articleViewHolder.snippetView.setText(article.snippet);

    }
    // This method clears the existing dataset and adds new articles
    void setNewArticles(List<Article> articles) {
        articleList.clear();
        articleList.addAll(articles);
    }

    // This method adds all articles to the existing dataset
    void addArticles(List<Article> articles) {
        articleList.addAll(articles);
    }
    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
