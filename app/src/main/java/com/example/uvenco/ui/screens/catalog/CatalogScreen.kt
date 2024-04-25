package com.example.uvenco.ui.screens.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.uvenco.R
import com.example.uvenco.entity.Coffee
import com.example.uvenco.entity.TypeCoffee

@Composable
fun CatalogScreen( onClickItem: (Int) -> Unit,
){
    val viewModel: CatalogViewModel = hiltViewModel()
    CatalogScreenCreateView(
        onClickItem = onClickItem,
        viewModel = viewModel,
    )
}
@Composable
fun CatalogScreenCreateView(
    onClickItem: (Int) -> Unit,
    viewModel: CatalogViewModel,
){
    val uiState by viewModel.catalogState.collectAsState()
    uiState.onClickItem = { id -> onClickItem (id)}
    CatalogScreenLayout(uiState = uiState)
}
@Composable fun CatalogScreenLayout( uiState: CatalogState
){
    Column(
        modifier = Modifier.fillMaxSize(),
        content = {
            CatalogLazyGrid( uiState = uiState)
        }
    )
}

@Composable fun CatalogLazyGrid(uiState: CatalogState
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(12.dp),
        columns = GridCells.Adaptive(minSize = 227.dp),
        state = rememberLazyGridState(),
    ) {
        items(uiState.typesCoffee){ coffee ->
            val index = uiState.typesCoffee.indexOf(coffee)
            Card(
                modifier = Modifier.padding(6.dp).clickable { uiState.onClickItem(index) },
                shape = MaterialTheme.shapes.extraSmall,
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                elevation = CardDefaults.cardElevation(),
                border = null,
                content = { ContentCard(coffee) }
            )
        }
    }
}
@Composable fun ContentCard(coffee: Coffee){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
        .padding(horizontal = 6.dp)
        .height(313.dp)
        .width(227.dp),
    ) {
        Image(
            painter = painterResource(
                id = if (coffee.typeCoffee == TypeCoffee.Milk) R.drawable.coffee_milk
                        else R.drawable.coffee_black),
            modifier = Modifier.size(166.dp),
            contentDescription = null,)
        Text(
            text = coffee.name,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            fontWeight = FontWeight.Normal,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.padding(vertical = 20.dp),
        )
        Row (horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 12.dp))
        {
            Text(
                text = coffee.size.toString(),
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 0.dp),
            )
            if (coffee.price > 0) {
                Spacer(modifier = Modifier.weight(1f))
//                val symbol = Currency.getInstance(Locale.current.region).symbol
                Text(
                    text = coffee.price.toString() ,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(start = 0.dp),
                )
            }
        }
    }
}