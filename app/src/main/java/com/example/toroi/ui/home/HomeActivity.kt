package com.example.toroi.ui.home

import android.os.Bundle
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.network_domain.network.model.DataItem
import com.example.toroi.R
import com.example.toroi.databinding.ActivityHomeBinding
import com.example.toroi.injection.component.AppComponent
import com.example.toroi.ui.base.BaseActivity
import com.example.toroi.ui.details_view.DetailsActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override fun layoutId(): Int = R.layout.activity_home
    private var item: DataItem? = null

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        initUi()
        addListeners()
        addObservers()
    }

    private fun getData() {
        if (intent.getParcelableExtra<DataItem>("ITEM") != null) {
            item = intent.getParcelableExtra<DataItem>("ITEM")
        }
    }

    override fun injectActivity(appComponent: AppComponent) {
        appComponent.homeComponent()
            .create()
            .inject(this)
    }

    private fun initUi() {
        with(binding) {
            name.text = item?.firstName
            email.text = item?.email
            mobile.text = item?.phoneNumber?:"----------"
            profileImage.load(item?.avatar) {
                transformations(CircleCropTransformation())
                placeholder(R.drawable.add)
            }
            button.setOnClickListener {
                navigator.startActivity(DetailsActivity::class.java)
            }
        }
    }

    private fun addListeners() {
    }

    override fun addObservers() {
        observeAccountBalance()
    }

    private fun observeAccountBalance() {
    }
}