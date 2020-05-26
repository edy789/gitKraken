package Dominio;

import java.util.ArrayList;
import Persistencia.ExtrasVehiTurismosDao;

public class ExtrasVehiTurismos {
	private int identificador;
	private String descripcion;
	private ExtrasVehiTurismosDao extrasVehiTurismosDao;

	public ExtrasVehiTurismos(int identificador, String descripcion) {
		this.identificador = identificador;
		this.descripcion = descripcion;
		extrasVehiTurismosDao= new ExtrasVehiTurismosDao();
	}
	public ExtrasVehiTurismos() {
		extrasVehiTurismosDao= new ExtrasVehiTurismosDao();
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "ExtrasVehiTurismos [identificador=" + identificador + ", descripcion=" + descripcion + "]";
	}
	
	public void insertar() throws ClassNotFoundException {
		extrasVehiTurismosDao.insertar(this);
	}

	public ArrayList<ExtrasVehiTurismos> leerTodos() throws ClassNotFoundException {
		return extrasVehiTurismosDao.leerTodos();
	}
	
	public ExtrasVehiTurismos leerExtrasVehiTurismos(int identificador) throws ClassNotFoundException {
		return extrasVehiTurismosDao.leer(identificador);
	}

	public void actualizar(int identificador) throws ClassNotFoundException {
		extrasVehiTurismosDao.actualizar(this, identificador);
	}

	public void eliminar() throws ClassNotFoundException {
		extrasVehiTurismosDao.eliminar(this);
	}

	public void eliminarTodo() throws ClassNotFoundException {
		extrasVehiTurismosDao.eliminarTodo();

	}

		
}
