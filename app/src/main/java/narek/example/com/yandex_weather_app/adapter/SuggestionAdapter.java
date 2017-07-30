package narek.example.com.yandex_weather_app.adapter;

/*
 * @author <a href="mailto: alyonamalchikhina@gmail.com">Alena Malchikhina</a>
 * @since 0.1
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder> {


    private List<SuggestCity> cityList = new ArrayList<>();
    private static OnItemClickListener listener;

    public SuggestionAdapter(List<SuggestCity> cityList, OnItemClickListener listener) {
        this.cityList = cityList;
        SuggestionAdapter.listener = listener;
    }

    @Override
    public SuggestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.item_city, parent, false);
        return new SuggestionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SuggestionViewHolder holder, int position) {
        SuggestCity city = cityList.get(position);

        holder.cityTv.setText(city.getCityName() + ", " + city.getCountry());

    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public static class SuggestionViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.city_tv)TextView cityTv;

        public SuggestionViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }



    }
}
