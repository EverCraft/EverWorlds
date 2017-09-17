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

import com.google.common.base.Preconditions;

import fr.evercraft.everapi.message.EMessageBuilder;
import fr.evercraft.everapi.message.EMessageFormat;
import fr.evercraft.everapi.message.format.EFormatString;
import fr.evercraft.everapi.plugin.file.EMessage;
import fr.evercraft.everapi.plugin.file.EnumMessage;

public class EWMessage extends EMessage<EverMultiWorlds> {

	public EWMessage(final EverMultiWorlds plugin) {
		super(plugin, EWMessages.values());
	}
	
	public enum EWMessages implements EnumMessage {
		PREFIX(						"[&4Ever&6&lWorlds&f] "),
		DESCRIPTION(				"Gestionnaire des mondes"),
		
		
		CREATE_DESCRIPTION(			"Création d'un monde"),
		CREATE_PLAYER(				"&7Vous avez créé le monde {world}."),
		CREATE_WORLD(				"&6{world}"),
		CREATE_WORLD_HOVER(	  		"&7Dimension : &6 {dimension}[RT]"
								  + "&7Generator : &6 {generator}[RT]"
								  + "&7Seed : &6 {seed}"),
		CREATE_ERROR_NAME(			"&c{world}"),
		CREATE_ERROR_DIMENSION(		"&c{dimension}"),
		CREATE_ERROR_GENERATOR(		"&c{generator}"),
		CREATE_ERROR_SEED(			"&c{seed}"),
		
		PERMISSIONS_COMMANDS_EXECUTE(""),
		PERMISSIONS_COMMANDS_HELP(""),
		PERMISSIONS_COMMANDS_RELOAD(""),
		PERMISSIONS_COMMANDS_CREATE(""),
		PERMISSIONS_COMMANDS_DELETE(""),
		PERMISSIONS_COMMANDS_IMPORT(""),
		PERMISSIONS_COMMANDS_COPY(""),
		PERMISSIONS_COMMANDS_LOAD(""),
		PERMISSIONS_COMMANDS_UNLOAD(""),
		PERMISSIONS_COMMANDS_RENAME(""),
		PERMISSIONS_COMMANDS_SETSPAWN(""),
		PERMISSIONS_COMMANDS_PROPERTIES(""),
		PERMISSIONS_COMMANDS_TELEPORT(""),
		PERMISSIONS_COMMANDS_LIST(""),
		PERMISSIONS_COMMANDS_INFO("");
		
		private final String path;
	    private final EMessageBuilder french;
	    private final EMessageBuilder english;
	    private EMessageFormat message;
	    private EMessageBuilder builder;
	    
	    private EWMessages(final String french) {   	
	    	this(EMessageFormat.builder().chat(new EFormatString(french), true));
	    }
	    
	    private EWMessages(final String french, final String english) {   	
	    	this(EMessageFormat.builder().chat(new EFormatString(french), true), 
	    		EMessageFormat.builder().chat(new EFormatString(english), true));
	    }
	    
	    private EWMessages(final EMessageBuilder french) {   	
	    	this(french, french);
	    }
	    
	    private EWMessages(final EMessageBuilder french, final EMessageBuilder english) {
	    	Preconditions.checkNotNull(french, "Le message '" + this.name() + "' n'est pas définit");
	    	
	    	this.path = this.resolvePath();	    	
	    	this.french = french;
	    	this.english = english;
	    	this.message = french.build();
	    }

	    public String getName() {
			return this.name();
		}
	    
		public String getPath() {
			return this.path;
		}

		public EMessageBuilder getFrench() {
			return this.french;
		}

		public EMessageBuilder getEnglish() {
			return this.english;
		}
		
		public EMessageFormat getMessage() {
			return this.message;
		}
		
		public EMessageBuilder getBuilder() {
			return this.builder;
		}
		
		public void set(EMessageBuilder message) {
			this.message = message.build();
			this.builder = message;
		}
	}
}
