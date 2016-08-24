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

import java.util.Arrays;
import java.util.List;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColor;

import com.google.common.base.Preconditions;

import fr.evercraft.everapi.plugin.EChat;
import fr.evercraft.everapi.plugin.file.EMessage;
import fr.evercraft.everapi.plugin.file.EnumMessage;

public class EWMessage extends EMessage {

	public EWMessage(final EverMultiWorlds plugin) {
		super(plugin, EWMessages.values());
	}
	
	public enum EWMessages implements EnumMessage {
		PREFIX("prefix", 				"[&4Ever&6&lWorlds&f] "),
		DESCRIPTION("description",		"Gestionnaire des mondes"),
		
		
		CREATE_DESCRIPTION("create.description",		"Création d'un monde"),
		CREATE_PLAYER("create.player",		"&7Vous avez créé le monde <world>."),
		CREATE_WORLD("create.world",		"&6<world>"),
		CREATE_WORLD_HOVER("create.worldHover",	  "&7Dimension : &6 <dimension>[RT]"
												+ "&7Generator : &6 <generator>[RT]"
												+ "&7Seed : &6 <seed>"),
		CREATE_ERROR_NAME("create.errorName",				"&c<world>"),
		CREATE_ERROR_DIMENSION("create.errorDimension",		"&c<dimension>"),
		CREATE_ERROR_GENERATOR("create.errorGenerator",		"&c<generator>"),
		CREATE_ERROR_SEED("create.errorSeed",				"&c<seed>"),;
		
		private final String path;
	    private final Object french;
	    private final Object english;
	    private Object message;
	    
	    private EWMessages(final String path, final Object french) {   	
	    	this(path, french, french);
	    }
	    
	    private EWMessages(final String path, final Object french, final Object english) {
	    	Preconditions.checkNotNull(french, "Le message '" + this.name() + "' n'est pas définit");
	    	
	    	this.path = path;	    	
	    	this.french = french;
	    	this.english = english;
	    	this.message = french;
	    }

	    public String getName() {
			return this.name();
		}
	    
		public String getPath() {
			return this.path;
		}

		public Object getFrench() {
			return this.french;
		}

		public Object getEnglish() {
			return this.english;
		}
		
		public String get() {
			if (this.message instanceof String) {
				return (String) this.message;
			}
			return this.message.toString();
		}
			
		@SuppressWarnings("unchecked")
		public List<String> getList() {
			if (this.message instanceof List) {
				return (List<String>) this.message;
			}
			return Arrays.asList(this.message.toString());
		}
		
		public void set(Object message) {
			this.message = message;
		}

		public Text getText() {
			return EChat.of(this.get());
		}
		
		public TextColor getColor() {
			return EChat.getTextColor(this.get());
		}
	}
}
