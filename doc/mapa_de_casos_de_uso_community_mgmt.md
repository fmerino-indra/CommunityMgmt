# Mapa de Casos de Uso – CommunityMgmt

## 1. Introducción

Este documento traduce el PRD de *CommunityMgmt* a un **mapa completo de casos de uso**, expresados en lenguaje de dominio y pensados para ser trazables directamente a la capa de aplicación (Clean / Hexagonal Architecture).

Los casos de uso describen **qué puede hacer el sistema y bajo qué condiciones**, no cómo se implementa ni cómo se presenta en la UI.

---

## 2. Actores del sistema

### 2.1 Responsable de comunidad
Hermano con carisma de responsabilidad (normalmente ejercido en matrimonio) que puede:
- Administrar la comunidad
- Planificar celebraciones
- Publicar grupos y calendarios

### 2.2 Hermano
Miembro ordinario de la comunidad que puede:
- Consultar información comunitaria
- Participar en grupos de preparación
- Interactuar con otros hermanos

### 2.3 Sistema
Actor implícito responsable de:
- Aplicar reglas litúrgicas
- Generar celebraciones
- Equilibrar asignaciones
- Garantizar invariantes de dominio

---

## 3. Mapa global de casos de uso

### Gestión de miembros
- UC-01 Solicitar enrollment en la comunidad
- UC-02 Aprobar enrollment
- UC-03 Editar datos de miembro
- UC-04 Gestionar matrimonios
- UC-05 Asignar carismas
- UC-06 Revocar carismas
- UC-07 Sortear grupos de garantes
- UC-08 Gestionar grupos de garantes

### Planificación de celebraciones
- UC-10 Abrir año pastoral
- UC-11 Cerrar año pastoral
- UC-12 Planificar celebraciones por período
- UC-13 Replanificar celebraciones
- UC-14 Aplicar reglas litúrgicas
- UC-15 Gestionar pausas litúrgicas
- UC-16 Planificar celebraciones intercomunitarias de verano

### Grupos de preparación
- UC-20 Generar grupos de preparación
- UC-21 Modificar grupos antes de publicación
- UC-22 Publicar grupos de preparación
- UC-23 Solicitar cambio de grupo
- UC-24 Resolver solicitud de cambio de grupo

### Uso comunitario
- UC-30 Consultar calendario comunitario
- UC-31 Consultar detalle de celebración
- UC-32 Consultar lista de la comunidad
- UC-33 Consultar detalle de miembro
- UC-34 Proponer datos de preparación
- UC-35 Aceptar propuesta de preparación

---

## 4. Casos de uso detallados

### UC-01 Solicitar enrollment en la comunidad
**Actor principal:** Hermano (no perteneciente aún)

**Descripción:**
Permite a una persona solicitar su incorporación a una comunidad mediante un mecanismo controlado (QR cifrado).

**Precondiciones:**
- Existe una comunidad activa
- El solicitante dispone de invitación válida

**Postcondiciones:**
- La solicitud queda pendiente de aprobación

---

### UC-02 Aprobar enrollment
**Actor principal:** Responsable

**Descripción:**
Permite aprobar o rechazar una solicitud de incorporación.

**Reglas de dominio:**
- No puede aprobarse automáticamente

---

### UC-03 Editar datos de miembro
**Actor principal:** Responsable / Hermano

**Descripción:**
Permite modificar los datos personales de un miembro.

**Reglas de dominio:**
- El hermano solo puede editar sus propios datos
- El responsable puede editar los de toda la comunidad

---

### UC-05 Asignar carismas
**Actor principal:** Responsable

**Descripción:**
Asigna uno o varios carismas a un hermano o matrimonio.

**Reglas de dominio:**
- Algunos carismas solo están disponibles a partir de ciertas etapas
- Algunos carismas se ejercen siempre en matrimonio

---

### UC-10 Abrir año pastoral
**Actor principal:** Responsable

**Descripción:**
Inicia un nuevo año pastoral habilitando la planificación de celebraciones.

**Postcondiciones:**
- El sistema queda preparado para generar celebraciones

---

### UC-12 Planificar celebraciones por período
**Actor principal:** Responsable

**Descripción:**
Genera celebraciones para un período determinado (mes, trimestre, rango personalizado).

**Reglas de dominio:**
- El sistema aplica automáticamente las reglas litúrgicas
- No se generan celebraciones en períodos de pausa

---

### UC-16 Planificar celebraciones intercomunitarias de verano
**Actor principal:** Responsable

**Descripción:**
Planifica Eucaristías de verano compartidas entre varias comunidades.

**Reglas de dominio:**
- Las celebraciones no se cancelan
- Las preparaciones se reparten entre comunidades
- El reparto debe ser equilibrado

---

### UC-20 Generar grupos de preparación
**Actor principal:** Sistema (disparado por Responsable)

**Descripción:**
Genera automáticamente los grupos de preparación asociados a celebraciones.

**Reglas de dominio:**
- Se respetan tamaños mínimos y objetivos
- Se busca equilibrio en las asignaciones

---

### UC-22 Publicar grupos de preparación
**Actor principal:** Responsable

**Descripción:**
Hace visibles los grupos a toda la comunidad.

**Postcondiciones:**
- Los grupos pasan a ser oficiales

---

### UC-23 Solicitar cambio de grupo
**Actor principal:** Hermano

**Descripción:**
Permite solicitar un cambio con otro hermano.

**Reglas de dominio:**
- El cambio debe ser aceptado por la otra parte
- El sistema registra el historial

---

### UC-30 Consultar calendario comunitario
**Actor principal:** Hermano

**Descripción:**
Permite consultar el calendario de celebraciones publicadas.

---

### UC-32 Consultar lista de la comunidad
**Actor principal:** Hermano

**Descripción:**
Permite ver la lista de miembros de la comunidad.

---

### UC-33 Consultar detalle de miembro
**Actor principal:** Hermano

**Descripción:**
Permite consultar los datos básicos de un miembro y acceder a acciones de contacto.

**Reglas de dominio:**
- Se permite navegación GPS y llamada telefónica
- No se permite captura de pantalla

---

## 5. Reglas transversales

- El sistema debe funcionar offline-first
- No se permite captura de pantalla en ningún flujo
- Todas las decisiones automáticas deben ser explicables
- El sistema nunca gestiona contenido espiritual

---

**Fin del mapa de casos de uso (versión inicial)**

