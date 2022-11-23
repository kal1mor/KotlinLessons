package com.example.kotlinproject

class HouseBuilder (val builder: Builder){
    fun hasSwimpool() = builder.swimpool
    fun howManyStocks() = builder.stock

    companion object Builder{
        var stock: Int = 0
        var swimpool: Boolean = false

        fun setStock(stock: Int) = apply {this.stock = stock}
        fun setSwimpool(swimPool: Boolean) = apply {this.swimpool = swimpool}

        fun build(): HouseBuilder{
            return  HouseBuilder(this)
        }
    }
}