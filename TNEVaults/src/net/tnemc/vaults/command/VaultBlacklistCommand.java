package net.tnemc.vaults.command;

import com.github.tnerevival.commands.TNECommand;
import net.tnemc.core.TNE;

/**
 * The New Economy Minecraft Server Plugin
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/ or send a letter to
 * Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 * Created by Daniel on 11/18/2017.
 */
public class VaultBlacklistCommand extends TNECommand {
  public VaultBlacklistCommand(TNE plugin) {
    super(plugin);
  }

  @Override
  public String getName() {
    return "blacklist";
  }

  @Override
  public String[] getAliases() {
    return new String[0];
  }

  @Override
  public String getNode() {
    return "tne.vault.blacklist";
  }

  @Override
  public boolean console() {
    return false;
  }
}