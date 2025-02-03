package turniplabs.examplemod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.helper.EnvironmentHelper;
import turniplabs.halplibe.helper.network.NetworkMessage;
import turniplabs.halplibe.helper.network.UniversalPacket;

public class OpenInventoryNetworkMessage implements NetworkMessage {
	String text;

	public OpenInventoryNetworkMessage() {}

	public OpenInventoryNetworkMessage(String text) {
		this.text = text;
	}


	@Override
	public void encodeToUniversalPacket(@NotNull UniversalPacket packet) {
		packet.writeString(text);
	}

	@Override
	public void decodeFromUniversalPacket(@NotNull UniversalPacket packet) {
		text = packet.readString();

	}

	/**
	 * This should be its own function since LocalPlayer don't exist on server environment
	 */
	@Environment(EnvType.CLIENT)
	private void clientOpenInventory() {
		final Player currentPlayer = Minecraft.getMinecraft().thePlayer;
		currentPlayer.displayContainerScreen(currentPlayer.inventory);
	}

	@Override
	public void handle(NetworkContext context) {
		ExampleMod.LOGGER.info("Receive the message : {}", text);
		if (EnvironmentHelper.isSinglePlayer() || EnvironmentHelper.isClientWorld()) {
			clientOpenInventory();
		}
	}
}
