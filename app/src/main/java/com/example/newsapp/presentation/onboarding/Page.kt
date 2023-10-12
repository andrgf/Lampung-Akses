package com.example.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class Page(
    val title: String,
    val desc: String,
    @DrawableRes val img: Int
)

val pages = listOf(
    Page(
        title = "Selamat Datang",
        desc = "Temukan berita terkini yang akan menambah wawasan anda",
        img = R.drawable.page1
    ),
    Page(
        title = "Selamat Menikmati",
        desc = "Temukan berita terkini yang akan menambah wawasan anda",
        img = R.drawable.logo
    )
)
