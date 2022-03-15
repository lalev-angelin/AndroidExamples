package uk.co.lalev.hello6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    class ProductViewHolder extends RecyclerView.ViewHolder{

        private TextView InventoryNumberView;
        private TextView NameView;
        private TextView PriceView;
        private TextView QuantityView;
        private ImageButton EditButton;
        private ImageButton DeleteButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            InventoryNumberView = itemView.findViewById(R.id.invenory_number_view);
            NameView = itemView.findViewById(R.id.product_name_view);
            PriceView = itemView.findViewById(R.id.product_prce_view);
            QuantityView = itemView.findViewById(R.id.product_quantity_view);
            EditButton = itemView.findViewById(R.id.edit_button);
            DeleteButton = itemView.findViewById(R.id.delete_button);

            DeleteButton.setOnClickListener((v)->{
                 db.productDAO().delete(products.get(getAdapterPosition()));
            });

            EditButton.setOnClickListener((v)->{
                Intent i = new Intent(context, AddEditProductActivity.class);
                Product p = products.get(getAdapterPosition());

                i.putExtra("mode", "edit");
                i.putExtra("invNo", String.valueOf(p.invNo));
                i.putExtra("name", p.name);
                i.putExtra("price", String.valueOf(p.price));
                i.putExtra("quantity", String.valueOf(p.quantity));
                context.startActivity(i);
            });
        }
    }


    private Context context;
    private ProductDatabase db;
    private List<Product> products;

    public ProductAdapter(Context context, ProductDatabase db) {
        this.context = context;
        this.db = db;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.product_recycler_row_layout, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.InventoryNumberView.setText(String.valueOf(products.get(position).invNo));
        holder.NameView.setText(products.get(position).name);
        holder.PriceView.setText(String.valueOf(products.get(position).price));
        holder.QuantityView.setText(String.valueOf(products.get(position).quantity));
    }

    @Override
    public int getItemCount() {
        if (products == null) {
            return 0;
        } else {
            return products.size();
        }
    }
}
