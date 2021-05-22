package uk.co.lalev.recyclerviewdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ContactViewHolder> {

    private final Context context;
    private final List<DummyData> dummyDataList;

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final TextView emailView;
        private final TextView phoneView;
        private final ImageButton callButton;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.nameView);
            emailView = itemView.findViewById(R.id.emailView);
            phoneView = itemView.findViewById(R.id.phoneView);
            callButton = itemView.findViewById(R.id.callButton);
        }
    }

    public DataAdapter(Context context) {
        this.context = context;
        dummyDataList = DummyData.generate();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_panel, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        DummyData dummyData = dummyDataList.get(position);
        holder.nameView.setText(dummyData.getName());
        holder.emailView.setText(dummyData.getEmail());
        holder.phoneView.setText(dummyData.getPhone());
        holder.callButton.setOnClickListener(a->{
            new AlertDialog.Builder(context)
                    .setTitle(dummyData.getName())
                    .setMessage(dummyData.getPhone()).show();
        });
    }

    @Override
    public int getItemCount() {
        return dummyDataList.size();
    }
}
