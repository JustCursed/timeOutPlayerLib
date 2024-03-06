package tech.funkyra.timeout

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import tech.funkyra.timeout.handlers.PlayerTimeoutHandler

@Mod(
	name = "JustTimeout",
	modid = "timeout",
	modLanguage = "scala",
	acceptableRemoteVersions = "*"
)
object Main {
	@EventHandler def preInit(e: FMLPreInitializationEvent): Unit = {
		MinecraftForge.EVENT_BUS.register(new PlayerTimeoutHandler)
	}
}
