import util.ConnectionManager;

import java.sql.Connection;

public class TesteConection {

    public static void main(String[] args) {

        try {
            Connection conn = ConnectionManager.getConnection();
            System.out.println("Conexão OK: " + conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
