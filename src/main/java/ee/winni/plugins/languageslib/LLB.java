package ee.winni.plugins.languageslib;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.HashMap;

public class LLB extends JavaPlugin implements LLBInterface{

    public static FileConfiguration lang_data;
    public static File lang_data_;
    public static final HashMap<String,Configuration> messages = new HashMap<String, Configuration>();

    @Override
    public void onEnable() {
        super.onEnable();

        saveDefaultConfig();
        lang_data_ = new File(getConfig().getString("data"));

        for(File m : getDataFolder().listFiles()){
            if(m.getName().startsWith("messages_")){
                messages.put(m.getName(),YamlConfiguration.loadConfiguration(m));
            }
        }
    }


    public String getLang(String player) {
        lang_data = YamlConfiguration.loadConfiguration(lang_data_);
        return lang_data.getString("players."+player,"chinese");
    }

    public String getString(String lang, String key) {
        if(messages.containsKey("messaegs_"+lang+".yml")){
            try {
                return messages.get("messaegs_" + lang + ".yml").getString("key");

            }catch (Exception e){
                return getString("chinese",key);
            }
        }
        return getString("chinese",key);
    }

    public String getPlayerString(String player, String key) {
        return getString(getLang(player),key);
    }
}
