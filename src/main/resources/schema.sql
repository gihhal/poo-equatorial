PRAGMA foreign_keys = ON;

-- =========================
-- AGENCIA
-- =========================
CREATE TABLE IF NOT EXISTS agencia (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL
);

-- =========================
-- EQUIPE
-- =========================
CREATE TABLE IF NOT EXISTS equipe (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    id_agencia INTEGER NOT NULL,
    FOREIGN KEY (id_agencia) REFERENCES agencia(id)
);

-- =========================
-- CLIENTE
-- =========================
CREATE TABLE IF NOT EXISTS cliente (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    cpf TEXT NOT NULL UNIQUE,
    contato TEXT,
    endereco TEXT NOT NULL
);

-- =========================
-- TECNICO
-- =========================
CREATE TABLE IF NOT EXISTS tecnico (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    cpf TEXT NOT NULL UNIQUE,
    contato TEXT,
    id_agencia INTEGER NOT NULL,
    id_equipe INTEGER,
    FOREIGN KEY (id_agencia) REFERENCES agencia(id),
    FOREIGN KEY (id_equipe) REFERENCES equipe(id)
);

-- =========================
-- ATENDIMENTO
-- =========================
CREATE TABLE IF NOT EXISTS atendimento (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    data_inicio DATE NOT NULL,
    data_prazo DATE,
    id_agencia INTEGER NOT NULL,
    id_tecnico INTEGER NOT NULL,
    id_cliente INTEGER NOT NULL,
    FOREIGN KEY (id_agencia) REFERENCES agencia(id),
    FOREIGN KEY (id_tecnico) REFERENCES tecnico(id),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

-- =========================
-- PROTOCOLO
-- =========================
CREATE TABLE IF NOT EXISTS protocolo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    data_abertura DATE NOT NULL,
    data_prevista DATE,
    data_encerramento DATE,
    status TEXT NOT NULL,
    id_cliente INTEGER NOT NULL,
    id_atendimento INTEGER NOT NULL,
    id_equipe INTEGER,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    FOREIGN KEY (id_atendimento) REFERENCES atendimento(id),
    FOREIGN KEY (id_equipe) REFERENCES equipe(id)
);
