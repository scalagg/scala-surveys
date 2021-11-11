package gg.scala.survey.survey.filter

import gg.scala.survey.player.KSurveyPlayer

/**
 * @author GrowlyX
 * @since 11/10/2021
 */
interface KSurveyAvailabilityFilter
{
    val id: String
    fun shouldBeAvailableTo(player: KSurveyPlayer): Boolean
}
