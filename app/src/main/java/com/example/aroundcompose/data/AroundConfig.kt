package com.example.aroundcompose.data

enum class AroundConfig(private val value: String) {
    HOST_ADDRESS("https://aroundgame.ru/api/v1"),
    AUTH_ADDRESS("$HOST_ADDRESS/auth"),
    LOGIN_ADDRESS("$AUTH_ADDRESS/login"),
    REGISTRATION_ADDRESS("$AUTH_ADDRESS/registration"),
    REFRESH_ADDRESS("$AUTH_ADDRESS/refresh"),
    RECOVERY_ADDRESS("$AUTH_ADDRESS/recovery"),
    USER_ADDRESS("$HOST_ADDRESS/user"),
    CHUNKS_ADDRESS("$HOST_ADDRESS/chunks"),
    STATISTIC_ADDRESS("$HOST_ADDRESS/stat"),
    STATISTIC_USER_ADDRESS("$STATISTIC_ADDRESS/user"),
    STATISTIC_TEAM_ADDRESS("$STATISTIC_ADDRESS/team"),
    SKILLS_ADDRESS("$HOST_ADDRESS/skills"),
    EVENTS_ADDRESS("$HOST_ADDRESS/map-events"),
    EVENTS_ALL_ADDRESS("$EVENTS_ADDRESS/all"),
    CELLS_ADDRESS("$HOST_ADDRESS/chunks"),
    CELLS_ALL_ADDRESS("$CELLS_ADDRESS/all"),

    WS_HOST("wss://aroundgame.ru/ws"),
    CELLS_CHANGES_FROM_USER("$WS_HOST/topic/chunk.changes"),
    CELLS_CHANGES_EVENT("$WS_HOST/topic/chunk.event");

    override fun toString(): String = value
}