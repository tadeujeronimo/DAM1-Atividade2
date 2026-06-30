# DAM1-Atividade2 – MarcianoApp (Android)

Projeto de aplicativo Android para robô marciano em Kotlin para Atividade 2 da disciplina de Desenvolvimento de Aplicações Móveis I.

## Descrição

O app serve de interface ao `🤖 Robô Marciano 👽` implementado na Atividade 1 *(console)*, permitindo ao usuário enviar mensagens ao robô e receber respostas em tempo real.

## Screenshots

<!-- ![App Screenshot](https://github.com/tadeujeronimo/DAM1-Atividade2/blob/main/screenshots.gif) -->
<img src="https://github.com/tadeujeronimo/DAM1-Atividade2/blob/main/screenshots.gif" height="500">

## Telas

- **Tela Principal**: entrada de mensagem e acesso às demais funcionalidades.
- **Tela de Resposta**: exibe a resposta do Robô Marciano.
- **Tela de Operações Matemáticas** *(extra)*: realiza operações básicas e avançadas com o robô.
- **Tela de Histórico** *(extra)*: lista de comandos enviados, clicáveis para reenvio.

## Funcionalidades

- Envio de mensagens ao Robô Marciano com resposta imediata
- Campo de mensagem limpo automaticamente ao retornar à tela principal
- Operações matemáticas: `some`, `subtraia`, `multiplique`, `divida`, `raiz`, `potencia`, `modulo`, `percentual`
- Histórico de conversas clicável

## Estrutura de Arquivos

```
app/src/main/
├── java/com/tadeujeronimo/marcianoapp/
│   ├── Marciano.kt                   # Classes Marciano, Matematico e Premium
│   ├── HistoricoManager.kt           # Persistência em memória do histórico
│   ├── MainActivity.kt               # Tela principal e lógica de Insets
│   ├── RespostaActivity.kt           # Exibição de respostas
│   ├── MatematicaActivity.kt         # Lógica das operações matemáticas
│   ├── RespostaMatematicaActivity.kt # Exibição de respostas matemáticas
│   └── HistoricoActivity.kt          # Listagem de interações
└── res/
    ├── layout/                       # Layouts XML das telas
    ├── drawable/                     # Estilos de botões e backgrounds
    └── values/                       # Definições de cores, temas e strings
```

## Requisitos

- Android Studio Koala ou superior
- **Android SDK 35+** (Android 15)
- Kotlin 2.0+

## Download

[Baixar APK](https://github.com/tadeujeronimo/DAM1-Atividade2//app/build/outputs/apk/debug/app-debug.apk)

## Atividade anterior

- [DAM1-Atividade1](https://github.com/tadeujeronimo/DAM1-Atividade1) — Robô Marciano em Kotlin (console)

## Autor

- **Nome**: Tadeu dos Santos Jerônimo
- **Matrícula**: 2026202194
- **E-mail**: tadeus.jeronimo@gmail.com
- **Instituição**: IF Sudeste/MG
