package model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listofusers.DetailActivity;
import com.example.listofusers.OnCardListener;
import com.example.listofusers.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Users> listusers;
    protected OnCardListener onCardListener;
    public Adapter(ArrayList<Users> listusers, OnCardListener cardListener) {
        this.listusers = listusers;
        this.onCardListener = cardListener;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Users users = listusers.get(position);

        holder.textView7.setText(listusers.get(position).getNama());
        holder.textView8.setText(listusers.get(position).getUmur());
        holder.textView9.setText(listusers.get(position).getAlamat());
    }

    @Override
    public int getItemCount() {
        return listusers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView7, textView8, textView9;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView7 = itemView.findViewById(R.id.textView7);
            textView8 = itemView.findViewById(R.id.textView8);
            textView9 = itemView.findViewById(R.id.textView9);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    intent.putParcelableArrayListExtra("arraylist", listusers);
                    intent.putExtra("position", getAdapterPosition());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
