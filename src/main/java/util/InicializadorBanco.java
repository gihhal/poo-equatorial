package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Collectors;

public class InicializadorBanco {

    public static void inicializar() {

        try (Connection conn = ConnectionManager.getConnection();
             Statement st = conn.createStatement()) {

            String sql = carregarSchema();

            System.out.println(sql);

            // SQLite aceita múltiplos comandos separados por ;
            st.execute(sql);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar banco de dados", e);
        }
    }

    private static String carregarSchema() {

        InputStream is = InicializadorBanco.class
                .getClassLoader()
                .getResourceAsStream("schema.sql");

        if (is == null) {
            throw new RuntimeException("Arquivo schema.sql não encontrado em resources");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler schema.sql", e);
        }
    }
}
