# Readme - Calories Tracker

# Calories Tracker - Android App

[se-006.mp4](https://firebasestorage.googleapis.com/v0/b/basic-calculator-001-c2092.appspot.com/o/demo-curse-felipe.mp4)

![SS2@1x.png](https://firebasestorage.googleapis.com/v0/b/basic-calculator-001-c2092.appspot.com/o/SS2%401x.png?alt=media&token=65c6e70c-f5d7-4966-9203-332307adabb9)

He finalizado el curso de **Multi-module architecture** android por **[Philipp Lackner](https://github.com/philipplackner)**, el cual recomiendo mucho, añadí una UI distinta pero con la esencia de la original, muchas gracias a **[Antonio Fernández](https://github.com/afalabarce)** y **[Carlos Muñoz](https://github.com/DerSarco/newastro/commits?author=DerSarco)** que me ayudaron a comprender mejor cada tema.

**Tech stack = Compose, modularization with clean architecture, Material, Navigation, Retrofit, Unittest, Test end 2 end.**

Personaje por **[Chitta Shanmukha](https://lottiefiles.com/CHITTA)**

# **Multi-Module**

De las muchas ventajas de trabajar con módulos es la posibilidad de dividir la responsabilidad de las capas que no deberían poder comunicarse entre sí, por ejemplo, la capa de presentación no debería tener acceso a importaciones de la capa de Data, el tener estas restricciones nos ayuda a no generar código con prácticas que pueden dificultar el mantenimiento del mismo a futuro, pero hay más ventajas, de acuerdo a la documentación oficial de [google](https://developer.android.com/topic/modularization):

## ¿Qué es la modularización?

La modularización es una práctica para organizar una base de código en partes con acoplamiento bajo y elementos independientes. Cada parte es un módulo. Cada módulo es independiente y tiene un propósito claro. Si divides un problema en subproblemas más pequeños y fáciles de resolver, se reduce la complejidad de diseñar y mantener un sistema grande.

![Untitled](https://firebasestorage.googleapis.com/v0/b/basic-calculator-001-c2092.appspot.com/o/1_sample_dep_graph.png?alt=media&token=18d8bd62-c3de-4ad4-9811-69e0386e181d)

Figura 1: Representación para dividir los feactures de una aplicación.

## Beneficios de la modularización

| Beneficio | Resumen |
| --- | --- |
| Capacidad de reutilización | La modularización ofrece oportunidades para compartir código y compilar varias apps desde la misma base. Los módulos son componentes fundamentales. Una app debe ser una suma de sus funciones cuando estas se organizan como módulos separados. La funcionalidad que proporciona un módulo determinado puede o no estar habilitada en una app en particular. Por ejemplo, un :feature:news puede ser parte de la versión final y la app de Wear, pero no de la versión de demostración. |
| Control de visibilidad estricto | Los módulos te permiten controlar fácilmente lo que expones a otras partes de tu base de código. Puedes marcar todo excepto la interfaz pública como internal o private para evitar que se use fuera del módulo. |
| Entrega personalizable | https://developer.android.com/guide/playcore/feature-delivery?hl=es-419 usa las funciones avanzadas de los paquetes de aplicaciones, lo que t permite ofrecer ciertas funciones de tu app de manera condicional o a pedido. |

Los beneficios anteriores solo se logran con una base de código modular. Los siguientes beneficios se pueden lograr con otras técnicas, pero la modularización puede ayudarte a aplicarlos aún más.

| Beneficio | Resumen |
| --- | --- |
| Escalabilidad | En una base de código de acoplamiento alto, un solo cambio puede desencadenar una cascada de alteraciones en partes de código aparentemente no relacionadas. Un proyecto modularizado adecuadamente adoptará el principio de https://en.wikipedia.org/wiki/Separation_of_concerns y, por lo tanto, limitará el acoplamiento. De esta manera, los colaboradores tendrán mayor autonomía. |
| Propiedad | Además de habilitar la autonomía, los módulos también se pueden usar para aplicar la responsabilidad. Un módulo puede tener un propietario dedicado que sea responsable de mantener el código, corregir errores, agregar pruebas y revisar los cambios. |
| Encapsulamiento | El encapsulamiento significa que cada parte de tu código debería tener el menor conocimiento posible sobre otras partes. El código aislado es más fácil de leer y entender. |
| Capacidad de realizar pruebas | La capacidad de realizar pruebas determina qué tan fácil es probar tu código. Un código que se puede probar es aquel en el que los componentes se pueden probar fácilmente de forma aislada. |
| Tiempo de compilación | Algunas funcionalidades de Gradle, como la compilación incremental, la caché de compilación o la compilación en paralelo, pueden aprovechar la modularidad para mejorar el rendimiento de la compilación. |

Fuente de información documentación oficial de Google [https://developer.android.com/topic/modularization](https://developer.android.com/topic/modularization)

Cada Feature contiene su capa de Data, Domain y Presentación correspondiente, uno de los problemas que me encontré fue como compartir los Modelos para la aplicación en general, la propuesta por el gran profesional **[Antonio Fernández](https://github.com/afalabarce),** es crear un Módulo específico para los **modelos** en general:

![Untitled](https://firebasestorage.googleapis.com/v0/b/basic-calculator-001-c2092.appspot.com/o/img.png?alt=media&token=ebb81b1c-55ba-47c4-8a9a-5f75962bf98a)

De esta manera podemos crear Data Object Transportation en la capa de data, pero que a su vez conoce nuestro módelo de UI.

Fue complicado entender la configuración de los archivos build.gradle.kts, pero que sin embargo es una excelente forma de organizar los features de una aplicación de gran tamaña.

# Screens

![SS1@1x.png](https://firebasestorage.googleapis.com/v0/b/basic-calculator-001-c2092.appspot.com/o/SS1%401x.png?alt=media&token=48a42ee4-5a39-47fb-81cd-3ae303b0bf89)

![SS3@1x.png](https://firebasestorage.googleapis.com/v0/b/basic-calculator-001-c2092.appspot.com/o/SS3%401x.png?alt=media&token=8c35446a-d1a5-4c8a-8a19-cdc6420a5e33g)

![SS4@1x.png](https://firebasestorage.googleapis.com/v0/b/basic-calculator-001-c2092.appspot.com/o/SS4%401x.png?alt=media&token=eb3e1444-0a41-42a2-8a99-9f16b1e7d69f)

![SS5@1x.png](https://firebasestorage.googleapis.com/v0/b/basic-calculator-001-c2092.appspot.com/o/SS1%401x.png?alt=media&token=48a42ee4-5a39-47fb-81cd-3ae303b0bf89)

## Muchas gracias por compartir ❤️
