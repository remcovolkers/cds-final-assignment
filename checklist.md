# Project Checklist

## Project Structuur

- [ ] Code voor het programma (zonder build artefacts zoals `out/`, `.git/`, etc.)
- [ ] Verantwoordingsdocument

## Verantwoordingsdocument Inhoud

- [ ] Naam van de student
- [ ] Apart hoofdstuk voor elk van de 10 rubric onderdelen:
    - [ ] Locatie in de code waar het onderdeel gevonden kan worden (of een referentietabel)
    - [ ] Toelichting en onderbouwing van keuzes
    - [ ] Beschrijving van de functionaliteit van de code
    - [ ] Testen die de werking bewijzen
    - [ ] Big-O kwalificatie en grafiek met uitleg (voor zoek- en sorteeralgoritmen)

## Programma Functionaliteiten

- [x] Lineaire gegevensstructuur (gelinkte lijst of array)
    - [x] Lineair zoekalgoritme
    - [x] Binair zoekalgoritme
- [x] Twee sorteeralgoritmen (één uit selection-/insertionsort en één uit quicksort/mergesort)
- [x] Binaire zoekboom (BST of AVL-tree)
- [x] Hashtabel met stationscode als sleutel
- [x] MinHeap op basis van een array
- [x] DFS- of BFS-algoritme voor "de rechthoek" functionaliteit
- [ ] Dijkstra en A* algoritmen voor kortste route
- [x] Regular Expressions voor CSV-bestand validatie
- [ ] Testen met minimaal 75% code coverage (class, method, line en branch)

## Menu-gebaseerde Functionaliteiten

- [x] Zoeken van een station (lineair en binair)
- [x] Sorteren van verbindingen (tweemaal)
- [ ] Bepalen van de kortste route via stationnamen of -codes
- [x] Bepalen van de MCST via stationnamen of -codes binnen een rechthoek
- [ ] Optioneel: Grafisch tonen van de kortste route of de MCST

## Datastructuur Methodes

- [x] `isEmpty()` en `size()`
- [x] Voor lineaire datastructuur en boom zonder sleutel (`T extends Comparable<T>`):
    - [x] `add(T value)`
    - [x] `contains(T value)`
    - [x] `get(T value)`
    - [x] `remove(T key)` (optioneel voor AVL, vereist voor BST voor een "Voldoende")
- [x] Voor hashmap en boom met sleutel (`K extends Comparable<K>`, `V value`):
    - [x] `put(K key, V value)`
    - [x] `contains(K key)`
    - [x] `get(K key)`
    - [x] `remove(K key)`
- [ ] Voor elke node in een boom:
    - [ ] `isLeaf()`
    - [ ] `height()`
- [x] Voor de min heap:
    - [x] `push(T value)`
    - [x] `peek()`
    - [x] `pop()`
    - [ ] Optioneel: `buildHeap()`
- [ ] `toString()` voor alle datastructuren
- [ ] Voor boom, heap en grafiek:
    - [ ] `graphViz()` representatie

## Documentatie en Plagiaat

- [ ] Documentatie van de eigen code
- [ ] Testgevallen voor de zelfstandig ontwikkelde of aangepaste code
- [ ] Bronvermelding voor gebruikte code (indien niet volledig zelf ontwikkeld)

## Overige Aandachtspunten

- [x] Geen gebruik van andermans code buiten de genoemde uitzonderingen
- [ ] Beide het codebestand en het verantwoordingsdocument zijn vereist voor beoordeling
