package com.example.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.presentation.common.OnBoardingButton
import com.example.newsapp.presentation.common.OnBoardingButtonText
import com.example.newsapp.presentation.common.OnBoardingIndicator
import com.example.newsapp.presentation.onboarding.component.OnBoardingContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit
) {
    
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                when(pagerState.currentPage) {
                    0 -> listOf("", "Selanjutnya")
                    1 -> listOf("Kembali", "Mulai")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pagerState) {
            OnBoardingContent(page = pages[it])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OnBoardingIndicator(
                modifier = Modifier.width(40.dp),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage,
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                val rememberScope = rememberCoroutineScope()

                if (buttonState.value[0].isNotEmpty()) {
                    OnBoardingButtonText(
                        text = buttonState.value[0],
                        onClick = {
                            rememberScope.launch {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage -1
                                )
                            }
                        }
                    )
                }
                OnBoardingButton(
                    text = buttonState.value[1],
                    onClick = {
                        rememberScope.launch {
                            if (pagerState.currentPage == 1) {
                                event(OnBoardingEvent.SavePref)
                            }
                            else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    val viewModel: OnBoardingViewModel = hiltViewModel()
    OnBoardingScreen(viewModel::onEvent)
}