package com.jagdon.removal_jagdon.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jagdon.removal_jagdon.models.MenuItem
import com.jagdon.removal_jagdon.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MenuViewModel : ViewModel() {

    private val api = RetrofitClient.apiService
    val menuItems = MutableLiveData<List<MenuItem>>()

    fun fetchMenuItems() {
        api.getMenuItems().enqueue(object : Callback<List<MenuItem>> {
            override fun onResponse(call: Call<List<MenuItem>>, response: Response<List<MenuItem>>) {
                if (response.isSuccessful) {
                    menuItems.value = response.body() ?: emptyList()
                    Log.d("MenuViewModel", "Fetched items: ${response.body()}")
                } else {
                    menuItems.value = emptyList()
                    Log.d("MenuViewModel", "Failed to fetch items: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<MenuItem>>, t: Throwable) {
                menuItems.value = emptyList()
                Log.d("MenuViewModel", "Error fetching items: ${t.message}")
            }
        })
    }

    fun addItem(item: MenuItem) {
        api.addItem(item).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("MenuViewModel", "Item added successfully")
                    fetchMenuItems()
                } else {
                    Log.d("MenuViewModel", "Failed to add item: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("MenuViewModel", "Error adding item: ${t.message}")
                fetchMenuItems()
            }
        })
    }

    fun editItem(itemId: String, updatedItem: MenuItem) {
        api.editItem(itemId, updatedItem).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("MenuViewModel", "Item updated successfully")
                    fetchMenuItems()
                } else {
                    Log.d("MenuViewModel", "Failed to update item: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("MenuViewModel", "Error updating item: ${t.message}")
                fetchMenuItems()
            }
        })
    }

    fun deleteItem(itemId: String) {
        api.deleteItem(itemId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("MenuViewModel", "Item deleted successfully")
                    fetchMenuItems()
                } else {
                    Log.d("MenuViewModel", "Failed to delete item: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("MenuViewModel", "Error deleting item: ${t.message}")
                fetchMenuItems()
            }
        })
    }
}
