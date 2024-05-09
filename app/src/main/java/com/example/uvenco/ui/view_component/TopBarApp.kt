package com.example.uvenco.ui.view_component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.uvenco.R
import com.example.uvenco.entity.TickTime
import com.example.uvenco.service.TimeProvider
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable fun TopBarApp(backScreen: ()->Unit = {},){

    val scope = rememberCoroutineScope()
    val timeState: MutableState<TickTime> = remember { mutableStateOf(TickTime()) }
    scope.launch { TimeProvider.getTickTime().collect{ timeState.value = it } }
    var currentTime = "11:11"
    var currentTemporary = "95.0"
    try {
        currentTime = timeState.value.time
        currentTemporary = timeState.value.temperature.toString()
    } catch (_:Exception){}

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { backScreen() }
            .height(54.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
    ){
        Icon(painter = painterResource(id = R.drawable.frame_1817),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface)
        Text(
            text = stringResource(R.string.runero_touch),
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            fontWeight = FontWeight.Normal,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp),
        )
        Spacer(modifier = Modifier.weight(1f))
        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
            modifier = Modifier.width(88.dp)) {
            Text(
                text = currentTime,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
            modifier = Modifier.width(88.dp)) {
            Text(
                text = currentTemporary,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Column(verticalArrangement = Arrangement.Top, modifier = Modifier
                .fillMaxHeight()
                .padding(top = 20.dp)){
                Icon(
                    painter = painterResource(id = R.drawable.grad),
                    modifier = Modifier.size(6.dp),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null)
            }

            Icon(
                painter = painterResource(id = R.drawable.primary__stroke_),
                modifier = Modifier.size(11.dp),
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null)
        }
        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
            modifier = Modifier.width(88.dp)) {
            Image(
                painter = painterResource(id = R.drawable.russia),
                contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(R.string.ru),
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}
