# Horse Track ATM Simulation

A Java-based console application that simulates an ATM system for a horse racing track. This coding challenge demonstrates object-oriented design, command pattern implementation, and comprehensive error handling.

## Quick Start

```bash
cd HorseTrackATMSimulation
./gradlew run
```

## Project Overview

This application simulates a betting ATM with the following features:
- Place bets on 7 different horses with varying odds
- Manage cash inventory with multiple denominations ($1, $5, $10, $20, $100)
- Set race winners and calculate payouts
- Handle various error conditions gracefully
- Command-line interface for all interactions

## Documentation

- **Detailed README**: [HorseTrackATMSimulation/README.md](HorseTrackATMSimulation/README.md)
- **Code Coverage Report**: https://jeanpaulruizvallejo.com/horse-track-atm-simulation/jacoco-coverage/
- **API Documentation**: https://jeanpaulruizvallejo.com/horse-track-atm-simulation/javadoc/

## Technologies

- Java 17
- Gradle with Kotlin DSL
- JUnit 5 for testing
- JaCoCo for code coverage
- Comprehensive Javadoc documentation

## Available Commands

| Command | Description | Example |
|---------|-------------|---------|
| `<horse_number> <amount>` | Place bet on horse | `1 50` |
| `w <horse_number>` | Set winning horse | `w 3` |
| `r` | Restock ATM cash | `r` |
| `q` | Quit application | `q` |

For complete documentation, build instructions, and architecture details, see the [detailed README](HorseTrackATMSimulation/README.md).
