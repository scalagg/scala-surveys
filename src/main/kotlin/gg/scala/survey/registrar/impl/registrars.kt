package gg.scala.survey.registrar

import com.solexgames.datastore.commons.connection.impl.mongo.UriMongoConnection
import com.solexgames.datastore.commons.layer.impl.MongoStorageLayer
import gg.scala.lemon.Lemon
import gg.scala.survey.player.KSurveyPlayer
import gg.scala.survey.survey.KSurveyEntity
import gg.scala.survey.survey.filter.KSurveyAvailabilityFilter
import gg.scala.survey.survey.filter.impl.GlobalKSurveyAvailability
import me.lucko.helper.Events
import net.evilblock.cubed.serializers.Serializers
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.*

/**
 * @author GrowlyX
 * @since 11/10/2021
 */
object KSurveyAvailabilityHandler : ObjectRegistrar<String, KSurveyAvailabilityFilter>()
{
    override fun initialLoad()
    {
        insert("default", GlobalKSurveyAvailability())
    }
}

object KSurveyHandler : ObjectRegistrar<UUID, KSurveyEntity>()
{
    override fun initialLoad()
    {

    }
}

object KPlayerHandler : ObjectRegistrar<UUID, KSurveyPlayer>()
{
    lateinit var playerHandle: MongoStorageLayer<KSurveyPlayer>

    override fun initialLoad()
    {
        MongoStorageLayer(
            UriMongoConnection(
                Lemon.instance.mongoConfig.uri
            ),
            "SolexGames", "ksurvey_players",
            KSurveyPlayer::class.java
        ).also {
            playerHandle = it
            it.supplyWithCustomGson(Serializers.gson)
        }

        Events.subscribe(AsyncPlayerPreLoginEvent::class.java).handler {
            playerHandle.fetchEntryByKey(
                it.uniqueId.toString()
            ).thenAccept { player ->
                if (player != null)
                {
                    insert(
                        it.uniqueId, player.also { _ ->
                            player.username = it.name
                        }
                    )
                } else
                {
                    insert(it.uniqueId, KSurveyPlayer(it.uniqueId, it.name))
                }
            }
        }

        Events.subscribe(PlayerQuitEvent::class.java).handler {
            delete(it.player.uniqueId)?.save()
        }
    }

    fun find(player: Player): KSurveyPlayer? = handle[player.uniqueId]
}
