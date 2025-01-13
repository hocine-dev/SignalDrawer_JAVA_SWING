Voici un fichier README.md pour votre projet :

```markdown
# SignalDrawer

SignalDrawer est une application Java Swing interactive qui permet de dessiner des signaux numériques à partir d'une suite binaire entrée par l'utilisateur. Elle supporte plusieurs types de codage, notamment NRZ, NRZI, Manchester, Miller et Manchester différentiel.

## Fonctionnalités

- Entrée de données binaires par l'utilisateur.
- Génération de signaux avec différents types de codage :
  - **NRZ** (Non-Return-to-Zero)
  - **NRZI** (Non-Return-to-Zero Inverted)
  - **Manchester**
  - **Miller**
  - **Manchester différentiel**
- Interface graphique intuitive pour visualiser les signaux.

## Prérequis

- Java 8 ou une version ultérieure.
- Maven (optionnel, pour gérer les dépendances).

## Installation

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/votre-utilisateur/SignalDrawer.git
   ```
2. Accédez au répertoire du projet :
   ```bash
   cd SignalDrawer
   ```
3. Compilez et exécutez le projet :
   ```bash
   javac -d bin src/*.java
   java -cp bin Main
   ```

## Utilisation

1. Lancez l'application.
2. Entrez une suite binaire (par exemple : `101011`).
3. Sélectionnez le type de codage souhaité.
4. Visualisez le signal généré dans l'interface graphique.


## Contribution

Les contributions sont les bienvenues ! Pour contribuer :
1. Forkez le projet.
2. Créez une branche pour votre fonctionnalité :
   ```bash
   git checkout -b nouvelle-fonctionnalite
   ```
3. Envoyez vos modifications avec une pull request.

## Licence

Ce projet est sous licence MIT. Consultez le fichier [LICENSE](LICENSE) pour plus d'informations
