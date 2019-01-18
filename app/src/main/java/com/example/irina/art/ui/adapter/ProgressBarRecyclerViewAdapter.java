package com.example.irina.art.ui.adapter;

import android.app.Activity;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.irina.art.R;

public class ProgressBarRecyclerViewAdapter extends RecyclerView.Adapter<ProgressBarRecyclerViewAdapter.ViewHolder> {

    private int quantity;
    private int duration;
    private int[] progressStatuses;
    private int currentProgressBar;
    Runnable onComplete;
    private Handler handler = new Handler();
    Activity activity;


    public ProgressBarRecyclerViewAdapter(int quantity, int duration, Activity activity, Runnable onComplete) {
        super();
        this.quantity = quantity;
        this.duration = duration;
        this.onComplete = onComplete;
        progressStatuses = new int[quantity];
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View progressBarLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress_bar, parent, false);
        return new ViewHolder(progressBarLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (quantity == 0) {
            return;
        }

        initializeThread(onComplete, holder.progressBar, position);
    }


    void initializeThread(Runnable onComplete, ProgressBar progressBar, int position) {
        new Thread(() -> {
            while (position != 0 && progressStatuses[position - 1] != 100) {
            }
            currentProgressBar = position;
            while (progressStatuses[position] < 100) {
                progressStatuses[position]++;
                android.os.SystemClock.sleep(duration / 100);
                handler.post(() -> progressBar.setProgress(progressStatuses[position]));
            }
            handler.post(onComplete);
        }).start();

    }

    public void makeCurrentProgressBarEqual100Percent() {
        progressStatuses[currentProgressBar] = 100;
    }


    @Override
    public int getItemCount() {
        return quantity;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        //Initializing Views
        ViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progressbar);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) progressBar.getLayoutParams();
            int leftMargin = layoutParams.leftMargin;
            int rightMargin = layoutParams.rightMargin;

            DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
            int pxScreenWidth = displayMetrics.widthPixels;
            progressBar.getLayoutParams().width = (pxScreenWidth / quantity - leftMargin - rightMargin);
        }
    }

}