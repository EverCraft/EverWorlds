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
package fr.evercraft.evermultiworlds;

import org.spongepowered.api.command.CommandSource;

import com.google.common.base.Preconditions;

import fr.evercraft.everapi.plugin.EnumPermission;

public enum EWPermissions implements EnumPermission {
	EVERMULTIWORLDS("commands.execute"),
	HELP("commands.help"),
	RELOAD("commands.reload"),
	
	CREATE("commands.create"),
	DELETE("commands.delete"),
	
	IMPORT("commands.import"),
	COPY("commands.copy"),
	
	LOAD("commands.load"),
	UNLOAD("commands.unload"),
	
	RENAME("commands.rename"),
	SETSPAWN("commands.setspawn"),
	PROPERTIES("commands.properties"),
	
	TELEPORT("commands.teleport"),
	
	LIST("commands.list"),
	INFO("commands.info");
	
	private final static String prefix = "evermultiworlds";
	
	private final String permission;
    
    private EWPermissions(final String permission) {   	
    	Preconditions.checkNotNull(permission, "La permission '" + this.name() + "' n'est pas définit");
    	
    	this.permission = permission;
    }

    public String get() {
		return EWPermissions.prefix + "." + this.permission;
	}
    
    public boolean has(CommandSource player) {
    	return player.hasPermission(this.get());
    }
}
