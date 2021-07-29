# API Notes

Application permettant d'enregistrer et de récupérer les notes des patients enregistrées par les praticiens

![image](https://user-images.githubusercontent.com/61044632/127455748-3c9fe026-e533-4200-9c9a-b1e412749afe.png)

La base de donées est une base MongoDB


## Installation

Construction de l'image docker , depuis le répertoire du projet: 

> docker build --build -t notes .

Lancement des builds docker lorsque toutes les APIs sont buildées:
> docker-compose up -d
