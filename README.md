# Notas de Sabiduría

Aplicación Android para guardar y organizar citas y reflexiones de libros y películas.

## Características

- Almacenar citas con autor, fuente y contexto
- Diferenciación entre libros (página) y películas (minuto)
- Lista de todas las citas guardadas
- Eliminar citas
- Persistencia local con SQLite

## Tecnologías

- **Lenguaje**: Java/Kotlin
- **SDK mínimo**: Android 7.0 (API 24)
- **SDK objetivo**: Android 15 (API 35)
- **Base de datos**: SQLite
- **UI**: RecyclerView, Material Design

## Estructura del Proyecto

```
app/src/main/java/com/cesarforall/notasdesabiduria/
├── activity/
│   ├── MainActivity.java          # Pantalla principal con lista de citas
│   └── AddTextActivity.java       # Pantalla para añadir nuevas citas
├── adapter/
│   └── TextAdapter.java           # Adaptador del RecyclerView
├── db/
│   └── DatabaseHelper.java        # Gestor de base de datos SQLite
└── model/
    └── Text.java                  # Modelo de datos para citas
```

## Instalación

1. Clonar el repositorio
2. Abrir el proyecto en Android Studio
3. Sincronizar Gradle
4. Ejecutar en un dispositivo o emulador Android

## Uso

1. Presionar el botón de añadir para crear una nueva cita
2. Ingresar el texto, autor, fuente y tipo de fuente (libro/película)
3. Para libros: especificar número de página
4. Para películas: especificar minuto
5. La cita se guarda automáticamente en la base de datos local

## Versión

**v1.0.0** - Versión inicial
