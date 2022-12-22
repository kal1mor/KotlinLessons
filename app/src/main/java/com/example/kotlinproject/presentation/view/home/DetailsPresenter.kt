package com.example.kotlinproject.presentation.view.home

import com.example.kotlinproject.domain.auth.AuthInteractor
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
    private val authInteractor: AuthInteractor
) {

    private lateinit var detailsView: DetailsView

    fun setView(detalesFragment: DetalesFragment) {
        detailsView = detalesFragment
    }

    fun getArguments(name: String?, date: String?, imageView: Int) {
        detailsView.displayItemData(
            when (name.isNullOrEmpty()) {
                true -> "novie merin"
                false -> name
            },
            when (date.isNullOrEmpty()) {
                true -> "starii merin"
                false -> date
            },
            imageView
        )
    }

    fun logoutUser() {
        authInteractor.userLogout()
        detailsView.userLoggetOut()

    }
}