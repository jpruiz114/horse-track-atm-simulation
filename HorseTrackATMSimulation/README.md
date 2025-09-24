# Horse Track ATM (Automated Teller Machine) Simulation

A Java-based console application that simulates an ATM system for a horse racing track. Users can place bets on horses, view race results, manage cash inventory, and perform administrative operations.

## Overview

This application simulates a betting ATM that allows users to:
- Place bets on racing horses with different odds
- View current cash inventory and horse status
- Set race winners and calculate payouts
- Manage ATM cash inventory through restocking
- Handle various error conditions gracefully

The system includes 7 predefined horses with different odds, manages cash denominations ($1, $5, $10, $20, $100), and provides a command-line interface for all interactions.

## Technologies Selected

- **Java**: OpenJDK 17
- **Build Tool**: Gradle with Kotlin DSL
- **Testing**: JUnit 5
- **Code Coverage**: JaCoCo
- **Documentation**: Javadoc

## Class Diagram

```mermaid
classDiagram
    %% Classes under com.vikingcloud.horsetrack.command

    class Command {
        <<interface>>
        +void execute(AutomatedTellerMachine atm)
    }

    class BetCommand {
        +execute(AutomatedTellerMachine atm)
        -int horseNumber
        -int amount
    }

    class InvalidBetCommand {
        +execute(AutomatedTellerMachine atm)
        -String amount
    }

    class InvalidCommand {
        +execute(AutomatedTellerMachine atm)
        -String rawInput
    }

    class InvalidHorseNumberCommand {
        +execute(AutomatedTellerMachine atm)
        -int horseNumber
    }

    class QuitCommand {
        +execute(AutomatedTellerMachine atm)
    }

    class RestockCommand {
        +execute(AutomatedTellerMachine atm)
    }

    class SetWinnerCommand {
        +execute(AutomatedTellerMachine atm)
        -int horseNumber
    }
    
    %% Classes under com.vikingcloud.horsetrack.constants
    
    class CommandConstants {
        <<static>>
        +String RESTOCK
        +String QUIT
        +String SET_WINNING_HORSE
    }
    
    class DenominationConstants {
        <<static>>
        +int DENOMINATION_1
        +int DENOMINATION_5
        +int DENOMINATION_10
        +int DENOMINATION_20
        +int DENOMINATION_100
        +int[] AVAILABLE_DENOMINATIONS
        +Map~Integer, Integer~ DEFAULT_RESTOCK_COUNTS
    }
    
    %% Classes under com.vikingcloud.horsetrack.model

    class Horse {
        -int number
        -String name
        -int odds
        -boolean isWinner
        +getNumber()
        +getName()
        +getOdds()
        +isWinner()
        +setWinner(boolean)
    }

    class CashInventory {
        -Map~Integer, Integer~ bills
        +dispenseBills(Map)
        +displayInventory()
        +getBillsForPayout(int)
        +restock()
    }
    
    %% Classes under com.vikingcloud.horsetrack.parser

    class CommandParser {
        +static Command parse(String input, AutomatedTellerMachine atm)
    }
    
    %% Classes under com.vikingcloud.horsetrack.service

    class HorseFactory {
        +static List~Horse~ createInitialHorseList()
    }
    
    %% Classes under com.vikingcloud.horsetrack

    class AutomatedTellerMachine {
        -CashInventory inventory
        -List~Horse~ horses
        +AutomatedTellerMachine()
        +void displayState()
        +Horse getHorseByNumber(int number)
        +void processBet(int horseNumber, int amount)
        +void restock()
        +void setWinningHorse(int number)
        -void initializeHorses()
    }

    class Main
    
    %% Dependencies between classes and interfaces
    
    Command <|-- BetCommand : implements
    Command <|-- InvalidBetCommand : implements
    Command <|-- InvalidCommand : implements
    Command <|-- InvalidHorseNumberCommand : implements
    Command <|-- QuitCommand : implements
    Command <|-- RestockCommand : implements
    Command <|-- SetWinnerCommand : implements
    
    BetCommand --> AutomatedTellerMachine : uses
    InvalidBetCommand --> AutomatedTellerMachine : uses
    InvalidCommand --> AutomatedTellerMachine : uses
    InvalidHorseNumberCommand --> AutomatedTellerMachine : uses
    QuitCommand --> AutomatedTellerMachine : uses
    RestockCommand --> AutomatedTellerMachine : uses
    SetWinnerCommand --> AutomatedTellerMachine : uses
    
    CashInventory --> DenominationConstants : uses
    
    CommandParser --> AutomatedTellerMachine : uses
    CommandParser --> Horse : validates
    CommandParser --> Command : returns
    
    %% CommandParser uses these specific commands
    CommandParser --> BetCommand : creates
    CommandParser --> InvalidBetCommand : creates
    CommandParser --> InvalidCommand : creates
    CommandParser --> InvalidHorseNumberCommand : creates
    CommandParser --> QuitCommand : creates
    CommandParser --> RestockCommand : creates
    CommandParser --> SetWinnerCommand : creates
    
    CommandParser --> CommandConstants : uses
    
    HorseFactory --> Horse : creates
    
    AutomatedTellerMachine --> CashInventory : has
    AutomatedTellerMachine --> Horse : manages
    AutomatedTellerMachine --> HorseFactory : uses
    AutomatedTellerMachine --> DenominationConstants : uses
    
    Main --> AutomatedTellerMachine : uses
    Main --> CommandParser : uses
    Main --> Command : executes
    Main --> QuitCommand : checks instance
```

