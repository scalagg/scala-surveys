package gg.scala.survey.survey

import gg.scala.survey.registrar.KSurveyAvailabilityHandler
import gg.scala.survey.survey.deployment.KSurveyDeployment
import java.util.*

/**
 * @author GrowlyX
 * @since 11/10/2021
 */
abstract class KSurveyEntity(
    val entityId: UUID, val name: String
)
{
    val description = setOf(
        "This is a newly created KSurvey",
        "survey entity."
    )

    val deployment = KSurveyDeployment()
    val questionnaire = KSurveyQuestionnaire()

    val availability = KSurveyAvailabilityHandler.find("default")!!
}
