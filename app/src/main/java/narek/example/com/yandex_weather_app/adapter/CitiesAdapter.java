package narek.example.com.yandex_weather_app.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.db.CityEntity;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> implements ItemTouchHelperAdapter{
    private static List<CityEntity> cityList = new ArrayList<>();
    private static OnItemClickListener recyclerlistener;
    private Context context;


    public CitiesAdapter(List<CityEntity> cityList, OnItemClickListener listener) {
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

        final CityEntity city = cityList.get(position);

        holder.cityNameTv.setText(city.getCityName());
        holder.relLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerlistener.onItemClick(city, holder.getLayoutPosition());
                }
            });



        if (city.isActive()) {
            holder.relLayout.setBackground(holder.accent);
        }else {
            holder.relLayout.setBackground(holder.usual);
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
        RelativeLayout relLayout;
        @BindDrawable(R.drawable.back_active)
        Drawable accent;
        @BindDrawable(R.drawable.back_l)
        Drawable usual;

        public CitiesViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

    }
}
