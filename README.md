EWN-Puzzle/
│
├── pom.xml                     <-- Maven build configuration file
│
├── src/
│   ├── main/
│   │   ├── java/               <-- Your main Java source files
│   │   │   └── ewnpuzzle/
│   │   │       ├── GameMain.java
│   │   │       ├── GameLoader.java
│   │   │       ├── GameState.java
│   │   │       ├── Player.java
│   │   │       ├── HumanPlayer.java
│   │   │       ├── RandomPlayer.java
│   │   │       ├── AIPlayer.java
│   │   │       └── Move.java
│   │   │
│   │   └── resources/          <-- Non-code resources (input/output files)
│   │       ├── levels/
│   │       │   ├── level1.txt
│   │       │   ├── level2.txt
│   │       │   ├── level3.txt
│   │       │   └── level4.txt
│   │       └── output/
│   │           └── moves.txt
│   │
│   └── test/
│       └── java/               <-- Unit tests (optional)
│           └── ewnpuzzle/
│               └── GameStateTest.java
│
└── target/                     <-- (Auto-generated when you build)
    ├── classes/
    └── EWN-Puzzle-1.0-SNAPSHOT.jar
