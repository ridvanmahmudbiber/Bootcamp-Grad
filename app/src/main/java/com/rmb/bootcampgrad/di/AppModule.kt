package com.rmb.bootcampgrad.di

import com.rmb.bootcampgrad.data.datasource.ProductsDataSource
import com.rmb.bootcampgrad.data.repo.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideProductsRepository(productsDataSource: ProductsDataSource) : ProductsRepository {
        return ProductsRepository(productsDataSource)
    }

    @Provides
    @Singleton
    fun provideProductsDataSource() : ProductsDataSource {
        return ProductsDataSource()
    }
}