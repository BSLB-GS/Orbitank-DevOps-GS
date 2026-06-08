## How To
## SE OS COMANDOS DOCKER NÃO ESTIVEREM FUNCIONANDO COLOQUE "sudo" ANTES DE CADA COMANDO!!
## 1. clonando a aplicacao
```bash
git clone https://github.com/BSLB-GS/Orbitank-DevOps-GS.git
cd Orbitank
```

## 2. criar .env
```bash
cat << 'EOF' > .env
INFORMAÇOES DO .env NO PDF DA ENTREGA
EOF
```

## 3. rodar container
```bash
docker compose up -d
```

## 4. mostrar logs
```bash
docker compose logs
```

## link do swagger
http://191.238.209.110:8080/swagger-ui/index.html#

## 5. entrar no container da aplicacao
```bash
docker container exec -it app-rm565049 sh

whoami

pwd

ls -l

exit
```

## 6. entrar no container do banco
```bash
docker container exec -it db-rm565049 bash

whoami

pwd

ls -l

exit
```

## 7. entrar no SQL plus
```bash
docker container exec -it db-rm565049 sqlplus rm565049/030705@//localhost:1521/XEPDB1

SELECT * FROM tb_operational_user;

SELECT * FROM tb_audit_log;
```




# 🌕 Orbitank API
...

---
## 📖 Descrição da Solução
O **Orbitank** é um sistema avançado de gestão e telemetria para Estações Lunares. A API fornece uma infraestrutura robusta para o monitoramento em tempo real de recursos vitais (Oxigênio, Hidrogênio, Água, Energia), gerenciamento de missões espaciais, controle de robôs e sensores IoT, além de comunicação de alertas críticos via WebSockets.

---
## 🔗 Links do projeto

Acesse a documentação interativa e realize testes diretamente pelo navegador:

Render
```bash
https://orbitank-javaadvanced-gs.onrender.com/swagger-ui/index.html
```
Swagger
```bash
http://localhost:8080/swagger-ui/index.html#
```
Documentação
```bash
http://localhost:8080/api-docs
```
Youtube
```bash
http://localhost:8080/h2-console
```

---
## 🛠️ Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3.x**
* **Spring Security & JWT** (Autenticação e Autorização)
* **Spring Data JPA & Hibernate** (Persistência e Modelagem Avançada)
* **Spring HATEOAS** (Navegabilidade de API)
* **Spring WebSockets** (Comunicação em Tempo Real)
* **Spring Mail** (Recuperação de Senha)
* **Banco de Dados:** Oracle DB (Produção) / H2 Database (Testes locais)
* **Java Records** (Estruturação imutável de DTOs)
* **Swagger/OpenAPI** (Documentação)
---

## 🧠 Modelagem Avançada Utilizada

Neste projeto, aplicamos conceitos avançados de persistência com **JPA/Hibernate** para garantir uma arquitetura robusta e escalável, destacando-se:

* **`@Embedded` e `@Embeddable`:** Utilizados para agrupar atributos relacionados em classes de valor (Value Objects), melhorando a coesão do modelo de domínio (ex: métricas de telemetria agrupadas).
* **`@IdClass` / Chaves Compostas:** Implementação de identificadores compostos onde regras de negócio exigem unicidade baseada em mais de um atributo.
* **DTOs com Java Records:** Substituição de classes tradicionais por `records` do Java para garantir imutabilidade e tráfego de dados mais leve e seguro na camada de visualização.

---

## 🧪 Guias de Teste e Validação

### 1. Como testar Login e JWT
1. Realize uma requisição **POST** para `/auth/login` enviando `email` e `password` no corpo (JSON).
2. A API retornará um token JWT na resposta.
3. Copie este token e, nas requisições seguintes, adicione-o no Header (Cabeçalho) da requisição da seguinte forma:
   * **Key:** `Authorization`
   * **Value:** `Bearer SEU_TOKEN_AQUI`

### 2. Como testar a Recuperação de Senha
1. Realize um **POST** em `/auth/forgot-password` com o seu e-mail.
2. Verifique a caixa de entrada do e-mail informado (um código de validação real será enviado via SMTP).
3. Faça um **POST** em `/auth/verify-reset-code` informando o e-mail e o código recebido.
4. Finalize com um **PATCH** em `/auth/reset-password`, enviando o e-mail, o código validado e a nova senha desejada.

