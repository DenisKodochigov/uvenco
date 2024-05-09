package com.example.uvenco.ui.screens.config

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.uvenco.R
import com.example.uvenco.domain.toDoubleMy
import com.example.uvenco.domain.toIntMy
import com.example.uvenco.entity.TypeCoffee
import com.example.uvenco.entity.TypeKeyboard
import com.example.uvenco.ui.lg
import com.example.uvenco.ui.theme.shapesApp
import com.example.uvenco.ui.view_component.TextApp
import com.example.uvenco.ui.view_component.TextFieldApp

@Composable
fun ConfigScreen(id: Int){
    lg("ConfigScreen")
    lg(" id $id")
    val viewModel: ConfigViewModel = hiltViewModel()
    ConfigScreenCreateView( id = id, viewModel = viewModel)
}

@Composable fun ConfigScreenCreateView( id: Int, viewModel: ConfigViewModel )
{
    lg(" ConfigScreenCreateView")
    val uiState by viewModel.configState.collectAsState()
    if (uiState.typesCoffee.isEmpty()) return
    if (uiState.coffeeTemp.value == null) uiState.coffeeTemp.value = uiState.typesCoffee[id].copy()

    ConfigScreenLayout(uiState = uiState)
}
@Composable fun ConfigScreenLayout( uiState: ConfigState ){
    lg("  ConfigScreenLayout")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp, start = 12.dp),
        content = { CardCoffee( uiState = uiState) }
    )
}
@Composable fun CardCoffee( uiState: ConfigState ) {
    lg("   CardCoffee")

    Row{
        Column {
            EditNameCoffee( uiState )
            EditPriceCoffee( uiState )
            Spacer(modifier = Modifier.height(12.dp))
            EditFreePrice( uiState )
            Button(
                onClick = { uiState.onSave( uiState.coffeeTemp.value!!) },
                enabled = uiState.enablesButton.value,
                shape = shapesApp.small,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary),
            ) {
                TextApp(
                    text = stringResource(id = R.string.save),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }
        SelectPicture(uiState)
    }
}

@Composable fun SelectPicture(uiState: ConfigState){
    lg("    SelectPicture")
    Row {
        PictureCoffee(uiState, TypeCoffee.Milk)
        PictureCoffee(uiState, TypeCoffee.Black)
    }
}
@Composable fun PictureCoffee(uiState: ConfigState, typeCoffee: TypeCoffee){
    lg("     PictureCoffee")
    Box( modifier = Modifier
        .height(227.dp)
        .width(227.dp))
    {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = if (typeCoffee == TypeCoffee.Black) 12.dp else 0.dp)
                .clickable {
                    uiState.coffeeTemp.value =
                        uiState.coffeeTemp.value?.copy(typeCoffee = typeCoffee)
                },
            painter = painterResource(
                id = if (typeCoffee ==TypeCoffee.Milk) R.drawable.coffee_milk
                        else R.drawable.coffee_black),
                contentDescription = null
        )
        lg("uiState.coffeeTemp.typeCoffee = ${uiState.coffeeTemp.value?.typeCoffee}")
        uiState.coffeeTemp.value?.let {
            uiState.enablesButton.value = uiState.typesCoffee[it.id] != it }

        lg("uiState.enablesButton = ${uiState.enablesButton.value}")
        if ( uiState.coffeeTemp.value?.typeCoffee == typeCoffee){
            Image(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(bottom = 36.dp),
                painter = painterResource(id = R.drawable.selected_figure),
                contentDescription = null)
        }
    }
}
@Composable fun EditNameCoffee( uiState: ConfigState){

    TextApp(
        modifier = Modifier.padding(bottom = 12.dp),
        text = stringResource(id = R.string.description),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onTertiary)
    TextFieldApp(
        modifier = Modifier.padding(bottom = 12.dp),
        placeholder = uiState.coffeeTemp.value?.name ?: "",
        textStyle = MaterialTheme.typography.titleLarge,
        typeKeyboard = TypeKeyboard.TEXT,
        onChangeValue = { uiState.coffeeTemp.value = uiState.coffeeTemp.value?.copy(name = it) }
    )
}
@Composable fun EditPriceCoffee( uiState: ConfigState){

    TextApp(
        modifier = Modifier.padding(bottom = 12.dp),
        text = stringResource(id = R.string.price),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onTertiary)
    Row (verticalAlignment = Alignment.CenterVertically
    ) {
        TextFieldApp(
            modifier = Modifier,
            placeholder = uiState.coffeeTemp.value?.price?.toString() ?: "",
            textStyle = MaterialTheme.typography.titleLarge,
            typeKeyboard = TypeKeyboard.DIGIT,
            onChangeValue = {
                uiState.coffeeTemp.value = uiState.coffeeTemp.value?.copy(price = it.toIntMy()) }
        )
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            tint = MaterialTheme.colorScheme.onSurface,
            painter = painterResource(id = R.drawable.rur),
            contentDescription = null)
    }
}
@Composable fun EditFreePrice(uiState: ConfigState){
    var checked by remember { mutableStateOf( uiState.coffeeTemp.value?.free ) }
    Row ( verticalAlignment = Alignment.CenterVertically)
    {
        TextApp(
            text = stringResource(id = R.string.no_price),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.width(24.dp))
        checked?.let {
            Switch(
                checked = it,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    checkedTrackColor = MaterialTheme.colorScheme.onSecondary),
                onCheckedChange = {
                    checked = it
                    uiState.coffeeTemp.value =
                        uiState.coffeeTemp.value?.copy(free = !uiState.coffeeTemp.value!!.free)
                }
            )
        }
    }
}