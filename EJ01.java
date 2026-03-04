void main() {
 /*
  * EJERCICIO:
  * - Crea ejemplos utilizando todos los tipos de operadores de tu lenguaje:
  *   Aritméticos, lógicos, de comparación, asignación, identidad, pertenencia, bits...
  *   (Ten en cuenta que cada lenguaje puede poseer unos diferentes)
  * - Utilizando las operaciones con operadores que tú quieras, crea ejemplos
  *   que representen todos los tipos de estructuras de control que existan
  *   en tu lenguaje:
  *   Condicionales, iterativas, excepciones...
  * - Debes hacer print por consola del resultado de todos los ejemplos.
  *
  * DIFICULTAD EXTRA (opcional):
  * Crea un programa que imprima por consola todos los números comprendidos
  * entre 10 y 55 (incluidos), pares, y que no son ni el 16 ni múltiplos de 3.
  *
  * Seguro que al revisar detenidamente las posibilidades has descubierto algo nuevo.
  */


 int a = 2;
 int b = 4;

 //Operadores aritméticos
 System.out.println(a + a);
 System.out.println(a - b);
 System.out.println(a * b);
 System.out.println((double) a / b); //Le añadimos un (double) para que pueda imprimir decimales

 //Estructura condicional

 if (a < b) {
  System.out.println(a + b);
 } else {
  return;
 }
//Estructura iterativa (while, for)
 while (a <= b) {
  a += a;
  System.out.println("a=" + a);
 }

 for (int i = 6; i < 8; i++) {
  System.out.println(a);
 }

 //Estructuras de excepciones: try, throw, exception...
 try {
     int resultado = 14 / 0;
     System.out.println(resultado);
 } catch (ArithmeticException e) {
     System.out.println(e.getMessage());
     e.printStackTrace();
 }
 //Tarea extra
 for (int w = 10; w<=55; w++) {
  if ((w % 2 == 0 && w != 16 && w % 3 != 0) || w == 55) {
   System.out.println(w);
  }
 }
EJ02.tareaExtra("stick", "carrot");
}

