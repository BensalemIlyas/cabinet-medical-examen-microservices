# cabinet-medical-examen-microservices
examen sur spring boot microservices M2 Développeur full stack

Se placer sur les répertoires des différents services et executer les commandes docker suivantes: 

pour le patient:
 * créer l'image docker: docker build --no-cache -t patient-service .
 * lancer le conteneur: docker run -p 8081:8081 patient-service
 * swagger ui : http://localhost:8081/swagger-ui/index.html
 * swagger openapi : http://localhost:8081/api-docs

pour le praticien:
* créer l'image docker: docker build --no-cache -t praticien-service .
* lancer le conteneur: docker run -p 8082:8082 praticien-service
* swagger ui : http://localhost:8082/swagger-ui/index.html
* swagger openapi : http://localhost:8082/api-docs

pour les rendez-vous:
non terminé

pour le dossier medical:
* créer l'image docker: docker build --no-cache -t dossier-medical .
* lancer le conteneur: docker run -p 8084:8084 dossier-medical
* swagger ui : http://localhost:8084/swagger-ui/index.html
* swagger openapi : http://localhost:8084/api-docs

pour le Api Gateway:
* créer l'image docker: docker build --no-cache -t api-gateway .
* lancer le conteneur: docker run -p 8085:8085 api-gateway
* swagger ui : http://localhost:8085/swagger-ui/index.html
* swagger openapi : http://localhost:8085/api-docs

pour eureka-server:
* créer l'image docker: docker build --no-cache -t eureka-server .
* lancer le conteneur: docker run -p 8761:8761 eureka-server
