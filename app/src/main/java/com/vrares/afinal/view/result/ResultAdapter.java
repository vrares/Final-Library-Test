package com.vrares.afinal.view.result;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vrares.afinal.R;
import com.vrares.afinal.model.pojo.Result;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {

    private ArrayList<Result> resultList;

    public ResultAdapter(ArrayList<Result> resultList) {
        this.resultList = resultList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result movie = resultList.get(position);
        //Bind the view items for each row
        setTheItems(movie, holder);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_movie_title_item) TextView movieTitleItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Since we will use only a String to update the row items, we create a String Observable which
    //has the value we are interested in: the movie title
    private Observable<String> getObservable(Result movie) {
        return Observable.just(movie.getOriginalTitle());
    }

    //We create a simple String Observer
    private Observer<String> getObserver(final MyViewHolder holder) {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            //As the onBindViewHolder() method runs through the objects, the onNext() method simply
            //sets the view to the movie title.
            public void onNext(String s) {
                holder.movieTitleItem.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    //The main method that sets the TextView from each row to the movie title
    private void setTheItems(Result movie, MyViewHolder holder) {
        getObservable(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(holder));
    }
}
