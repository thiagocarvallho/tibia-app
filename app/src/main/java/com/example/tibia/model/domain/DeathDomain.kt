package com.example.tibia.model.domain

import java.util.Collections.emptyList

data class DeathDomain(
    val level: Int?,
    val reason: String?,
    val time: String?,
    val assists: List<KillerOrAssistDomain> = emptyList(),
    val killers: List<KillerOrAssistDomain> = emptyList()
)