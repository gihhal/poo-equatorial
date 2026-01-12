PRAGMA foreign_keys = ON;

-- =========================
-- AGENCIA (1)
-- =========================
INSERT INTO agencia (nome)
VALUES ('Agência Central de Energia');

-- =========================
-- EQUIPE (1)
-- =========================
INSERT INTO equipe (nome, id_agencia)
VALUES ('Equipe Norte', 1);

-- =========================
-- CLIENTES (3)
-- =========================
INSERT INTO cliente (nome, cpf, contato, endereco) VALUES
                                                       ('João da Silva', '11111111111', '11999990001', 'Rua A, 100'),
                                                       ('Maria Oliveira', '22222222222', '11999990002', 'Rua B, 200'),
                                                       ('Carlos Pereira', '33333333333', '11999990003', 'Rua C, 300');

-- =========================
-- TECNICOS (2)
-- =========================
INSERT INTO tecnico (nome, cpf, contato, id_agencia, id_equipe) VALUES
                                                                    ('Pedro Técnico', '44444444444', '11988880001', 1, 1),
                                                                    ('Ana Técnica', '55555555555', '11988880002', 1, 1);

-- =========================
-- ATENDIMENTOS (4)
-- =========================
INSERT INTO atendimento (data_inicio, data_prazo, id_agencia, id_tecnico, id_cliente) VALUES
                                                                                          ('2026-01-10', '2026-01-12', 1, 1, 1),
                                                                                          ('2026-01-11', '2026-01-13', 1, 2, 2),
                                                                                          ('2026-01-12', '2026-01-14', 1, 1, 3),
                                                                                          ('2026-01-13', '2026-01-15', 1, 2, 1);

-- =========================
-- PROTOCOLOS (2)
-- =========================
INSERT INTO protocolo (
    data_abertura,
    data_prevista,
    data_encerramento,
    status,
    id_cliente,
    id_atendimento,
    id_equipe
) VALUES
      ('2026-01-09', '2026-01-12', NULL, 'ABERTO', 1, 1, 1),
      ('2026-01-10', '2026-01-13', NULL, 'EM_ANDAMENTO', 2, 2, 1);
