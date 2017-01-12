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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.GeneratorType;
import org.spongepowered.api.world.WorldArchetype;
import org.spongepowered.api.world.WorldArchetype.Builder;

import fr.evercraft.everapi.plugin.command.ESubCommand;
import fr.evercraft.evermultiworlds.EWCommand;
import fr.evercraft.evermultiworlds.EWPermissions;
import fr.evercraft.evermultiworlds.EverMultiWorlds;
import fr.evercraft.evermultiworlds.EWMessage.EWMessages;

public class EWCreate extends ESubCommand<EverMultiWorlds> {
	public EWCreate(final EverMultiWorlds plugin, final EWCommand command) {
        super(plugin, command, "create");
    }
	
	public boolean testPermission(final CommandSource source) {
		return source.hasPermission(EWPermissions.CREATE.get());
	}

	public Text description(final CommandSource source) {
		return EWMessages.CREATE_DESCRIPTION.getText();
	}
	
	public List<String> subTabCompleter(final CommandSource source, final List<String> args) throws CommandException {
		List<String> suggest = new ArrayList<String>();
		if (args.size() == 1) {
			suggest.add("world_name");
		} else if (args.size() == 2) {
			for (DimensionType value : this.plugin.getGame().getRegistry().getAllOf(DimensionType.class)) {
				suggest.add(value.getName().toUpperCase());
			}
		} else if (args.size() == 3) {
			for (GeneratorType value : this.plugin.getGame().getRegistry().getAllOf(GeneratorType.class)) {
				if (!value.getId().equalsIgnoreCase("debug_all_block_states") && !value.getId().equalsIgnoreCase("default_1_1")) {
					suggest.add(value.getId().toUpperCase());
				}
			}
		} else if (args.size() == 4) {
			suggest.add("0000000000000000000");
		}
		return suggest;
	}

	public Text help(final CommandSource source) {
		return Text.builder("/" + this.getName() + " <world> [dimension] [generator] [seed]")
					.onClick(TextActions.suggestCommand("/" + this.getName() + " "))
					.color(TextColors.RED)
					.build();
	}
	
	public boolean subExecute(final CommandSource source, final List<String> args) {
		boolean resultat = false;
		
		if (args.size() == 1) {
			resultat = this.command(source, args.get(0), Optional.empty(), Optional.empty(), Optional.empty());
		} else if (args.size() == 2) {
			resultat = this.command(source, args.get(0), Optional.of(args.get(1)), Optional.empty(), Optional.empty());
		} else if (args.size() == 3) {
			resultat = this.command(source, args.get(0), Optional.of(args.get(1)), Optional.of(args.get(2)), Optional.empty());
		} else if (args.size() == 4) {
			resultat = this.command(source, args.get(0), Optional.of(args.get(1)), Optional.of(args.get(2)), Optional.of(args.get(3)));
		} else {
			source.sendMessage(this.help(source));
		}
		
		return resultat;
	}
	
	private boolean command(final CommandSource player, String name,  Optional<String> dimension_name, 
																			Optional<String> generator_name,
																			Optional<String> seed_name) {
		// Si le monde n'existe pas
		if (!this.plugin.getEServer().getWorld(name).isPresent()) {
			Optional<DimensionType> dimension = Optional.empty();
			Optional<GeneratorType> generator = Optional.empty();
			Optional<Long> seed  = Optional.empty();
			
			// Si le type de la dimension est présent
			if (dimension_name.isPresent()) {
				dimension = this.plugin.getGame().getRegistry().getType(DimensionType.class, dimension_name.get());
				// Si le type de la dimension est correct
				if (dimension.isPresent()) {
					
					// Si le type de génération est présent
					if (generator_name.isPresent()) {
						generator = this.plugin.getGame().getRegistry().getType(GeneratorType.class, generator_name.get());
						// Si le type de la génération est correct
						if (generator.isPresent()) {
							
							// Si le seed est présent
							if (seed_name.isPresent()) {
								try {
									seed = Optional.of(Long.parseLong(seed_name.get()));
									return this.commandCreate(player, name, dimension, generator, seed);
								// Le seed est incorrect
								} catch(NumberFormatException e) {
									EWMessages.CREATE_ERROR_SEED.sender()
										.replace("<seed>", seed_name.get())
										.sendTo(player);
								}
							// Le nom, le type de dimension et le type de génération
							} else {
								return this.commandCreate(player, name, dimension, generator, seed);
							}
							
						// Le type de la génération est incorrect
						} else {
							EWMessages.CREATE_ERROR_GENERATOR.sender()
								.replace("<generator>", generator_name.get())
								.sendTo(player);
						}
					// Le nom et le type de dimension
					} else {
						return this.commandCreate(player, name, dimension, generator, seed);
					}
					
				// Le type de la dimension est incorrect
				} else {
					EWMessages.CREATE_ERROR_DIMENSION.sender()
						.replace("<dimension>", dimension_name.get())
						.sendTo(player);
				}
			// Uniquement le nom
			} else {
				return this.commandCreate(player, name, Optional.empty(), Optional.empty(), Optional.empty());
			}
		// Un monde porte déjà ce nom
		} else {
			EWMessages.CREATE_ERROR_NAME.sender()
				.replace("<world>", name)
				.sendTo(player);
		}
		return false;
	}

	private boolean commandCreate(final CommandSource player, String name,  Optional<DimensionType> dimension, 
																			Optional<GeneratorType> generator,
																			Optional<Long> seed) {
		Builder build = WorldArchetype.builder()
										.enabled(true)
										.loadsOnStartup(true)
										.keepsSpawnLoaded(true);
		
		if (dimension.isPresent()) {
			build.dimension(dimension.get());
		}
		
		if (generator.isPresent()) {
			build.generator(generator.get());
		}
		
		if (seed.isPresent()) {
			build.seed(seed.get());
		}

		
		WorldArchetype world = build.build(name, name);
		try {
			this.plugin.getEServer().loadWorld(this.plugin.getEServer().createWorldProperties(name, world));
			EWMessages.CREATE_PLAYER.sender()
				.replace("<world>", () -> this.getButtonPosition(world))
				.sendTo(player);
		} catch (IOException e) {
			
		}		
		return true;
	}
	
	public Text getButtonPosition(final WorldArchetype world){
		return EWMessages.CREATE_WORLD.getFormat()
					.toText("<world>", world.getName()).toBuilder()
					.onHover(TextActions.showText(EWMessages.CREATE_WORLD_HOVER.getFormat().toText(
								"<dimension>", world.getDimensionType().getName().toUpperCase(),
								"<generator>", world.getGeneratorType().getName().toUpperCase(),
								"<seed>", String.valueOf(world.getSeed()))))
					.build();
	}
}
