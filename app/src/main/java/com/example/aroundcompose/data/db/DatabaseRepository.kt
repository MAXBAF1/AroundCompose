package com.example.aroundcompose.data.db

import com.example.aroundcompose.data.db.entities.Account
import com.example.aroundcompose.data.db.entities.Settings
import com.example.aroundcompose.data.db.entities.Skills
import com.example.aroundcompose.data.models.SkillDTO
import com.example.aroundcompose.data.models.UserDTO
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val dao: Dao
) {
    suspend fun insertNewAccountData(userInfo: UserDTO, geolocation: String) =
        dao.insertNewAccountData(
            Account(
                id = userInfo.id,
                name = userInfo.username,
                email = userInfo.email,
                level = userInfo.level,
                teamId = userInfo.teamId,
                geolocation = geolocation
            )
        )

    suspend fun insertNewSkillData(skill: SkillDTO) = dao.insertNewSkillData(
        Skills(
            id = skill.id,
            maxLevel = skill.maxLevel,
            skillName = skill.name,
            skillDesc = skill.description,
            imageUrl = skill.imageUrl
        )
    )

    suspend fun insertNewSettingsData(settings: Settings) = dao.insertNewSettingsData(
        Settings(
            toggleNotification = settings.toggleNotification,
            theme = settings.theme,
            language = settings.language
        )
    )
}