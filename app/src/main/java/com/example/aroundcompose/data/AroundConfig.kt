package com.example.aroundcompose.data

enum class AroundConfig(private val value: String) {
    HOST_ADDRESS("http://localhost:8080/api/v1"),
    AUTH_ADDRESS("$HOST_ADDRESS/auth"),
    LOGIN_ADDRESS("$AUTH_ADDRESS/login"),
    REGISTRATION_ADDRESS("$AUTH_ADDRESS/registration"),
    REFRESH_ADDRESS("$AUTH_ADDRESS/refresh"),
    RECOVERY_ADDRESS("$AUTH_ADDRESS/recovery"),
    USER_ADDRESS("$HOST_ADDRESS/user"),
    CHUNKS_ADDRESS("$HOST_ADDRESS/chunks"),
    STATISTIC_ADDRESS("$HOST_ADDRESS/stat"),
    SKILLS_ADDRESS("$HOST_ADDRESS/skills");

    override fun toString(): String = value
}