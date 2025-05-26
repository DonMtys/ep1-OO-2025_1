# Sistema Acadêmico - FCTE

## Descrição do Projeto
Desenvolvimento de um sistema acadêmico para gerenciar alunos, disciplinas, professores, turmas, avaliações e frequência, utilizando os conceitos de orientação a objetos (herança, polimorfismo e encapsulamento) e persistência de dados em arquivos.

O enunciado do trabalho pode ser encontrado aqui:
**Trabalho 1 - Sistema Acadêmico**

## Dados do Aluno
- **Nome completo:** João Victor da Costa Cruz
- **Matrícula:** 202046185
- **Curso:** Engenharia de Software
- **Turma:** [2025.1]

## Instruções para Compilação e Execução

### Compilação:
```bash
javac -d bin src/modelo/*.java src/app/Main.java
```

### Execução:
```bash
java -cp bin app.Main
```

### Estrutura de Pastas:
```
ep1-OO-2025_1/
├── src/
│   ├── app/
│   │   └── Main.java                # Classe principal com interface
│   └── modelo/
│       ├── Aluno.java              # Classe abstrata base
│       ├── AlunoNormal.java        # Herança - aluno regular
│       ├── AlunoEspecial.java      # Herança - aluno especial
│       ├── Disciplina.java         # Dados da disciplina
│       ├── Professor.java          # Dados do professor
│       └── Turma.java              # Associação disciplina-professor
├── bin/                            # Arquivos compilados (.class)
├── dados_sistema.txt               # Arquivo de persistência
└── README.md                       # Este arquivo
```

### Versão do JAVA utilizada:
**Java 21** (compatível com Java 8+)

## Vídeo de Demonstração
[Inserir o link para o vídeo no YouTube/Drive aqui]
*Vídeo demonstrando todas as funcionalidades do sistema (máx. 5 minutos)*

## Prints da Execução
![image](https://github.com/user-attachments/assets/acae40c4-cd60-4ab4-b54b-b4b6b9962838)
![image](https://github.com/user-attachments/assets/9e303027-bbae-4a59-b015-4b83e85eb1db)
![image](https://github.com/user-attachments/assets/432b4409-e1fe-487b-a463-64607e3f3987)
![image](https://github.com/user-attachments/assets/8c383216-9ead-47c6-9534-4f969772dfe5)




### Menu Principal:
```
======================================
 Bem-vindo ao Sistema Acadêmico FCTE 
======================================

MENU PRINCIPAL
1. Modo Aluno
2. Modo Disciplina/Turma
3. Modo Avaliação/Frequência
4. Salvar Dados
0. Sair do Sistema
```

### Cadastro de Aluno:
```
-- Cadastro de Novo Aluno --
Nome completo: Maria Silva
Matrícula: 2024001
Curso: Ciência da Computação
Aluno Normal ou Especial? (N/E): E

----- Aluno Cadastrado com Sucesso! -----
Matrícula: 2024001, Nome: Maria Silva, Curso: Ciência da Computação [Tipo: Especial - Máx: 2 disciplinas]
```

### Relatório de Turma:
```
===== RELATÓRIO DA TURMA =====
Disciplina: Matemática Discreta
Professor: Dr. Carlos Oliveira
Semestre: 2025.1
Alunos matriculados: 2/30
Lista de alunos:
- Maria Silva (2024001) [Especial]
- João Santos (2024002) [Normal]
==============================
```

## Principais Funcionalidades Implementadas

### ✅ Modo Aluno:
- ✅ Cadastro, listagem, edição e exclusão de alunos (Normais e Especiais)
- ✅ Matrícula de alunos em turmas, respeitando vagas e limite de disciplinas
- ✅ Trancamento de disciplinas
- ✅ Validação de matrícula única

### ✅ Modo Disciplina/Turma:
- ✅ Cadastro de disciplinas com código único
- ✅ Cadastro de professores
- ✅ Criação de turmas (disciplina + professor + semestre)
- ✅ Controle de capacidade das turmas

### ✅ Modo Avaliação/Frequência:
- ✅ Lançamento de notas (diferenciado para alunos especiais)
- ✅ Controle de presença
- ✅ Relatórios completos por turma
- ✅ Boletim individual do aluno

### ✅ Persistência e Validações:
- ✅ Persistência de dados em arquivo .txt
- ✅ Carregamento automático na inicialização
- ✅ Salvamento automático ao sair
- ✅ Tratamento de duplicidade de matrículas
- ✅ Validação de limite para alunos especiais (máx. 2 disciplinas)

### ✅ Programação Orientada a Objetos:
- ✅ **Herança:** `Aluno` → `AlunoNormal` / `AlunoEspecial`
- ✅ **Polimorfismo:** Métodos abstratos com implementações específicas
- ✅ **Encapsulamento:** Atributos privados com getters/setters apropriados

## Arquitetura do Sistema

### Conceitos de POO Implementados:
1. **Herança:** Classe abstrata `Aluno` com subclasses `AlunoNormal` e `AlunoEspecial`
2. **Polimorfismo:** Métodos `getTipoAluno()`, `podeMatricularEmNovaDisciplina()`, `recebeNotas()`
3. **Encapsulamento:** Todos os atributos privados com métodos de acesso controlado

### Regras de Negócio:
- **Alunos Especiais:** Limitados a 2 disciplinas simultâneas e não recebem notas
- **Alunos Normais:** Sem limite de disciplinas e recebem avaliações completas
- **Turmas:** Controle de capacidade e verificação de duplicidade de matrícula

## Observações (Extras ou Dificuldades)

### Funcionalidades Extras Implementadas:
- ✅ **Interface amigável** com menus intuitivos e mensagens claras
- ✅ **Validações robustas** para todos os inputs do usuário
- ✅ **Sistema de persistência completo** com backup manual e automático
- ✅ **Relatórios detalhados** com informações abrangentes
- ✅ **Tratamento de erros** para casos especiais

### Principais Desafios:
1. **Implementação da persistência** - Serialização de objetos complexos em formato texto
2. **Validação de regras de negócio** - Especialmente o limite de disciplinas para alunos especiais
3. **Gerenciamento de relacionamentos** - Associações entre alunos, turmas e disciplinas
4. **Interface de usuário** - Criação de menus intuitivos e tratamento de inputs inválidos

### Decisões de Design:
- **Uso de `List<Aluno>` na classe `Turma`** para facilitar operações de adição/remoção
- **Método `equals()` customizado** na classe `Aluno` para comparação por matrícula
- **Separação clara de responsabilidades** entre as classes do modelo e a interface

## Tecnologias Utilizadas
- **Linguagem:** Java 21
- **Paradigma:** Programação Orientada a Objetos
- **Persistência:** Arquivo texto (.txt)
- **Interface:** Terminal/Console
- **Controle de Versão:** Git/GitHub

## Contato
- **GitHub:** https://github.com/DonMtys
- **Repositório:** https://github.com/DonMtys/ep1-OO-2025_1

---
**Sistema Acadêmico FCTE** - Desenvolvido para a disciplina de Programação Orientada a Objetos - 2025.1
