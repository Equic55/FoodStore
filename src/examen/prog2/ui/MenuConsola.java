/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.prog2.ui;

import examen.prog2.services.CategoriaService;
import examen.prog2.services.ProductoService;
import examen.prog2.services.UsuarioService;
import examen.prog2.services.PedidoService;
import examen.prog2.entities.Categoria;
import examen.prog2.entities.Pedido;
import examen.prog2.entities.Producto;
import examen.prog2.entities.Usuario;
import examen.prog2.enums.Estado;
import examen.prog2.enums.FormaPago;
import examen.prog2.enums.Rol;
import examen.prog2.exceptions.DatoInvalidoException;
import examen.prog2.exceptions.EntidadNoEncontradaException;
import examen.prog2.exceptions.StockInvalidoException;
import java.util.List;
import java.util.Scanner;

public class MenuConsola {

    private final Scanner scanner = new Scanner(System.in);

    // Services
    private final CategoriaService categoriaService = new CategoriaService();
    private final ProductoService productoService = new ProductoService();
    private final UsuarioService usuarioService = new UsuarioService();
    private final PedidoService pedidoService = new PedidoService();

    public void iniciar() {
        int opcion;
        do {
            System.out.println("=== SISTEMA DE PEDIDOS (FOOD STORE) ===");
            System.out.println("1. Categorias");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> menuCategorias();
                case 2 -> menuProductos();
                case 3 -> menuUsuarios();
                case 4 -> menuPedidos();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void menuCategorias() {
        int opcion;
        do {
            System.out.println("=== MENU CATEGORIAS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            opcion = leerEntero();

            try {
                switch (opcion) {
                    case 1 -> {
                        var categorias = categoriaService.listarCategorias();
                        if (categorias.isEmpty()) {
                            System.out.println("No hay categoras cargadas");
                        } else {
                            categorias.forEach(System.out::println);
                        }
                    }
                    case 2 -> {
                        System.out.print("Ingrese nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese descripcion: ");
                        String descripcion = scanner.nextLine();
                        categoriaService.crearCategoria(nombre, descripcion);
                    }
                    case 3 -> {
                        categoriaService.listarCategorias().forEach(System.out::println);
                        System.out.print("Ingrese ID a editar: ");
                        Long id = Long.valueOf(scanner.nextLine());
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nueva descripcion: ");
                        String nuevaDescripcion = scanner.nextLine();
                        categoriaService.modificarCategoria(id, nuevoNombre, nuevaDescripcion);
                    }
                    case 4 -> {
                        categoriaService.listarCategorias().forEach(System.out::println);
                        
                        System.out.print("Ingrese ID a eliminar: ");
                        Long id = Long.valueOf(scanner.nextLine());
                        System.out.print("Confirma eliminar? (S/N): ");
                        String confirmacion = scanner.nextLine();
                        if (confirmacion.equalsIgnoreCase("S")) {
                             categoriaService.eliminarCategoria(id);
                        } else {
                            System.out.println("Operacion cancelada");
                          
                        }
                       
                    }
                    case 0 -> System.out.println("Volviendo al menu principal...");
                    default -> System.out.println("Opcion invalida. Intente nuevamente.");
                }
            } catch (DatoInvalidoException | EntidadNoEncontradaException | NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void menuProductos() {
        int opcion;
        do {
            System.out.println("=== MENU PRODUCTOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            opcion = leerEntero();

            try {
                switch (opcion) {
                    case 1 -> {
                        System.out.println("1. Listado general");
                        System.out.println("2. Listar por categoria");
                        System.out.print("Seleccione opcion: ");
                        int subOpcion = Integer.parseInt(scanner.nextLine());

                    if (subOpcion == 1) {
                        List<Producto> productos = (List<Producto>) productoService.listarProductos();
                        if (productos.isEmpty()) {
                            System.out.println("No hay productos cargados");
                        } else {
                            productos.forEach(p -> System.out.println(
                                "ID: " + p.getId() +
                                ", Nombre: " + p.getNombre() +
                                ", Precio: " + p.getPrecio() +
                                ", Stock: " + p.getStock() +
                                ", Categoria: " + (p.getCategoria() != null ? p.getCategoria().getNombre() : "Sin categoria")
                            ));
                        }
                    } else if (subOpcion == 2) {
                        categoriaService.listarCategorias().forEach(System.out::println);
                        System.out.print("Ingrese ID de la categoria: ");
                        Long catId = Long.valueOf(scanner.nextLine());

                        List<Producto> productos = productoService.listarProductosPorCategoria(catId);
                        if (productos.isEmpty()) {
                            System.out.println("No hay productos en esa categoria");
                        } else {
                            productos.forEach(p -> System.out.println(
                                "ID: " + p.getId() +
                                ", Nombre: " + p.getNombre() +
                                ", Precio: " + p.getPrecio() +
                                ", Stock: " + p.getStock() +
                                ", Categoria: " + (p.getCategoria() != null ? p.getCategoria().getNombre() : "Sin categoria")
            ));
        }
    }
}


                    case 2 -> {
                        System.out.print("Ingrese nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese precio: ");
                        Double precio = Double.valueOf(scanner.nextLine());
                        System.out.print("Ingrese descripcion: ");
                        String descripcion = scanner.nextLine();
                        System.out.print("Ingrese stock: ");
                        Integer stock = Integer.valueOf(scanner.nextLine());
                        System.out.print("Ingrese imagen: ");
                        String imagen = scanner.nextLine();
                        Boolean disponible = true; // siempre disponible al crear

                        // Asociar categoria
                        categoriaService.listarCategorias().forEach(System.out::println);
                        System.out.print("Ingrese ID de categoria: ");
                        Long idCat = Long.valueOf(scanner.nextLine());
                        Categoria categoria = categoriaService.buscarPorId(idCat);
                        productoService.crearProducto(nombre, precio, descripcion, stock, imagen, disponible, categoria);
                    }
                    case 3 -> {
                        productoService.listarProductos().forEach(System.out::println);
                        System.out.print("Ingrese ID a editar: ");
                        Long id = Long.valueOf(scanner.nextLine());
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nuevo precio: ");
                        Double nuevoPrecio = Double.valueOf(scanner.nextLine());
                        System.out.print("Nueva descripcion: ");
                        String nuevaDescripcion = scanner.nextLine();
                        System.out.print("Nuevo stock: ");
                        Integer nuevoStock = Integer.valueOf(scanner.nextLine());
                        System.out.print("Nueva imagen: ");
                        String nuevaImagen = scanner.nextLine();
                        System.out.print("Disponible (true/false): ");
                        Boolean disponible = Boolean.valueOf(scanner.nextLine());
                        categoriaService.listarCategorias().forEach(System.out::println);
                        System.out.print("Ingrese ID de nueva categoria: ");
                        Long idCat = Long.valueOf(scanner.nextLine());
                        Categoria nuevaCategoria = categoriaService.buscarPorId(idCat);
                        productoService.modificarProducto(id, nuevoNombre, nuevoPrecio, nuevaDescripcion, nuevoStock, nuevaImagen, disponible, nuevaCategoria);
                    }
                
                    case 4 -> {
                        productoService.listarProductos().forEach(System.out::println);
                        System.out.print("Ingrese ID a eliminar: ");
                        Long id = Long.valueOf(scanner.nextLine());
                        productoService.eliminarProducto(id);
                    }
                    case 0 -> System.out.println("Volviendo al menu principal...");
                    default -> System.out.println("Opcion invalida. Intente nuevamente.");
                }
            } catch (DatoInvalidoException | EntidadNoEncontradaException | StockInvalidoException | NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void menuUsuarios() {
    int opcion;
    do {
        System.out.println("=== MENU USUARIOS ===");
        System.out.println("1. Listar");
        System.out.println("2. Crear");
        System.out.println("3. Editar");
        System.out.println("4. Eliminar");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");

        opcion = leerEntero();

        try {
            switch (opcion) {
                // HU-USR-01: Listar usuarios
                case 1 -> {
                    List<Usuario> usuarios = usuarioService.listarUsuarios();
                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios cargados");
                    } else {
                        usuarios.forEach(u -> System.out.println(
                            "ID: " + u.getId() +
                            ", Nombre: " + u.getNombre() +
                            ", Apellido: " + u.getApellido() +
                            ", Mail: " + u.getMail() +
                            ", Rol: " + u.getRol()
                        ));
                    }
                }

                // HU-USR-02: Crear usuario
                case 2 -> {
                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Ingrese mail: ");
                    String mail = scanner.nextLine();
                    System.out.print("Ingrese celular: ");
                    String celular = scanner.nextLine();
                    System.out.print("Ingrese direccion: ");
                    String direccion = scanner.nextLine();

                    Rol rol = Rol.USUARIO; // siempre por defecto

                    try {
                        usuarioService.crearUsuario(nombre, apellido, mail, celular,direccion, rol);
                    } catch (DatoInvalidoException e) {
                        System.out.println("Error al crear usuario: " + e.getMessage());
                    }
                }

                // HU-USR-03: Editar usuario
                case 3 -> {
                    List<Usuario> usuarios = usuarioService.listarUsuarios();
                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios cargados");
                        break;
                    }
                    usuarios.forEach(System.out::println);

                    System.out.print("Ingrese ID a editar: ");
                    Long id = Long.valueOf(scanner.nextLine());

                    try {
                        Usuario usuario = usuarioService.buscarPorId(id);

                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nuevo apellido: ");
                        String nuevoApellido = scanner.nextLine();
                        System.out.print("Nuevo mail: ");
                        String nuevoMail = scanner.nextLine();
                        System.out.print("Nuevo telefono: ");
                        String nuevoTelefono = scanner.nextLine();
                        System.out.print("Nueva direccion: ");
                        String nuevaDireccion = scanner.nextLine();

                        // Validar unicidad del mail si se cambia
                        if (nuevoMail != null && !nuevoMail.isBlank() &&
                            !nuevoMail.equalsIgnoreCase(usuario.getMail())) {
                            boolean existe = usuarioService.listarUsuarios().stream()
                                    .anyMatch(u -> u.getMail().equalsIgnoreCase(nuevoMail));
                            if (existe) {
                                System.out.println("Error: ya existe un usuario con ese mail");
                                break;
                            }
                        }

                        usuarioService.modificarUsuario(
                            id,
                            nuevoNombre,
                            nuevoApellido,
                            nuevoMail,
                            nuevoTelefono,
                            nuevaDireccion,
                            usuario.getRol()
                        );

                        System.out.println("Usuario actualizado con éxito.");
                    } catch (EntidadNoEncontradaException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                // HU-USR-04: Eliminar usuario (baja lógica)
                case 4 -> {
                    List<Usuario> usuarios = usuarioService.listarUsuarios();
                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios cargados");
                        break;
                    }
                    usuarios.forEach(System.out::println);

                    System.out.print("Ingrese ID a eliminar: ");
                    Long id = Long.valueOf(scanner.nextLine());
                    System.out.print("Confirma eliminar? (S/N): ");
                    String confirmacion = scanner.nextLine();

                    if (confirmacion.equalsIgnoreCase("S")) {
                        try {
                            usuarioService.eliminarUsuario(id);
                            System.out.println("Usuario eliminado correctamente.");
                        } catch (EntidadNoEncontradaException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                }

                case 0 -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    } while (opcion != 0);
}


       private void menuPedidos() {
    int opcion;
    do {
        System.out.println("=== MENU PEDIDOS ===");
        System.out.println("1. Listar");
        System.out.println("2. Crear pedido con detalles");
        System.out.println("3. Actualizar estado / forma de pago");
        System.out.println("4. Eliminar");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");

        opcion = leerEntero();

        try {
            switch (opcion) {
                // HU-PED-01: Listar pedidos
                case 1 -> {
                    List<Pedido> pedidos = pedidoService.listarPedidos();
                    if (pedidos.isEmpty()) {
                        System.out.println("No hay pedidos cargados");
                    } else {
                        pedidos.forEach(p -> System.out.println(
                            "ID: " + p.getId() +
                            ", Usuario: " + p.getUsuario().getNombre() + " " + p.getUsuario().getApellido() +
                            ", Estado: " + p.getEstado() +
                            ", Forma de pago: " + p.getFormaPago() +
                            ", Total: " + p.getTotal() +
                            ", Fecha: " + p.getFecha()
                        ));
                    }

                    System.out.print("¿Desea filtrar por usuario? (S/N): ");
                    String resp = scanner.nextLine();
                    if (resp.equalsIgnoreCase("S")) {
                        usuarioService.listarUsuarios().forEach(System.out::println);
                        System.out.print("Ingrese ID usuario: ");
                        Long idUsuario = Long.valueOf(scanner.nextLine());
                        List<Pedido> pedidosUsuario = pedidoService.listarPedidosPorUsuario(idUsuario);
                        pedidosUsuario.forEach(System.out::println);
                    }
                }

                // HU-PED-02: Crear pedido con detalles
                case 2 -> {
                    usuarioService.listarUsuarios().forEach(System.out::println);
                    System.out.print("Ingrese ID usuario: ");
                    Long idUsuario = Long.valueOf(scanner.nextLine());
                    Usuario usuario = usuarioService.buscarPorId(idUsuario);

                    System.out.println("Seleccione forma de pago: 1.TARJETA 2.TRANSFERENCIA 3.EFECTIVO");
                    int fp = leerEntero();
                    var formaPago = switch (fp) {
                        case 1 -> FormaPago.TARJETA;
                        case 2 -> FormaPago.TRANSFERENCIA;
                        default -> FormaPago.EFECTIVO;
                    };

                    System.out.println("Seleccione estado inicial: 1.PENDIENTE 2.CONFIRMADO 3.TERMINADO 4.CANCELADO");
                    int est = leerEntero();
                    var estado = switch (est) {
                        case 1 -> Estado.PENDIENTE;
                        case 2 -> Estado.CONFIRMADO;
                        case 3 -> Estado.TERMINADO;
                        default -> Estado.CANCELADO;
                    };

                    Pedido pedido = pedidoService.crearPedido(usuario, formaPago, estado);

                    boolean seguir = true;
                    while (seguir) {
                        productoService.listarProductos().forEach(System.out::println);
                        System.out.print("Ingrese ID producto: ");
                        Long idProducto = Long.valueOf(scanner.nextLine());
                        Producto producto = productoService.buscarPorId(idProducto);

                        System.out.print("Cantidad: ");
                        int cantidad = Integer.parseInt(scanner.nextLine());

                        pedido.addDetallePedido(producto, cantidad); // obligatorio

                        System.out.print("¿Agregar otro producto? (S/N): ");
                        String resp = scanner.nextLine();
                        if (!resp.equalsIgnoreCase("S")) {
                            seguir = false;
                        }
                    }

                    pedido.calcularTotal(); // obligatorio con interfaz Calculable
                    System.out.println("Pedido creado con éxito: " + pedido);
                }

                // HU-PED-03: Actualizar estado / forma de pago
                case 3 -> {
                    pedidoService.listarPedidos().forEach(System.out::println);
                    System.out.print("Ingrese ID pedido: ");
                    Long idPedido = Long.valueOf(scanner.nextLine());

                    System.out.println("Seleccione nuevo estado: 1.PENDIENTE 2.CONFIRMADO 3.TERMINADO 4.CANCELADO");
                    int est = leerEntero();
                    var estado = switch (est) {
                        case 1 -> Estado.PENDIENTE;
                        case 2 -> Estado.CONFIRMADO;
                        case 3 -> Estado.TERMINADO;
                        default -> Estado.CANCELADO;
                    };

                    System.out.println("Seleccione nueva forma de pago: 1.TARJETA 2.TRANSFERENCIA 3.EFECTIVO");
                    int fp = leerEntero();
                    var formaPago = switch (fp) {
                        case 1 -> FormaPago.TARJETA;
                        case 2 -> FormaPago.TRANSFERENCIA;
                        default -> FormaPago.EFECTIVO;
                    };

                    pedidoService.modificarPedido(idPedido, estado, formaPago);
                    System.out.println("Pedido actualizado con éxito.");
                }

                // HU-PED-04: Eliminar pedido (baja lógica)
                case 4 -> {
                    pedidoService.listarPedidos().forEach(System.out::println);
                    System.out.print("Ingrese ID pedido a eliminar: ");
                    Long idPedido = Long.valueOf(scanner.nextLine());
                    System.out.print("Confirma eliminar? (S/N): ");
                    String confirmacion = scanner.nextLine();

                    if (confirmacion.equalsIgnoreCase("S")) {
                        pedidoService.eliminarPedido(idPedido);
                        System.out.println("Pedido eliminado correctamente.");
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                }

                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } catch (DatoInvalidoException | EntidadNoEncontradaException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    } while (opcion != 0);
}

    private int leerEntero() {
    try {
        return Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Debe ingresar un número.");
        return -1; // valor inválido para forzar repetir
    }
}
}