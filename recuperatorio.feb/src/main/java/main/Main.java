package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String [] args) throws IOException{
		crearArchivo();
		menu();
	}
	public static void crearArchivo() throws IOException{
		File archivo = new File("datos.csv");
		
		if(!archivo.exists()) {
			try {
				FileWriter fw = new FileWriter("datos.csv");
				fw.close();
				System.out.printf("Archivo creado Correctamente\n");
			}catch(IOException e) {
				System.out.printf("Error al crear el codigo\n");
			}
						
			
		}
	}
	public static void menu() throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		while(true) {
			System.out.printf("=====MENU=====\n");
			System.out.printf("1. Agregar producto\n");
			System.out.printf("2. Mostrar productos\n");
			System.out.printf("3. Mostrar stock\n");
			System.out.printf("4. Salir\n");
			System.out.printf("Seleccione una opcion: ");
			
			String opcion = br.readLine();
			
			switch (opcion) {
			case "1":
				agregarProducto(br);
				break;
			case "2":
				mostrarProducto();
				break;
			case "3":
				mostrarStock(br);
				break;
			case "4":
				System.out.printf("Saliendo....");
				return;
			default:
				System.out.printf("opcion invalida\n");
				break;
			}
		}
 }

	public static void agregarProducto(BufferedReader br) throws IOException{
		String producto = validarString(br, "Ingresar producto: ");
		String marca = validarString(br, "Ingresar marca: ");
		int cantidad = validarInt(br, "Ingresar cantidad: ");
		int precio = validarInt(br, "Ingresar precio: ");
		
		FileWriter fw = new FileWriter("datos.csv", true);
		fw.write(producto.toUpperCase() + "_;_" + marca.toUpperCase() + "_;_" + cantidad + "_;_" + precio + "\n");
		fw.close();
		System.out.printf("Producto agregado correctamente\n");
	}
	
	public static void mostrarProducto() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("datos.csv"));
		String linea;
		
		System.out.printf("======Lista de Productos=====\n");
		System.out.printf("%-12s %-12s %-12s %-12s\n",
				"Nombre","Marca","Cantidad","Precio");
		System.out.printf("-------------------------------------------\n");
		
		while((linea = br.readLine()) != null) {
			String [] datos = linea.split("_;_");
			System.out.printf("%-12s %-12s %-12s %-12s\n",
					datos[0],
					datos[1],
					datos[2],
					datos[3]);
		}
		
	}
	public static void mostrarStock(BufferedReader br) throws IOException{
		BufferedReader lector = new BufferedReader(new FileReader("datos.csv"));
		
		String linea;
		int stockProd;
		
		System.out.printf("De que producto quiere ver el stock: ");
		String producto = br.readLine();
		System.out.printf("======STOCK DEL PRODUCTO======\n");
		System.out.printf("%-12s %-12s %-12s \n",
				"Nombre","Marca","Stock");
		System.out.printf("-----------------------------------------\n");
		while((linea = lector.readLine()) != null ) {
			String [] datos = linea.split("_;_");
			String nombre = datos[0];
			String marca = datos[1];
			String numero = datos[2];
			stockProd = Integer.parseInt(numero);
			if (producto.toUpperCase().equals(nombre)) {
				System.out.printf("%-12s %-12s %-12s \n",nombre, marca, stockProd + "\n");
			}
			
			
		}
	
	}
	
	public static String validarString(BufferedReader br, String mensaje) throws IOException{
		String input;
		while(true) {
			System.out.printf(mensaje);
			input = br.readLine();
			if(input.trim().isEmpty() || input == null) {
				System.out.printf("No puede estar vacio\n");
				continue;
			}
			if(input.length() <= 1){
				System.out.printf("Texto demasiado corto\n");
				continue;
			}
			if(input.matches("\\d+")){
				System.out.printf("No puede ser un numero\n");
				continue;
			}
			return input;
		}
	}
	public static int validarInt(BufferedReader br, String mensaje) throws IOException{
		int numero;
		while (true) {
			System.out.printf(mensaje);
			String input = br.readLine();
			
			try {
				numero = Integer.parseInt(input);
				if(numero < 0) {
					System.out.printf("No puede ser negativo\n");
				}
				return numero;
			}catch(NumberFormatException e) {
				System.out.printf("Formato invalido\n");
			}
		}
	}
}
	
