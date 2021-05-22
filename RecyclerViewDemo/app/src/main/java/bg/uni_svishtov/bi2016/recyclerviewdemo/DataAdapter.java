package bg.uni_svishtov.bi2016.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<DummyData> dummyData;

    class ContactsViewHolder extends RecyclerView.ViewHolder {
        private TextView sipTextView;
        private TextView nameTextView;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            sipTextView = itemView.findViewById(R.id.sipTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }
    }

    public DataAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        dummyData = DummyData.generate();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactsView = layoutInflater.inflate(R.layout.contacts_recycler, parent, false);
        return new ContactsViewHolder(contactsView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ContactsViewHolder)holder).sipTextView.setText(dummyData.get(position).getSip());
        ((ContactsViewHolder)holder).nameTextView.setText(dummyData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dummyData.size();
    }
}
