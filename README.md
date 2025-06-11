# 🍤 Proyecto Final - Restaurante de Mariscos
Programación III


# Descripción General
Este proyecto tiene como objetivo el diseño y desarrollo de una aplicacion para la gestión integral de un restaurante de mariscos, utilizando el lenguaje de programación Java. El sistema permitirá administrar funcionalidades clave del restaurante, tales como la visualización del menú, la toma de pedidos, la administracion de inventario y la gestión de clientes.


# 🖌️ Primera Etapa: Diseño de Interfaz (Prototipado en Figma)
Durante esta fase inicial, se elaboró un prototipo interactivo utilizando Figma, con el propósito de planificar y estructurar visualmente la aplicación antes de su desarrollo.

Funcionalidades diseñadas:

Menú de productos (platillos y bebidas)

Toma de pedidos

Administración de inventario

Gestión de usuarios (login y control de acceso)

Gestion de clientes

Flujo de navegación entre pantallas

Este prototipo actúa como una guía visual y funcional para la implementación en código, asegurando una interfaz coherente, moderna y amigable para el usuario final.


# 💻 Segunda Etapa: Implementación en Java
En esta fase se comenzó la construcción funcional de la aplicación con Java, replicando fielmente el diseño prototipado.

Características implementadas:

Maquetación visual del sistema de administración con componentes personalizados como botones y paneles con esquinas redondeadas.

Navegación fluida entre ventanas mediante eventos de botón.

Gestión de tarjetas visuales para representar platillos y órdenes.

Conexión a una base de datos remota para manejar la autenticación de inicio de sesión de usuarios.

Este avance marca una transición efectiva del diseño al desarrollo, enfocándose en una arquitectura funcional y visualmente atractiva.


# 📋 Tercera entrega - Sistema CRUD de Clientes

🧾 Descripción General

Este proyecto corresponde a la tercera entrega del sistema de gestión de clientes mediante operaciones CRUD (Crear, Leer, Actualizar, Eliminar). 

El sistema está diseñado para permitir al administrador gestionar eficientemente los datos de los clientes desde una interfaz amigable y funcional.

✅ Funcionalidades principales implementadas
En esta entrega se trabajó en nuevas funcionalidades que fortalecen la administración y el control sobre los registros de los clientes:

🔍 Ordenamiento de clientes:
El sistema ahora permite al administrador ordenar los registros de clientes para facilitar su localización y análisis. Este orden puede ser por nombre, 

ID u otro criterio establecido según las necesidades del sistema.

✏️ Edición de clientes:

El administrador puede modificar los datos de cualquier cliente registrado, accediendo a una vista con los campos prellenados para su rápida edición.

🗑️ Eliminación de clientes:

Se habilitó la opción de eliminar clientes, incluyendo confirmaciones previas para evitar acciones accidentales.

📥 Descarga de información:

El sistema ahora permite descargar la información del cliente, así como su historial de compras, generando un archivo con los datos relevantes para respaldo o revisión.


# 🍤 Cuarta entrega (Entrega Final)

## 🧾 Funcionalidades CRUD por Módulo

El sistema implementa operaciones CRUD completas (Crear, Leer, Actualizar, Eliminar) en todos los módulos principales del restaurante, permitiendo una gestión eficiente y controlada de los datos.



## 🍽️ Módulo de Platillos


✅ Crear platillo: Registro de nuevos platillos al menú con nombre, precio, descripción e imagen.

🔍 Leer platillos: Visualización de todos los platillos disponibles en formato de tarjetas.

✏️ Editar platillo: Modificación de información existente (nombre, precio, imagen, etc.).

❌ Eliminar platillo: Opción para eliminar un platillo del sistema con confirmación previa.



## 🧾 Módulo de Órdenes


✅ Crear orden: Registro de una nueva orden con los productos seleccionados por el cliente.

🔍 Leer órdenes: Consulta de órdenes actuales y pasadas desde el panel de administración.

✏️ Editar orden: Actualización de los productos o datos de una orden antes de su entrega.

❌ Eliminar orden: Cancelación o eliminación de órdenes en caso necesario.



## 👤 Módulo de Clientes

✅ Registrar cliente: Añadir nuevos clientes al sistema con nombre, contacto y dirección.

🔍 Consultar clientes: Listado completo con búsqueda y ordenamiento por nombre o ID.

✏️ Editar cliente: Modificación de los datos del cliente de forma rápida y segura.

❌ Eliminar cliente: Opción para remover registros obsoletos o duplicados.



## 📦 Módulo de Inventario

✅ Añadir ingrediente: Registro de nuevos insumos con nombre, unidad y cantidad.

🔍 Visualizar inventario: Vista categorizada por estado (stock suficiente, bajo, o faltante).

✏️ Actualizar stock: Edición de cantidades o detalles de un ingrediente.

❌ Eliminar ingrediente: Eliminación de ingredientes no utilizados del sistema.


# 🛠️ Tecnologías Utilizadas

Lenguaje: Java

IDE: Eclipse

Diseño UI/UX: Figma

Base de datos: MySQL (remota)

Librerías: Swing, JDBC


# 🔐 Acceso al Sistema

Login de prueba

Usuario: jonasoto

Contraseña: 1234

Base de Datos

Nombre: freedb_Restaurante_El_Manglar

Host: sql.freedb.tech

Puerto: 3306

Usuario: freedb_civanrflores

Contraseña: Pm6kE#W!3sQyK5s


# ⚙️ Instrucciones de instalación/ejecución

Asegúrate de tener instalado el Java Runtime Environment (JRE) en tu equipo. 

Puedes descargarlo desde: https://www.oracle.com/java/technologies/javase-downloads.html

Descarga el archivo .jar del proyecto compilado.

Ejecuta el proyecto haciendo clic derecho sobre el archivo .jar y posteriormente abrirlo con JAVA

# 📂 Estructura del proyecto

📁 Proyecto_Final

├── 📁 src

│   ├── 📁 application          # Lógica principal de la aplicación (Main, inicializadores)

│   ├── 📁 controllers          # Controladores que manejan la lógica entre vistas y modelos

│   ├── 📁 customClasses        # Clases personalizadas (helpers, utilidades, etc.)

│   ├── 📁 files                # Archivos de datos o configuraciones

│   ├── 📁 images               # Imágenes utilizadas en la interfaz

│   ├── 📁 libreries            # Librerías externas (JARs u otras dependencias)

│   ├── 📁 models               # Modelos de datos (Platillos, Clientes, Inventario, etc.)

│   └── 📁 views                # Interfaces gráficas (ventanas, paneles, etc.)

├── 📦 ElManglar.jar            # Archivo ejecutable principal del proyecto

│
├── 📁 doc                     # Documentación adicional

│   ├── 📦 Manglar.jar         # Otro ejecutable opcional o versión alternativa

│   └── 📄 Reporte Restaurant.pdf  # Informe en PDF del proyecto

├── 📄 README.md               # Documento principal con detalles del proyecto

├── 📁 JRE System Library [jre]     # Librerías del entorno de ejecución de Java

└── 📁 Referenced Libraries         # Librerías externas referenciadas (como JDBC)

# 📸 Capturas de pantalla
