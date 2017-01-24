package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juanlucena.privaliatest.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Film;

/**
 * Created by Juan Lucena on 21/01/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewAdapterObjectViewHolder>{

    private Context context;
    private List<Film> filmList;
    private int firstVisibleItem, visibleItemCount, totalItemCount, previousTotal = 0;

    public RecyclerViewAdapter(Context context, List<Film> filmList){
        this.context = context;
        this.filmList = filmList;
    }


    @Override
    public RecyclerViewAdapter.RecyclerViewAdapterObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_view, parent, false);
        RecyclerViewAdapterObjectViewHolder recyclerViewAdapterObjectViewHolder = new RecyclerViewAdapterObjectViewHolder(itemView);

        return recyclerViewAdapterObjectViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.RecyclerViewAdapterObjectViewHolder holder, int position) {

        String[] date = filmList.get(position).getRelease_date().split("-");
        holder.filmTitle.setText(filmList.get(position).getOriginal_title() + " (" + date[0] + ")");

        holder.filmOverview.setText(filmList.get(position).getOverview());

        String filmPosterUrl = context.getResources().getString(R.string.image_base_url) + filmList.get(position).getPoster_path();
        Glide.with(context).load(filmPosterUrl).into(holder.filmPicture);
    }

    public static class RecyclerViewAdapterObjectViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.filmPicture) ImageView filmPicture;
        @BindView(R.id.filmTitle) TextView filmTitle;
        @BindView(R.id.filmOverview) TextView filmOverview;

        public RecyclerViewAdapterObjectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindFilm(Film film) {
        }

    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }
}
