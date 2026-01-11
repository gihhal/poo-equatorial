import dao.AtendimentoDAO;
import dao.ClienteDAO;
import dao.ProtocoloDAO;
import dao.impl.ClienteDAOImpl;
import factory.DAOFactory;

public class TesteService {

    public static void main(String[] args) {
        DAOFactory factory = new DAOFactory();

        // Design Pattern: Fly weight
        // cria-se referência para objetos e então chamadas de funções
        // repetidas para evitar novas instâncias do mesmo tipo alocadas
        // na memória
        ClienteDAO cliService = factory.getClienteDAO();
        ProtocoloDAO protoService = factory.getProtocoloDAO();
        AtendimentoDAO atendimService = factory.getAtendimentoDAO();

        // teste para exibir os clientes
        System.out.println("Listar clientes");

        cliService.listarTodos().forEach(c ->
                System.out.println(c.getNome())
        );

        // teste para exibir os protocolos
        System.out.println("Listar protocolos");

        protoService.listarTodos().forEach(p ->
                System.out.println("Protocolo: " + p.getId())
        );

        // teste para exibir os atendimentos
        System.out.println("Listar atendimentos");

        atendimService.listarTodos().forEach(a ->
                System.out.println(a.getId())
        );

    }
}
