package id.com.miqdad.basicrecycleview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterRcycler extends RecyclerView.Adapter<AdapterRcycler.RecyclerHolder> implements Filterable{

  private ArrayList<ModelMovie>listdata;
  private ArrayList<ModelMovie>filterData;

    public AdapterRcycler(ArrayList<ModelMovie> dataList) {

        listdata = dataList;
        filterData = dataList;

    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder recyclerHolder, int i) {

        recyclerHolder.binView(filterData.get(i));

    }

    @Override
    public int getItemCount() {
        return filterData.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()){
                    filterData = listdata;
                }else {
                    ArrayList<ModelMovie> filterList = new ArrayList<>();
                    for (ModelMovie modelMovie:listdata){
                        if(modelMovie.getJudul().toLowerCase().contains(charString) | modelMovie.getSubJudul().toLowerCase().contains(charString) ){
                            filterData.add(modelMovie);
                        }
                    }
                    filterData = filterList;

                }
                FilterResults filterResult = new FilterResults();
                filterResult.values = filterData;
                return filterResult;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                filterData = (ArrayList<ModelMovie>)results.values;
                notifyDataSetChanged();

            }
        };
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {

        private TextView tvJudul, subJudul;
        private ImageView gambar, ivLogo;
        String idMove;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            subJudul = itemView.findViewById(R.id.tv_subJudul);
            gambar = itemView.findViewById(R.id.gambar);
            ivLogo = itemView.findViewById(R.id.ivLogo);

        }

        private void binView(ModelMovie data){
            tvJudul.setText(data.getJudul());
            subJudul.setText(data.getSubJudul());
            gambar.setImageResource(data.getGambar());
            ivLogo.setImageResource(data.getGambar());
            idMove = data.getId();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    itemView.getContext().startActivity(new Intent(itemView.getContext(), DetaiMovie.class)
                            .putExtra(DetaiMovie.KEY_MOVIE, idMove));

                }
            });




        }
    }
}
