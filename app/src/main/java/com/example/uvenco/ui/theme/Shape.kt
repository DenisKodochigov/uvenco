
package com.example.uvenco.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapesApp = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)
val topBarShape = RoundedCornerShape(topEnd = 0.dp, topStart = 0.dp, bottomEnd = 6.dp, bottomStart = 6.dp)
val bottomBarShape = RoundedCornerShape(topEnd = 6.dp, topStart = 6.dp, bottomEnd = 0.dp, bottomStart = 0.dp)
val shapeAddExercise = RoundedCornerShape(topEnd = 0.dp, topStart = 6.dp, bottomEnd = 6.dp, bottomStart = 0.dp)