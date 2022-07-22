package com.example.compose.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.model.Person
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun PersonCard(
    person: Person
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
        elevation = 3.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 14.dp)
        ) {
            Text(
                text = person.Name,
                modifier = Modifier
                    .fillMaxWidth(0.20f)
                    .wrapContentWidth(Alignment.Start),
                color = Color.DarkGray,
                fontSize = 18.sp
            )
            Text(
                text = "2",
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color.DarkGray,
                fontSize = 18.sp
            )
            Text(
                text = person.Department,
                modifier = Modifier
                    .wrapContentWidth(Alignment.End),
                color = Color.DarkGray,
                fontSize = 18.sp
            )
        }
    }
}