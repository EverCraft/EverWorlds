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

import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

import fr.evercraft.everapi.plugin.EPlugin;
import fr.evercraft.everworlds.command.sub.EWReload;

@Plugin(id = "fr.evercraft.everworlds", 
		name = "EverWorlds", 
		version = "1.2", 
		description = "EverWorlds",
		url = "http://evercraft.fr/",
		authors = {"rexbut"},
		dependencies = {
		    @Dependency(id = "fr.evercraft.everapi", version = "1.2")
		})
public class EverWorlds extends EPlugin {
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
