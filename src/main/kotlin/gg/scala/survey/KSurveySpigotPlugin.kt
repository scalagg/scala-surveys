package gg.scala.survey

import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.survey.registrar.KSurveyAvailabilityHandler
import gg.scala.survey.registrar.KPlayerHandler
import gg.scala.survey.registrar.KSurveyHandler
import gg.scala.survey.survey.deployment.KSurveyDeploymentHelper

/**
 * @author GrowlyX
 * @since 11/10/2021
 */
class KSurveySpigotPlugin : ExtendedScalaPlugin()
{
    override fun enable()
    {
        listOf(
            KSurveyDeploymentHelper, KPlayerHandler,
            KSurveyAvailabilityHandler, KSurveyHandler
        ).forEach { it.initialLoad() }
    }
}
