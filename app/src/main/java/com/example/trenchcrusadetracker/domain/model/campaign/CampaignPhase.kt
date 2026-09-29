package com.example.trenchcrusadetracker.domain.model.campaign

/**
 * Fases de Campaña (RF-1, RF-2, RF-3).
 */
sealed class CampaignPhase {
    object Barracks : CampaignPhase() // Fase de Cuartel (Permite editar warband)
    object Battle : CampaignPhase()   // Fase de Batalla (Bloquea edición, visualización oculta)
    object Finished : CampaignPhase()
}
