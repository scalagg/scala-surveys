package gg.scala.survey.player

import gg.scala.common.Savable
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
    val

    override fun save(): CompletableFuture<Void>
    {
        TODO("Not yet implemented")
    }
}
