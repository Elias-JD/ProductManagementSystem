# ProductManagementSystem

Solución integral de inventario desarrollada con Spring Boot 4. Proporciona una arquitectura de APIs REST modulares para la gestión de productos, categorías y proveedores, vinculadas a un núcleo transaccional de movimientos de stock que asegura la consistencia de datos y el cumplimiento de reglas de negocio complejas en cada operación.

## Funcionalidades Principales
- Control de Existencias en Tiempo Real: Seguimiento preciso del stock disponible, evitando discrepancias entre el inventario físico y el digital.

- Gestión de Trazabilidad de Movimientos: Registro detallado de cada entrada (compras), salida (ventas/pérdidas) y ajuste manual, permitiendo auditar el historial de cada producto.

- Administración de Catálogo Multicategoría: Organización jerárquica de productos para una búsqueda y clasificación eficiente.

- Red de Proveedores por Producto: Capacidad de asociar múltiples proveedores a un mismo artículo, facilitando la gestión de reabastecimiento.

- Identificación mediante SKU: Sistema de codificación único por producto para una gestión logística estandarizada.

- Protección contra Quiebre de Stock: Validación automática que impide realizar salidas de mercancía si no existe stock suficiente, garantizando la integridad de las operaciones.

- Alertas de Consistencia de Datos: Restricciones de borrado inteligente que impiden la eliminación de categorías o proveedores que tengan productos activos vinculados.

## Tecnologías Implementadas
- Lenguaje: Java 21.

- Framework: Spring Boot 4.0.

- Persistencia: Spring Data JPA / Hibernate.

- Base de Datos: MySQL.

- Mapeo: MapStruct (Desacoplamiento total de Entidades y DTOs).

- Validación: Jakarta Bean Validation (Integridad en capa de entrada).

- Productividad: Lombok.

#

Esta solución permite a las empresas centralizar su operación logística, garantizando la trazabilidad total de sus productos y la optimización de sus existencias.
