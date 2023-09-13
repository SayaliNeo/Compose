package com.example.neospacecompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.neospacecompose.ui.theme.CardBackground
import com.example.neospacecompose.ui.theme.Purple71

@Composable
 fun DraggableThumbButton(
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            //.shadow(8.dp, shape = CircleShape)
            .size(64.dp)
            .clip(CircleShape)
            .clickable { onClick() }
            .background(Purple71)
    ) {
        Text(
            text = value,
            color = Color.White,
            //style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
        )
    }
}