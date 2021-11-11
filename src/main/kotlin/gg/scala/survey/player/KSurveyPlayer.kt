package gg.scala.survey.player

import gg.scala.common.Savable
import gg.scala.survey.registrar.KPlayerHandler
import gg.scala.survey.survey.KSurveyEntityCompletion
import java.util.*
import java.util.concurrent.CompletableFuture

/**
 * @author GrowlyX
 * @since 11/10/2021
 */
class KSurveyPlayer(
    val uniqueId: UUID,
    var username: String
) : Savable
{
    val completion = KSurveyEntityCompletion()

    override fun save(): CompletableFuture<Void>
    {
        return KPlayerHandler.playerHandle
            .saveEntry(uniqueId.toString(), this)
    }
}
