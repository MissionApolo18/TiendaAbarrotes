# Proyecto Tienda de Abarrotes "Zorro"

![Estado](https://img.shields.io/badge/Estado-En_progreso-991e34?style=for-the-badge&logo=apache-spark) ![EstadoRedaccion](https://img.shields.io/badge/Documentacion-En_progreso-AC5840?style=for-the-badge&logo=github) ![ImplementacionFinal](https://img.shields.io/badge/Implementacion_Final-No_disponible-490C19?style=for-the-badge&logo=intellijidea)

## Problematica

Nos acaban de contratar para realizar la actualización de puntos de venta que necesita la empresa **Abarrotes El Zorro**.

> El principal objetivo, es agregar nuevas funcionalidades como creación de pdf, creación de un excel para mandarlos a los clientes y distribuidores.

Para los clientes generar un pdf con sus compras incluyendo las imagenes de productos y precios, a los distribuidores un excel, con la cantidad de productos y precios unitarios que necesitamos en el almacén.

Se debe de respetar lo que ya se tiene que son el login de cada trabajador en caja, el registro de las ventas de los clientes y el resultado del inventario que se tiene en la bodega de los productos vendidos y que falten en la misma

## Propuesta

Dado a que se trata de la actualizacion de un sistema, se propone la implementacion de Spring con el fin de incluir nuevas funcionalidades, manteniendo el diseño previo y mejorar la eficiencia en la gestion de inventario, asi mismo se busca la visualizacion y generacion de:

- Lista de los productos en stock (como vendedor).
- Los reportes de corte (como administrador).
- La lista de productos agotados o cercanos a agotarse.

### Tecnologias propuestas

**Backend:** Java y Spring

<p >
<a href="https://skillicons.dev">
<img src="https://skillicons.dev/icons?i=java,spring&perline=10" alt="Lenguajes y tecnologías"/>
</a>
</p>

**Frontend:** HTML 5, CSS y JavaScript

<p >

<a href="https://skillicons.dev">
<img src="https://skillicons.dev/icons?i=html,css,js&perline=10" alt="Lenguajes y tecnologías"/>
</a>
</p>

**Base de datos:** MYSQL

<p >

<a href="https://skillicons.dev">
<img src="https://skillicons.dev/icons?i=mysql,&perline=10" alt="Lenguajes y tecnologías"/>
</a>
</p>

### Dependencias que se usarán

- Thymeleaf
- Spring Web
- MariaDB Driver
- Spring Data JPA
- Lombok

## Implementacion

1. **Controladores:** Permiten manejar las peticiones para generacion de documentos.
2. **Servicios:** Aqui tenemos toda la logica para la generacion de los respectivos PDFs y archivos Excel.
3. **Repositorios y Modelo:** Es donde se realiza la conexion a la base de datos, para obtener informacion de ventas y productos.
4. **Vistas:** Interfaces para que los trabajadores puedan seleccionar ventas o inventarios y realizar los reportes correspondientes.
