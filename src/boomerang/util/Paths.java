package boomerang.util;

import android.os.Environment;

public class Paths {
	
	public static String SERVER_IP = "192.168.43.1";

	public static String apppath = Environment.getExternalStorageDirectory() + "/boom/";

	public static String appSettingsPath = apppath + "settings/";

	public static String appZipSettingsPath = appSettingsPath + "zip/";

	public static String appEncryptSettingsPath = appSettingsPath + "encrypt/";

	public static String appRestroreSettingsPath = appSettingsPath + "inbox/";

	public static String registered_user_file = appSettingsPath
			+ "user_register_file.txt";

	public static String bluetooth_file = appSettingsPath + "bluetooth.txt";

}