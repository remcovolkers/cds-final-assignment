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

- [ ] Lineaire gegevensstructuur (gelinkte lijst of array)
    - [ ] Lineair zoekalgoritme
    - [ ] Binair zoekalgoritme
- [ ] Twee sorteeralgoritmen (één uit selection-/insertionsort en één uit quicksort/mergesort)
- [ ] Binaire zoekboom (BST of AVL-tree)
- [ ] Hashtabel met stationscode als sleutel
- [ ] MinHeap op basis van een array
- [ ] DFS- of BFS-algoritme voor "de rechthoek" functionaliteit
- [ ] Dijkstra en A* algoritmen voor kortste route
- [ ] Regular Expressions voor CSV-bestand validatie
- [ ] Testen met minimaal 75% code coverage (class, method, line en branch)

## Menu-gebaseerde Functionaliteiten

- [ ] Zoeken van een station (lineair en binair)
- [ ] Sorteren van verbindingen (tweemaal)
- [ ] Bepalen van de kortste route via stationnamen of -codes
- [ ] Bepalen van de MCST via stationnamen of -codes binnen een rechthoek
- [ ] Optioneel: Grafisch tonen van de kortste route of de MCST

## Datastructuur Methodes

- [ ] `isEmpty()` en `size()`
- [ ] Voor lineaire datastructuur en boom zonder sleutel (`T extends Comparable<T>`):
    - [ ] `add(T value)`
    - [ ] `contains(T value)`
    - [ ] `get(T value)`
    - [ ] `remove(T key)` (optioneel voor AVL, vereist voor BST voor een "Voldoende")
- [ ] Voor hashmap en boom met sleutel (`K extends Comparable<K>`, `V value`):
    - [ ] `put(K key, V value)`
    - [ ] `contains(K key)`
    - [ ] `get(K key)`
    - [ ] `remove(K key)`
- [ ] Voor elke node in een boom:
    - [ ] `isLeaf()`
    - [ ] `height()`
- [ ] Voor de min heap:
    - [ ] `push(T value)`
    - [ ] `peek()`
    - [ ] `pop()`
    - [ ] Optioneel: `buildHeap()`
- [ ] `toString()` voor alle datastructuren
- [ ] Voor boom, heap en grafiek:
    - [ ] `graphViz()` representatie

## Documentatie en Plagiaat

- [ ] Documentatie van de eigen code
- [ ] Testgevallen voor de zelfstandig ontwikkelde of aangepaste code
- [ ] Bronvermelding voor gebruikte code (indien niet volledig zelf ontwikkeld)

## Overige Aandachtspunten

- [ ] Geen gebruik van andermans code buiten de genoemde uitzonderingen
- [ ] Beide het codebestand en het verantwoordingsdocument zijn vereist voor beoordeling
