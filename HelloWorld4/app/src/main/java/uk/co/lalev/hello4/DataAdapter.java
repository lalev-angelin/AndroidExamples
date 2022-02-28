package uk.co.lalev.hello4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ClientViewHolder> {

    private Context context;

    public DataAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DataAdapter.ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.client_view_holder, parent, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ClientViewHolder holder, int position) {
        Client client = Client.clients.get(position);
        ((ClientViewHolder)holder).nameTextView.setText(client.getName());
        ((ClientViewHolder)holder).phoneTextView.setText(client.getPhone());
        ((ClientViewHolder)holder).emailTextView.setText(client.getEmail());
        if (client.isVip()) {
            ((ClientViewHolder)holder).userIconImageView.setImageResource(R.drawable.ic_baseline_vip_person_24);
        } else {
            ((ClientViewHolder) holder).userIconImageView.setImageResource(R.drawable.ic_baseline_person_24);
        }
    }

    @Override
    public int getItemCount() {
        return Client.clients.size();
    }


    static class ClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        public TextView phoneTextView;
        public TextView emailTextView;
        public ImageView userIconImageView;

        private Context context;

        public ClientViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            userIconImageView = itemView.findViewById(R.id.userIconImageView);

        }

        @Override
        public void onClick(View view) {
            int position = getPosition();
            Toast.makeText(context, Client.clients.get(position).getPhone(), Toast.LENGTH_LONG).show();
    };

}
