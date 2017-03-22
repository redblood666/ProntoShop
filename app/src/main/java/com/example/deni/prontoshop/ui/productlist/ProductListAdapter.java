package com.example.deni.prontoshop.ui.productlist;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindBitmap;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.deni.prontoshop.R;
import com.example.deni.prontoshop.core.listeners.OnProductSelectedListener;
import com.example.deni.prontoshop.model.Product;
import com.example.deni.prontoshop.util.Formatter;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by deni on 21/03/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<Product> mProducts;
    private final Context mContext;
    private final OnProductSelectedListener mListener;

    public ProductListAdapter(List<Product> products, Context context, OnProductSelectedListener listener) {
        this.mProducts = products;
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mProducts != null) {
            Product product = mProducts.get(position);
            Picasso.with(mContext)
                    .load(product.getImagePath())
                    .fit()
                    .placeholder(R.drawable.default_image)
                    .into(holder.productImage);
            holder.productName.setText(product.getProductName());
            holder.category.setText(product.getCategoryName());
            holder.productPrice.setText(Formatter.formatCurrency(product.getSalePrice()));
            String productDescription = product.getDescription();
            String shortDescription = productDescription.substring(0, Math.min(productDescription.length(), 70));
            holder.description.setText(shortDescription);
        }
    }

    @Override
    public int getItemCount() {
        if (mProducts != null) {
            return mProducts.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.product_image)
        ImageView productImage;
        @BindView(R.id.text_view_product_name)
        TextView productName;
        @BindView(R.id.textview_product_category)
        TextView category;
        @BindView(R.id.textview_product_description)
        TextView description;
        @BindView(R.id.image_view_add_to_cart_button)
        ImageView addToCartButton;
        @BindView(R.id.textview_product_price)
        TextView productPrice;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            addToCartButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }


}