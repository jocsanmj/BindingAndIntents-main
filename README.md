# Proyecto Android: Implementación de Data Binding e Intents

<img src="https://uxwing.com/wp-content/themes/uxwing/download/brands-and-social-media/android-studio-icon.png" alt="Logo Android" width="100" />

Este proyecto tiene como objetivo demostrar el uso de **Data Binding** e **Intents** en aplicaciones Android. Fue desarrollado con **Android Studio Koala 2024.1.1 Patch 1** (lanzado el 10 de julio de 2024) y presenta una serie de ejemplos prácticos para integrar navegación, compartir contenido y realizar acciones como llamadas o abrir sitios web.

## 📝 Descripción del Proyecto

Este repositorio contiene ejemplos de cómo utilizar el **Data Binding** para simplificar la interacción con la interfaz de usuario y cómo los **Intents** permiten realizar diferentes acciones dentro de la app y con otras apps del dispositivo. El objetivo principal es ofrecer una base sólida para desarrollar apps Android con estos componentes esenciales.

---

## 🛠 Características Principales

### 1. **Enlace Directo entre UI y Código con Data Binding**
- Se ha implementado **Data Binding** para vincular componentes de la interfaz como `TextView` directamente con propiedades de código Kotlin, eliminando la necesidad de invocar `findViewById`. Esto mejora la eficiencia del desarrollo y facilita el mantenimiento del código.

### 2. **Navegación Fluida entre Actividades con Intents**
- Implementación de navegación explícita entre actividades mediante **Intents**. Un botón permite cambiar de una actividad a otra, mostrando mensajes personalizados en la actividad secundaria.

### 3. **Compatibilidad con Otras Aplicaciones Mediante Intents Implícitos**
- Se ha configurado un botón que utiliza un **Intent implícito** para compartir el contenido de un `TextView` o un mensaje predefinido con otras apps instaladas en el dispositivo.

### 4. **Funcionalidades de Llamadas y Navegación Web**
- Incluye un campo `EditText` donde el usuario puede introducir un número de teléfono para realizar una llamada. De forma similar, se ha implementado un botón para abrir una URL introducida por el usuario en el navegador del dispositivo.

### 5. **Enviar Correos Electrónicos**
- Utiliza un **Intent** implícito para abrir el cliente de correo y enviar un mensaje predefinido.

### 6. **Compartir Imágenes**
- Un botón permite compartir la imagen mostrada en un `ImageView` a través de otras aplicaciones que soporten el formato de imagen.

### 7. **Acceso a la Ubicación Actual**
- A través de un **Intent**, se lanza una aplicación de mapas que muestra la ubicación actual del dispositivo.

### 8. **Acceso Directo a Configuraciones del Dispositivo**
- Utiliza un **Intent** para llevar al usuario a la pantalla de configuración del dispositivo.

### 9. **Reproducción de Videos de YouTube**
- Permite al usuario ingresar un ID de video de YouTube en un campo `EditText`, y luego abrir la app de YouTube para reproducirlo automáticamente.


## 🔗 Enlace de Descarga

Puedes descargar el APK del proyecto a través del siguiente enlace:

**[Descargar la APK](https://drive.google.com/file/d/1FJYYLecCz7UsCyiYW9JlZGLI9opkYpRj/view?usp=drive_link)**

---

