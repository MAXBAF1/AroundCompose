package com.example.aroundcompose.data

import android.content.SharedPreferences
import com.example.aroundcompose.data.models.TokenResponse
import com.example.aroundcompose.di.EncryptedSharedPref
import javax.inject.Inject

class TokenManager @Inject constructor(
    @EncryptedSharedPref private val sharedPref: SharedPreferences
) {

    fun saveTokens(tokenResponse: TokenResponse) {
        with(sharedPref.edit()) {
            putString("access_token", tokenResponse.accessToken)
            putString("refresh_token", tokenResponse.refreshToken)
            apply()
        }
    }

    fun getTokens(): Pair<String?, String?> {
        val accessToken = sharedPref.getString("access_token", null)
        val refreshToken = sharedPref.getString("refresh_token", null)
        return Pair(accessToken, refreshToken)
    }

    fun clearTokens() {
        with(sharedPref.edit()) {
            remove("access_token")
            remove("refresh_token")
            apply()
        }
    }
}
