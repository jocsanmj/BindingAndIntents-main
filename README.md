# Binding e Intents en Android

<img src="https://uxwing.com/wp-content/themes/uxwing/download/brands-and-social-media/android-studio-icon.png" alt="Logo Android" width="100" />


Este proyecto es una demostración de cómo utilizar **Data Binding** y **Intents** en Android, construido con Android Studio Koala 2024.1.1 Patch 1 (AI-241.180034.62.2411.12071903) publicado en julio 10, 2024. Incluye varios ejemplos de cómo interactuar con los componentes de Android para manejar navegación entre actividades, compartir contenido, realizar llamadas, y más.

**[Descargar la APK](https://unanmanagua-my.sharepoint.com/:f:/g/personal/jasson_martinez21041657_estu_unan_edu_ni/EpxCnIBHnh5OsLYkgFk9GmUBasfFZvPzRbRkNyLa9RnOaA?e=Ryns5a)**

---

## 📜 Resumen de Binding e Intents

### Binding
**Data Binding** en Android permite conectar los componentes de UI (como `TextView`, `ImageView`, etc.) directamente a las propiedades de tu código Kotlin. Esto elimina la necesidad de llamadas repetitivas a `findViewById`, lo que simplifica el código y mejora el rendimiento de la app.

### Intents
Los **Intents** son mensajes que permiten la comunicación entre diferentes componentes de la app (como actividades y servicios). Pueden ser **explícitos**, para abrir una actividad específica, o **implícitos**, para realizar acciones en otras apps, como abrir una página web o hacer una llamada telefónica.

---

## 📱 Versión de la Aplicación

- **Versión de Android**: Nougat 7.1.1 (API Level 25)

---

## 📖 Funcionalidades

### 1. Mostrar un Mensaje en un TextView
Crea un `TextView` en el layout y usa **Data Binding** para vincularlo con el código Kotlin. Luego, establece un mensaje dinámico desde la actividad principal para ser mostrado en la interfaz de usuario.

### 2. Navegación entre Actividades
Incluye dos actividades. Desde la primera actividad, un botón utiliza un **Intent** explícito para abrir la segunda actividad, donde se muestra un mensaje de bienvenida.

### 3. Compartir Contenido
Implementa un botón que utiliza un **Intent** implícito para compartir un mensaje predefinido o el contenido de un `TextView` a través de otras aplicaciones disponibles en el dispositivo.

### 4. Realizar una Llamada
Incluye un `EditText` para ingresar un número de teléfono. Al presionar un botón, un **Intent** implícito inicia una llamada al número proporcionado.

### 5. Abrir una Página Web
Implementa un `EditText` donde el usuario puede ingresar una URL. Al presionar un botón, se abre el navegador del dispositivo para cargar la página web.

---

## ⚙️ Opcionales

### 6. Envío de Correo Electrónico
Un botón que permite al usuario enviar un correo electrónico predefinido utilizando un **Intent** implícito.

### 7. Compartir Imágenes
Un `ImageView` que muestra una imagen y un botón que, al ser presionado, utiliza un **Intent** implícito para compartir la imagen a través de otras apps.

### 8. Obtener la Ubicación del Dispositivo
Un botón que abre una aplicación de mapas utilizando un **Intent** implícito, mostrando la ubicación actual del dispositivo.

### 9. Abrir Configuraciones del Dispositivo
Un botón que utiliza un **Intent** implícito para abrir la pantalla de configuración del dispositivo, permitiendo ajustar varias configuraciones.

### 10. Reproducir un Video de YouTube
Un `EditText` donde el usuario puede ingresar un ID de video de YouTube. Al presionar un botón, se abre la app de YouTube y reproduce el video correspondiente.

---

### 💾 [Descargar la APK aquí](https://unanmanagua-my.sharepoint.com/:f:/g/personal/jasson_martinez21041657_estu_unan_edu_ni/EpxCnIBHnh5OsLYkgFk9GmUBasfFZvPzRbRkNyLa9RnOaA?e=Ryns5a)

---

## 👨‍💻 Herramientas Utilizadas
- **IDE**: Android Studio Koala 2024.1.1 Patch 1
- **API Level**: 25 (Nougat 7.1.1)
