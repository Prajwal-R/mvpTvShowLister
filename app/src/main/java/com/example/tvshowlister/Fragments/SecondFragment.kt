package com.example.tvshowlister.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tvshowlister.Model.TvShowInformationData
import com.example.tvshowlister.Presenters.SecondFragmentPresenter
import com.example.tvshowlister.R
import com.example.tvshowlister.Views.SecondFragmentView
import com.example.tvshowlister.databinding.ItemTvShowDetails

class SecondFragment : Fragment(), SecondFragmentView {

    private lateinit var presenter: SecondFragmentPresenter
    private lateinit var binding: ItemTvShowDetails

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemTvShowDetails.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = SecondFragmentPresenter()
        presenter.bindView(this)
        presenter.setDao(requireContext())
        presenter.getData(requireArguments().getInt("tvShowId"))
    }

    override fun setItemData(itemData: TvShowInformationData) {
        with(binding) {
            tvTvShowTitle.text = itemData.title
            tvTvShowDescription.text = itemData.crew

            Glide.with(binding.root)
                .load(itemData.image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(ivTvShowPoster)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun showProgress() {
    }

    override fun showError() {
    }
}
