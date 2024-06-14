package com.example.aroundcompose.data.db

import com.example.aroundcompose.data.db.entities.AccountEntity
import com.example.aroundcompose.data.db.entities.SettingsEntity
import com.example.aroundcompose.data.db.entities.SkillsEntity
import com.example.aroundcompose.data.models.SettingsDTO
import com.example.aroundcompose.data.models.SkillDTO
import com.example.aroundcompose.data.models.UserDTO
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val dao: Dao
) {
    suspend fun insertNewAccountData(userInfo: UserDTO, geolocation: String) =
        dao.insertNewAccountData(
            AccountEntity(
                id = userInfo.id,
                name = userInfo.username,
                email = userInfo.email,
                level = userInfo.level,
                teamId = userInfo.teamId,
                geolocation = geolocation
            )
        )

    suspend fun insertNewSkillData(skill: SkillDTO) = dao.insertNewSkillData(
        SkillsEntity(
            id = skill.id,
            maxLevel = skill.maxLevel,
            skillName = skill.name,
            skillDesc = skill.description,
            imageUrl = skill.imageUrl
        )
    )

    suspend fun insertNewSettingsData(settings: SettingsEntity) = dao.insertNewSettingsData(
        SettingsEntity(
            toggleNotification = settings.toggleNotification,
            theme = settings.theme,
            language = settings.language
        )
    )

    suspend fun getAllAccountData(): UserDTO {
        val accountInfo = dao.getAllAccountData()

        return UserDTO(
            id = accountInfo.id,
            username = accountInfo.name,
            email = accountInfo.email,
            level = accountInfo.level,
            teamId = accountInfo.teamId,
        )
    }

    suspend fun getAllSkillsData(): List<SkillDTO> {
        val list = mutableListOf<SkillDTO>()

        dao
            .getAllSkillsData()
            .forEach { skill ->
                list.add(
                    SkillDTO(
                        id = skill.id,
                        name = skill.skillName,
                        maxLevel = skill.maxLevel,
                        description = skill.skillDesc,
                        imageUrl = skill.imageUrl,
                        iconId = 1
                    )
                )
            }
        return list
    }

    suspend fun getAllSettingsData(): SettingsDTO {
        val settingsInfo = dao.getAllSettingsData()

        return SettingsDTO(
            id = settingsInfo.id,
            toggleNotification = settingsInfo.toggleNotification,
            theme = settingsInfo.theme,
            language = settingsInfo.language
        )
    }
}