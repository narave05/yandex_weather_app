package narek.example.com.yandex_weather_app.adapter;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Forecasts;
import narek.example.com.yandex_weather_app.ui._common.widget.WeatherImageView;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> implements ItemTouchHelperAdapter{
    private static List<City> cityList = new ArrayList<>();
    private static OnItemClickListener recyclerlistener;
    private Context context;
    private static int selectedPosition = -1;


    public CitiesAdapter(List<City> cityList, OnItemClickListener listener) {
        CitiesAdapter.cityList = cityList;
        CitiesAdapter.recyclerlistener = listener;
    }

    @Override
    public CitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.city_item, parent, false);
        return new CitiesAdapter.CitiesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CitiesViewHolder holder, final int position) {

        final City city = cityList.get(position);

        holder.cityNameTv.setText(city.getName());
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerlistener.onItemClick(v, holder.getLayoutPosition());
                    selectedPosition = holder.getAdapterPosition();
                }
            });



        if (selectedPosition == holder.getAdapterPosition()) {
            holder.frameLayout.setBackgroundColor(holder.accent);
        }else {
            holder.frameLayout.setBackgroundColor(holder.usual);
        }
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    @Override
    public void onDismiss(int position) {
        cityList.remove(position);
        notifyItemRemoved(position);
    }


    public static class CitiesViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.city_name_item_tv) TextView cityNameTv;
        @BindView(R.id.frame)
        FrameLayout frameLayout;
        @BindColor(R.color.colorAccent)
        int accent;
        @BindColor(R.color.city)
        int usual;

        public CitiesViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

    }
}
