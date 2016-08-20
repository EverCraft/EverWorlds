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
package fr.evercraft.everworlds;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

import fr.evercraft.everapi.plugin.command.EParentCommand;
import fr.evercraft.everworlds.EWMessage.EWMessages;

public class EWCommand extends EParentCommand<EverWorlds> {
	
	public EWCommand(final EverWorlds plugin) {
        super(plugin, "everworldguard", "worldguard", "everwg", "wg");
    }
	
	@Override
	public boolean testPermission(final CommandSource source) {
		return source.hasPermission(EWPermissions.EVERWORLDGUARD.get());
	}

	@Override
	public Text description(final CommandSource source) {
		return EWMessages.DESCRIPTION.getText();
	}

	@Override
	public boolean testPermissionHelp(final CommandSource source) {
		return source.hasPermission(EWPermissions.HELP.get());
	}
}
