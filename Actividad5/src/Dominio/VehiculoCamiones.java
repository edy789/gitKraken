 package Dominio;

import java.util.ArrayList;
import Persistencia.VehiculoCamionesDao;

import Persistencia.VehiculoCamionesDao;

public class VehiculoCamiones extends Vehiculo{
	private double capacarga;
	private VehiculoCamionesDao cDAO;

	public VehiculoCamiones(String matricula, String marca, String modelo, String color, double precio,
			double capacarga) {
		super(matricula, marca, modelo, color, precio);
		cDAO = new VehiculoCamionesDao();
		this.capacarga = capacarga;
	}
	public VehiculoCamiones() {
		cDAO = new VehiculoCamionesDao();
	}

	public double getCapacarga() {
		return capacarga;
	}

	public void setCapacarga(double capacarga) {
		this.capacarga = capacarga;
	}

	public boolean insertar() throws ClassNotFoundException { 
		return cDAO.insertar(this);
	}

	@Override
	public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException {
		return cDAO.leerTodos();
	}

	@Override
	public Vehiculo leerVehiculo(String matricula) throws ClassNotFoundException {
		return cDAO.leer(matricula); 
	}

	@Override
	public void actualizar(String matricula) throws ClassNotFoundException {
		cDAO.leer(matricula); 
	}

	@Override
	public void eliminar() throws ClassNotFoundException {
		cDAO.eliminar(this);
	}

	@Override
	public void eliminarTodo() throws ClassNotFoundException {
		cDAO.eliminarTodo();

}
		
	
}
