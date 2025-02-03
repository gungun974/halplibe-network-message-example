package turniplabs.examplemod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.core.net.command.CommandManager;
import net.minecraft.core.net.command.CommandSource;
import turniplabs.halplibe.helper.EnvironmentHelper;
import turniplabs.halplibe.helper.network.NetworkHandler;

public class TestCommand implements CommandManager.CommandRegistry {

	@Override
	public void register(CommandDispatcher<CommandSource> commandDispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = LiteralArgumentBuilder.<CommandSource>literal("test");

		builder.then(LiteralArgumentBuilder.<CommandSource>literal("openInventory").executes((c) -> {
			return 1;
		}).executes((c) -> {
			if (EnvironmentHelper.isServerEnvironment()){
				NetworkHandler.sendToAllPlayers(new OpenInventoryNetworkMessage("Hoi from server"));
				return 1;
			}
			if (EnvironmentHelper.isSinglePlayer()){
				NetworkHandler.sendToAllPlayers(new OpenInventoryNetworkMessage("Hoi from single player"));
			}
			return 1;
		}));

		commandDispatcher.register(builder);
	}
}
