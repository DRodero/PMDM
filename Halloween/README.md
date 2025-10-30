# 🎃 Conversión de Juego Web a App Android con Capacitor

## Objetivo del Proyecto

En este tutorial aprenderás a transformar tu **Juego de Halloween** (o cualquier aplicación web) en una aplicación nativa Android utilizando **Capacitor**. Al final tendrás una APK instalable en dispositivos Android.

## 📋 Requisitos Previos

### Software Necesario
- ✅ **Node.js** (versión 16 o superior) - [Descargar aquí](https://nodejs.org/)
- ✅ **Android Studio** - [Descargar aquí](https://developer.android.com/studio)
- ✅ **Java Development Kit (JDK)** 8 o superior

### Verificar Instalación
Abre una terminal y ejecuta:
```bash
node --version
npm --version
```

### Juego de Ejemplo
Para este tutorial usaremos como referencia el **Juego de Halloween - Atrapa Calabazas** que incluye:
- `index.html` - Estructura HTML
- `halloween.css` - Estilos CSS
- `halloween.js` - Lógica del juego en JavaScript
- Recursos gráficos (opcional)

## 🚀 Pasos para Crear la App Android

### 1. Preparar el Proyecto Base

Crea una carpeta para tu proyecto Capacitor:
```bash
mkdir mi-juego-android
cd mi-juego-android
```

Inicializa el proyecto npm:
```bash
npm init -y
```

### 2. Instalar Capacitor

Instala las dependencias principales de Capacitor:
```bash
npm install @capacitor/core @capacitor/cli
```

### 3. Preparar los Archivos Web

#### 3.1 Crear la carpeta web
```bash
mkdir www
```

#### 3.2 Copiar tu juego
Copia **todos los archivos** de tu juego web a la carpeta `www/`:
- `index.html`
- `halloween.css`
- `halloween.js`
- Carpeta de imágenes (si tienes)
- Cualquier otro archivo CSS, JS o recursos

**Estructura esperada:**
```
mi-juego-android/
├── package.json
├── www/
│   ├── index.html
│   ├── halloween.css
│   ├── halloween.js
│   └── (otros archivos del juego)
└── node_modules/
```

### 4. Configurar Capacitor

Inicializa Capacitor en tu proyecto:
```bash
npx cap init
```

Te pedirá la siguiente información:
- **App name**: `Juego Halloween` (o el nombre que prefieras)
- **App ID**: `com.tuapellido.halloween` (ejemplo: `com.garcia.halloween`)
- **Web asset directory**: `www` (confirma que es correcto)

### 5. Añadir Soporte para Android

Instala el plugin de Android:
```bash
npm install @capacitor/android
```

Añade la plataforma Android:
```bash
npx cap add android
```

### 6. Sincronizar el Proyecto

Copia todos los archivos web al proyecto Android:
```bash
npx cap sync android
```

> **⚠️ Importante**: Ejecuta `npx cap sync android` cada vez que modifiques archivos en la carpeta `www/`

### 7. Abrir en Android Studio

Abre el proyecto en Android Studio:
```bash
npx cap open android
```

## 📱 Compilar y Generar APK

### En Android Studio:

1. **Espera** a que Android Studio termine de sincronizar el proyecto
2. **Conecta tu dispositivo Android** o configura un emulador
3. Para **probar la app**: Haz clic en el botón ▶️ "Run"
4. Para **generar APK**:
   - Ve a: `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`
   - El APK se generará en: `android/app/build/outputs/apk/debug/`

### Firmar APK para Distribución (Opcional)
Para publicar en Google Play Store:
- Ve a: `Build` → `Generate Signed Bundle / APK`
- Sigue el asistente para crear una clave de firma

## 🔧 Personalización Adicional

### Cambiar Icono de la App
1. Coloca tu icono (PNG, 1024x1024px) en: `android/app/src/main/res/`
2. Usa Android Studio: `File` → `New` → `Image Asset`

### Cambiar Nombre de la App
Edita el archivo: `android/app/src/main/res/values/strings.xml`
```xml
<string name="app_name">Mi Juego Halloween</string>
```

### Configuraciones de Pantalla
En `android/app/src/main/res/values/styles.xml` puedes:
- Ocultar la barra de estado
- Cambiar orientación (portrait/landscape)
- Personalizar colores

## 🐛 Problemas Comunes y Soluciones

### Error: "Web asset directory not found"
**Solución**: Verifica que la carpeta `www/` exista y contenga `index.html`

### Error: "Android SDK not found"
**Solución**: 
1. Abre Android Studio
2. Ve a `SDK Manager`
3. Instala Android SDK y Build Tools

### La app no refleja cambios
**Solución**: 
1. Guarda cambios en `www/`
2. Ejecuta: `npx cap sync android`
3. En Android Studio: `Build` → `Clean Project`

### Problemas de permisos
**Solución**: Edita `android/app/src/main/AndroidManifest.xml` y añade permisos necesarios:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## 📚 Comandos de Referencia Rápida

```bash
# Sincronizar cambios web con Android
npx cap sync android

# Abrir proyecto en Android Studio
npx cap open android

# Limpiar y reconstruir
npx cap clean android
npx cap sync android

# Ver logs en tiempo real
npx cap run android --livereload --external
```

## 🎯 Resultado Esperado

Al completar todos los pasos tendrás:
- ✅ Una aplicación Android nativa
- ✅ APK instalable en cualquier dispositivo Android
- ✅ Tu juego web funcionando como app móvil
- ✅ Icono personalizado en el launcher de Android

## 📞 Ayuda Adicional

### Documentación Oficial
- [Capacitor Docs](https://capacitorjs.com/docs)
- [Android Developer Guide](https://developer.android.com/guide)

### Recursos del Curso
- Revisa los ejemplos en la carpeta `Halloween/`
- Consulta con el profesor cualquier duda específica

---

**¡Felicidades! 🎉 Has convertido tu juego web en una aplicación Android profesional.**

> **Nota para el profesor**: Este README está diseñado para el nivel de 2º DAM y asume conocimientos básicos de HTML, CSS y JavaScript. Los estudiantes deberían poder seguir estos pasos de manera autónoma.