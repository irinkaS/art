package com.example.irina.art.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.irina.art.R;

public class ProgressBarRecyclerViewAdapter extends RecyclerView.Adapter<ProgressBarRecyclerViewAdapter.ViewHolder> {

    private int quantity;
    private int duration;
    private int[] progressStatuses;

    public ProgressBarRecyclerViewAdapter(int quantity, int duration) {
        super();
        this.quantity = quantity;
        this.duration = duration;
        progressStatuses = new int[quantity];
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


        final Context context = holder.itemView.getContext();

        initializeThread(() -> {
            System.out.println("Hello");
        }, holder.progressBar, position);


    }

    private Handler handler = new Handler();

    void initializeThread(Runnable onComplete, ProgressBar progressBar, int num) {
        new Thread(() -> {
            while (num != 0 && progressStatuses[num - 1] != 100) {
            }
            while (progressStatuses[num] < 100) {
                progressStatuses[num]++;
                android.os.SystemClock.sleep(50);
                handler.post(() -> progressBar.setProgress(progressStatuses[num]));
            }
            handler.post(onComplete);
        }).start();

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
        }
    }

}