### 3. Como testar WebSockets (Tempo Real)
1. Utilize um cliente WebSocket (como Postman v10+ ou Insomnia).
2. Conecte-se à URL da aplicação via protocolo WS: `ws://URL_DO_RENDER/ws` (ou `ws://localhost:8080/ws` localmente).
3. Inscreva-se no tópico correspondente (ex: `/topic/telemetry`) para começar a escutar as atualizações em tempo real disparadas pela API.

### 4. Como testar a Rota de Telemetria IoT

A rota `/iot/telemetry` foi desenhada para receber disparos automatizados de hardwares (como o ESP32). Para simular via Swagger ou Postman:
1. Faça um **POST** na rota `/iot/telemetry`.
2. Envie o seguinte JSON simulando um pacote de dados de hardware:
\`\`\`json
{
  "deviceId": "ESP32-STATION-01",
  "stationCode": 1001,
  "timestamp": "2026-06-04T12:00:00Z",
  "metrics": {
    "oxygenLevelPercent": 82.5,
    "temperatureCelsius": -40.2
  },
  "alertActive": false
}
\`\`\`
3. Verifique o retorno `201 Created` e observe se a conexão WebSocket (se estiver ativa) recebeu o broadcast dessa leitura.

---

## 🎬 Apresentação do Projeto

* **Vídeo Pitch (Apresentação Comercial):** [Clique aqui para assistir no YouTube](https://youtube.com/...)
* **Vídeo Demonstração (API/Software em funcionamento):** [Clique aqui para assistir no YouTube](https://youtube.com/...)

## Cronograma (ARRUMAR)

# 📅 Cronograma de Desenvolvimento

| Atividade | Responsável | Data | Status |
| :--- | :--- | :--- | :--- |
| Inicialização do projeto Spring Boot e configuração do banco H2 | Manuelalacerda | 27/05 | Concluído |
| Criação das entidades base, enums, repositórios e controllers | Manuelalacerda | 27/05 | Concluído |
| Padronização de entidades com Lombok e @Builder | Manuelalacerda | 29/05 | Concluído |
| Implementação da camada Service e DTOs (Request/Response) | Manuelalacerda | 29/05 | Concluído |
| Implementação de tratamento global de exceções | Manuelalacerda | 29/05 | Concluído |
| Implementação de regras de negócio (suprimentos e abastecimento) | Manuelalacerda | 30/05 | Concluído |
| Criação de rotas de leitura para o Front-end (alertas e sensores) | Manuelalacerda | 30/05 | Concluído |
| Ajustes de relacionamentos das entidades com a base LunarStation | Manuelalacerda | 30/05 | Concluído |
| Criação do endpoint de Dashboard agregando dados operacionais | Manuelalacerda | 30/05 | Concluído |
| Implementação de registo e autenticação de utilizadores operacionais | Manuelalacerda | 30/05 | Concluído |
| Implementação do processamento completo de telemetria IoT (ESP32) | Manuelalacerda | 31/05 | Concluído |
| Criação de rotas de filtros (missões, robôs e tanques por estação) | Manuelalacerda | 31/05 | Concluído |
| Configuração de conexão com banco de dados Oracle (JDBC) | Manuelalacerda | 01/06 | Concluído |
| Preparação de ficheiros e permissões para deploy no Render | Manuelalacerda | 01/06 | Concluído |
| Implementação de criptografia e início da segurança da API | Manuelalacerda | 02/06 | Concluído |
| Implementação completa de Autenticação e Autorização via JWT | Manuelalacerda | 02/06 | Concluído |
| Implementação do fluxo real de recuperação de palavra-passe por e-mail | Manuelalacerda | 03/06 | Concluído |
| Implementação de comunicação em tempo real via WebSockets | Manuelalacerda | 03/06 | Concluído |
| Configuração de CORS e respostas de erro (GlobalExceptionHandler) | Manuelalacerda | 03/06 | Concluído |
| Implementação de HATEOAS na entidade LunarStation | Manuelalacerda | 03/06 | Concluído |
| Implementação de JPA Avançada (@Embeddable, @IdClass, @Embedded) | Manuelalacerda | 03/06 | Concluído |
| Refatorização: conversão de DTOs clássicos para Java Records | Manuelalacerda | 04/06 | Concluído |
| Adição de ferramentas de otimização (spring-boot-devtools) | Manuelalacerda | 04/06 | Concluído |
| Finalização e revisão completa da documentação da API (README) | Manuelalacerda | 04/06 | Concluído |

---

## 👥 Integrantes do Grupo

<table>
  <tr>
    <td width="130">
      <img src="https://github.com/moisesBarsoti.png" width="120" style="border-radius: 50%;"/>
    </td>
    <td>
      <b>Moisés Barsoti Andrade de Oliveira</b><br/>
      <b>RM:</b> 565049 &nbsp;&nbsp;|&nbsp;&nbsp;<b>Turma:</b> 2TDSPG - FIAP <br/>
    </td>
  </tr>

  <tr>
    <td width="130">
      <img src="https://github.com/sSofia-s.png" width="120" style="border-radius: 50%;"/>
    </td>
    <td>
      <b>Sofia Siqueira Fontes</b><br/>
      <b>RM:</b> 563829 &nbsp;&nbsp;|&nbsp;&nbsp;<b>Turma:</b> 2TDSPG - FIAP <br/>
    </td>
  </tr>

  <tr>
    <td width="130">
      <img src="https://github.com/manuelalacerda.png" width="120" style="border-radius: 50%;"/>
    </td>
    <td>
      <b>Manuela de Lacerda Soares</b><br/>
      <b>RM:</b> 564887 &nbsp;&nbsp;|&nbsp;&nbsp;<b>Turma:</b> 2TDSPG - FIAP <br/>
    </td>
  </tr>

   <tr>
    <td width="130">
      <img src="https://avatars.githubusercontent.com/u/161476897?v=4)" width="120" style="border-radius: 50%;"/>
    </td>
    <td>
      <b>Lucas Abud Berbel </b><br/>
      <b>RM:</b> 557957 &nbsp;&nbsp;|&nbsp;&nbsp;<b>Turma:</b> 2TDSPG - FIAP <br/>
    </td>
  </tr>
</table>

---

## 🚀 Como Executar o Projeto Localmente

1. **Clone o repositório:**
   \`\`\`bash
   git clone https://github.com/seu-usuario/orbitank-api.git
   \`\`\`
2. **Navegue até a pasta do projeto:**
   \`\`\`bash
   cd orbitank-api
   \`\`\`
3. **Configure as variáveis de ambiente** mencionadas acima.
4. **Compile e execute com o Maven:**
   \`\`\`bash
   ./mvnw spring-boot:run
   \`\`\`
5. A API estará disponível em `http://localhost:8080`.
   
