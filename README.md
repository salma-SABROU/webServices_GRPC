# webService GRPC

=> Premièr partie (/chatServer.java)
 Créer un serveur Chat GRPC vous pouvez tester le serveur Chat avec un client GRPC comme BloomRPC

=> Deusième partie (/NbrMagiqueServer.java)
 un serveur JEU GRPC :
     - Au démarrage le serveur choisit un nombre aléatoire. 
     - Ensuite les clients GRPC doivent deviner en compétition le nombre secret.
     - le serveur répond à chaque fois avec les éventualités suivantes :
        . Votre nombre est plus grand
        . Votre nombre est plus petit
        . BRAVO vous avez gagné et envoyer le gagnant aux clients
        . Jeu terminé, le gagnant est ""Numéro du gagnant"
