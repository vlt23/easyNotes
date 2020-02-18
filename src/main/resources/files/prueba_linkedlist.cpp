#include <iostream>
#include "linkedlist.h"

using namespace std;


// Descomentar para mandar al Mooshak y Comentar para probar desde fichero
//#undef ENTRADA_DESDE_FICHERO

// Descomentar para que el programa de prueba tome la entrada del fichero
// prueba_listas.txt y ¡Comentar para mandar al Mooshak!
#define ENTRADA_DESDE_FICHERO

#include <iostream>
#ifdef ENTRADA_DESDE_FICHERO
  #include <fstream>
  #include <sstream>
#endif


void testEmptyConstructor() {
    LinkedList<int> l;
    cout << l;
}

void testCopyConstructor(istream& is) {
    LinkedList<int> l;
    is >> l;
    LinkedList<int> l2(l);
    cout << l2;
}

void testInsertFirst(istream& is) {
    LinkedList<int> l;
    int num_elementos;

    is >> num_elementos;
    for (int i=0; i<num_elementos; i++) {
        int e;
        is >> e;
        l.insertFirst(e);
    }
    std::cout << l;
}

void testInsertLast(istream& is) {
    LinkedList<int> l;
    int num_elementos;

    is >> num_elementos;
    for (int i=0; i<num_elementos; i++) {
        int e;
        is >> e;
        l.insertLast(e);
    }
    std::cout << l;
}

void testFirst(istream& is) {
    LinkedList<int> l;
    is >> l;
    int e = l.first();
    cout << e;
}

void testLast(istream& is) {
    LinkedList<int> l;
    is >> l;
    int e = l.last();
    cout << e;
}

void testRemaining(istream& is) {
    LinkedList<int> l;
    is >> l;

    LinkedList<int> r = l.remaining();
    cout << r << endl << l;
}

void testIsEmpty(istream& is) {
    LinkedList<int> l;
    is >> l;
    if (l.isEmpty()) {
        cout << "VACIA";
    } else {
        cout << "NO VACIA";
    }
}

void testLength(istream& is) {
    LinkedList<int> l;
    is >> l;
    cout << l.length();
}

void testRemove(istream& is) {
    LinkedList<int> l;
    int e;
    is >> e;
    is >> l;
    l.remove(e);
    cout << l;
}

void testConcat(istream& is) {
    LinkedList<int> l;
    LinkedList<int> l2;
    is >> l;
    is >> l2;
    l.concat(l2);
    cout << l;
}

void testGetElement(istream& is) {
    LinkedList<int> l;
    int n;
    is >> n;
    is >> l;
    cout << l.getElement(n);
}

void testContains(istream& is) {
    LinkedList<int> l;
    int n;
    is >> n;
    is >> l;
    cout << l.contains(n);
}

void testOperatorAssign(istream& is) {
    LinkedList<int> l;
    is >> l;
    LinkedList<int> l2 = l;
    cout << l2;
}

void testOperatorEqual(istream& is) {
    LinkedList<int> l;
    is >> l;
    LinkedList<int> l2;
    is >> l2;
    cout << (l == l2);
}

void testOperatorDiff(istream& is) {
    LinkedList<int> l;
    is >> l;
    LinkedList<int> l2;
    is >> l2;
    cout << (l != l2);
}

int main(int, char * argv[]) {

//#define FICHERO "/Users/jesussanchezoro/OneDrive - Universidad Rey Juan Carlos/Universidad/Docencia/ED/2017-2018/practicas/practica1/casos_in/resto_03.in"
#define FICHERO argv[1]

#ifdef ENTRADA_DESDE_FICHERO
    std::ifstream is(FICHERO);

    if (!is.is_open()) {
        std::cerr << "No se puede abrir el fichero" << std::endl;
        return 1;
    }
#else
#define is std::cin
#endif

    char opcion;
    try {
        // Lectura de la operación a probar
        is >> opcion;
        switch(opcion) {
        case 'C':   testCopyConstructor(is);
                    break;
        case 'i':   testInsertFirst(is);
                    break;
        case 'j':   testInsertLast(is);
                    break;
        case 'b':   testRemove(is);
                    break;
        case 't':   testConcat(is);
                    break;
        case 'v':   testIsEmpty(is);
                    break;
        case '=':   testOperatorEqual(is);
                    break;
        case 'l':   testLength(is);
                    break;
        case 'P':   testContains(is);
                    break;
        case 'p':   testFirst(is);
                    break;
        case 'r':   testRemaining(is);
                    break;
        case 'u':   testLast(is);
                    break;
        case 'g':   testGetElement(is);
                    break;
        }
    } catch (std::exception const& excepcion) {
      std::cout << "EXCEPCION GENERADA: "  <<  excepcion.what();
    }
}







