# Detalle de Casos de Uso – Planificación de Celebraciones

Este documento desarrolla en detalle los casos de uso del grupo **Planificación de celebraciones**, partiendo del mapa v1.

Casos incluidos:
- UC-10 Abrir año pastoral
- UC-11 Cerrar año pastoral
- UC-12 Planificar celebraciones por período
- UC-13 Replanificar celebraciones
- UC-14 Aplicar reglas litúrgicas
- UC-15 Gestionar pausas litúrgicas
- UC-16 Planificar celebraciones intercomunitarias de verano

## UC-10 Abrir año pastoral

**Identificador**: UC-12

**Actor principal**: Responsable de la comunidad (o hermano delegado)

**Actor secundario**: Sistema




### Objetivo
 Inicializar formalmente un nuevo año pastoral, entendido siempre como año litúrgico, dejando el sistema en un estado consistente que permita la planificación posterior de celebraciones.

### Precondiciones

* Existe una comunidad activa
* El actor posee el carisma de responsable
* No existe ya un año litúrgico abierto para la comunidad

**Flujo principal:**

1. El responsable solicita la apertura de un nuevo año pastoral

2. El sistema solicita el año litúrgico (por ejemplo, 2026)

3. El responsable introduce y confirma el año litúrgico

4. El sistema calcula automáticamente el rango temporal del año litúrgico, incluyendo fiestas y solemnidades por región:
    * Desde el I Domingo de Adviento del año previo
    * Hasta la solemnidad de Cristo Rey

5. El sistema valida que no exista solapamiento con otro año pastoral

6. El sistema inicializa el año litúrgico:

    * Rango temporal completo

    * Calendario litúrgico base correspondiente al año

    * Conjunto de reglas litúrgicas aplicables

    * Conjunto de reglas comunitarias vigentes

    * Estado inicial del año como ABIERTO

7. El sistema deja el año disponible para la planificación de celebraciones

8. El sistema incorpora también los períodos de los tiempos litúrgicos: Adviento, Navidad, Cuaresma y Pascua. También otros períodos necearios para las pausas aunque no sean litúrgicos: semana Santa, Verano, etc.

**Flujos alternativos:**

* 5a. Si existe ya un año litúrgico abierto o solapado → el sistema rechaza la operación

**Postcondiciones:**

* Existe exactamente un año litúrgico abierto para la comunidad

* No se han generado celebraciones

* No se han generado grupos de preparación

**Reglas de dominio implicadas:**

* El año pastoral se identifica exclusivamente por el año litúrgico

* Solo puede existir un año litúrgico abierto por comunidad

* Abrir el año no implica planificar ni publicar celebraciones

***

## UC-12 – Planificar celebraciones por período

**Identificador**: UC-12

**Actor principal**: Responsable de la comunidad (o hermano delegado)

**Actor secundario**: Sistema

### Objetivo
Generar celebraciones comunitarias dentro de un período temporal definido, aplicando automáticamente las reglas litúrgicas y comunitarias vigentes, sin publicar aún los resultados.

### Precondiciones
- Existe una comunidad activa
- Existe un año pastoral ABIERTO
- El actor tiene permisos de planificación
- El período solicitado pertenece al año pastoral abierto

### Flujo principal
1. El actor selecciona el tipo de período a planificar (mes, trimestre o rango personalizado).
2. El sistema valida que el período no excede el año pastoral abierto.
3. El sistema identifica las reglas litúrgicas aplicables (tiempos, solemnidades, pausas).
4. El sistema identifica las reglas comunitarias vigentes.
5. El sistema genera las celebraciones correspondientes al período con estado **PLANIFICADA**.
6. El sistema descarta automáticamente las fechas incluidas en pausas litúrgicas.
7. El sistema deja las celebraciones disponibles para revisión.

### Flujos alternativos
- 2a. El período está parcialmente planificado → el sistema solicita confirmación para replanificar.
- 2b. El período excede el año pastoral → operación rechazada.

### Postcondiciones
- Las celebraciones existen en estado PLANIFICADA
- No se han publicado celebraciones
- No se han generado grupos de preparación

### Reglas de dominio
- El sistema nunca genera celebraciones fuera del año pastoral abierto
- Las reglas litúrgicas prevalecen sobre las comunitarias
- Planificar no implica publicar


