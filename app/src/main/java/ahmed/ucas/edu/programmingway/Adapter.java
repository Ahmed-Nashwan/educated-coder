package ahmed.ucas.edu.programmingway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

@Keep
public class Adapter extends RecyclerView.Adapter<Adapter.HolderV> {

    ArrayList<Language_data> arrayList;
    onItemClick onItemClick;
    Language_data data;
    Context context;

    public Adapter(ArrayList<Language_data> arrayList, onItemClick onItemClicks, Context context) {
        this.arrayList = arrayList;
        this.onItemClick = onItemClicks;
        this.context = context;

    }


    @NonNull
    @Override
    public HolderV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        HolderV holderV = new HolderV(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_design, null, false));


        return holderV;

    }


    @Override
    public void onBindViewHolder(@NonNull HolderV holder, int position) {
        data = arrayList.get(position);
        holder.tv_all_languages.setText(data.getLanguageName());
        Glide.with(context).load(data.getImageUrl()).into(holder.image_all_languages);
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.screen2));


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class HolderV extends RecyclerView.ViewHolder {
        ImageView image_all_languages;
        TextView tv_all_languages;

        public HolderV(@NonNull View itemView) {
            super(itemView);

            image_all_languages = itemView.findViewById(R.id.image_all_languages);
            tv_all_languages = itemView.findViewById(R.id.tv_all_languages);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClick.itemClickedRecycler(arrayList.get(getAdapterPosition()).getDocId());

                }
            });


        }
    }
}
