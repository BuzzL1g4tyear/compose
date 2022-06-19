package com.example.compose.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.Amber

@Composable
fun SelectableItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    title: String,
    titleColor: Color =
        if (selected) Amber
        else MaterialTheme.colors.onSurface.copy(0.2f),
    titleSize: TextUnit = MaterialTheme.typography.h5.fontSize,
    titleWeight: FontWeight = FontWeight.Medium,

    subTitle: String,
    subTitleColor: Color =
        if (selected) Amber
        else MaterialTheme.colors.onSurface.copy(0.2f),
    borderWidth: Dp = 1.dp,
    borderColor: Color =
        if (selected) Amber
        else MaterialTheme.colors.onSurface.copy(0.2f),
    borderShape: Shape = RoundedCornerShape(size = 10.dp),
    icon: ImageVector = Icons.Default.CheckCircle,
    iconColor: Color =
        if (selected) Amber
        else MaterialTheme.colors.onSurface.copy(0.2f),
    onClick: () -> Unit
) {

    Column(
        modifier = modifier
            .border(
                width = borderWidth,
                color = borderColor,
                shape = borderShape
            )
            .clickable {
                onClick()
            }
    ) {
        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
//                modifier = Modifier.width(width = 8f),
                    text = title,
                    style = TextStyle(
                        color = titleColor,
                        fontSize = titleSize,
                        fontWeight = titleWeight
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = onClick,
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "",
                            tint = iconColor
                        )
                    }
                }

            }
            Text(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                text = subTitle,
                style = TextStyle(
                    color = subTitleColor
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}