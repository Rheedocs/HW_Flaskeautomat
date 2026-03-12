# Flaskeautomaten

## Beskrivelse
Et producer/consumer program som simulere en flaskeautomat, producer setter flaskerne på båndet og consumer henter henholdsvis øl og vand.

## Programstart
1. Du åbner projektet i intelij
2. Du navigerer til `Main.java`
3. Du kører programmet med run knappen
4. Simulering kører i konsolen

## Struktur

<img width="721" height="241" alt="design" src="https://github.com/user-attachments/assets/78eee8c4-0d55-4b9c-92ef-3fe99619baad" />

## Klasser

### Main
Main-metoden opretter tre buffere og fire tråde.
### Buffer
Bufferen sikrer korrekt synkronisering mellem tråde
f.eks. "Tilføj flaske, vent hvis bufferen fuld." / "Hent flaske, vent hvis bufferen er tom."
### Producer
Den producere flasker og lægger dem i bufferen
### Splitter
Henter flasker fra bufferen, sortere dem og sender dem videre til bufferen.
Bruger farvekoder for tydelighed mellem flaskerne.
### ConsumerBeer
Henter øl-flasker fra øl bufferen
### ConsumerWater
Henter vand-flasker fra vand bufferen

## Kilder
- Java Condition (signal/await): https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Condition.html
- Java ReentrantLock: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantLock.html
