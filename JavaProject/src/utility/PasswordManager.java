package utility;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Classe che si occupa di gestire i <it>segreti</it>. In particolare
 * utilizza un file chiamato system.properties nella cartella ./config
 * per ottenere le informazioni necessarie alla connessione al database
 * e per il sistema di notifica.
 *
 * La classe è implementata come singleton, così da assicurarne l'unicità.
 * La classe è anche Thread-Safe.
 * 
 * @author Mario Calcagno
 */
public class PasswordManager {
    /**
     * L'istanza di PasswordManager
     *
     */
    private static PasswordManager instance;
    /**
     * Le proprietà ottenute dal file
     *
     */
    private final Properties props;

    /**
     * Costruttore privato della classe
     *
     */
    private PasswordManager() {
	this.props = new Properties();
	try(FileInputStream fs =new FileInputStream("config/system.properties")){
	    props.load(fs);
	} catch (IOException e) {
	    throw new RuntimeException("Failed to load system properties", e);
	}
    }

    /**
     * Metodo statico che permette di ottenere l'istanza di
     * PasswordManager
     *
     */
    public static  PasswordManager getInstance() {
	if(instance == null){
	    synchronized (PasswordManager.class) {
		if(instance == null)
		    instance = new PasswordManager();
	    }
	}
	return instance;
    }

    /**
     * Metodo thread-safe per ottenere la proprietà richiesta
     *
     * @param Il nome della proprietà
     * @return Il valore della proprietà
     */
    public synchronized String get(String key) {
	return props.getProperty(key);
    }
}

