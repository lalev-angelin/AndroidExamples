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
    private List<Contact> contacts;

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
        Contact contact = contacts.get(position);
        holder.nameView.setText(contact.getName());
        holder.emailView.setText(contact.getEmail());
        holder.phoneView.setText(contact.getPhone());
        holder.callButton.setOnClickListener(a->{
            new AlertDialog.Builder(context)
                    .setTitle(contact.getName())
                    .setMessage(contact.getPhone()).show();
        });
    }

    @Override
    public int getItemCount() {
        if (contacts==null) {
            return 0;
        } else {
            return contacts.size();
        }
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
