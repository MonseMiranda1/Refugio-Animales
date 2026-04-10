import java.util.*; /// Importamos todas las clases del paquete java.util para usar List, Map, Set, Scanner, etc.
public class RefugioAnimales {
   public static void main(String[] args) {
       // Scanner para leer datos del usuario
       Scanner sc = new Scanner(System.in);
       // ============================
       // ESTRUCTURAS DE DATOS
       // ============================
       // Lista de animales (solo nombres)
       List<String> animales = new ArrayList<>();
       // Mapa: Animal -> Estado (Disponible / Adoptado)
       Map<String, String> estadoAnimal = new HashMap<>();/// Se podría usar un ArrayList de objetos, pero el HashMap es más eficiente para buscar por nombre y actualizar estado
       // Set de especies (no permite duplicados)
       Set<String> especies = new HashSet<>(); /// Se podría usar un ArrayList, pero el HashSet es más eficiente para validar duplicados
       // Mapa: Animal -> Especie
       Map<String, String> animalEspecie = new HashMap<>();
       // Array fijo de estados
       String[] estados = {"Disponible", "Adoptado"};
       int opcion;
       // ============================
       // MENÚ PRINCIPAL (do-while)
       // ============================
       do { /// El do-while asegura que el menú se muestre al menos una vez y se repita hasta que el usuario decida salir
           System.out.println("\n=== REFUGIO DE ANIMALES ===");
           System.out.println("1. Registrar animal");
           System.out.println("2. Registrar especie");
           System.out.println("3. Marcar animal como adoptado");
           System.out.println("4. Mostrar animales disponibles");
           System.out.println("5. Mostrar animales adoptados");
           System.out.println("6. Mostrar reporte general");
           System.out.println("7. Salir");
           System.out.print("Seleccione una opción: ");
           // Validación para evitar que el programa se rompa
           if (!sc.hasNextInt()) { /// Si el usuario ingresa algo que no es un número, se muestra un mensaje de error y se limpia el buffer para evitar un bucle infinito
               System.out.println("Debe ingresar un número válido");
               sc.nextLine(); // limpiar buffer
               opcion = 0;
               continue;
           }
           opcion = sc.nextInt(); /// Lee la opción seleccionada por el usuario
           sc.nextLine(); // limpiar buffer
           switch (opcion) {
               case 1:
                   registrarAnimal(sc, animales, estadoAnimal, especies, animalEspecie); /// Se pasan todas las estructuras necesarias para registrar un animal, incluyendo la validación de especie y el registro del estado y especie del animal
                   break;
               case 2:
                   registrarEspecie(sc, especies);
                   break;
               case 3:
                   adoptarAnimal(sc, animales, estadoAnimal);
                   break;
               case 4:
                   mostrarPorEstado(animales, estadoAnimal, estados[0]);
                   break;
               case 5:
                   mostrarPorEstado(animales, estadoAnimal, estados[1]);
                   break;
               case 6:
                   reporte(animales, estadoAnimal, animalEspecie);
                   break;
               case 7:
                   System.out.println("Saliendo del sistema...");
                   break;
               default:
                   System.out.println("Opción inválida");
           }
       } while (opcion != 7);
       sc.close();
   }
   // ============================
   // REGISTRAR ESPECIE
   // ============================
   public static void registrarEspecie(Scanner sc, Set<String> especies) {
       System.out.print("Ingrese especie: ");
       String especie = sc.nextLine().trim();
       // add() devuelve false si ya existe
       if (especies.add(especie)) { /// El método add() del Set devuelve true si la especie se agregó correctamente (es decir, no existía antes) y false si ya existía, lo que permite validar duplicados de forma sencilla
           System.out.println("Especie registrada correctamente");
       } else {  /// Si la especie ya existe, se muestra un mensaje de error
           System.out.println("La especie ya existe");
       }
   }
   // ============================
   // REGISTRAR ANIMAL
   // ============================
   public static void registrarAnimal(Scanner sc, // Se pasan todas las estructuras necesarias para registrar un animal, incluyendo la validación de especie y el registro del estado y especie del animal
                                      List<String> animales,
                                      Map<String, String> estadoAnimal,
                                      Set<String> especies,
                                      Map<String, String> animalEspecie) {
       System.out.print("Nombre del animal: ");
       String nombre = sc.nextLine().trim();
       // Validación: no duplicados
       if (animales.contains(nombre)) {
           System.out.println("El animal ya está registrado");
           return;
       }
       System.out.print("Especie: ");
       String especie = sc.nextLine().trim(); /// Se lee la especie del animal y se valida que exista en el Set de especies antes de registrar el animal, lo que garantiza que solo se puedan registrar animales de especies previamente registradas
       // Validación: especie debe existir
       if (!especies.contains(especie)) { /// Si la especie ingresada no existe en el Set de especies, se muestra un mensaje de error y se cancela el registro del animal
           System.out.println("La especie no existe. Regístrala primero."); /// Se le indica al usuario que debe registrar la especie antes de poder registrar un animal de esa especie, lo que mejora la experiencia del usuario y mantiene la integridad de los datos
           return;
       }
       // Guardar datos
       animales.add(nombre);
       estadoAnimal.put(nombre, "Disponible");
       animalEspecie.put(nombre, especie);
       System.out.println("Animal registrado correctamente");
   }
   // ============================
   // ADOPTAR ANIMAL
   // ============================
   public static void adoptarAnimal(Scanner sc,
                                    List<String> animales,
                                    Map<String, String> estadoAnimal) {
       boolean hayDisponibles = false;
       System.out.println("Animales disponibles:");
       // Mostrar solo disponibles
       for (String animal : animales) {
           if (estadoAnimal.get(animal).equals("Disponible")) {
               System.out.println("- " + animal);
               hayDisponibles = true;
           }
       }
       if (!hayDisponibles) {
           System.out.println("No hay animales disponibles");
           return;
       }
       System.out.print("Ingrese el nombre del animal a adoptar: ");
       String nombre = sc.nextLine().trim();
       // Validación: existe
       if (!animales.contains(nombre)) {
           System.out.println("El animal no existe");
           return;
       }
       // Validación: ya adoptado
       if (estadoAnimal.get(nombre).equals("Adoptado")) {
           System.out.println("El animal ya fue adoptado");
           return;
       }
       // Cambiar estado
       estadoAnimal.put(nombre, "Adoptado");
       System.out.println("Animal adoptado correctamente");
   }
   // ============================
   // MOSTRAR POR ESTADO
   // ============================
   public static void mostrarPorEstado(List<String> animales,
                                       Map<String, String> estadoAnimal,
                                       String estado) {
       boolean encontrado = false;
       for (String animal : animales) {
           if (estadoAnimal.get(animal).equals(estado)) {
               System.out.println("- " + animal);
               encontrado = true;
           }
       }
       if (!encontrado) {
           System.out.println("No hay animales en este estado");
       }
   }
   // ============================
   // REPORTE GENERAL
   // ============================
   public static void reporte(List<String> animales,
                              Map<String, String> estadoAnimal,
                              Map<String, String> animalEspecie) {
       int disponibles = 0;
       int adoptados = 0;
       // Contar estados
       for (String estado : estadoAnimal.values()) {
           if (estado.equals("Disponible")) disponibles++;
           if (estado.equals("Adoptado")) adoptados++;
       }
       System.out.println("\n=== REPORTE GENERAL ===");
       System.out.println("Total animales: " + animales.size());
       System.out.println("Disponibles: " + disponibles);
       System.out.println("Adoptados: " + adoptados);
       // Tabla
       System.out.println("\nNombre | Especie | Estado");/// Se agrega la columna de especie al reporte general para proporcionar información más completa sobre cada animal registrado
       for (String animal : animales) { /// Se recorre la lista de animales para mostrar su nombre, especie y estado en el reporte general, lo que permite al usuario tener una visión completa de todos los animales registrados en el refugio
           System.out.println(animal + " | " /// Se muestra el nombre del animal en el reporte general, lo que permite identificar cada animal registrado en el refugio
                   + animalEspecie.get(animal) + " | " /// Se muestra la especie del animal en el reporte general, lo que proporciona información más completa sobre cada animal registrado
                   + estadoAnimal.get(animal)); /// Se muestra el estado del animal (Disponible o Adoptado) en el reporte general, lo que permite al usuario conocer la situación actual de cada animal registrado en el refugio
       }
   }
}
