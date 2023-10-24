package api.utils;

import java.util.Properties;

public class DataLoader {

    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/api/data.properties");
    }

    //Ensuring that only single object of this class is created
    public static DataLoader getInstance() {
        if (dataLoader == null) {
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getGetPlaylistId() {
        String prop = properties.getProperty("get_playlist_id");
        if (prop != null){
            return prop;
        } else {
            throw new RuntimeException("Property get_playlist_id is not specified in data.properties file");
        }
    }

    public String getUpdatePlaylistId() {
        String prop = properties.getProperty("update_playlist_id");
        if (prop != null){
            return prop;
        } else {
            throw new RuntimeException("Property update_playlist_id is not specified in data.properties file");
        }
    }

    public String getRefreshToken() {
        String prop = properties.getProperty("refresh_token");
        if (prop != null){
            return prop;
        } else {
            throw new RuntimeException("Property refresh_token is not specified in config.properties file");
        }
    }

    public String getGrantType() {
        String prop = properties.getProperty("grant_type");
        if (prop != null){
            return prop;
        } else {
            throw new RuntimeException("Property grant_type is not specified in config.properties file");
        }
    }

    public String getUserId() {
        String prop = properties.getProperty("user_id");
        if (prop != null){
            return prop;
        } else {
            throw new RuntimeException("Property user_id is not specified in config.properties file");
        }
    }
}
