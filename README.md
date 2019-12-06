# Secure-Locally-Chat-Application


Une simple application de méssagerie instantannée et sécurisée en local.  Elle est developper en Java


le contenue graphique de l'application ainsi que les boites de dialogue sont en Francais


1- Description Globale :

une application de discussion permettant aux ordinateurs hôtes d’un réseau d’échanger des messages 
instantanément, conçu pour être utilisé au sein d’un réseau local de petite, moyenne et grande entreprise, et cela par l’intermédiaire
d’un serveur connecté au même réseau informatique Ce messager intranet remplace idéalement les messageries Internet dans les entreprises
et qui est défini par une messagerie privée et sécurisée et améliore les communications internes, la productivité de l'entreprise et les 
relations avec les collègues de l'entreprise. 

   
Ce logiciel comprend la discussion textuelle instantanée, le partage de fichiers, la messagerie hors ligne, le chat vocal… etc., avec des
correspondants faisant parti d’une liste d’amis privé dans laquelle sont intégrées uniquement les personnes souhaitées et qui est gérable 
par l’utilisateur.  Le logiciel ne nécessite aucune connexion Internet et fonctionne sur un port Ethernet à l'aide du protocole TCP / IP.






2- Conception Globale

   L’application se repose sur le modèle d'architecture client-serveur, Divisé en deux modules (module de discussion client et module serveur) qui  peuvent communiquer entre eux sur un réseau lors de l’établissement d’une connexion, ce qui dépend des numéros de port et des adresses IP de l’ordinateur hôte. Chaque modèle contiendrait un schéma de chiffrement et de déchiffrement utilisant la cryptographie hybride en mixant l'algorithme AES  avec RAS, afin de chiffrer chaque message sortant d’une extrémité (client ou serveur) et de le déchiffré à l'extrémité de réception de la communication (client ou serveur).
   
   
   


3-  Le mécanisme de cryptographie utilisé
  
   Cryptograpie hybride :

 Les systèmes cryptographiques asymétriques et symétriques assurent ensemble la sécurité de la transmission des données sur un support non sécurisé. Cela fonctionne en deux étapes, dans un premier temps, la clé pour chiffrer / déchiffrer les informations est partagée à l'aide de paires de clés RSA (cryptographie asymétrique). Une fois la première étape terminée, la communication s'effectue au cours de la deuxième étape, où les informations sont cryptées / décryptées à l’aide de clés symétriques AES (partagées en toute sécurité durant l’étape 1 ).
 
 
 
 4- Vue global sur le fonctionnement :
 
La fenetre serveur
avant toutes utilisation, il faut lancer le serveur qui recevra les connexions des clients et leurs echanges.

 ![Server](https://user-images.githubusercontent.com/58481599/70313517-117d6880-1816-11ea-8d17-997afa1f3929.PNG)
 


La fenetre d'authentification

1-	Demande de connexion au server est primordial avant toute utilisation.	
2-	L’utilisateur devra ensuite s’authentifier par un nom d’utilisateur unique et un mot de passe. La requête d’authentification est envoyée au serveur, et un message d’erreur apparait si les informations introduites n’existent pas. Toutefois si un utilisateur ne possède pas de compte, il pourra s’enregistrer et créer son propre compte


 ![Login](https://user-images.githubusercontent.com/58481599/70267110-0e469600-179e-11ea-8e6f-36c34686e2df.PNG)
 
 
 
 La fenetre d'accueil
 
 1- La fenetre contient l’ensemble des options que peut satisfaire l’application (gestion du compte, ajout d’un ami, gestion des invitations reçus, consulter les messages manqués, voir la liste des contacts...).
 
 ![page acceuille](https://user-images.githubusercontent.com/58481599/70313515-10e4d200-1816-11ea-8609-abc1ee7815f9.PNG)
                                           
                                          
                                           





