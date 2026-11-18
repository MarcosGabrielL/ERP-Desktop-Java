package Connection;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static Properties props = new Properties();

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input != null) {
                props.load(input);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar arquivo de configuração do banco: " + e.getMessage());
        }
    }

    public static String get(String key) {
        // Fallback caso o arquivo não seja encontrado, mantendo o padrão atual para não quebrar
        return props.getProperty(key);
    }
}