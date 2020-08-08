package ee.winni.plugins.languageslib;

import java.util.List;

public interface LLBInterface {

    String getLang(String player);

    String getString(String lang, String key);

    String getPlayerString(String player, String key);

    List<String> getListString(String lang, String key);

    List<String> getPlayerListString(String player, String key);
}
