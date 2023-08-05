
- nome das imagens de container geradas

    cartoes
        docker build --tag afsilva-appcartoes .
        docker run --name afsilva-appcartoes --network afsilva-network-cartoes -e RABBTIMQ_SERVER=afsilva-rabbtimq -e EUREKA_SERVER=afsilva-appeureka -d afsilva-appcartoes


    Clientes

        docker build --tag afsilva-appclientes . 
        docker run --name afsilva-appclientes --network afsilva-network-cartoes -e EUREKA_SERVER=afsilva-appeureka -d afsilva-appclientes

    Avaliador de Credito
        docker build --tag afsilva-appavaliadorcredito .
        docker run --name afsilva-appavaliadorcredito --network afsilva-network-cartoes -e RABBTIMQ_SERVER=afsilva-rabbtimq -e EUREKA_SERVER=afsilva-appeureka -d afsilva-appavaliadorcredito

    Gateway
        docker build --tag afsilva-appgateway .
        docker run --name afsilva-appgateway --network afsilva-network-cartoes -p 8080:8080 -e KEYCLOAK_SERVER=afsilva-keycloak18 -e KEYCLOAK_PORT=8080 -e EUREKA_SERVER=afsilva-appeureka -d afsilva-appgateway

    eureka
        docker build --tag afsilva-appeureka .
        docker run --name afsilva-appeureka --network afsilva-network-cartoes -p 8761:8761 afsilva-eureka


    keycloak
        docker run --name afsilva-keycloak18 -p 8081:8080 --network afsilva-network-cartoes -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.0 start-dev 

    Rabbti Mq
        docker run -it --name afsilva-rabbtimq -p 5672:5672 -p 15672:15672 --network afsilva-network-cartoes rabbitmq:3.9-management
        docker run -it --name afsilva-rabbtimq -p 5672:5672 -p 15672:15672 --network afsilva-network-cartoes rabbitmq:3.12-management

            

    afsilva-keycloak18.0.0

    afsilva-keycloak18.0.0

- nome da rede no docker
    docker network create afsilva-network-cartoes


- nome da fila rabbtimq 3.9

    emissao-cartoes

- Variaveis de ambiente no docker
    
    RABBTIMQ_SERVER
    EUREKA_SERVER
    KEYCLOAK_SERVER


- docker build --tag {aplicacao} .






