package net.tnemc.maria;

import com.github.tnerevival.core.DataManager;
import com.github.tnerevival.core.db.SQLDatabase;

/**
 * The New Economy Minecraft Server Plugin
 * <p>
 * Created by Daniel on 10/25/2018.
 * <p>
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/ or send a letter to
 * Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 * Created by creatorfromhell on 06/30/2017.
 */
public class Maria extends SQLDatabase {
  public Maria(DataManager manager) {
    super(manager);
  }

  @Override
  public String getDriver() {
    return "org.mariadb.jdbc.Driver";
  }

  @Override
  public Boolean dataSource() {
    return false;
  }

  @Override
  public String dataSourceURL() {
    return null;
  }

  @Override
  public String getURL(String file, String host, int port, String database) {
    return "jdbc:mariadb://" + host + ":" + port + "/" + database;
  }
}