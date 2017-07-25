package narek.example.com.yandex_weather_app.adapter;

/*
 * @author <a href="mailto: alyonamalchikhina@gmail.com">Alena Malchikhina</a>
 * @since 0.1
 */


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder> {



    @Override
    public SuggestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SuggestionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class SuggestionViewHolder extends RecyclerView.ViewHolder{

        public SuggestionViewHolder(View itemView) {
            super(itemView);
        }


    }
}
