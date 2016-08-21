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

import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

import fr.evercraft.everapi.plugin.EPlugin;
import fr.evercraft.evermultiworlds.command.sub.EWCreate;
import fr.evercraft.evermultiworlds.command.sub.EWReload;

@Plugin(id = "fr.evercraft.evermultiworlds", 
		name = "EverMultiWorlds", 
		version = "1.2", 
		description = "MultiWorlds",
		url = "http://evercraft.fr/",
		authors = {"rexbut"},
		dependencies = {
		    @Dependency(id = "fr.evercraft.everapi", version = "1.2")
		})
public class EverMultiWorlds extends EPlugin {
	private EWConfig configs;
	private EWMessage messages;
	
	@Override
	protected void onPreEnable() {		
		this.configs = new EWConfig(this);
		this.messages = new EWMessage(this);
		
		this.getGame().getEventManager().registerListeners(this, new EWListener(this));
	}
	
	@Override
	protected void onCompleteEnable() {
		EWCommand command = new EWCommand(this);
		
		command.add(new EWReload(this, command));
		command.add(new EWCreate(this, command));
	}
	
	@Override
	protected void onStartServer() {
	}

	protected void onReload(){
		this.reloadConfigurations();
	}
	
	protected void onDisable() {
	}

	/*
	 * Accesseurs
	 */
	
	public EWMessage getMessages(){
		return this.messages;
	}
	
	public EWConfig getConfigs() {
		return this.configs;
	}
}
