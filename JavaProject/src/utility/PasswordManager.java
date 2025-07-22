package utility;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class PasswordManager {
    private static PasswordManager instance;
    private final Properties props;

    private PasswordManager() {
	this.props = new Properties();
	try(FileInputStream fs =new FileInputStream("config/system.properties")){
	    props.load(fs);
	} catch (IOException e) {
	    throw new RuntimeException("Failed to load system properties", e);
	}
    }

    public static  PasswordManager getInstance() {
	if(instance == null){
	    synchronized (PasswordManager.class) {
		if(instance == null)
		    instance = new PasswordManager();
	    }
	}
	return instance;
    }

    public synchronized String get(String key) {
	return props.getProperty(key);
    }
}

