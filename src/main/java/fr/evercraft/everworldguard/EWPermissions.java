/*
 * This file is part of EverSigns.
 *
 * EverSigns is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EverSigns is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with EverSigns.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.evercraft.everworldguard;

import org.spongepowered.api.command.CommandSource;

import com.google.common.base.Preconditions;

import fr.evercraft.everapi.plugin.EnumPermission;

public enum EWPermissions implements EnumPermission {
	EVERWORLDGUARD("command"),
	
	HELP("help"),
	RELOAD("reload"),
	
	DEFINE("region.define"),
	
	REDEFINE_OWN("redefine.own"),
	REDEFINE_MEMBER("redefine.member"),
	
	INFO_OWN("info.own"),
	INFO_MEMBER("info.member"),
	INFO_REGION("info.region"),
	
	ADD_OWNER_OWN("addowner.own"),
	ADD_OWNER_MEMBER("addowner.member"),
	ADD_OWNER_ALL("addowner"),
	
	REMOVE_OWNER_OWN("removeowner.own"),
	REMOVE_OWNER_MEMBER("removeowner.member"),
	REMOVE_OWNER_ALL("removeowner"),
	
	ADD_MEMBER_OWN("addmember.own"),
	ADD_MEMBER_MEMBER("addmember.member"),
	ADD_MEMBER_ALL("addmember"),
	
	REMOVE_MEMBER_OWN("removemember.own"),
	REMOVE_MEMBER_MEMBER("removemember.member"),
	REMOVE_MEMBER_ALL("removemember"),
	
	LIST_OWN("list.own"),
	LIST("list");
	
	private final static String prefix = "everworldguard";
	
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
