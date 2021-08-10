package com.learn.tindertemplate.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.learn.tindertemplate.MyApp
import com.learn.tindertemplate.R
import com.learn.tindertemplate.data.model.ResultsItem
import com.learn.tindertemplate.myutils.MyConstant.Companion.BaseUrl
import com.learn.tindertemplate.myutils.Status
import com.learn.tindertemplate.repositories.ApiRepositoryImpl
import com.yuyakaido.android.cardstackview.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CardStackListener {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var layoutManager: CardStackLayoutManager
    private lateinit var cardStackView: CardStackView
    private lateinit var profileAdapter: ProfileAdapter
    private lateinit var progress: ProgressBar

    @Inject
    lateinit var apiRepositoryImpl: ApiRepositoryImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApp).appComponent.inject(this)
        cardStackView = findViewById(R.id.swipeView)
        progress = findViewById(R.id.progress)
        layoutManager = CardStackLayoutManager(this, this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }
        profileAdapter = ProfileAdapter()
        cardStackView.layoutManager = layoutManager
        cardStackView.adapter = profileAdapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.setApiRepo(apiRepositoryImpl)
        observeDataFromServer()
    }

    private fun observeDataFromServer() {
        progress.visibility = View.VISIBLE
        viewModel.getProfile(BaseUrl).observe(this, Observer { resources ->
            resources?.apply {
                when (status) {
                    Status.SUCCESS -> {
                        progress.visibility = View.GONE
                        Log.e("MainActivity", data.toString())

                        data?.results?.let {
                            profileAdapter.setUserList(data.results as List<ResultsItem>?)
                        }
                    }
                    Status.ERROR -> {
                        progress.visibility = View.GONE
                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progress.visibility = View.VISIBLE
                    }
                }
            }

        })
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        when (direction) {
            Direction.Right -> {

            }
            Direction.Left -> {

            }
            else -> {

            }
        }
    }

    override fun onCardSwiped(direction: Direction?) {
        observeDataFromServer()
    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }
}