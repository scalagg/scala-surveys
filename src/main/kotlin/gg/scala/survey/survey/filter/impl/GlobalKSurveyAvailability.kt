package gg.scala.survey.survey.filter.impl

import gg.scala.survey.player.KSurveyPlayer
import gg.scala.survey.survey.filter.KSurveyAvailabilityFilter

/**
 * @author GrowlyX
 * @since 11/10/2021
 */
class GlobalKSurveyAvailability : KSurveyAvailabilityFilter
{
    override val id: String
        get() = "Everyone"

    override fun shouldBeAvailableTo(player: KSurveyPlayer) = true
}
