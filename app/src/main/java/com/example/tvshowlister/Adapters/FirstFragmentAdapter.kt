package com.example.tvshowlister.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshowlister.Model.TvShowInformationData
import com.example.tvshowlister.R
import com.example.tvshowlister.databinding.ItemTvShowBinding

class FirstFragmentAdapter : RecyclerView.Adapter<FirstFragmentAdapter.FirstFragmentViewHolder>() {

    private var itemList: List<TvShowInformationData>? = listOf()

    var clickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstFragmentViewHolder {
        return FirstFragmentViewHolder(
            ItemTvShowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FirstFragmentViewHolder, position: Int) {
        holder.clickListener = clickListener
        holder.bindView(itemList!![position])
    }

    override fun getItemCount() = itemList!!.size

    fun setTvShowInformationList(tvItemList: List<TvShowInformationData>?) {
        itemList = tvItemList
        notifyDataSetChanged()
    }

    class FirstFragmentViewHolder(val binding: ItemTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var clickListener: ((Int) -> Unit)? = null

        fun bindView(tvShowInformation: TvShowInformationData) {
            with(binding) {
                tvTvShowTitle.text = tvShowInformation.title
                tvTvShowDescription.text = tvShowInformation.imDbRating

                //Log.d("Adapter", "bindView:Prajwal \n${tvShowInformation.tvShowIllustrationUrl}")
                Glide.with(binding.root)
                    .load(tvShowInformation.image)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(ivTvShowIllustration)

                layoutItemTvShow.setOnClickListener {
                    clickListener?.invoke(tvShowInformation.id)
                }
            }
        }
    }
}