---

## ⚙️ Variáveis de Ambiente

Para rodar o projeto localmente, você precisará configurar as seguintes variáveis de ambiente no seu arquivo `application.properties` ou na sua IDE:

\`\`\`properties
### Conexão com o Banco de Dados (Oracle)
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@//host:porta/servico
SPRING_DATASOURCE_USERNAME=seu_usuario
SPRING_DATASOURCE_PASSWORD=sua_senha

### Configurações JWT
JWT_SECRET=sua_chave_secreta_super_segura
JWT_EXPIRATION=86400000

### Configurações de E-mail (Se aplicável)
SPRING_MAIL_USERNAME=seu_email@gmail.com
SPRING_MAIL_PASSWORD=sua_senha_de_app
\`\`\`

---

##📍 Endpoints da API (Orbitank)
---

## 📦 Supply Requests (Pedidos de Suprimentos)
Gerenciamento das solicitações de recursos (água, oxigênio, energia) feitas pelas estações.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/supply-requests` | Lista todos os pedidos de suprimentos |
| POST | `/supply-requests` | Cria um novo pedido de suprimento |
| GET | `/supply-requests/{id}` | Busca os detalhes de um pedido específico |
| PUT | `/supply-requests/{id}` | Atualiza a quantidade ou prioridade do pedido |
| DELETE | `/supply-requests/{id}` | Cancela ou remove um pedido do sistema |
| POST | `/supply-requests/{id}/analyze` | Analisa a viabilidade e urgência de um pedido |

