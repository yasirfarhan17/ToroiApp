package com.example.toroi.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.network_domain.network.exceptions.UnauthorizedException
import com.example.network_domain.storage.PrefsUtil
import com.example.toroi.BaseApplication
import com.example.toroi.injection.component.AppComponent
import com.example.toroi.utils.UiUtil
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    lateinit var navigator: Navigator
    protected lateinit var uiUtil: UiUtil

    @Inject
    lateinit var prefsUtil: PrefsUtil

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent =
            (application as BaseApplication).appComponent
        injectActivity(appComponent)
        super.onCreate(savedInstanceState)
        bindContentView(layoutId())
        navigator = Navigator(this)
        uiUtil = UiUtil(this)
        observeViewState()
        addObservers()
    }

    abstract fun addObservers()


    abstract fun injectActivity(appComponent: AppComponent)

    private fun bindContentView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[getViewModelClass()]
    }

    @LayoutRes
    abstract fun layoutId(): Int

    abstract fun getViewModelClass(): Class<VM>

    protected fun showProgress() {
        uiUtil.showProgress()
    }

    protected fun hideProgress() {
        uiUtil.hideProgress()
    }

    protected fun showMessage(message: String) {
        uiUtil.showMessage(message)
    }

    protected fun showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
        message?.let { uiUtil.showToast(it, duration) }
    }

    private fun observeViewState() {
        viewModel.viewState
            .observe(this, {
                when (it) {
                    ViewState.Idle -> {
                    }
                    ViewState.Loading -> {
                        showProgress()
                    }
                    is ViewState.Success -> {
                        hideProgress()
                        it.message?.let { it1 -> showMessage(it1) }
                    }
                    is ViewState.StopLoading -> {
                        hideProgress()
                    }
                    is ViewState.StartLoading -> {
                        hideProgress()
                    }
                    is ViewState.Error -> {
                        hideProgress()
                        handleException(it.throwable)
                    }
                }
            })
    }

    private fun handleException(throwable: Throwable?) {
        when (throwable) {
            is UnauthorizedException -> showToast(throwable.message)
            else -> throwable?.message?.let { showMessage(it) }
        }
    }


    fun getLayoutBinding() = binding
}