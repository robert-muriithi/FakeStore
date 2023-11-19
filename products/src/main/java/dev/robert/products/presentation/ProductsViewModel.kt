package dev.robert.products.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.robert.products.data.utils.Resource
import dev.robert.products.domain.model.Product
import dev.robert.products.domain.usecase.GetCategoriesUseCase
import dev.robert.products.domain.usecase.GetProductById
import dev.robert.products.domain.usecase.GetProductCategory
import dev.robert.products.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductById: GetProductById,
    private val getProductCategory: GetProductCategory,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _selectedCategory = mutableStateOf("All")
    val selectedCategory: State<String> = _selectedCategory
    fun setCategory(value: String) {
        _selectedCategory.value = value
    }

    private val _productCategoryState = mutableStateOf(StateHolder<Product>())
    val productCategoryState: State<StateHolder<Product>> = _productCategoryState

    fun getProductCategory(category: String) {
        viewModelScope.launch {
            getProductCategory.invoke(category).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        it.value.let { product ->
                            _productsState.value = productsState.value.copy(
                                isLoading = false,
                                data = product
                            )
                        }
                    }
                    is Resource.Failure -> {
                        _productsState.value = productsState.value.copy(
                            isLoading = false,
                            error = it.throwable.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }
    }
    fun getProductById(id: Int) {
        viewModelScope.launch {
            getProductById.invoke(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        it.value.let { product ->
                            _productCategoryState.value = productCategoryState.value.copy(
                                isLoading = false,
                                data = product
                            )
                        }
                    }
                    is Resource.Failure -> {
                        _productCategoryState.value = productCategoryState.value.copy(
                            isLoading = false,
                            error = it.throwable.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }
    }

    private val _categoriesState = mutableStateOf(emptyList<String>())
    val categoriesState: State<List<String>> = _categoriesState

    private val _productsState = mutableStateOf(StateHolder<List<Product>>())
    val productsState: State<StateHolder<List<Product>>> = _productsState

     fun getProductCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        it.value.let { categories ->
                            _categoriesState.value = listOf("All") + categories
                        }
                    }
                    is Resource.Failure -> {
                        _categoriesState.value = emptyList()
                    }
                }
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            getProductsUseCase.invoke().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        it.value.let { products ->
                            _productsState.value = productsState.value.copy(
                                isLoading = false,
                                data = products
                            )
                        }
                    }
                    is Resource.Failure -> {
                        _productsState.value = productsState.value.copy(
                            isLoading = false,
                            error = it.throwable.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }
    }

    init {
        getProductCategories()
    }

}