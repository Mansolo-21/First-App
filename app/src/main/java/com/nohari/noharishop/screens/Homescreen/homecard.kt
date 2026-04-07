package com.nohari.noharishop.screens.Homescreen

import androidx.compose.ui.graphics.Color
import android.icu.text.CaseMap
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeCard(title: String, background: Color){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = background
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(size = 16.dp)

    ) {
        Text(text=title)
    }

}
