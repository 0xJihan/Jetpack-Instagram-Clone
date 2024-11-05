package com.jihan.jetpack_instagram_clone.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.jihan.jetpack_instagram_clone.R

@Composable
fun CircularImageWithBorder(
    painter: AsyncImagePainter = rememberAsyncImagePainter(R.drawable.img),
    imageSize: Int = 100,
    label: String = "",
) {

    fun getRandomGradientColors(): List<Color> {
        val colors = listOf(
            Color(0xFFFBAA47),
            Color(0xFFD91A46),
            Color(0xFFA60F93),
            Color(0xFFE20337),
            Color(0xFFC60188),
            Color(0xFF7700C3),

            )
        return colors.shuffled()
    }


    // Define the solid color for the border when isStory is false
    val solidBorderColor = MaterialTheme.colorScheme.onSurfaceVariant

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(4.dp) // Outer padding for spacing around the border
                .drawBehind {
                    val borderWidth = 3.dp.toPx() // Width of the border in pixels
                    val radius = size.minDimension / 2
                    drawCircle(
                        brush = Brush.linearGradient(getRandomGradientColors()),
                        radius = radius,
                        center = center
                    )
                    drawCircle(
                        color = Color.White, radius = radius - borderWidth, center = center
                    )
                }
                .size(imageSize.dp)
        ) {

            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .clip(CircleShape) // Clip image to circle
                    .shadow(
                        elevation = 15.dp,
                        shape = CircleShape,
                        ambientColor = MaterialTheme.colorScheme.primary,
                        spotColor = MaterialTheme.colorScheme.primary
                    )
            )
        }
        }
    }


@Composable
fun CircularImage(modifier: Modifier = Modifier, painter: AsyncImagePainter = rememberAsyncImagePainter(R.drawable.img)) {
    Image(
        modifier = modifier.clip(CircleShape),
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

