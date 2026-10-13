package com.yukilearn.lite.data;

import com.yukilearn.lite.models.PasoLeccion;

public class ContenidoCurso {

    public static String obtenerTitulo(int leccionId) {

        switch (leccionId) {
            case 1:
                return "¿Qué es programar?";
            case 2:
                return "Mostrar información";
            case 3:
                return "Variables";
            case 4:
                return "Tipos de datos";
            case 5:
                return "Operadores";
            case 6:
                return "Condicionales";
            case 7:
                return "Desafío de Luna";
            default:
                return "";
        }
    }

    public static PasoLeccion[] obtenerPasos(int leccionId) {

        switch (leccionId) {

            case 1:
                return new PasoLeccion[]{

                        new PasoLeccion(
                                "Programar significa crear instrucciones que una computadora puede seguir para realizar una tarea.",
                                "",
                                "Por ejemplo, podemos programar una aplicación para mostrar mensajes, realizar cálculos o guardar información.",
                                ""
                        ),

                        new PasoLeccion(
                                "Una instrucción es una orden concreta que le damos a la computadora.",
                                "",
                                "Ejemplo: mostrar un mensaje en pantalla o sumar dos números.",
                                "¡Entonces programar es darle instrucciones a la computadora!"
                        ),

                        new PasoLeccion(
                                "Un algoritmo es un conjunto de pasos ordenados para resolver un problema.",
                                "",
                                "Ejemplo para preparar un jugo:\n1. Lavar la fruta.\n2. Cortarla.\n3. Licuarla.\n4. Servir el jugo.",
                                "¡Ahora entiendo por qué importa el orden!"
                        )
                };

            case 2:
                return new PasoLeccion[]{

                        new PasoLeccion(
                                "En Java podemos utilizar System.out.println() para mostrar información en pantalla.",
                                "System.out.println(\"Hola\");",
                                "Resultado:\nHola",
                                ""
                        ),

                        new PasoLeccion(
                                "Los textos se escriben entre comillas dobles. Los números pueden escribirse directamente.",
                                "System.out.println(\"Yuki\");\nSystem.out.println(10);",
                                "La primera instrucción muestra un texto y la segunda muestra un número.",
                                ""
                        ),

                        new PasoLeccion(
                                "También podemos realizar operaciones dentro de System.out.println().",
                                "System.out.println(5 + 3);",
                                "Resultado:\n8",
                                "¡Java realizó la suma antes de mostrar el resultado!"
                        )
                };

            case 3:
                return new PasoLeccion[]{

                        new PasoLeccion(
                                "Una variable permite almacenar información para utilizarla después.",
                                "int edad = 18;",
                                "Tipo: int\nNombre: edad\nValor: 18",
                                ""
                        ),

                        new PasoLeccion(
                                "Podemos utilizar el valor guardado en una variable sin escribirlo nuevamente.",
                                "int edad = 18;\nSystem.out.println(edad);",
                                "Resultado:\n18",
                                ""
                        ),

                        new PasoLeccion(
                                "El valor de una variable puede cambiar durante el programa.",
                                "int puntos = 10;\npuntos = 20;\nSystem.out.println(puntos);",
                                "Resultado:\n20",
                                "¡Entonces ahora puntos vale 20!"
                        )
                };

            case 4:
                return new PasoLeccion[]{

                        new PasoLeccion(
                                "El tipo int se utiliza para almacenar números enteros, sin decimales.",
                                "int edad = 18;",
                                "Otros ejemplos: 5, 20, 100.",
                                ""
                        ),

                        new PasoLeccion(
                                "El tipo double permite almacenar números con decimales.",
                                "double precio = 4.5;",
                                "Podemos utilizarlo para representar precios, medidas y otros valores decimales.",
                                ""
                        ),

                        new PasoLeccion(
                                "El tipo String permite almacenar texto. Su valor se escribe entre comillas dobles.",
                                "String nombre = \"Yuki\";",
                                "También puede almacenar frases como \"Hola, Luna\".",
                                ""
                        ),

                        new PasoLeccion(
                                "El tipo boolean solamente puede almacenar true o false.",
                                "boolean aprobado = true;",
                                "true significa verdadero y false significa falso.",
                                "¡Cada tipo de dato sirve para guardar información diferente!"
                        )
                };

            case 5:
                return new PasoLeccion[]{

                        new PasoLeccion(
                                "Los operadores aritméticos permiten realizar cálculos: + suma, - resta, * multiplicación y / división.",
                                "System.out.println(5 + 3);\nSystem.out.println(8 - 2);\nSystem.out.println(4 * 2);\nSystem.out.println(8 / 2);",
                                "Resultados:\n8\n6\n8\n4",
                                ""
                        ),

                        new PasoLeccion(
                                "Los operadores de comparación permiten comparar dos valores y producen true o false.",
                                "System.out.println(7 > 4);\nSystem.out.println(4 == 4);",
                                "Ambas comparaciones muestran true.\nTambién existen <, >= y <=.",
                                ""
                        ),

                        new PasoLeccion(
                                "El símbolo = asigna un valor a una variable. En cambio, == compara si dos valores son iguales.",
                                "int edad = 18;\nSystem.out.println(edad == 18);",
                                "Resultado: true.\nEl operador != comprueba si dos valores son diferentes.",
                                "¡Asignar un valor y comparar valores no es lo mismo!"
                        )
                };

            case 6:
                return new PasoLeccion[]{

                        new PasoLeccion(
                                "La estructura if permite ejecutar instrucciones solamente cuando una condición es verdadera.",
                                "int edad = 18;\nif (edad >= 18) {\n    System.out.println(\"Mayor de edad\");\n}",
                                "Como 18 es mayor o igual que 18, se muestra el mensaje.",
                                ""
                        ),

                        new PasoLeccion(
                                "La estructura else permite ejecutar otras instrucciones cuando la condición del if es falsa.",
                                "int nota = 8;\nif (nota >= 11) {\n    System.out.println(\"Aprobado\");\n} else {\n    System.out.println(\"Desaprobado\");\n}",
                                "Resultado:\nDesaprobado",
                                ""
                        ),

                        new PasoLeccion(
                                "Con if y else podemos tomar decisiones según los valores de nuestras variables.",
                                "int puntos = 7;\nif (puntos > 5) {\n    System.out.println(\"Avanzas\");\n} else {\n    System.out.println(\"Practica\");\n}",
                                "Resultado:\nAvanzas",
                                "¡Ahora podemos hacer que un programa tome decisiones!"
                        )
                };

            default:
                return new PasoLeccion[0];
        }
    }
}
