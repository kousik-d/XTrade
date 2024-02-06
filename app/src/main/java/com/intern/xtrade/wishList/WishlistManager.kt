package com.intern.xtrade.wishList

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object WishlistManager {
    private const val WISHLIST_KEY = "wishlist"
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
    fun removeFromWishlist(context: Context, stockId: Int) {
        val wishlist = getWishlist(context)
        wishlist.remove(stockId)
        saveWishlist(context, wishlist)
    }
    private fun saveWishlist(context: Context, wishlist: MutableList<Int>) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val wishlistJson = Gson().toJson(wishlist)
        editor.putString(WISHLIST_KEY, wishlistJson)
        editor.apply()
    }
}
