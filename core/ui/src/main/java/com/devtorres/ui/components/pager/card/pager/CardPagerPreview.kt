package com.devtorres.ui.components.pager.card.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

internal val CARD_LIST = listOf(
    CardUi(
        alias = "BBVA",
        pan = "1234",
        isDigital = true,
        cardType = "AMEX"
    ),
    CardUi(
        alias = "SANTANDER",
        pan = "3456",
        isDigital = true,
        cardType = "AMEX"
    ),
    CardUi(
        alias = "NU",
        pan = "5678",
        isDigital = true,
        cardType = "AMEX"
    ),
    CardUi(
        alias = "NU",
        pan = "6789",
        isDigital = true,
        cardType = "AMEX"
    )
)

internal val BACKGROUND_IMAGE = Color.Gray

internal const val BEYOND_VIEWPORT_PAGE_COUNT = 2

@Preview
@Composable
internal fun CardPagerPreview(

) {
    val pagerState = rememberPagerState { CARD_LIST.size }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND_IMAGE),
        contentAlignment = Alignment.Center
    ) {
        CardPager(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            pagerState = pagerState,
            beyondViewportPageCount = BEYOND_VIEWPORT_PAGE_COUNT,
            cardList = CARD_LIST
        )
    }
}