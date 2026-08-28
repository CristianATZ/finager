package com.devtorres.ui.components.pager.card.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devtorres.ui.components.cards.CustomOutlinedCard
import com.devtorres.ui.components.pager.card.pager.CardUi

@Composable
fun CardItem(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
    card: CardUi
) {
    CustomOutlinedCard(
        modifier = modifier
    ) {
        Column {
            Text(
                text = card.getFormattedPan()
            )
        }
    }
}