### Exemplo de Pedido de Suprimento (POST `/supply-requests`)
```json
{
  "stationId": 1,
  "resourceType": "OXYGEN",
  "requestedQuantity": 1500.0,
  "priority": "HIGH",
  "justification": "Nível crítico no tanque principal do Módulo 2"
}
```
---

## 🌕 Lunar Stations (Estações Lunares)
Gerenciamento da infraestrutura principal, painéis de controle e agregações de recursos.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/stations` | Lista todas as estações lunares |
| POST | `/stations` | Cadastra uma nova Estação Lunar |
| GET | `/stations/{id}` | Busca detalhes de uma estação (Retorna links HATEOAS) |
| PUT | `/stations/{id}` | Atualiza informações da estação |
| DELETE | `/stations/{id}` | Desativa ou remove uma estação do sistema |
| GET | `/stations/code/{stationCode}` | Busca uma estação pelo seu código identificador único |
| GET | `/stations/{id}/dashboard` | Retorna o painel consolidado de métricas da estação |
| GET | `/stations/{id}/tanks` | Lista todos os tanques de recursos associados |
| GET | `/stations/{id}/sensors/latest-readings` | Retorna a última leitura de todos os sensores |
| GET | `/stations/{id}/robots` | Lista os robôs alocados na estação |
| GET | `/stations/{id}/alerts` | Lista todos os alertas ativos da estação |
| GET | `/stations/{id}/alerts/critical` | Filtra e retorna apenas os alertas críticos |

### Exemplo de Cadastro de Estação (POST `/stations`)
```json
{
  "stationCode": 1001,
  "name": "Estação Alpha",
  "location": "Cratera Copérnico",
  "status": "ONLINE"
}
```
---

## ⚙️ Station Configurations (Configurações da Estação)
Gerenciamento de parâmetros globais, limites operacionais e protocolos de segurança de cada estação lunar.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/station-configurations` | Lista todas as configurações cadastradas |
| POST | `/station-configurations` | Cria uma nova configuração para uma estação |
| GET | `/station-configurations/{id}` | Busca os detalhes de uma configuração específica |
| PUT | `/station-configurations/{id}` | Atualiza os parâmetros, limites ou status de emergência |
| DELETE | `/station-configurations/{id}` | Remove uma configuração do sistema |

### Exemplo de Configuração (POST `/station-configurations`)
```json
{
  "stationId": 1,
  "maxTemperatureCelsius": -10.5,
  "minOxygenPercent": 75.0,
  "emergencyProtocolEnabled": true,
  "telemetryIntervalSeconds": 60
}
```
---

## 🌡️ Sensors (Gestão de Sensores)
Cadastro físico e gerenciamento do hardware de monitoramento instalado nas estações lunares.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/sensors` | Lista todos os sensores cadastrados |
| POST | `/sensors` | Cadastra um novo sensor na infraestrutura |
| GET | `/sensors/{id}` | Busca os detalhes técnicos e o status de um sensor específico |
| PUT | `/sensors/{id}` | Atualiza informações do sensor (ex: nome, modelo, status) |
| DELETE | `/sensors/{id}` | Remove ou inativa um sensor do sistema |

### Exemplo de Cadastro de Sensor (POST `/sensors`)
```json
{
  "sensorName": "Sensor de Pressão Atmosférica - Módulo 2",
  "sensorType": "PRESSURE",
  "hardwareModel": "ST-9000X",
  "status": "ACTIVE",
  "stationId": 1
}
```
---

## 📊 Sensor Readings (Leituras de Sensores)
Armazenamento e consulta do histórico de dados brutos capturados pelos sensores da estação.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/sensor-readings` | Lista o histórico de leituras registradas |
| POST | `/sensor-readings` | Registra uma nova leitura de sensor manualmente |
| GET | `/sensor-readings/{id}` | Busca os detalhes de uma leitura específica |
| PUT | `/sensor-readings/{id}` | Atualiza ou corrige os dados de um registro de leitura |
| DELETE | `/sensor-readings/{id}` | Remove um registro de leitura do histórico |

### Exemplo de Registro de Leitura (POST `/sensor-readings`)
```json
{
  "sensorId": 1,
  "readingValue": -45.2,
  "unit": "CELSIUS",
  "timestamp": "2026-06-04T13:40:00"
}
```
---

