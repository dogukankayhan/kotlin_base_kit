package com.base.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TypeChip(
    type: String,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (type.lowercase()) {
        "fire" -> Color(0xFFFF4422)
        "water" -> Color(0xFF3399FF)
        "grass" -> Color(0xFF77CC55)
        "electric" -> Color(0xFFFFCC33)
        "psychic" -> Color(0xFFFF5599)
        "ice" -> Color(0xFF66CCFF)
        "dragon" -> Color(0xFF7766EE)
        "dark" -> Color(0xFF775544)
        "fairy" -> Color(0xFFEE99EE)
        "normal" -> Color(0xFFAAAA99)
        "fighting" -> Color(0xFFBB5544)
        "flying" -> Color(0xFF8899FF)
        "poison" -> Color(0xFFAA5599)
        "ground" -> Color(0xFFDDBB55)
        "rock" -> Color(0xFFBBAA66)
        "bug" -> Color(0xFFAABB22)
        "ghost" -> Color(0xFF6666BB)
        "steel" -> Color(0xFFAAAABB)
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) backgroundColor else backgroundColor.copy(alpha = 0.2f))
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = type.replaceFirstChar { it.uppercase() },
            color = if (isSelected) Color.White else backgroundColor,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
    }
}
