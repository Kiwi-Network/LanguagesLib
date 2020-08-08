package ee.winni.plugins.languageslib;

public interface LLBInterface {

    String getLang(String player);

    String getString(String lang, String key);

    String getPlayerString(String player, String key);
}
