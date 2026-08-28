package com.devtorres.ui.components.cards.blurred

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.devtorres.ui.theme.green
import com.devtorres.ui.theme.onGreen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Bitcoin

@Preview
@Composable
internal fun BlurredCardPreview(

) {
    val contentLayerMock = rememberGraphicsLayer()
    val itemsMock by remember {
        mutableStateOf(listOf(1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,9))
    }
    var contentSizeMock by remember {
        mutableStateOf(IntSize.Zero)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged { contentSizeMock = it }
                .drawWithContent {
                    contentLayerMock.record {
                        this@drawWithContent.drawContent()
                    }
                    drawLayer(contentLayerMock)
                }
        ) {

            items(
                items = itemsMock
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(
                            if(it % 2 == 0) green else onGreen
                        )
                )
            }
        }

        BlurredCard(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.75f)
                .padding(16.dp),
            contentLayer = contentLayerMock,
            contentSize = contentSizeMock
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Brands.Bitcoin,
                    contentDescription = null
                )

                Icon(
                    imageVector = FontAwesomeIcons.Brands.Bitcoin,
                    contentDescription = null
                )

                Icon(
                    imageVector = FontAwesomeIcons.Brands.Bitcoin,
                    contentDescription = null
                )
            }
        }
    }
}
