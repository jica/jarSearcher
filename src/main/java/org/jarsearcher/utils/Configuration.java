package org.jarsearcher.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jica
 */
public class Configuration {

    private static final String CONFIG_FILENAME = "conf.properties";
    private static final Logger logger = Logger.getLogger(Configuration.class.getName());
    private static final Properties props = new Properties();

    public static final String PROP_DEFAULT_PATH = "DEFAULT_PATH";
    public static final String PROP_DEFAULT_QUERY = "DEFAULT_QUERY";
    public static final String PROP_NEXUS_URL = "NEXUS_URL";

    private static final String ERROR_OPENING_FILE = "There was an error opening configuration file: " + CONFIG_FILENAME;

    /**
     * Open configuration file (conf.properties) and return key property
     *
     * @param key name of the property
     * @return property value. If there is an error opening the file it returns
     * "There was an error opening configuration file: ". If there is no
     * property with key name, it try to load the file again, but if the error
     * persists, it returns null
     */
    public static String getProperty(String key) {
        if (props.get(key) == null) {
            try {
                loadProperties();
            } catch (IOException e) {
                return ERROR_OPENING_FILE;
            }
        }
        return (String) props.get(key);
    }

    private static void loadProperties() throws IOException {
        props.clear();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream configIS = loader.getResourceAsStream(CONFIG_FILENAME);
        try {
            props.load(configIS);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

}
