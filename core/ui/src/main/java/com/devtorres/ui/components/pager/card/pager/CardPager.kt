package com.devtorres.ui.components.pager.card.pager

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.devtorres.ui.components.pager.card.item.CardItem
import kotlin.math.absoluteValue

@Composable
fun CardPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    cardList: List<CardUi>,
    beyondViewportPageCount: Int = 1,
) {
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 24.dp),
        beyondViewportPageCount = beyondViewportPageCount,
        modifier = modifier
            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
            .drawWithContent {
                drawContent()
                drawRect(
                    brush = Brush.horizontalGradient(
                        0f to Color.Transparent,
                        0.04f to Color.Black,
                        0.96f to Color.Black,
                        1f to Color.Transparent,
                        startX = 0f,
                        endX = size.width
                    ),
                    blendMode = BlendMode.DstIn
                )
            }
    ) { page ->
        val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction

        CardItem(
            card = cardList[page],
            modifier = modifier
                .zIndex(-pageOffset.absoluteValue)
                .graphicsLayer {
                    val absOffset = pageOffset.absoluteValue

                    // Escala: la centrada full, las demás encogen
                    val scale = lerp(1f, 0.82f, absOffset.coerceIn(0f, 1f))
                    scaleX = scale
                    scaleY = scale

                    // Las de atrás se atenúan un poco pero NO desaparecen
                    alpha = lerp(1f, 0.55f, absOffset.coerceIn(0f, 1f))

                    // Overlap visual (reemplaza al pageSpacing negativo, que rompía
                    // el scroll bounds y no dejaba centrar la última tarjeta).
                    // Proporcional al ancho real de la tarjeta, no un valor fijo,
                    // para que el apilado se vea consistente en cualquier pantalla.
                    translationX = pageOffset.coerceIn(-1f, 1f) * size.width * 0.55f

                    // Punto de anclaje para que se apilen limpio
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                }
        )
    }

}

data class CardUi(
    val alias: String,
    val pan: String,
    val isDigital: Boolean,
    val cardType: String
) {
    fun getFormattedPan(): String = "**** **** **** ${this.pan}"
}