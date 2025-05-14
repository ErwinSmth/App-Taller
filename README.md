# Talleres Backend - Sistema de Gestión de Talleres

Aplicación backend para el sistema de talleres educativos desarrollado con Spring Boot.

## Tecnologías
- Java 21
- Spring Boot 3.4.5
- Lombok


## Estructura Actualizada del Proyecto
    📁src/
    └── 📁main/
        └── 📁java/
            └── 📁com/
                └── 📁curso/
                    └── 📁spring/
                        ├── 📁domain/                    # Representación del Dominio (DTOs y lógica de negocio)
                        │   ├── 📁dto/                   # Objetos para entrada/salida de datos (API ⇄ cliente) (Datos Simplificados)
                        │   │
                        │   ├── 📁repositoryInterfaces/    # Interfaces que abstraen el acceso a los datos
                        │   │
                        │   ├── 📁service/               # Lógica de negocio central de la aplicación
                        │   │
                        │   ├── 📁mapper/                 # Conversores entre entidades y DTOs
                        |
                        ├── 📁persistence/               # Acceso a Datos (implementaciones concretas)
                        │   ├── 📁crud/                  # Interfaces JPA para acceso directo a datos (DAO)
                        │   │
                        │   ├── 📁entity/                # Entidades JPA que representan las tablas de la base de datos
                        │   │
                        │   │
                        │   ├── 📁repository/            # Implementaciones concretas de los repositorios del dominio
                        │
                        ├── 📁web/                       # Controladores REST (Interfaz API)
                        │   ├── 📁controller/            # Manejo de solicitudes HTTP
                        │
                        └── TallerApplication.java           # Clase principal del proyecto
        └── 📁resources/
            ├── 📁static/                                
            ├── 📁templates/                             
            ├── application.properties                 # Configuración general
            ├── application-david.properties    
            ├── application-yerico.properties
        └── 📁test/
            └── 📁java/
                └── 📁com/
                    └── 📁curso/
                        └── 📁spring/
                            └── ApplicationTests.java