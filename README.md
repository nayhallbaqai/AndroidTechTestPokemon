Android Tech Challenge (Pokemon)
------

This codebase includes a near complete implementation of a card game application. The application is complete with the exception of the RoundFactoryImpl class which is a stub. You must implement the RoundFactoryImpl so that it allows the game to be played.

Tests are included for the class in RoundFactoryImplTest however they are not exhaustive, please feel free to add any tests you feel are necessary. 

_Note: The applications is written in Kotlin, you may change the RoundFactoryImpl and RoundFactoryImplTest to Java if you prefer._

##### Application Requirements

The requirements for the finished application are as follows:

* The app must show a loading screen while loading the application’s data.
* The app must load a list of Pokémon cards from the above api.
* The game must present a user with just the image for two cards and ask them which is rarer.
* A user must never be given two cards with the same rarity to pick from.
* Card rarity is defined as follows from least rare to most rare:
..* common
..* uncommon
..* rare
..* rare holo
..* rare ultra
..* rare secret
* Tapping the correct card will award the player 1 point.
* Tapping the incorrect card will have no effect on the user’s score.
* A user will be informed if they have selected correctly.
* After tapping a card the user will be presented with two new cards.
* When a user gains 5 points the user will be shown a success screen.

##### Submission

Please place your submission in a GitHub private repo and add deepmatter-recruitment as a collaborator. You may fork this repo to create your private repo if you wish.
