CREATE TABLE IF NOT EXISTS cliente (
                                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                                       nome TEXT NOT NULL,
                                       cpf TEXT NOT NULL,
                                       telefone TEXT,
                                       endereco TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS equipe (
                                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                                      nome TEXT NOT NULL,
                                      base TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS protocolo (
                                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                                         data_abertura DATE NOT NULL,
                                         data_prevista DATE,
                                         status TEXT NOT NULL,
                                         id_cliente INTEGER NOT NULL,
                                         id_equipe INTEGER,
                                         FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    FOREIGN KEY (id_equipe) REFERENCES equipe(id)
    );
