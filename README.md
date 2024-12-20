# Projet Données Massives & Cloud - Tiny LDF
## Lucas Danneaux - URL du Projet : https://sound-catalyst-437209-c1.appspot.com/

## Commande manipulés lors de la mise en place de la Machine distante

#### Commande exécuté dans le cloud shell
- `mvn -v`
- `gcloud init`
- `git clone https://github.com/momo54/webandcloud.git`
`cd webandcloud`
`mvn install` -> "" Build Success ""

#### Modification de fichier dans l'éditeur cloud shell
- [pom.xml](webandcloud/pom.xml) : Remplacement de l'identifiant du projet par tiny-ldf
- [src/main/webapp/WEB-INF/appengine-web.xml](webandcloud/src/main/webapp/WEB-INF/appengine-web.xml) : Remplacement de l'identifiant par tiny-ldf

#### Commande dans le cloud shell
- `gcloud app create`
- `6 - Europe west 6`
- `mvn package appengine:deploy`

La machine distante est désormais accessible à l'adresse suivante : [https://sound-catalyst-437209-c1.appspot.com/](https://sound-catalyst-437209-c1.appspot.com/)

## Commande pour la manipulation du Cloud 
- `mvn package` -> Compilation du Java
- `mvn appengine:run` -> Déploiement dans l'environnement de developpement  
- `mvn appengine:deploy` -> Deploiement sur l'adresse principale

#### Test du fonctionnement du code dans l'environnement de test
- `mvn package`
- `mvn appengine:run`

#### Mise en place de l'indexation de l'API Score pour la récupération des scores filtrés sur le nom à l'aide du fichier [index.yaml](webandcloud/src/main/webapp/WEB-INF/index.yaml)
- `cd src/main/webapp/WEB-INF`
- `gcloud datastore indexes cleanup index.yaml`
- `gcloud datastore indexes create index.yaml`
- `cd ~/webandcloud`
- `mvn appengine:deploy`
