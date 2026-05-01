# UC-11 – Cerrar año pastoral

## Identificador
UC-11

## Nombre
Cerrar año pastoral

---

## Actor principal
Responsable de la comunidad (o hermano delegado explícitamente)

## Actores secundarios
- Sistema

---

## Objetivo
Cambiar el estado de un año pastoral de **ABIERTO** a **CERRADO**, una vez que su período temporal ha finalizado, garantizando su carácter histórico e inmutable a partir de ese momento.

---

## Descripción
Este caso de uso permite finalizar formalmente un año pastoral cuando ya ha transcurrido completamente en el tiempo. El cierre no implica modificaciones sobre las celebraciones, grupos o cualquier otra entidad asociada.

El cierre establece un límite temporal claro a partir del cual la información del año se considera histórica y no modificable.

---

## Precondiciones
- Existe una comunidad activa
- Existe un año pastoral en estado **ABIERTO**
- El actor tiene permisos de gestión sobre la comunidad
- La fecha actual es posterior a la fecha de fin del año pastoral

---

## Disparador
El responsable solicita el cierre del año pastoral.

---

## Flujo principal
1. El actor solicita el cierre del año pastoral activo.
2. El sistema recupera el año pastoral en estado **ABIERTO**.
3. El sistema valida que la fecha actual es posterior a la fecha de fin del año pastoral.
4. El sistema cambia el estado del año pastoral a **CERRADO**.
5. El sistema persiste el cambio de estado.
6. El sistema confirma la operación.

---

## Flujos alternativos / errores

### A1 – Año pastoral no finalizado
- En el paso 3, si la fecha actual es anterior o igual a la fecha de fin del año:
  - El sistema rechaza la operación

### A2 – No existe año pastoral abierto
- En el paso 2, si no existe un año pastoral en estado **ABIERTO**:
  - El sistema rechaza la operación

---

## Postcondiciones
- El año pastoral pasa a estado **CERRADO**
- No se modifican celebraciones, grupos ni ningún otro dato asociado

---

## Invariantes de dominio
- Un año pastoral cerrado no puede ser reabierto
- Los datos asociados a un año pastoral cerrado son inmutables
- El cierre no implica validación de completitud de planificación

---

## Límites explícitos (fuera de alcance)
Este caso de uso NO:
- Modifica celebraciones existentes
- Genera nuevas celebraciones
- Genera o modifica grupos de preparación
- Publica información a la comunidad
- Abre un nuevo año pastoral

---

## Observaciones de diseño
- El cierre es una operación puramente de estado sobre el agregado **AñoPastoral**
- La restricción de inmutabilidad tras el cierre debe aplicarse en otros casos de uso mediante validaciones transversales
- Este caso de uso es idempotente en términos de resultado lógico (una vez cerrado, no puede volver a ejecutarse con efecto)

---

**Fin del UC-11 (versión inicial)**

