package fr.evercraft.everworlds.command.sub;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;

import fr.evercraft.everapi.EAMessage.EAMessages;
import fr.evercraft.everapi.plugin.EChat;
import fr.evercraft.everapi.plugin.command.ESubCommand;
import fr.evercraft.everworlds.EWCommand;
import fr.evercraft.everworlds.EWPermissions;
import fr.evercraft.everworlds.EverWorlds;
import fr.evercraft.everworlds.EWMessage.EWMessages;

public class EWReload extends ESubCommand<EverWorlds> {
	public EWReload(final EverWorlds plugin, final EWCommand command) {
        super(plugin, command, "reload");
    }
	
	public boolean testPermission(final CommandSource source) {
		return source.hasPermission(EWPermissions.RELOAD.get());
	}

	public Text description(final CommandSource source) {
		return EAMessages.RELOAD_DESCRIPTION.getText();
	}
	
	public List<String> subTabCompleter(final CommandSource source, final List<String> args) throws CommandException {
		return new ArrayList<String>();
	}

	public Text help(final CommandSource source) {
		return Text.builder("/" + this.getName())
					.onClick(TextActions.suggestCommand("/" + this.getName()))
					.color(TextColors.RED)
					.build();
	}
	
	public boolean subExecute(final CommandSource source, final List<String> args) {
		if(args.size() == 0) {
			return commandReload(source);
		}
		source.sendMessage(this.help(source));
		return false;
	}

	private boolean commandReload(final CommandSource player) {
		this.plugin.reload();
		player.sendMessage(EChat.of(EWMessages.PREFIX.get() + EAMessages.RELOAD_COMMAND.get()));
		return true;
	}
}
