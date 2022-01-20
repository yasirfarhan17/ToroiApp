package com.example.toroi.ui.details_view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.network_domain.network.model.DataItem
import com.example.network_domain.network.model.EmployeeResponseModel
import com.example.toroi.R
import com.example.toroi.databinding.ActivityDetailsBinding
import com.example.toroi.injection.component.AppComponent
import com.example.toroi.ui.base.BaseActivity
import com.example.toroi.ui.details_view.adapter.DetailsAdapter
import com.example.toroi.ui.details_view.adapter.DetailsAdapterCallBack
import com.example.toroi.ui.home.HomeActivity

class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailViewModel>(),
    DetailsAdapterCallBack {

    override fun layoutId(): Int = R.layout.activity_details
    private var homeList = ArrayList<EmployeeResponseModel>()

    override fun getViewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        addListeners()
        addObservers()
    }

    override fun injectActivity(appComponent: AppComponent) {
        appComponent.homeComponent()
            .create()
            .inject(this)
    }

    private fun initUi() {
        with(binding) {
            viewModel.getEmployeeList()
            rvDetails.layoutManager = LinearLayoutManager(this@DetailsActivity)
            rvDetails.scrollBy(0,0)
            rvDetails.adapter = DetailsAdapter(this@DetailsActivity)
        }
    }

    private fun addListeners() {
    }

    override fun addObservers() {
        viewModel.employeeList.observe(this) {
            if (it == null) {
                showMessage("No data Found")
                return@observe
            }
            (binding.rvDetails.adapter as DetailsAdapter).submitList(it.data as ArrayList<DataItem>)
        }
    }

    override fun onItemClick(position: Int, item: DataItem) {
        val bundle = Bundle().apply {
            putParcelable("ITEM", item)
        }
        navigator.startActivityWithData(HomeActivity::class.java, bundle)
    }

}