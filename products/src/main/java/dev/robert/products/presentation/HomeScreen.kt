package dev.robert.products.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import dev.robert.products.domain.model.Product

@Composable
fun HomeScreen(
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val productsState = viewModel.productsState.value
    val categoriesState = viewModel.categoriesState.value
    val selectedCategory = viewModel.selectedCategory.value

    ProductsWidget(
        productsState = productsState,
        categoriesState = categoriesState,
        selectedCategory = selectedCategory,
        onCategorySelected = viewModel::setCategory,
        onProductSelected = viewModel::getProductById,
        onProductCategorySelected = viewModel::getProductCategory,
        onProductCategories = viewModel::getProductCategories,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductsWidget(
    productsState: StateHolder<List<Product>>,
    categoriesState: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    onProductSelected: (Int) -> Unit,
    onProductCategorySelected: (String) -> Unit,
    onProductCategories: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                categories = categoriesState,
                selectedCategory = selectedCategory,
                onCategorySelected = onCategorySelected,
                onProductCategories = onProductCategories
            )
        },
        content = {
            ProductsList(
                productsState = productsState,
                onProductSelected = onProductSelected,
                onProductCategorySelected = onProductCategorySelected
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    onProductCategories: () -> Unit
) {
    TopAppBar(
        title = {
        ScrollableTabRow(selectedTabIndex = categories.indexOf(selectedCategory)) {
            categories.forEachIndexed { index, category ->
                Tab(
                    selected = selectedCategory == category,
                    onClick = { onCategorySelected(category) },
                    text = { Text(category) }
                )
            }
        }

    })
}

@Composable
fun ProductsList(
    productsState: StateHolder<List<Product>>,
    onProductSelected: (Int) -> Unit,
    onProductCategorySelected: (String) -> Unit
) {
    
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(160.dp),
        content = {
            productsState.data?.let {
                items(it.size) { index ->
                    ProductCard(
                        product = productsState.data[index],
                        onProductSelected = onProductSelected,
                        onProductCategorySelected = onProductCategorySelected
                    )
                }
            }
        }
    )
}

@Composable
fun ProductCard(
    product: Product,
    onProductSelected: (Int) -> Unit,
    onProductCategorySelected: (String) -> Unit
) {

    Box(modifier = androidx.compose.ui.Modifier.clickable { onProductSelected(product.id) }){
        ProductImage(
            imageUrl = product.image,
            contentDescription = product.title
        )
        ProductTitle(
            title = product.title,
            modifier = androidx.compose.ui.Modifier.clickable { onProductCategorySelected(product.category) }
        )
    }
}

@Composable
fun ProductTitle(
    title: String,
    modifier: androidx.compose.ui.Modifier
) {
    Text(
        text = title,
        modifier = modifier
    )
}


@Composable
fun ProductImage(
    imageUrl: String,
    contentDescription: String
) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                crossfade(true)
            }
        ),
        contentDescription = contentDescription
    )
}

