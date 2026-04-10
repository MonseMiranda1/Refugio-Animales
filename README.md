# Refugio-Animales
🐾 Refugio de Animales (Java)

Sistema de consola desarrollado en Java para la gestión de un refugio de animales. Permite registrar especies, ingresar animales, gestionar adopciones y generar reportes.

📌 Funcionalidades
Registrar especies de animales
Registrar animales con ID único
Marcar animales como adoptados
Listar animales disponibles
Listar animales adoptados
Generar reporte general
🧠 Estructuras de Datos Utilizadas
List<Integer> → Almacena IDs de animales
Map<Integer, String> → Relaciona ID con:
Nombre del animal
Estado (Disponible / Adoptado)
Especie
Set<String> → Almacena especies únicas
▶️ Ejecución
Compilar el programa:
javac RefugioAnimales.java
Ejecutar:
java RefugioAnimales
📋 Menú del Sistema
=== REFUGIO DE ANIMALES ===
1. Registrar animal
2. Registrar especie
3. Marcar animal como adoptado
4. Mostrar animales disponibles
5. Mostrar animales adoptados
6. Mostrar reporte general
7. Salir
🧩 Lógica del Programa
🔹 Registrar Especie

Permite agregar nuevas especies al sistema evitando duplicados.

🔹 Registrar Animal
Solicita nombre y especie
Valida que la especie exista
Asigna ID automático
Estado inicial: Disponible
🔹 Adoptar Animal
Muestra lista de animales disponibles
Permite seleccionar por ID
Cambia estado a Adoptado
🔹 Mostrar por Estado

Filtra animales según:

Disponibles
Adoptados
🔹 Reporte General

Muestra:

Total de animales
Cantidad disponibles
Cantidad adoptados
Tabla completa con:
ID
Nombre
Especie
Estado
⚠️ Validaciones Incluidas
Verificación de entrada numérica
Control de IDs existentes
Prevención de adopciones duplicadas
Validación de especies registradas
💡 Ejemplo de Salida
=== REPORTE GENERAL ===
Total animales: 3
Disponibles: 2
Adoptados: 1

ID | Nombre | Especie | Estado
1 | Luna | Perro | Disponible
2 | Milo | Gato | Adoptado
3 | Rocky | Perro | Disponible
🚀 Posibles Mejoras
Persistencia de datos (archivos o base de datos)
Interfaz gráfica (JavaFX / Swing)
Búsqueda por nombre
Eliminación de animales
Filtro por especie
