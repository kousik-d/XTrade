package com.intern.xtrade.wishList

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object WishlistManager {
    private const val WISHLIST_KEY = "wishlist"
    private const val YOUR_STOCK_KEY = "YOUR_STOCKS"

    fun login(context: Context){
        Log.i("LOGINFUNC","INITALIZED")
        val LoginsharedPreferences = context.getSharedPreferences("LoginPref",Context.MODE_PRIVATE)
        LoginsharedPreferences.edit().putBoolean("IsLoggedIn",true)
    }

    fun isLoggedIn(context: Context):Boolean{
        Log.i("LOGINFUNC","INITALIZEDCALLED")
        val LoginsharedPreferences = context.getSharedPreferences("LoginPref",Context.MODE_PRIVATE)
        val islogined =  LoginsharedPreferences.getBoolean("IsLoggedIn",true)
        Log.i("LOGINFUNC","${islogined}")
        return islogined
    }
    fun getYourStocks(context: Context): MutableList<Int> {
        val sharedPreferences = context.getSharedPreferences("MyStocks", Context.MODE_PRIVATE)
        val MystocksJson = sharedPreferences.getString(YOUR_STOCK_KEY, null)
        return if (MystocksJson != null) {
            Gson().fromJson(MystocksJson, object : TypeToken<MutableList<Int>>() {}.type)
        } else {
            mutableListOf()
        }
    }
    fun addToYourStocks(context: Context, stockId: Int) {
        val MyStocklist = getYourStocks(context)
        if (!MyStocklist.contains(stockId)) {
            MyStocklist.add(stockId)
            saveYourStocks(context, MyStocklist)
        }
    }
    fun getWishlist(context: Context): MutableList<Int> {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val wishlistJson = sharedPreferences.getString(WISHLIST_KEY, null)
        return if (wishlistJson != null) {
            Gson().fromJson(wishlistJson, object : TypeToken<MutableList<Int>>() {}.type)
        } else {
            mutableListOf()
        }
    }
    fun addToWishlist(context: Context, stockId: Int) {
        val wishlist = getWishlist(context)
        if (!wishlist.contains(stockId)) {
            wishlist.add(stockId)
            saveWishlist(context, wishlist)
        }
    }

    private fun saveYourStocks(context: Context, Stocks: MutableList<Int>) {
        val sharedPreferences = context.getSharedPreferences("MyStocks", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val StocklistJson = Gson().toJson(Stocks)
        editor.putString(YOUR_STOCK_KEY, StocklistJson)
        editor.apply()
    }
    fun removeFromWishlist(context: Context, stockId: Int) {
        val wishlist = getWishlist(context)
        wishlist.remove(stockId)
        saveWishlist(context, wishlist)
    }

    fun removeFromYourStocks(context: Context,stockId: Int){
        val stocks = getYourStocks(context)
        stocks.remove(stockId)
        saveYourStocks(context,stocks)
    }
    private fun saveWishlist(context: Context, wishlist: MutableList<Int>) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val wishlistJson = Gson().toJson(wishlist)
        editor.putString(WISHLIST_KEY, wishlistJson)
        editor.apply()
    }

}