## Features

### Core Functionality
- **Horse Betting**: Place bets on any of 7 horses with varying odds (3-10x)
- **Payout Calculation**: Automatic calculation of winnings based on horse odds
- **Cash Management**: ATM handles multiple denominations with intelligent dispensing
- **Race Management**: Set winning horses and view race results
- **Inventory Tracking**: Real-time display of cash denominations and counts

### Available Horses
1. **That Darn Gray Cat** (5:1 odds) - Default winner
2. **Fort Utopia** (10:1 odds)
3. **Count Sheep** (9:1 odds)
4. **Ms Traitour** (4:1 odds)
5. **Real Princess** (3:1 odds)
6. **Pa Kettle** (5:1 odds)
7. **Gin Stinger** (6:1 odds)

### Cash Denominations
- $100, $20, $10, $5, $1 bills
- Default inventory: 10 bills of each denomination
- Intelligent dispensing algorithm (largest bills first)

## Usage

### Running the Application

```bash
# Navigate to project directory
cd HorseTrackATMSimulation

# Run with Gradle
./gradlew run

# Or compile and run manually
./gradlew build
java -cp build/classes/java/main com.vikingcloud.horsetrack.Main
```

### Available Commands

| Command | Format | Description | Example |
|---------|--------|-------------|---------|
| **Bet** | `<horse_number> <amount>` | Place bet on specified horse | `1 50` |
| **Set Winner** | `w <horse_number>` | Set the winning horse | `w 3` |
| **Restock** | `r` | Restock ATM with default cash amounts | `r` |
| **Quit** | `q` | Exit the application | `q` |

### Command Examples

```bash
# Place a $50 bet on horse #1 (That Darn Gray Cat)
1 50

# Set horse #3 (Count Sheep) as the winner
w 3

# Restock the ATM cash inventory
r

# Quit the application
q
```

### Error Handling

The application handles various error conditions:
- **Invalid Horse Number**: `9 100` → `Invalid Horse Number: 9`
- **Invalid Bet Amount**: `1 10.25` → `Invalid Bet: 10.25`
- **Negative Amounts**: `4 -5` → `Invalid Bet: -5`
- **Non-numeric Input**: `4 ten` → `Invalid Bet: ten`
- **Unknown Commands**: `x 5` → `Invalid Command: x 5`
- **Insufficient Funds**: Proper error message when ATM cannot dispense requested amount

## Building and Testing

### Prerequisites
- Java 17 or higher
- Gradle 7.0+ (or use included wrapper)

### Build Commands

```bash
# Clean and build the project
./gradlew clean build

# Run tests
./gradlew test

# Generate test coverage report
./gradlew jacocoTestReport

# Generate Javadoc
./gradlew javadoc

# Run all quality checks
./gradlew check
```

### Project Structure

```
src/
├── main/java/com/vikingcloud/horsetrack/
│   ├── Main.java                           # Application entry point
│   ├── AutomatedTellerMachine.java         # Core ATM logic
│   ├── command/                            # Command pattern implementations
│   │   ├── Command.java                    # Command interface
│   │   ├── BetCommand.java                 # Handle betting operations
│   │   ├── SetWinnerCommand.java           # Set race winner
│   │   ├── RestockCommand.java             # Restock cash inventory
│   │   ├── QuitCommand.java                # Exit application
│   │   └── Invalid*Command.java            # Error handling commands
│   ├── constants/                          # Application constants
│   │   ├── CommandConstants.java           # Command string constants
│   │   └── DenominationConstants.java      # Cash denomination settings
│   ├── model/                              # Data models
│   │   ├── Horse.java                      # Horse representation
│   │   └── CashInventory.java              # Cash management
│   ├── parser/                             # Input parsing
│   │   └── CommandParser.java              # Parse user input to commands
│   └── service/                            # Business services
│       └── HorseFactory.java               # Horse creation utility
└── test/java/                              # Unit tests (12 test classes)
```

## Documentation and Reports

- **Code Coverage**: [Jacoco Coverage Report](https://jeanpaulruizvallejo.com/horse-track-atm-simulation/jacoco-coverage/)
- **API Documentation**: [Javadoc](https://jeanpaulruizvallejo.com/horse-track-atm-simulation/javadoc/)

## Architecture

This application follows several design patterns:
- **Command Pattern**: All user actions are encapsulated as command objects
- **Factory Pattern**: Horse creation is handled by HorseFactory
- **Model-View-Controller**: Separation of data models, business logic, and user interface
- **Single Responsibility**: Each class has a focused, single purpose
