package com.yukilearn.lite.data;

import com.yukilearn.lite.models.PasoLeccion;
import com.yukilearn.lite.models.Pregunta;
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

    public static Pregunta[] obtenerPreguntas(int leccionId) {

        switch (leccionId) {

            case 1:
                return new Pregunta[]{

                        Pregunta.unica(
                                "¿Qué significa programar?",
                                new String[]{
                                        "Dibujar pantallas",
                                        "Crear instrucciones para una computadora",
                                        "Encender una computadora",
                                        "Instalar aplicaciones"
                                },
                                1,
                                "Programar consiste en crear instrucciones para realizar tareas."
                        ),

                        Pregunta.lista(
                                "¿Cuál es un ejemplo de instrucción?",
                                new String[]{
                                        "Mostrar un mensaje",
                                        "El color azul",
                                        "Una fotografía",
                                        "El tamaño de una pantalla"
                                },
                                0,
                                "Mostrar un mensaje es una acción que podemos indicar a la computadora."
                        ),

                        Pregunta.multiple(
                                "¿Qué tareas puede realizar un programa?",
                                new String[]{
                                        "Mostrar mensajes",
                                        "Sumar números",
                                        "Dormir físicamente",
                                        "Guardar información"
                                },
                                new int[]{0, 1, 3},
                                "Un programa puede mostrar, calcular y guardar información."
                        ),

                        Pregunta.texto(
                                "¿Cómo se llama una secuencia de pasos ordenados?",
                                "algoritmo",
                                "Un algoritmo organiza los pasos necesarios para resolver un problema."
                        ),

                        Pregunta.unica(
                                "¿Por qué importa el orden de un algoritmo?",
                                new String[]{
                                        "Porque los pasos deben seguir una secuencia lógica",
                                        "Porque todos los pasos son iguales",
                                        "Porque elimina la necesidad de instrucciones",
                                        "Porque cambia el color del programa"
                                },
                                0,
                                "El orden ayuda a obtener el resultado esperado."
                        )
                };

            case 2:
                return new Pregunta[]{

                        Pregunta.unica(
                                "¿Qué instrucción muestra información en Java?",
                                new String[]{
                                        "mostrar('Hola');",
                                        "System.out.println(\"Hola\");",
                                        "print Hola;",
                                        "System.println = Hola;"
                                },
                                1,
                                "System.out.println() permite mostrar información en la consola."
                        ),

                        Pregunta.texto(
                                "Completa: System.out._____(\"Hola\");",
                                "println",
                                "La palabra que falta es println."
                        ),

                        Pregunta.lista(
                                "¿Qué muestra System.out.println(5 + 3);?",
                                new String[]{"5", "53", "8", "3"},
                                2,
                                "Java realiza la suma antes de mostrar el resultado: 8."
                        ),

                        Pregunta.multiple(
                                "Selecciona las instrucciones válidas en Java.",
                                new String[]{
                                        "System.out.println(\"Hola\");",
                                        "System.out.println(10);",
                                        "print(\"Hola\");",
                                        "System.out.println Hola;"
                                },
                                new int[]{0, 1},
                                "Las dos primeras utilizan correctamente System.out.println()."
                        ),

                        Pregunta.unica(
                                "¿Cómo escribimos un texto en Java?",
                                new String[]{
                                        "Sin símbolos",
                                        "Entre paréntesis solamente",
                                        "Entre comillas dobles",
                                        "Después del símbolo +"
                                },
                                2,
                                "Los textos literales se escriben entre comillas dobles."
                        )
                };

            case 3:
                return new Pregunta[]{

                        Pregunta.unica(
                                "En int edad = 18;, ¿cuál es el nombre?",
                                new String[]{"int", "edad", "18", "="},
                                1,
                                "edad es el nombre de la variable."
                        ),

                        Pregunta.texto(
                                "¿Qué valor tiene edad en int edad = 18;?",
                                "18",
                                "La variable edad guarda el número 18."
                        ),

                        Pregunta.lista(
                                "¿Qué instrucción cambia puntos a 20?",
                                new String[]{
                                        "puntos == 20;",
                                        "puntos = 20;",
                                        "20 = puntos;",
                                        "int = 20;"
                                },
                                1,
                                "El símbolo = permite asignar un nuevo valor."
                        ),

                        Pregunta.multiple(
                                "Selecciona declaraciones válidas de variables.",
                                new String[]{
                                        "int edad = 18;",
                                        "String nombre = \"Yuki\";",
                                        "edad + 2;",
                                        "\"Hola\";"
                                },
                                new int[]{0, 1},
                                "Las dos primeras declaran variables con tipo, nombre y valor."
                        ),

                        Pregunta.unica(
                                "Si puntos vale 10 y luego se asigna 20, ¿cuánto vale?",
                                new String[]{"10", "30", "20", "0"},
                                2,
                                "La segunda asignación reemplaza el valor anterior."
                        )
                };

            case 4:
                return new Pregunta[]{

                        Pregunta.unica(
                                "¿Qué tipo de dato guarda números enteros?",
                                new String[]{"String", "double", "int", "boolean"},
                                2,
                                "int almacena números enteros."
                        ),

                        Pregunta.lista(
                                "¿Qué tipo utilizarías para guardar 4.5?",
                                new String[]{"boolean", "int", "String", "double"},
                                3,
                                "double permite almacenar números decimales."
                        ),

                        Pregunta.texto(
                                "¿Qué tipo de dato se utiliza para guardar texto?",
                                "String",
                                "String almacena cadenas de texto."
                        ),

                        Pregunta.multiple(
                                "Selecciona declaraciones correctas.",
                                new String[]{
                                        "int edad = 18;",
                                        "boolean activo = true;",
                                        "double precio = 4.5;",
                                        "int edad = \"Hola\";"
                                },
                                new int[]{0, 1, 2},
                                "La última es incorrecta: int no almacena textos."
                        ),

                        Pregunta.unica(
                                "¿Qué valores admite boolean?",
                                new String[]{
                                        "1 y 2 solamente",
                                        "true y false",
                                        "Textos",
                                        "Cualquier número decimal"
                                },
                                1,
                                "boolean representa verdadero o falso."
                        )
                };

            case 5:
                return new Pregunta[]{

                        Pregunta.unica(
                                "¿Cuál es el resultado de 4 * 2?",
                                new String[]{"6", "8", "2", "42"},
                                1,
                                "El símbolo * representa multiplicación."
                        ),

                        Pregunta.lista(
                                "¿Qué devuelve la comparación 7 > 4?",
                                new String[]{"false", "7", "true", "4"},
                                2,
                                "La comparación es verdadera porque 7 es mayor que 4."
                        ),

                        Pregunta.multiple(
                                "Selecciona operadores de comparación.",
                                new String[]{">=", "==", "!=", "="},
                                new int[]{0, 1, 2},
                                "= asigna valores; los otros tres comparan."
                        ),

                        Pregunta.texto(
                                "¿Qué operador compara si dos valores son iguales?",
                                "==",
                                "== compara igualdad; = asigna un valor."
                        ),

                        Pregunta.unica(
                                "¿Cuál es la diferencia entre = y ==?",
                                new String[]{
                                        "Ambos sirven para sumar",
                                        "= compara y == asigna",
                                        "Ambos significan lo mismo",
                                        "= asigna y == compara"
                                },
                                3,
                                "La asignación y la comparación tienen funciones diferentes."
                        )
                };

            case 6:
                return new Pregunta[]{

                        Pregunta.unica(
                                "¿Cuándo se ejecuta el bloque de un if?",
                                new String[]{
                                        "Cuando su condición es verdadera",
                                        "Siempre",
                                        "Cuando su condición es falsa",
                                        "Solo al cerrar el programa"
                                },
                                0,
                                "if ejecuta su bloque cuando la condición es verdadera."
                        ),

                        Pregunta.lista(
                                "Si nota = 8 y la condición es nota >= 11, ¿qué rama se ejecuta?",
                                new String[]{"if", "else", "Ambas", "Ninguna"},
                                1,
                                "La condición es falsa, por eso se ejecuta else."
                        ),

                        Pregunta.multiple(
                                "Selecciona afirmaciones correctas sobre if y else.",
                                new String[]{
                                        "if evalúa una condición",
                                        "else se ejecuta cuando el if es falso",
                                        "else siempre se ejecuta",
                                        "if puede utilizarse sin else"
                                },
                                new int[]{0, 1, 3},
                                "else es opcional y representa el camino alternativo."
                        ),

                        Pregunta.texto(
                                "¿Qué palabra se utiliza para el bloque alternativo?",
                                "else",
                                "else permite ejecutar instrucciones cuando el if es falso."
                        ),

                        Pregunta.unica(
                                "Si puntos = 7 y usamos if (puntos > 5), ¿qué ocurre?",
                                new String[]{
                                        "La condición es falsa",
                                        "La condición es verdadera",
                                        "El programa siempre falla",
                                        "puntos cambia a 5"
                                },
                                1,
                                "7 > 5 es verdadero, por eso se ejecuta el bloque if."
                        )
                };

            case 7:
                return new Pregunta[]{

                        Pregunta.unica(
                                "¿Qué es un algoritmo?",
                                new String[]{
                                        "Un tipo de dato",
                                        "Un conjunto ordenado de pasos",
                                        "Un color de pantalla",
                                        "Una aplicación instalada"
                                },
                                1,
                                "Un algoritmo es una secuencia de pasos para resolver un problema."
                        ),

                        Pregunta.multiple(
                                "¿Cuáles son declaraciones válidas de variables?",
                                new String[]{
                                        "int puntos = 10;",
                                        "String nombre = \"Luna\";",
                                        "boolean listo = true;",
                                        "int edad = \"Hola\";"
                                },
                                new int[]{0, 1, 2},
                                "El tipo debe corresponder con el valor guardado."
                        ),

                        Pregunta.lista(
                                "¿Qué muestra System.out.println(\"Hola\");?",
                                new String[]{"Hola", "\"Hola\"", "0", "Error"},
                                0,
                                "println muestra el contenido del texto."
                        ),

                        Pregunta.texto(
                                "¿Qué tipo se utiliza para guardar enteros?",
                                "int",
                                "int representa números enteros."
                        ),

                        Pregunta.unica(
                                "¿Cuál es el resultado de 5 + 3?",
                                new String[]{"53", "2", "8", "15"},
                                2,
                                "El operador + realiza la suma de dos números."
                        ),

                        Pregunta.multiple(
                                "¿Cuáles son operadores de comparación?",
                                new String[]{"<", "==", "!=", "="},
                                new int[]{0, 1, 2},
                                "El símbolo = asigna; los demás comparan."
                        ),

                        Pregunta.lista(
                                "¿Qué valor tiene boolean aprobado = false;?",
                                new String[]{"true", "false", "0.5", "\"false\""},
                                1,
                                "La variable guarda el valor booleano false."
                        ),

                        Pregunta.texto(
                                "¿Qué palabra permite evaluar una condición en Java?",
                                "if",
                                "La estructura if evalúa una condición."
                        ),

                        Pregunta.unica(
                                "Si edad = 16 y la condición es edad >= 18, ¿qué sucede?",
                                new String[]{
                                        "La condición es verdadera",
                                        "Se ejecutan las dos ramas",
                                        "La condición es falsa",
                                        "edad cambia automáticamente"
                                },
                                2,
                                "16 no es mayor o igual que 18; se ejecutaría else si existe."
                        ),

                        Pregunta.multiple(
                                "¿Qué acciones puede realizar un programa?",
                                new String[]{
                                        "Mostrar mensajes",
                                        "Realizar cálculos",
                                        "Guardar información",
                                        "Dormir físicamente"
                                },
                                new int[]{0, 1, 2},
                                "Un programa puede procesar y guardar información."
                        )
                };

            default:
                return new Pregunta[0];
        }
    }

}
