import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;

import net.minecraft.client.main.Main;

public class Start
{
    public static void main(String[] args)
    {

        File minecraft_loc = new File(System.getenv("appdata"), ".minecraft");
        File assets_loc = new File(minecraft_loc, "assets");

        Main.main(concat(new String[] {
                "--version", "mcp",
                "--accessToken", "0",
                "--gameDir", minecraft_loc.exists() ? minecraft_loc.toString() : "",
                "--assetsDir", assets_loc.exists() ? assets_loc.toString() : "assets",
                "--assetIndex", "1.8",
                "--userProperties", "{}"}, args));
    }

    public static <T> T[] concat(T[] first, T[] second)
    {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
