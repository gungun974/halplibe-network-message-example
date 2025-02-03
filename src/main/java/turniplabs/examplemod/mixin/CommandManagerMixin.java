package turniplabs.examplemod.mixin;

import net.minecraft.core.net.command.CommandManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import turniplabs.examplemod.TestCommand;

@Mixin(value = CommandManager.class, remap = false)
public class CommandManagerMixin {
	@Inject(at = @At("HEAD"), method = "init")
	public void initInject(CallbackInfo ci) {
		CommandManager.registerCommand(new TestCommand());
	}
}
