classDiagram

class Agencia {
    +int id
    +String nome
}

class Equipe {
    +int id
    +String nome
    +int id_agencia
}

class Cliente {
    +int id
    +String nome
    +String cpf
    +String contato
    +String endereco
}

class Tecnico {
    +int id
    +String nome
    +String cpf
    +String contato
    +int id_agencia
    +int id_equipe
}

class Atendimento {
    +int id
    +LocalDate data_inicio
    +LocalDate data_prazo
    +int id_agencia
    +int id_tecnico
    +int id_cliente
}

class Protocolo {
    +int id
    +LocalDate data_abertura
    +LocalDate data_prevista
    +LocalDate data_encerramento
    +String status
    +int id_cliente
    +int id_atendimento
    +int id_equipe
}

%% Relacionamentos

Agencia "1" --> "many" Equipe : possui
Agencia "1" --> "many" Tecnico : gerencia
Agencia "1" --> "many" Atendimento : registra

Equipe "1" --> "many" Tecnico : composta_por
Equipe "1" --> "many" Protocolo : executa

Cliente "1" --> "many" Atendimento : solicita
Cliente "1" --> "many" Protocolo : possui

Tecnico "1" --> "many" Atendimento : realiza

Atendimento "1" --> "1" Protocolo : gera
