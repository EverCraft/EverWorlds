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

import fr.evercraft.everapi.plugin.EnumPermission;
import fr.evercraft.everapi.plugin.file.EnumMessage;
import fr.evercraft.evermultiworlds.EWMessage.EWMessages;

public enum EWPermissions implements EnumPermission {
	EVERMULTIWORLDS("commands.execute", EWMessages.PERMISSIONS_COMMANDS_EXECUTE),
	HELP("commands.help", EWMessages.PERMISSIONS_COMMANDS_HELP),
	RELOAD("commands.reload", EWMessages.PERMISSIONS_COMMANDS_RELOAD),
	
	CREATE("commands.create", EWMessages.PERMISSIONS_COMMANDS_CREATE),
	DELETE("commands.delete", EWMessages.PERMISSIONS_COMMANDS_DELETE),
	
	IMPORT("commands.import", EWMessages.PERMISSIONS_COMMANDS_IMPORT),
	COPY("commands.copy", EWMessages.PERMISSIONS_COMMANDS_COPY),
	
	LOAD("commands.load", EWMessages.PERMISSIONS_COMMANDS_LOAD),
	UNLOAD("commands.unload", EWMessages.PERMISSIONS_COMMANDS_UNLOAD),
	
	RENAME("commands.rename", EWMessages.PERMISSIONS_COMMANDS_RENAME),
	SETSPAWN("commands.setspawn", EWMessages.PERMISSIONS_COMMANDS_SETSPAWN),
	PROPERTIES("commands.properties", EWMessages.PERMISSIONS_COMMANDS_PROPERTIES),
	
	TELEPORT("commands.teleport", EWMessages.PERMISSIONS_COMMANDS_TELEPORT),
	
	LIST("commands.list", EWMessages.PERMISSIONS_COMMANDS_LIST),
	INFO("commands.info", EWMessages.PERMISSIONS_COMMANDS_INFO);
	
	private static final String PREFIX = "evermultiworlds";
	
	private final String permission;
	private final EnumMessage message;
	private final boolean value;
    
    private EWPermissions(final String permission, final EnumMessage message) {
    	this(permission, message, false);
    }
    
    private EWPermissions(final String permission, final EnumMessage message, final boolean value) {   	    	
    	this.permission = PREFIX + "." + permission;
    	this.message = message;
    	this.value = value;
    }

    @Override
    public String get() {
		return this.permission;
	}

	@Override
	public boolean getDefault() {
		return this.value;
	}

	@Override
	public EnumMessage getMessage() {
		return this.message;
	}
}
