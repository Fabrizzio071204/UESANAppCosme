package com.example.uesanapp.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.uesanapp.data.model.CountryModel

val mockCountries = listOf(
    CountryModel("Colombia", 5, "https://flagcdn.com/w320/co.png"),
    CountryModel("Francia", 3, "https://flagcdn.com/w320/fr.png"),
    CountryModel("Brasil", 8, "https://flagcdn.com/w320/br.png"),
    CountryModel("España", 2, "https://flagcdn.com/w320/es.png"),
    CountryModel("Portugal", 7, "https://flagcdn.com/w320/pt.png"),
    CountryModel("Argentina", 1, "https://flagcdn.com/w320/ar.png"),
    CountryModel("Japón", 10, "https://flagcdn.com/w320/jp.png"),
    CountryModel("Perú", 50, "https://flagcdn.com/w320/pe.png"),
)

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val favorites by viewModel.favorites.collectAsState()
    val favoriteNames = favorites.map { it.name }.toSet()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            "Ranking FIFA 2026",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(mockCountries) { country ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = country.imageUrl,
                            contentDescription = country.name,
                            modifier = Modifier.size(64.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(country.name, style = MaterialTheme.typography.titleMedium)
                            Text("Ranking FIFA 2026: ${country.ranking}")
                        }
                        IconButton(onClick = { viewModel.toggleFavorite(country) }) {
                            Icon(
                                imageVector = if (country.name in favoriteNames)
                                    Icons.Filled.Favorite
                                else
                                    Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorito",
                                tint = if (country.name in favoriteNames) Color.Red
                                else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}