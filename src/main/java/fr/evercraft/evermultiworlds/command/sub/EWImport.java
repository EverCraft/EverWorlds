/*
 * This file is part of EverWorlds.
 *
 * EverWorlds is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EverWorlds is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with EverWorlds.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.evercraft.evermultiworlds.command.sub;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.World;

import fr.evercraft.everapi.plugin.EChat;
import fr.evercraft.everapi.plugin.command.ESubCommand;
import fr.evercraft.evermultiworlds.EWCommand;
import fr.evercraft.evermultiworlds.EWPermissions;
import fr.evercraft.evermultiworlds.EverMultiWorlds;
import fr.evercraft.evermultiworlds.EWMessage.EWMessages;

public class EWImport extends ESubCommand<EverMultiWorlds> {
	public EWImport(final EverMultiWorlds plugin, final EWCommand command) {
        super(plugin, command, "import");
    }
	
	public boolean testPermission(final CommandSource source) {
		return source.hasPermission(EWPermissions.IMPORT.get());
	}

	public Text description(final CommandSource source) {
		return EWMessages.CREATE_DESCRIPTION.getText();
	}
	
	public Collection<String> tabCompleter(final CommandSource source, final List<String> args) throws CommandException {
		if (args.size() == 1) {
			return Arrays.asList("world_name");
		}
		return Arrays.asList();
	}

	public Text help(final CommandSource source) {
		return Text.builder("/" + this.getName() + " <world> [dimension] [generator] [seed]")
					.onClick(TextActions.suggestCommand("/" + this.getName() + " "))
					.color(TextColors.RED)
					.build();
	}
	
	public CompletableFuture<Boolean> execute(final CommandSource source, final List<String> args) {
		if (args.size() == 1) {
			return this.command(source, args.get(0));
		} else {
			source.sendMessage(this.help(source));
		}
		
		return CompletableFuture.completedFuture(false);
	}
	
	private CompletableFuture<Boolean> command(final CommandSource player, String name) {        
		Optional<World> world = this.plugin.getEServer().loadWorld(name);
		// Si le monde n'existe pas
		if (world.isPresent()) {
			player.sendMessage(EChat.of("World load : " + world.get().getName()));
		} else {
			player.sendMessage(EChat.of("World empty"));
		}
		return CompletableFuture.completedFuture(false);
	}
}
