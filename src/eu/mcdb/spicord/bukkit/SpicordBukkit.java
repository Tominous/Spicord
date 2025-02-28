/*
 * Copyright (C) 2019  OopsieWoopsie
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package eu.mcdb.spicord.bukkit;

import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;
import eu.mcdb.spicord.Spicord;
import eu.mcdb.spicord.SpicordLoader;
import eu.mcdb.spicord.SpicordCommand;
import eu.mcdb.universal.MCDB;
import eu.mcdb.util.ServerType;

public class SpicordBukkit extends JavaPlugin {

    private static SpicordBukkit instance;
    private SpicordLoader loader;

    @Override
    public void onEnable() {
        instance = this;
        this.loader = new SpicordLoader(getLogger(), getClass().getClassLoader(), ServerType.BUKKIT);

        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> loader.load(), 200);
        MCDB.registerCommand(this, new SpicordCommand());
    }

    @Override
    public void onDisable() {
        if (loader != null)
            loader.disable();

        this.loader = null;
        instance = null;
    }

    /**
     * Gets the Spicord instance
     * 
     * @deprecated As of snapshot 2.0.0, use {@link Spicord#getInstance()} instead.
     * @return the Spicord instance
     */
    @Deprecated
    public Spicord getSpicord() {
        return Spicord.getInstance();
    }

    public static SpicordBukkit getInstance() {
        return instance;
    }

    @Override
    public File getFile() {
        return super.getFile();
    }
}
