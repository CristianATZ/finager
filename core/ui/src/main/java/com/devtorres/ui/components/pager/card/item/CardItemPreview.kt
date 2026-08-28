package com.devtorres.ui.components.pager.card.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devtorres.ui.components.pager.card.pager.CardUi

internal val BACKGROUND_COLOR = Color.Gray

internal val CARD_ITEM = CardUi(
    alias = "BBVA C",
    pan = "1234",
    isDigital = true,
    cardType = "AMEX"
)

@Preview
@Composable
internal fun CardItemPreview(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND_COLOR),
        contentAlignment = Alignment.Center
    ) {
        CardItem(
            card = CARD_ITEM,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}