## 💧 Resource Tanks (Tanques de Recursos)
Gerenciamento do armazenamento, capacidade e níveis atuais dos recursos vitais nas estações lunares.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/resource-tanks` | Lista todos os tanques de recursos cadastrados |
| POST | `/resource-tanks` | Registra um novo tanque de armazenamento na estação |
| GET | `/resource-tanks/{id}` | Busca os detalhes e o nível atual de um tanque específico |
| PUT | `/resource-tanks/{id}` | Atualiza a capacidade máxima ou a quantidade armazenada do tanque |
| DELETE | `/resource-tanks/{id}` | Remove um tanque do sistema |

### Exemplo de Cadastro de Tanque (POST `/resource-tanks`)
```json
{
  "resourceType": "HYDROGEN",
  "capacity": 5000.0,
  "currentQuantity": 3500.5,
  "unit": "LITERS",
  "stationId": 1
}
```
---

## ⛽ Refuel Orders (Ordens de Abastecimento)
Gerenciamento das ordens de serviço e logística para o reabastecimento físico de tanques e missões espaciais.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/refuel-orders` | Lista todas as ordens de abastecimento |
| POST | `/refuel-orders` | Cria uma nova ordem de abastecimento manual |
| GET | `/refuel-orders/{id}` | Busca os detalhes e o status de uma ordem específica |
| PUT | `/refuel-orders/{id}` | Atualiza o status logístico da ordem (ex: Em trânsito, Concluído) |
| DELETE | `/refuel-orders/{id}` | Cancela ou remove uma ordem do sistema |
| POST | `/refuel-orders/from-supply-request/{id}` | Gera uma ordem de abastecimento automaticamente a partir de um pedido de suprimento aprovado |

### Exemplo de Ordem de Abastecimento (POST `/refuel-orders`)
```json
{
  "stationId": 1,
  "resourceType": "HYDROGEN",
  "quantity": 1500.0,
  "scheduledDate": "2026-06-10T10:00:00",
  "status": "SCHEDULED",
  "assignedRobotId": 3
}
```
---

## 🚨 Operational Alerts (Alertas Operacionais)
Gerenciamento de notificações preventivas, incidentes e eventos críticos ocorridos na infraestrutura espacial.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/operational-alerts` | Lista o histórico de todos os alertas registrados |
| POST | `/operational-alerts` | Registra manualmente um novo alerta no sistema |
| GET | `/operational-alerts/{id}` | Busca os detalhes completos de um alerta específico |
| PUT | `/operational-alerts/{id}` | Atualiza os dados ou o status de um alerta (ex: em análise, resolvido) |
| DELETE | `/operational-alerts/{id}` | Remove um registro de alerta do histórico |

### Exemplo de Registro de Alerta (POST `/operational-alerts`)
```json
{
  "stationCode": 1001,
  "alertType": "SYSTEM_FAILURE",
  "alertSeverity": "CRITICAL",
  "alertMessage": "Falha na válvula principal do tanque de oxigênio do Módulo 3.",
  "moduleStatus": "EMERGENCY"
}
```
---

## 🚀 Space Missions (Missões Espaciais)
Agendamento, controle logístico e acompanhamento de status das missões que dependem da infraestrutura das estações lunares.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/missions` | Lista todas as missões espaciais cadastradas |
| POST | `/missions` | Agenda uma nova missão espacial |
| GET | `/missions/awaiting-fuel` | Lista as missões que estão paradas aguardando reabastecimento |
| GET | `/missions/{id}` | Busca os detalhes completos de uma missão específica |
| PUT | `/missions/{id}` | Atualiza o status (ex: EM ANDAMENTO, CONCLUÍDO) ou os dados da missão |
| DELETE | `/missions/{id}` | Cancela ou remove uma missão do cronograma |

### Exemplo de Agendamento de Missão (POST `/missions`)
```json
{
  "missionName": "Exploração da Cratera Tycho",
  "commander": "Andrei de Paiva Gibbini",
  "scheduledDate": "2026-08-15T09:00:00",
  "status": "AWAITING_FUEL",
  "stationId": 1
}
```
---

