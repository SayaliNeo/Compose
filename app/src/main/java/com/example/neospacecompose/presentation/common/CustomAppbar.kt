package com.example.neospacecompose.presentation.common


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun CustomTopAppBar(
        title: String
        //onNavigationIconClick: () -> Unit,
        //onActionItemClick: () -> Unit
    ) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(1f),
            title = { Text(text = title) },


        )
}

@Preview(showBackground = true)
@Composable
fun CustomTopAppBarPreview() {
    CustomTopAppBarPreview()
}

