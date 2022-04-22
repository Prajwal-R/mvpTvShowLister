package com.example.tvshowlister.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshowlister.Adapters.FirstFragmentAdapter
import com.example.tvshowlister.Model.TvShowInformationData
import com.example.tvshowlister.Presenters.FirstFragmentPresenter
import com.example.tvshowlister.Views.FirstFragmentView
import com.example.tvshowlister.databinding.FirstFragmentBinding

class FirstFragment() : Fragment(), FirstFragmentView {

    private lateinit var presenter: FirstFragmentPresenter
    private lateinit var binding: FirstFragmentBinding
    private var recyclerAdapter: FirstFragmentAdapter? = null
    var callBackInterface: CallBackInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FirstFragmentBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = FirstFragmentPresenter()
        presenter.bindView(this)
        setUpRecyclerView()
        // put data to room here
        presenter.setDao(requireContext())
        presenter.retrofitData()
    }

    private fun setUpRecyclerView() {
        with(binding) {
            layoutRecyclerView.layoutManager = LinearLayoutManager(context)
            recyclerAdapter = FirstFragmentAdapter().apply {
                clickListener = ::onItemClick
            }
            layoutRecyclerView.adapter = recyclerAdapter
        }
    }

    private fun onItemClick(tvShowId: Int) {
        if (callBackInterface != null) {
            callBackInterface?.onCallBack(tvShowId)
        }
    }

    override fun setRecyclerViewData(tvShowInfo: List<TvShowInformationData>?) {
        recyclerAdapter?.setTvShowInformationList(tvShowInfo)
    }

    override fun showProgress() {
        Toast.makeText(context, "Keep waiting all your life!", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.CompositeDisposer()
    }
}

interface CallBackInterface {
    fun onCallBack(tvShowId: Int)
}