## 🤖 Mining Robots (Robôs Mineradores)
Gestão da frota de extração de recursos, controle de manutenção e alocação dos robôs autônomos.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/mining-robots` | Lista toda a frota de robôs mineradores cadastrada |
| POST | `/mining-robots` | Registra um novo robô autônomo na base |
| GET | `/mining-robots/{id}` | Busca os detalhes, status e alocação de um robô específico |
| PUT | `/mining-robots/{id}` | Atualiza informações ou o status operacional (ex: MANUTENÇÃO, ATIVO) |
| DELETE | `/mining-robots/{id}` | Aposenta ou remove o registro de um robô do sistema |

### Exemplo de Cadastro de Robô (POST `/mining-robots`)
```json
{
  "robotName": "Miner-X1",
  "model": "Taurus-V",
  "status": "ACTIVE",
  "stationId": 1
}
```
---

## 🛡️ Audit Logs (Logs de Auditoria)
Registros imutáveis de ações críticas realizadas no sistema, garantindo a rastreabilidade operacional e segurança.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/audit-logs` | Lista o histórico de ações e eventos de auditoria do sistema |
| POST | `/audit-logs` | Registra uma nova ação de auditoria (Geralmente gerado automaticamente pela própria API) |
| GET | `/audit-logs/{id}` | Busca os detalhes completos e o contexto de um log específico |
| PUT | `/audit-logs/{id}` | Atualiza informações adicionais de um log (Acesso estritamente restrito) |
| DELETE | `/audit-logs/{id}` | Remove um registro de auditoria (Acesso estritamente restrito) |

### Exemplo de Log de Auditoria (POST `/audit-logs`)
```json
{
  "actionType": "THRESHOLD_UPDATE",
  "entityName": "StationConfiguration",
  "entityId": 1,
  "userId": 5,
  "description": "Limiar de oxigênio crítico alterado de 70% para 75% no Módulo Principal."
}
```
---

## 👥 Operational Users (Equipe Administrativa)
Gerenciamento dos usuários operacionais, níveis de acesso e controle da equipe responsável pelas estações.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/admin/operational-users` | Lista todos os usuários operacionais cadastrados |
| POST | `/admin/operational-users` | Cadastra um novo membro da equipe operacional |
| GET | `/admin/operational-users/{id}` | Busca os dados e permissões de um usuário específico |
| PUT | `/admin/operational-users/{id}` | Atualiza os dados, departamento ou cargo de um usuário |
| DELETE | `/admin/operational-users/{id}` | Remove ou inativa o acesso de um usuário do sistema |

### Exemplo de Cadastro de Usuário Operacional (POST `/admin/operational-users`)
```json
{
  "fullName": "Comandante Shepard",
  "email": "shepard@orbitank.com",
  "role": "ADMIN",
  "department": "Operações",
  "active": true
}
```
---

## 📡 IoT Telemetry (Telemetria IoT)
Endpoint dedicado ao recebimento massivo de pacotes de dados em tempo real, enviados pelos equipamentos e sensores da estação.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| POST | `/iot/telemetry` | Recebe pacotes de telemetria (leitura consolidada de sensores) em alta frequência |

### Exemplo de Envio de Telemetria (POST `/iot/telemetry`)
```json
{
  "deviceId": "SENSOR-HUB-01",
  "stationCode": 1001,
  "timestamp": "2026-06-04T13:52:10Z",
  "metrics": {
    "oxygenLevelPercent": 85.5,
    "waterLevelPercent": 90.0,
    "energyLevelPercent": 75.2,
    "temperatureCelsius": -45.0
  },
  "alertActive": false
}
```
---

## 🔐 Auth (Autenticação)
Controle de acesso seguro, geração de tokens (JWT) e fluxos de recuperação de credenciais para os usuários operacionais.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| POST | `/auth/login` | Autentica um usuário e retorna o token de acesso |
| POST | `/auth/forgot-password` | Solicita o envio de um código de recuperação de senha por e-mail |
| POST | `/auth/verify-reset-code` | Valida o código de segurança recebido pelo usuário |
| PATCH | `/auth/reset-password` | Define uma nova senha de acesso após a validação do código |

### Exemplo de Autenticação (POST `/auth/login`)
```json
{
  "email": "admin@orbitank.com",
  "password": "SenhaSuperSegura123!"
}
```
---




