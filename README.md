# FoodStore

Sistema de gestión de pedidos desarrollado en **Java** como parte de la materia **Programación II (UTN)**.  
El proyecto implementa un modelo orientado a objetos con entidades, servicios y un menú de consola para la interacción del usuario.

---

##  Estructura del proyecto
src/examen/prog2
├── entities/        # Clases de dominio (Usuario, Producto, Pedido, etc.)
├── enums/           # Enumeraciones (Estado, FormaPago, Rol)
├── exceptions/      # Excepciones personalizadas
├── interfaces/      # Interfaces (Calculable)
├── services/        # Lógica de negocio (PedidoService, UsuarioService, etc.)
├── ui/              # Menú de consola (MenuConsola)
└── Main.java        # Punto de entrada principal

---

##  Funcionalidades principales

- **Gestión de usuarios**: creación, listado y roles.  
- **Gestión de productos**: alta, baja, modificación y consulta.  
- **Gestión de pedidos**: detalle de pedidos, cálculo de totales y control de stock.  
- **Menú de consola**: interfaz interactiva para ejecutar las operaciones CRUD.  
- **Excepciones personalizadas**: validación de datos y manejo de errores.  

---

##  Tecnologías utilizadas

- Lenguaje: **Java 8+**  
- IDE: **NetBeans**  
- Control de versiones: **Git/GitHub**  

---

##  Ejecución

1. Abrir el proyecto en **NetBeans**.  
2. Compilar con **Clean and Build**.  
3. Ejecutar el archivo principal:
   - `Main.java` → inicia el sistema.  
   - `MenuConsola.java` → despliega el menú interactivo.  

---

##  UML (resumen)

- **Entities**: Usuario, Producto, Pedido, DetallePedido, Categoria.  
- **Services**: UsuarioService, ProductoService, PedidoService, CategoriaService.  
- **Enums**: Estado, FormaPago, Rol.  
- **Interfaces**: Calculable.  
- **UI**: MenuConsola.  

---

## 👨‍💻 Autor

- **Ezequiel Gonzalez**  
- Estudiante de Programación en UTN  
- Puerto Madryn, Argentina  
 link video https://youtu.be/xU2TcV2ftkI
