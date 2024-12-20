# Projet Données Massives & Cloud - Tiny LDF
## Lucas Danneaux - URL du Projet : https://tiny-ldf.oa.r.appspot.com/

## Avancememnt du projet
### Fonctionnel

- Mise en place du projet sur une machine distance dédié
- Mise en place de l'ajout de nouveau Quad par un utilisateur identifié
- Jeu de données du projet Web Sémantique uploadé
- Requête pour la selection fonctionnel

### Non Fonctionnel / Inachevé

- La récupération des élements sélectionné n'est pas affiché
- Pas de Pagination
- Pas de mise en page supplementaire

#### Commande exécuté dans le cloud shell
- `mvn -v`
- `gcloud init`
- `git clone https://github.com/dann0008/TinyLDF.git`
`cd TinyLDF`
`mvn install` -> "" Build Success ""

#### Modification de fichier dans l'éditeur cloud shell
- [pom.xml](webandcloud/pom.xml) : Remplacement de l'identifiant du projet par tiny-ldf
- [src/main/webapp/WEB-INF/appengine-web.xml](webandcloud/src/main/webapp/WEB-INF/appengine-web.xml) : Remplacement de l'identifiant par tiny-ldf

#### Commande dans le cloud shell
- `gcloud app create`
- `6 - Europe west 6`
- `mvn package appengine:deploy`

La machine distante est désormais accessible à l'adresse suivante : [https://tiny-ldf.oa.r.appspot.com/](https://tiny-ldf.oa.r.appspot.com/)

## Commande pour la manipulation du Cloud 
- `mvn package` -> Compilation du Java
- `mvn appengine:run` -> Déploiement dans l'environnement de developpement  
- `mvn appengine:deploy` -> Deploiement sur l'adresse principale

#### Upload du jeu de données

Le jeu de données correspondant est le résultat du projet réalisé dans le cours Web Sémantique accessible ici : [Dépot Projet Web Sémantique](https://github.com/dann0008/WebSemantique)

Une partie du jeu de données a été uploadé sur le projet TinyLDF avec l'utilisation d'un script python qui a lu le fichier csv afin de réaliser un appel à l'api pour chaque ligne du fichier csv. La restriction d'authentification a été levé le temps de l'upload pour un gain de temps. [Script d'upload des Quad](upload.py)

Cet étape à permit l'ajout de 28 300 quad dans le Datastore de TinyLDF.

- `.\.venv\Scripts\activate`
- `pip install requests`
- `python .\upload.py`