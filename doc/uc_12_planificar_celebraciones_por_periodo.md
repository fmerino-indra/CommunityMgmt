# UC-12 – Planificar celebraciones por período

## Identificador
UC-12

## Nombre
Planificar celebraciones por período

---

## Actor principal
Responsable de la comunidad (o hermano delegado explícitamente)

## Actores secundarios
- Sistema

---

## Objetivo
Generar de forma controlada las celebraciones comunitarias (Eucaristía, Palabra, Convivencia) dentro de un período temporal definido, aplicando automáticamente las reglas litúrgicas y comunitarias vigentes, **sin publicar los resultados**.

---

## Descripción
Este caso de uso constituye el núcleo de la planificación operativa de la comunidad. A partir de un período temporal definido por el responsable, el sistema calcula qué celebraciones deben existir, en qué fechas y bajo qué condiciones.

La planificación es **determinista y explicable**, basada en:
- Calendario litúrgico
- Reglas comunitarias (según etapa del camino)
- Pausas litúrgicas y excepciones

El resultado es un conjunto de celebraciones en estado **PLANIFICADA**, preparadas para revisión y posterior generación de grupos.

---

## Precondiciones
- Existe una comunidad activa
- Existe exactamente un año pastoral en estado **ABIERTO** (UC-10 ejecutado)
- El actor tiene permisos de planificación
- El período solicitado está completamente contenido dentro del año pastoral abierto

---

## Disparador
El responsable solicita la planificación de celebraciones para un período concreto.

---

## Flujo principal
1. El actor selecciona el tipo de período a planificar:
   - Mes natural
   - Trimestre
   - Rango de fechas personalizado

2. El sistema valida que el período:
   - Está contenido dentro del año pastoral abierto
   - Es coherente (fecha inicio < fecha fin)

3. El sistema identifica las reglas litúrgicas aplicables al período:
   - Tiempo litúrgico
   - Solemnidades, fiestas y memorias
   - Días con tratamiento especial

4. El sistema identifica las pausas litúrgicas y excepciones:
   - Semana Santa
   - Navidad
   - Otras pausas definidas

5. El sistema identifica las reglas comunitarias vigentes:
   - Tipos de celebraciones activas según etapa
   - Frecuencia de cada tipo de celebración
   - Configuraciones específicas de la comunidad

6. El sistema calcula las celebraciones teóricas del período:
   - Tipo de celebración
   - Fecha

7. El sistema elimina automáticamente las celebraciones que caen dentro de pausas litúrgicas.

8. El sistema realiza **replanificación parcial**:
   - Mantiene celebraciones existentes no conflictivas
   - Actualiza o reemplaza únicamente las afectadas por cambios

9. El sistema persiste las celebraciones en estado **PLANIFICADA**.

10. El sistema deja el resultado disponible para revisión por el responsable.

---

## Flujos alternativos / errores

### A1 – Período fuera del año pastoral
- En el paso 2, si el período excede el año pastoral:
  - El sistema rechaza la operación

### A2 – Período inválido
- En el paso 2, si las fechas no son coherentes:
  - El sistema rechaza la operación

### A3 – Reglas comunitarias inexistentes
- En el paso 5, si no existen reglas aplicables:
  - El sistema rechaza la operación

### A4 – Conflictos en replanificación
- En el paso 8, si existen inconsistencias no resolubles automáticamente:
  - El sistema marca las celebraciones como **pendientes de revisión**

---

## Postcondiciones
- Las celebraciones del período existen en estado **PLANIFICADA**
- No se han generado grupos de preparación
- No se ha publicado información a la comunidad
- El sistema mantiene consistencia con reglas litúrgicas y comunitarias

---

## Invariantes de dominio
- El sistema nunca genera celebraciones fuera del año pastoral abierto
- Las reglas litúrgicas prevalecen sobre las comunitarias
- Toda celebración generada debe ser explicable
- La planificación no implica publicación

---

## Límites explícitos (fuera de alcance)
Este caso de uso NO:
- Genera grupos de preparación (UC-20)
- Publica celebraciones (UC-22 / UC-30)
- Permite edición manual directa de celebraciones individuales
- Gestiona cambios solicitados por hermanos

---

## Observaciones de diseño
- Este caso de uso es candidato a un **servicio de dominio complejo** (motor de planificación)
- Debe permitir trazabilidad de decisiones (auditoría funcional)
- La replanificación parcial debe ser idempotente a nivel lógico
- Requiere separación clara entre:
  - cálculo teórico
  - persistencia
  - estado de publicación

---

**Fin del UC-12 (versión inicial)**

