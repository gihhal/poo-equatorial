package factory;

// SOLID: Interface segregation
// implementação dependem de classes abstratas (interfaces)
import dao.*;

// SOLID: Liskov
// Substituir a classe derivada por uma implementação da classe
// sem desestruturar suas funcionalidades
import dao.impl.*;

// Design Pattern aplicado: Singleton
// motivo: uma classe que gerencia as implementações
// dos contratos de Data Access Object (DAO)
public class DAOFactory {

    public static ClienteDAO getClienteDAO() {
        return new ClienteDAOImpl();
    }

    public static ProtocoloDAO getProtocoloDAO() {
        return new ProtocoloDAOImpl();
    }

    public static AtendimentoDAO getAtendimentoDAO() {
        return new AtendimentoDAOImpl();
    }

    public static EquipeCampoDAO getEquipeDAO() {
        return new EquipeCampoDAOImpl();
    }

}

