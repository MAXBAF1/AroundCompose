package com.example.aroundcompose.data

import android.content.SharedPreferences
import com.example.aroundcompose.data.models.TokensDTO
import com.example.aroundcompose.di.EncryptedSharedPref
import javax.inject.Inject

class TokenManager @Inject constructor(
    @EncryptedSharedPref private val sharedPref: SharedPreferences,
) {

    fun saveTokens(tokens: TokensDTO) {
        with(sharedPref.edit()) {
            putString(ACCESS_TOKEN, tokens.accessToken)
            putString(REFRESH_TOKEN, tokens.refreshToken)
            apply()
        }
    }

    fun getTokens(): TokensDTO? {
        val accessToken = sharedPref.getString(ACCESS_TOKEN, null)
        val refreshToken = sharedPref.getString(REFRESH_TOKEN, null)

        if (accessToken == null || refreshToken == null) return null

        return TokensDTO(accessToken, refreshToken)
    }

    fun clearTokens() {
        with(sharedPref.edit()) {
            remove(ACCESS_TOKEN)
            remove(REFRESH_TOKEN)
            apply()
        }
    }

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
    }
}
