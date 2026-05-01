# UC-10 – Abrir año pastoral

## Identificador
UC-10

## Nombre
Abrir año pastoral

---

## Actor principal
Responsable de la comunidad (o hermano delegado explícitamente)

## Actores secundarios
- Sistema

---

## Objetivo
Inicializar formalmente un nuevo **año pastoral (año litúrgico)** para una comunidad, dejando el sistema en un estado consistente que permita la planificación posterior de celebraciones y grupos, **sin generar todavía ninguna celebración ni asignación**.

---

## Descripción
Este caso de uso permite al responsable abrir un nuevo año pastoral indicando el año litúrgico correspondiente. El sistema calcula automáticamente el rango temporal del año, carga el calendario litúrgico base y deja preparado el conjunto de reglas necesarias para la planificación posterior.

Abrir el año pastoral es un **acto fundacional**, imprescindible y previo a cualquier planificación.

---

## Precondiciones
- Existe una comunidad activa
- El actor posee el carisma de responsable
- No existe un año pastoral en estado **ABIERTO** para la comunidad

---

## Disparador
El responsable selecciona la opción *“Abrir año pastoral”*.

---

## Flujo principal
1. El responsable solicita la apertura de un nuevo año pastoral.
2. El sistema solicita el **año litúrgico** (por ejemplo: 2026).
3. El responsable introduce y confirma el año litúrgico.
4. El sistema calcula automáticamente el rango temporal del año pastoral:
   - Inicio: I Domingo de Adviento del año civil anterior
   - Fin: Solemnidad de Cristo Rey del mismo año litúrgico
5. El sistema valida que no exista otro año pastoral en estado **ABIERTO**.
6. El sistema valida que no exista solapamiento temporal con años anteriores.
7. El sistema inicializa el año pastoral con:
   - Identificador del año litúrgico
   - Rango temporal completo
   - Calendario litúrgico base correspondiente
   - Reglas litúrgicas aplicables
   - Reglas comunitarias vigentes
   - Estado inicial: **ABIERTO**
8. El sistema persiste el año pastoral.
9. El sistema confirma la apertura del año pastoral.

---

## Flujos alternativos / errores

### A1 – Ya existe un año pastoral abierto
- En el paso 5, si existe un año pastoral en estado **ABIERTO**:
  - El sistema rechaza la operación

### A2 – Solapamiento temporal
- En el paso 6, si el rango calculado se solapa con otro año existente:
  - El sistema rechaza la operación

---

## Postcondiciones
- Existe **exactamente un año pastoral en estado ABIERTO** para la comunidad
- El año pastoral queda listo para planificación
- No se han generado celebraciones
- No se han generado grupos de preparación

---

## Invariantes de dominio
- Solo puede existir un año pastoral en estado **ABIERTO** por comunidad
- Un año pastoral se identifica de forma única por el **año litúrgico**
- Abrir el año no implica planificación ni publicación automática

---

## Límites explícitos (fuera de alcance)
Este caso de uso NO:
- Genera celebraciones
- Aplica reglas litúrgicas de planificación
- Genera grupos de preparación
- Publica información a la comunidad

---

## Observaciones de diseño
- Este caso de uso es prerrequisito para UC-12, UC-13 y UC-16
- Debe ser idempotente a nivel lógico (no repetible en estado válido)
- Constituye el punto de entrada del agregado **AñoPastoral**

---

**Fin del UC-10 (versión inicial)**

