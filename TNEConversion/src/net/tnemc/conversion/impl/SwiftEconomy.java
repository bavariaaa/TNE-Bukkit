package net.tnemc.conversion.impl;

import com.github.tnerevival.core.db.sql.MySQL;
import net.tnemc.conversion.ConversionModule;
import net.tnemc.conversion.Converter;
import net.tnemc.conversion.InvalidDatabaseImport;
import net.tnemc.core.TNE;
import net.tnemc.core.economy.currency.Currency;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

/**
 * The New Economy Minecraft Server Plugin
 * <p>
 * Created by Daniel on 5/28/2018.
 * <p>
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/ or send a letter to
 * Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 * Created by creatorfromhell on 06/30/2017.
 */
public class SwiftEconomy extends Converter {
  @Override
  public String name() {
    return "SwiftEconomy";
  }

  @Override
  public void mysql() throws InvalidDatabaseImport {
    db = new MySQL(conversionManager);

    try(Connection connection = mysqlDB().getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet results = mysqlDB().executeQuery(statement, "SELECT uuid, money FROM SWIFTeco;")) {

      final Currency currency = TNE.manager().currencyManager().get(TNE.instance().defaultWorld);
      while(results.next()) {
        ConversionModule.convertedAdd(results.getString("uuid"),
            TNE.instance().defaultWorld, currency.name(),
            new BigDecimal(results.getDouble("money")));
      }
    } catch(SQLException ignore) {}
  }

  @Override
  public void yaml() throws InvalidDatabaseImport {
    File configFile = new File("plugins/SwiftEconomy/players.yml");
    FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    final Set<String> accounts = config.getKeys(false);
    for(String uuid : accounts) {
      if(uuid.equalsIgnoreCase("mysql")) continue;
      Currency cur = TNE.manager().currencyManager().get(TNE.instance().defaultWorld);
      ConversionModule.convertedAdd(uuid, TNE.instance().defaultWorld, cur.name(), new BigDecimal(config.getString(uuid + ".Money")));
    }
  }
}