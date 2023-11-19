package dev.robert.products.data.mappers

import dev.robert.database.entities.ProductEntity
import dev.robert.products.data.dto.product.ProductRating
import dev.robert.products.data.dto.product.ProductsDto
import dev.robert.products.data.dto.product.ProductsDtoItem
import dev.robert.products.domain.model.Product
import dev.robert.products.domain.model.Rating

fun ProductsDtoItem.toDomain() : Product {
    return Product(
        category = category,
        description = description,
        image = image,
        price = price,
        rating = rating.toDomain(),
        title = title,
        id = id
    )
}



fun ProductsDtoItem.toEntity() : ProductEntity {
    return ProductEntity(
        category = category,
        description = description,
        image = image,
        price = price,
        id = id,
        title = title,
        rating = rating.toEntity()
    )
}

fun ProductRating.toEntity() : dev.robert.database.entities.ProductRating {
    return dev.robert.database.entities.ProductRating(
        count = count,
        rate = rate
    )
}

fun ProductEntity.toDomain() : Product {
    return Product(
        category = category,
        description = description,
        image = image,
        price = price,
        rating = rating.toDomain(),
        title = title,
        id = id
    )
}

fun dev.robert.database.entities.ProductRating.toDomain() : Rating {
    return Rating(
        count = count,
        rate = rate
    )
}

fun ProductRating.toDomain() : Rating {
    return Rating(
        count = count,
        rate = rate
    )
}