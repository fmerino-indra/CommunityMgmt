# PRD – CommunityMgmt

## 1. Contexto y propósito del documento

Este PRD (Product Requirements Document) describe **qué es CommunityMgmt, qué problema resuelve y cómo debe comportarse el sistema**, antes de entrar en decisiones técnicas de implementación. El objetivo principal es **validar entendimiento del dominio** y fijar un marco común de trabajo.

El documento se redacta desde una perspectiva **de producto + dominio**, no de infraestructura.

---

## 2. Definiciones previas (lenguaje ubicuo)

### 2.1 Comunidad

Una **Comunidad** es un grupo relativamente estable de personas ("hermanos") que recorren juntas el Camino Neocatecumenal dentro de una parroquia concreta.

Características:
- Tiene identidad propia
- Tiene un responsable (y equipo responsable)
- Celebra regularmente Eucaristía, Palabra y Convivencia
- Evoluciona por etapas del Camino
- Comparte reglas litúrgicas comunes, con variaciones por etapa

La comunidad es la **unidad básica de planificación**.

---

### 2.2 Hermano / Miembro

Un **hermano** es una persona perteneciente a una comunidad.

Propiedades relevantes:
- Datos personales y de contacto
- Relación conyugal (matrimonio)
- Carismas / roles asignados

Notas importantes:
- El matrimonio es una relación de dominio explícita
- Algunos carismas se ejercen siempre en matrimonio (responsable, catequista, ostiario)
- Otros carismas son estrictamente personales (lector, salmista, garante, etc.)

---

### 2.3 Carisma / Rol

Un **carisma** es una función reconocida dentro de la comunidad.

Características:
- No todos están disponibles desde el inicio del Camino
- Se asignan normalmente tras elecciones en convivencias
- Su duración es larga pero no indefinida
- Algunos carismas habilitan funcionalidades del sistema

Ejemplos:
- Responsable de comunidad
- Equipo responsable
- Catequista
- Garante
- Lector / Salmista (y responsables respectivos)

---

### 2.4 Celebración

Una **celebración** es un evento comunitario con fecha concreta y significado litúrgico.

Tipos principales:
- Eucaristía
- Celebración de la Palabra
- Convivencia

Propiedades:
- Fecha y tipo
- Contexto litúrgico
- Comunidad (o comunidades) implicadas
- Necesidad o no de preparación previa

La celebración es una **entidad central**, no un simple evento de calendario.

---

### 2.5 Preparación

La **preparación** es una reunión previa necesaria para algunas celebraciones.

Características:
- Siempre asociada a una celebración concreta
- Realizada por un grupo asignado
- Se celebra normalmente días antes
- Tiene lugar, fecha y hora propuestos por el grupo

El sistema **no gestiona el contenido espiritual**, solo la logística.

---

### 2.6 Grupo de preparación

Un **grupo de preparación** es un conjunto de hermanos asignados a preparar una celebración concreta.

Características:
- Se genera automáticamente (normalmente por sorteo)
- Tiene reglas de tamaño mínimo y objetivo
- Puede modificarse antes de su publicación oficial
- Tiene trazabilidad histórica

El grupo existe **porque existe una celebración**.

---

## 3. Problema que resuelve el producto

Las comunidades neocatecumenales gestionan actualmente:
- Calendarios complejos
- Reglas litúrgicas no triviales
- Equilibrio en las asignaciones a grupos
- Cambios frecuentes y excepciones

Todo ello se gestiona habitualmente con:
- Papeles
- Excel
- WhatsApp
- Memoria del responsable

Esto provoca:
- Errores
- Sobrecarga del responsable
- Desequilibrios en las asignaciones a grupos
- Falta de visión global

CommunityMgmt busca **centralizar, automatizar y hacer justo** este proceso, sin banalizar el contenido espiritual.

---

## 4. Objetivos del producto

### Objetivos principales

1. Facilitar la planificación anual de celebraciones
2. Automatizar la creación y reparto de grupos de preparación
3. Respetar estrictamente la lógica litúrgica real
4. Reducir la carga organizativa del responsable
5. Dar visibilidad clara a todos los hermanos

### Objetivos explícitamente fuera de alcance

- Preparación litúrgica o catequética
- Contenido espiritual
- Evaluación de la vivencia de los hermanos

---

## 5. Funcionalidades de alto nivel

### 5.1 Gestión de miembros

- Enrollment controlado (QR cifrado)
- Edición de datos personales
- Gestión de matrimonios
- Asignación y revocación de carismas
- Sorteo y gestión de grupos de garantes

---

### 5.2 Planificación de celebraciones

- Apertura y cierre de año pastoral
- Planificación por períodos configurables
- Generación automática de celebraciones
- Aplicación de reglas litúrgicas
- Gestión de pausas (Navidad, Semana Santa, verano)

Caso especial:
- En verano las Eucaristías se celebran intercomunitariamente y las preparaciones se reparten entre comunidades

---

### 5.3 Gestión de grupos de preparación

- Creación automática por sorteo
- Ajustes manuales antes de publicación
- Publicación oficial
- Solicitudes de cambio de grupo
- Registro histórico de cambios

---

### 5.4 Uso comunitario

- Visualización clara del calendario
- Detalle de celebraciones y grupos
- Lista completa de los miembros de la comunidad
- Acceso al detalle de cada miembro, permitiendo:
  - Navegación GPS hasta su domicilio
  - Llamada telefónica directa
  - Consulta de sus datos básicos
- Propuestas internas dentro del grupo:
  - Fecha y lugar de preparación
  - Aceptación
  - Propuesta de plato para la cena
- Acceso a navegación (Maps, Waze, etc.)
- Bloqueo completo de capturas de pantalla en toda la aplicación

---

## 6. Principios no funcionales clave

- Offline-first en cliente móvil
- Separación estricta de capas
- Dominio expresivo y explícito
- Reglas trazables y explicables
- Evitar simplificaciones pastorales

---

## 7. Criterio de éxito del producto

CommunityMgmt será un éxito si:
- El responsable confía en el sistema
- Los repartos se perciben como justos
- Las reglas litúrgicas no se "rompen"
- El sistema puede crecer sin volverse inmanejable

---

**Fin del PRD (versión inicial)**

