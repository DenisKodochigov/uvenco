package com.example.uvenco.ui.view_component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uvenco.entity.TypeKeyboard
import com.example.uvenco.ui.lg

@Composable
fun TextFieldApp(
    modifier: Modifier = Modifier,
    typeKeyboard: TypeKeyboard,
    textStyle: TextStyle,
    contentAlignment: Alignment = Alignment.BottomCenter,
    placeholder: String = "",
    maxLines: Int = 1,
    onChangeValue:(String)->Unit = {},
){
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember { mutableStateOf(TextFieldValue(placeholder)) }
    val mergedStyle = LocalTextStyle.current.merge(textStyle.copy(color = LocalContentColor.current))
    val paddingHor = if (textStyle.fontSize > 14.sp) 8.dp else 4.dp
    val paddingVer = if (textStyle.fontSize > 14.sp) 6.dp else 2.dp
    lg(text.text)
    BasicTextField(
        value = text,
        enabled = true,
        singleLine = true,
        maxLines = maxLines,
        modifier = modifier,
        keyboardOptions = keyBoardOpt(typeKeyboard),
        keyboardActions = KeyboardActions(onDone = {
            onChangeValue(text.text)
            keyboardController?.hide() }),
        onValueChange = {
            text = it
            lg("onValueChange $text")
            onChangeValue(text.text)},
        visualTransformation = VisualTransformation.None,
        textStyle = mergedStyle,
        decorationBox = { innerTextField ->
            Row( verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(horizontal = paddingHor, vertical = paddingVer)
            ) {
                Box(contentAlignment = contentAlignment, modifier = modifier)
                {
                    innerTextField()
                }
            }
        },
    )
}
@Composable fun keyBoardOpt(typeKeyboard: TypeKeyboard): KeyboardOptions {
    return when (typeKeyboard) {
        TypeKeyboard.TEXT -> {
            KeyboardOptions(keyboardType = KeyboardType.Password,
                capitalization = KeyboardCapitalization.Sentences).copy(imeAction = ImeAction.Done) }
        TypeKeyboard.DIGIT -> {
            KeyboardOptions(keyboardType = KeyboardType.Decimal).copy(imeAction = ImeAction.Done) }
        TypeKeyboard.PASS -> {
            KeyboardOptions(keyboardType = KeyboardType.Password).copy(imeAction = ImeAction.Done) }
    }
}