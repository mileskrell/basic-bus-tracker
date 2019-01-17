package com.mileskrell.basicbustracker.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.mileskrell.basicbustracker.R
import com.mileskrell.basicbustracker.model.EstimatesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    lateinit var estimatesViewModel: EstimatesViewModel
    private val adapter = EstimatesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        estimatesViewModel = ViewModelProviders.of(this).get(EstimatesViewModel::class.java)
        estimatesViewModel.estimates.observe(this, Observer {
            adapter.loadStops(it ?: listOf())
            swipe_refresh_layout.isRefreshing = false
        })

        setupUI()
        fetchEstimates()
    }

    private fun setupUI() {
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
        swipe_refresh_layout.setOnRefreshListener {
            fetchEstimates()
        }
    }

    private fun fetchEstimates() {
        launch(Dispatchers.IO) {
            estimatesViewModel.fetchNewEstimates()
        }
    }
}
