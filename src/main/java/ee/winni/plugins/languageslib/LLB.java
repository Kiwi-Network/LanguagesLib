package ee.winni.plugins.languageslib;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        if(!new File(getDataFolder(),"messages_chinese.yml").exists()) {
            saveResource("messages_chinese.yml", false);
            saveResource("messages_chineset.yml", false);
            saveResource("messages_english.yml", false);
        }
    }


    public String getLang(String player) {
        lang_data = YamlConfiguration.loadConfiguration(lang_data_);
        return lang_data.getString("players."+player,"chinese");
    }

    public String getString(String lang, String key) {
        if(messages.containsKey("messages_"+lang+".yml")){
            try {
                return messages.get("messages_" + lang + ".yml").getString(key);

            }catch (Exception e){
                if(lang.equals("chinese")){
                    System.out.print("!!!!!! Unfounded Key: " + key + " !!!!!!");
                    return "";
                }
                else
                    return getString("chinese",key);
            }
        }
        if(lang.equals("chinese")){
            System.out.print("We cannot found chinese language file!");
            return "";
        }
        else
            return getString("chinese",key);
    }

    public String getPlayerString(String player, String key) {
        return getString(getLang(player),key);
    }

    public List<String> getListString(String lang, String key) {
        if(messages.containsKey("messages_"+lang+".yml")){
            try {
                return messages.get("messages_" + lang + ".yml").getStringList(key);

            }catch (Exception e){
                if(lang.equals("chinese")) {
                    System.out.print("!!!!!! Unfounded Key: " + key + " !!!!!!");
                    return new ArrayList<String>();
                }
                else
                return getListString("chinese",key);
            }
        }

        if(lang.equals("chinese")) {
            System.out.print("We cannot found chinese language file!");
            return new ArrayList<String>();
        }
        else
            return getListString("chinese",key);
    }

    public List<String> getPlayerListString(String player, String key) {
        return getListString(getLang(player),key);
    }
}
