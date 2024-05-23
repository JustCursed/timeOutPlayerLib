package tech.funkyra.timeout.handlers

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent
import net.minecraftforge.event.entity.living._
import net.minecraftforge.event.entity.player.PlayerInteractEvent.{LeftClickBlock, RightClickBlock, RightClickItem}
import net.minecraftforge.event.entity.player.{EntityItemPickupEvent, PlayerDropsEvent, PlayerInteractEvent}
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent
import tech.funkyra.timeout.handlers.PlayerTimeoutHandler.{checkTimedoutPlayer, timedoutPlayers}

import scala.collection.Set

class PlayerTimeoutHandler {
	@SubscribeEvent def onPlayerLeave(e: PlayerLoggedOutEvent): Unit =
		if (timedoutPlayers.contains(e.player.getName))
			timedoutPlayers -= e.player.getName

	@SubscribeEvent def onTickPlayer(e: LivingUpdateEvent): Unit =
		checkTimedoutPlayer(e)

	@SubscribeEvent def onPlayerHurt(e: LivingHurtEvent): Unit =
		checkTimedoutPlayer(e)

	@SubscribeEvent def onPlayerDeath(e: LivingDeathEvent): Unit =
		checkTimedoutPlayer(e)

	@SubscribeEvent def onPlayerDestroyClock(e: LivingDestroyBlockEvent): Unit =
		checkTimedoutPlayer(e)

	@SubscribeEvent def onPlayerDrops(e: PlayerDropsEvent): Unit =
		checkTimedoutPlayer(e)

	@SubscribeEvent def onPlayerAttack(e: LivingAttackEvent): Unit =
		checkTimedoutPlayer(e)

	@SubscribeEvent def onPlayerInteract(e: PlayerInteractEvent): Unit =
		checkTimedoutPlayer(e)

	@SubscribeEvent def onPlayerUseItem(e: RightClickItem): Unit =
		checkTimedoutPlayer(e)

	@SubscribeEvent def onPlayerBlockLClick(e: LeftClickBlock): Unit =
		checkTimedoutPlayer(e)

	@SubscribeEvent def onPlayerBlockRClick(e: RightClickBlock): Unit =
		checkTimedoutPlayer(e)

	@SubscribeEvent def onEntityItemPickupEvent(e: EntityItemPickupEvent): Unit =
		checkTimedoutPlayer(e)
}

object PlayerTimeoutHandler {
	var timedoutPlayers: Set[String] = Set {""}

	private def checkTimedoutPlayer(e: LivingEvent): Unit =
		if (timedoutPlayers.contains(e.getEntity.getName))
			e.setCanceled(true)
}
