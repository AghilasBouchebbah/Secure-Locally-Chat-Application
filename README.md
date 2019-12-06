# Secure-Locally-Chat-Application


Une simple application de méssagerie instantannée et sécurisée en local.  Elle est developper en Java

MOTS CLES: Java, génie logiciel,programmation ortienté objets, d’interface homme-machine, programmation réseau à sockets, Sécurité informatique, gestion de bases de données Mysql.

Platformes: Netbeans, Wampserver.

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
 
A)- La fenetre serveur

avant toutes utilisation, il faut lancer le serveur qui recevra les connexions des clients et leurs echanges.

 ![Server](https://user-images.githubusercontent.com/58481599/70313517-117d6880-1816-11ea-8d17-997afa1f3929.PNG)
 


B)- La fenetre d'authentification

1-	Demande de connexion au server est primordial avant toute utilisation.	
2-	L’utilisateur devra ensuite s’authentifier par un nom d’utilisateur unique et un mot de passe. La requête d’authentification est envoyée au serveur, et un message d’erreur apparait si les informations introduites n’existent pas. Toutefois si un utilisateur ne possède pas de compte, il pourra s’enregistrer et créer son propre compte


 ![Login](https://user-images.githubusercontent.com/58481599/70267110-0e469600-179e-11ea-8e6f-36c34686e2df.PNG)
 
 
 
 C)- La fenetre d'accueil
 
 cette fenetre est constituer de deux paneneaux: 
 
 1- un panneau fixe qui se situe sur le coté gauche de la fenetre, contenant l’ensemble des options que peut satisfaire l’application (gestion du compte, ajout d’un ami, gestion des invitations reçus, consulter les messages manqués, voir la liste des contacts...).
 
 2-un panneau dynamique qui se situe sur le coté droit de la fenetre et qui change avec les cas demandé par l'utilisateur.
 
 -  Le panneau à droite present, représente celui de la gestion du compte qui apparait en premier lors de l’authentification, et qui est aussi accessible via un bouton. L’utilisateur peut modifier ses informations et envoyer la requête au serveur qui mit à jour sa BDD. 
 
 ![page acceuille](https://user-images.githubusercontent.com/58481599/70313515-10e4d200-1816-11ea-8609-abc1ee7815f9.PNG)
 
 
 
 D)- La panneau d'ajout d'ami
 
  L'utilisateur peut voir l’ensemble des personnes possédant un compte sur l’application et qui ne sont pas ami avec lui. En cliquant sur une personne, ses informations ainsi que sa photo seront afficher en haut du panneau. Il peut ainsi lui envoyer une demande à devenir ami, comme il peut supprimer une demande déjà envoyée.
  
![AjoutAmis](https://user-images.githubusercontent.com/58481599/70313507-0f1b0e80-1816-11ea-8e1c-8927860ab880.PNG)


E)- La panneau de gestion des invitations

L’utilisateur peut voir le nombre d’invitation qui lui sont envoyées, et peut consulter l’ensemble de ces invitations, En cliquant sur une personne, ses informations ainsi que sa photo seront afficher en haut du panneau.
Il peut ainsi accepter  ou refuser une invitation et  la requête sera envoyer au serveur qui mit à jour la BDD :

1-	En cas d’acceptation, la personne acceptée sera supprimer de tableau d’invitations, et sera ajouter dans la liste des contacts.

2-	En cas de refus, la personne refusée sera supprimé du tableau d’invitations.

![accepter invitation](https://user-images.githubusercontent.com/58481599/70313505-0f1b0e80-1816-11ea-89ef-9e4a4fc8111a.PNG)


F)- Le panneau du profil d'ami

L’utilisateur peut voir la liste de ses contacts. En cliquant sur un contact, le panneau affichant son profil apparaitra, et une requet sera envoyer au serveur pour renvoyer ses informations

1-	Il peut voir l’ensemble de ses informations (nom, prénom, état, photo …).

2-	Il peut voir l’historique des messages échangés avec cet ami et ceux qui seront échangés durant cette session. il peut ainsi supprimer tout l’historique des messages mais pas chez l’ami concerné.

3-	Voir l’état de cet ami, si il est connecté il pourra s’échanger des messages instantanés,
Sinon il peut lui laisser un message et le destinataire le verra à sa connexion.
L’échange des messages avec un ami bloqué ne peut pas avoir lieu.

4-	 Il peut supprimer cet ami, et donc il sera supprimer de sa liste des contacts, et idem pour extrémité. Par ailleurs il peut lui envoyer de nouveau une demande d’ami.

5-	Il peut bloquer cet ami mais son le supprimer, et tout échange avec lui sera impossible.
Par ailleurs, il peut le débloquer à tout moment.
Concernant l’autre extrémité, cet utilisateur sera totalement invisible.

![profil ami](https://user-images.githubusercontent.com/58481599/70313516-117d6880-1816-11ea-9ef1-fae081d06f8e.PNG)


G)- echanges de message et fichiers 

 Les deux utilisateurs sont connectés, donc les messages sont échangés d’une manière instantanée et sécurisé par l’intermédiaire du serveur qui permet d’acheminer les messages.
 
Les deux utilisateurs peuvent entamer  une conversation audio, s’échanger des messages textuel, envoyer des fichiers. 
En cas du fichier, le destinataire aura la possibilité d’accepter ou de refuser la réception du fichier.
Si l’un d’entre eux supprime l’historique des messages, l’autre extrémité conservera toujours l’historique.

![envoi ficher 4](https://user-images.githubusercontent.com/58481599/70313508-0fb3a500-1816-11ea-9780-eb52d21c7af3.PNG)

![envoi fichier 2](https://user-images.githubusercontent.com/58481599/70313510-0fb3a500-1816-11ea-98e5-8204d3bdb6c5.PNG)

Le panneau de messages manqués

L’utilisateur, lors de sa  connexion à l’application, il peut voir le nombre d’amis qui lui ont laissé des messages en son absence, il peut ainsi consulter la liste des  amis propriétaires de ces messages. 
En cliquant sur un ami, ce dernier sera supprimer de la liste des messages manqués,  le nombre de message manqués sera décrémenter, et le panneau dédier au profile d’amis sera afficher et l’utilisateur peut voir le contenue du message qu’il a manqué.

![message manquee](https://user-images.githubusercontent.com/58481599/70313512-104c3b80-1816-11ea-843a-30e4b7dd66d6.PNG)

