package com.jihan.jetpack_instagram_clone.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jihan.jetpack_instagram_clone.R
import com.jihan.jetpack_instagram_clone.ui.theme.AppTheme
import io.eyram.iconsax.IconSax

@PreviewLightDark
@Composable
fun PostImage(modifier: Modifier = Modifier) {
    AppTheme {
        Column {
            PostHeader()
            Spacer(Modifier.height(10.dp))
            PostBody()
            Spacer(Modifier.height(4.dp))
            PostBottom()
        }
    }
}

@Composable
private fun PostHeader() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
       CircularImage(hideBorder = false , imageSize = 50)
        Spacer(Modifier.width(8.dp))
        Column {
            UserInfo(name = "Jihan Khan", location = "Tokyo, Japan")
        }
    }
}

@Composable
private fun UserInfo(name: String, location: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
        lineHeight = 10.sp
    )
    Text(
        text = location,
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.onSurface,
        lineHeight = 10.sp
    )
}

@Composable
private fun PostBody() {
    Image(
        painterResource(R.drawable.img),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun PostBottom() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        var isHeartSelected by rememberSaveable  { mutableStateOf(false) }
        val animatedScale by animateFloatAsState(
            targetValue = if (isHeartSelected) 1.5f else 1f,
            animationSpec = tween(durationMillis = 300), label = "Animated Scale"
        )

        IconButton(onClick = { isHeartSelected = !isHeartSelected }) {
            val icon = if (isHeartSelected) IconSax.Bold.Heart else IconSax.Outline.Heart
            Icon(
                modifier = Modifier.scale(animatedScale),
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Red
            )
        }

        ActionIcon(icon = IconSax.Outline.Message)
        ActionIcon(icon = IconSax.Outline.Send2)

        Box(Modifier.weight(1f).padding(end = 15.dp), contentAlignment = Alignment.BottomEnd) {
            ActionIcon(icon = IconSax.Outline.Save2)
        }
    }
}

@Composable
private fun ActionIcon(icon: Int) {
    IconButton(onClick = { }) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}
