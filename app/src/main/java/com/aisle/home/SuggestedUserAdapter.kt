package com.aisle.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aisle.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation

class SuggestedUserAdapter(var mContext : Context,val mUserList : List<String>) : RecyclerView.Adapter<SuggestedUserAdapter.ViewHolder>() {

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

        val user_image = itemView.findViewById<ImageView>(R.id.iv_user_image)
        val tx_user_name = itemView.findViewById<TextView>(R.id.tx_user_full_name_item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.tx_user_name.setText(mUserList[position])

        if (position==0 || position%2==0){
            Picasso.get().load(R.drawable.photo_2)
                .transform(BlurTransformation(mContext, 25, 2))
                .into(holder.user_image)

        }else if (position%2==1){
            Picasso.get().load(R.drawable.photo_3)
                .transform(BlurTransformation(mContext, 25, 2))
                .into(holder.user_image)
        }


    }

    override fun getItemCount(): Int {
        return mUserList.size
    }
}