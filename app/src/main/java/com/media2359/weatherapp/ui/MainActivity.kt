package com.media2359.weatherapp.ui

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.safetynet.SafetyNet
import com.media2359.weatherapp.BuildConfig
import com.media2359.weatherapp.BuildConfig.SAFETYNET_API_KEY
import com.media2359.weatherapp.R
import com.media2359.weatherapp.content.model.Forecast
import com.media2359.weatherapp.content.model.wrapper.Resource
import com.media2359.weatherapp.databinding.ActivityMainBinding
import com.media2359.weatherapp.ui.adapter.ForecastItem
import com.media2359.weatherapp.ui.adapter.WeatherSearchAdapter
import com.media2359.weatherapp.ui.adapter.WeatherSearchItem
import com.media2359.weatherapp.utils.LOG_TAG
import com.media2359.weatherapp.utils.SEARCH_KEYWORD_SIZE
import com.media2359.weatherapp.utils.createAlertDialog
import com.media2359.weatherapp.utils.showProgressDialog
import com.scottyab.rootbeer.RootBeer
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import java.security.SecureRandom
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding

    private var progressDialog: Dialog? = null

    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }

    private val weatherSearchAdapter = WeatherSearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!BuildConfig.DEBUG) {
            checkRootDevices()
        }

        registerObserver()

        initView()
    }

    private fun initView() {
        binding.run {
            etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    btSearch.isEnabled = s != null && s.length >= SEARCH_KEYWORD_SIZE
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            btSearch.setOnClickListener {
                mainViewModel.searchKeyword(etSearch.text.toString())
            }

            rvWeather.layoutManager = LinearLayoutManager(this@MainActivity)
            rvWeather.adapter = weatherSearchAdapter
        }
    }

    private fun registerObserver() {
        mainViewModel.searchResultLiveData.observe(this) {
            when (it) {
                is Resource.Success -> {
                    displayLoading(false)
                    weatherSearchAdapter.submitList(createWeatherSearchItem(it.getAvailableData()?.forecastList))
                }
                is Resource.Error -> {
                    displayLoading(false)
                    createAlertDialog(it.error.message.toString())
                }
                is Resource.Loading -> {
                    displayLoading(true)
                }
            }
        }
    }

    private fun showLoading() {
        if (progressDialog == null) {
            progressDialog = this.showProgressDialog()
        }

        progressDialog?.show()
    }

    private fun hideLoading() {
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    private fun displayLoading(isDisplay: Boolean) {
        if (isDisplay) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    private fun checkRootDevices() {
        val rootBeer = RootBeer(this)
        if (rootBeer.isRooted) {
            this.createAlertDialog(getString(R.string.error_root_detected)) {
                finish()
            }
        } else {
            requestSafetyNet()
        }
    }

    private fun requestSafetyNet() {
        displayLoading(true)

        val nonce = ByteArray(16)
        SecureRandom().nextBytes(nonce)

        SafetyNet.getClient(this).attest(nonce, SAFETYNET_API_KEY)
            .addOnSuccessListener(this) {
                Timber.e("${LOG_TAG}_CheckRootSuccess")
                displayLoading(false)
            }
            .addOnFailureListener(this) {
                displayLoading(false)
                this.createAlertDialog(getString(R.string.error_root_detected)) {
                    finish()
                }
            }
    }

    private fun createWeatherSearchItem(data: List<Forecast>?): List<WeatherSearchItem> {
        return data?.map {
            ForecastItem(it)
        } ?: emptyList()
    }
}