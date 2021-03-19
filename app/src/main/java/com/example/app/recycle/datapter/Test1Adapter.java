package com.example.app.recycle.datapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;
import com.example.app.recycle.MyImageView;

import java.util.List;

/**
 * Create by liuxue on 2021/2/23 0023.
 * description:
 */
public class Test1Adapter extends RecyclerView.Adapter<Test1Adapter.MyViewHolder> {

    private Context context;
    private List<String> dataList;
    private ItemTouchHelper itemTouchHelper;

    public Test1Adapter(Context context, List<String> list, ItemTouchHelper itemTouchHelper) {
        this.context = context;
        this.dataList = list;
        this.itemTouchHelper = itemTouchHelper;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_simple_layout, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(dataList.get(position));
//        holder.imgIcon.setOnLongClickListener(v -> {
//            itemTouchHelper.startDrag(holder);
//            return false;
//        });


       holder.imgIcon.setOnTouchListener((v, event) -> {
           Log.e("Test1", "onTouch :" + event.getAction());
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                itemTouchHelper.startDrag(holder);
                return true;
            }
            return true;
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

   public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private MyImageView imgIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            imgIcon = itemView.findViewById(R.id.icon);
        }
    }
}
