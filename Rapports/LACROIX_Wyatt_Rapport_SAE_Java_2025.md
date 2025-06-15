Lacroix Wyatt 14B

# Rapport SAE - Java

## Réalisation

Dans le cadre de la SAE Java, j'ai été ammené à travailler avec 3 camarades : Valentin Pawlowicz, Okan Keles et Titouan Guerrier.

J'ai développer pour notre application LivreExpress, les fichiers `ConnexionMysql.java`, `AppLibrairie.java`, `Administrateur.java`, `Administrateur.java` et `Magasin.java`. J'ai également fait la méthode `maxNumVendeur()` dans `VendeurBD.java`. De plus, je me suis occupé de faire l'entièreté des tests de chaque méthode de chaque `classeBD.java`, et également des autres classes.java, j'ai donc dû effectuer des correctifs avec des modifcations mineures, parfois majeures, de chacunes des classes présentent dans notre projet. J'ai fait une vérification finale le dimanche 15 juin 2025 --qui s'est conclue après 3 heures de déboguage et de corrections des méthodes-- par la vidéo démonstrative et j'ai également essayer d'optimiser au mieux les codes des classes.

Pour ce qui concerne la base de données, j'ai créer 3 nouvelles tables : `ADMINISTRATEUR`, `VENDEUR` et `AFFILIATION` ; j'ai également modifier le script de création de la base de données afin qu'on ne puisse pas mettre des valeurs en null dans certaines tables, néanmoins je n'ai pas eu le temps d'ajouter des tests sécuritaire pour qu'on ne puisse pas laisser des caractères vides dans certaines classes même si la majorité possède ces tests.

## Ressources Mobilisées

### Développement orienté objets

Pour cette SAE, j'ai développer un total 5 classes dans leurs entièreté, et j'ai vérifier au maximum l'entièreté des classes que mes camarades ont fait après plusieurs dizaines de vérifications et corrections.

Je me suis beaucoup aidé du tp 8 JDBC vu en BD pour réaliser cette SAE.

### Qualité de développement

Après avoir créer le dépôt sur github et l'avoir cloné sur mon espace de travail à l'université, j'ai pu le partager à mes camarades.

GitHub à été pour moi un outil plus que nécessaire, il était vital.
En effet sur Github, j'ai pu travailler librement à la fois sur l'université, mon pc Windows et une machine virtuelle en Linux.
Chaque environnement étant différent, j'ai dû et ai pu paramétrer mes besoins et travailler sur des points critiques en fonctions de ce que je devais utiliser. Par exemple, la MV m'a permis de tester toutes sortes de commandes et de tests pour notre application que je n'aurais pas oser faire à l'université ou sur mon pc, j'ai par ailleur dû plusieurs fois recommencer l'environnement de ma MV, et c'est là que GitHub à jouer un rôle essentiel : sauvegardé notre projet.

Le projet à été optimiser grâce à l'utilisation de branches secondaires comme `wyatt` ou `wyatt_vm` qui ont été mes branches principales de travail, dessus j'ai effectuer des git commits qui m'ont permis de laisser une trace des modifications que j'ai apporté et j'ai pu faire des pull request, qui ont permis de fusionné mes branches plusieurs fois afin d'apporté mes modifications à l'ensemble du projet et ainsi, permettre à mes camarades qui se servaient de mes méthodes dans `AdministrateurBD.java` pour faire les leurs.

### Communication technique

La communication technique a été une partie très difficile dans notre projet, en effet le travail de groupe a presque été délaissé par l'équipe, me laissant la tâche de développer seul et la pression qui va avec pendant un peu plus que deux semaines. A une semaine de la fin, j'ai dû consulter mon professeur référent car je ne savais plus comment gérer la situation, n'étant pas le chef de projet, j'ai quand même dû créer un groupe sur l'application Instagram --ce qui n'avait jamais été fait jusqu'à là-- afin de discuter calmement d'une situation pesante et de trouver des solutions. Suite à ça, le travail de groupe à repris et nous avons pu répartir le reste des tâches à réaliser, nous nous sommes organisé grâce à trello, qui à un accès direct et simple.

## Apprentissages critiques

### Implémenter des conceptions simples

Afin de ne pas répéter ce que j'ai dis précédemment dans ce rapport, je joint ci-dessous des images d'une partie de ce que j'ai réaliser.

![](./img/screen_dev1.png)
AppLibrairie.java

![](./img/screen_dev2.png)

### Élaborer des conceptions simples

Pour ne pas être à la fois submerger et perdu, j'ai du élaborer un plan d'action quant à ce que je devait réaliser, j'ai donc commencer par la réalisation du fichier pour faire la connexion à la base de donnée, et j'ai ensuite progressivement implémenté les différents menus dans `AppLibraire` en fonctions du plus au moins important.

Après ça j'ai pu commencer le développement des méthodes des 5 différents fichiers, parfois simultanément car c'était une nécessité.

### Faire des essais et évaluer leurs résultats en regard des spécifications

Pour faire mes essais et mes tests, je lançais en majorité l'application de la librairie, comme les menus était implémentés, je savait de quoi j'avais besoin et ce qu'il me manquait, j'ai pu donc effectuer des tests efficaces et mettre en place des correctifs à une vitesse convenable.

Par exemple, je me suis rendu compte assez vite que mon menu administrateur ne permettait pas de gérer les stock des magasin, j'ai donc implémenter cette option au menu et j'ai ensuite développer les méthodes dans la classe adminBD.