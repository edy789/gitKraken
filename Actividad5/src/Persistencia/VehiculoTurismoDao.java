package Persistencia;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Dominio.*;
import Persistencia.*;

public class VehiculoTurismoDao extends VehiculoDao {

	@Override
	public boolean insertar(Vehiculo VehiculoTurismos) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		boolean registrar = false;
		
		Statement stm= null;
		Connection con=null;
		
		String sql="INSERT INTO turismos values ('"+VehiculoTurismos.getMatricula()+"','"+VehiculoTurismos.getMarca()+"','"+VehiculoTurismos.getModelo()+"',"+VehiculoTurismos.getColor() +")";
		String sql2="INSERT INTO turismos values ('"+VehiculoTurismos.getMatricula()+"',"+((VehiculoTurismos)VehiculoTurismos).getNumpuertas()
				+")";
		
		try {			
			con=Conexion.conectar();
			stm= con.createStatement();
			stm.execute(sql);
			registrar=true;
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase VehiculoTurismos, método registrar");
			e.printStackTrace();
		}
		return registrar;
	}

	@Override
	public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException    {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM turismos ORDER BY matricula";

		ArrayList<Vehiculo> listaVehiculo = new ArrayList<Vehiculo>();

		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				listaVehiculo.add(new VehiculoTurismos(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getInt(6),null));
			}
			stm.close();
			rs.close();
			co.close();
			for (int i = 0; i < listaVehiculo.size(); i++) {
				co = Conexion.conectar();
				stm = co.createStatement();
				sql = "SELECT * FROM turismos WHERE matricula='" + listaVehiculo.get(i).getMatricula() + "'";
				rs = stm.executeQuery(sql);
				rs.next();
				listaVehiculo.get(i).setMarca(rs.getString(2));
				listaVehiculo.get(i).setModelo(rs.getString(3));
				listaVehiculo.get(i).setColor(rs.getString(4));
				listaVehiculo.get(i).setPrecio(rs.getInt(6));
				

				stm.close();
				rs.close();
				co.close();
			}
		} catch (SQLException e) {
			System.out.println("Error: Clase VehiculoTurismos, método obtener");
			e.printStackTrace();
		}

		return listaVehiculo;
	}

	@Override
	public Vehiculo leer(String matricula) throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;

		Vehiculo leerVehiculo = null;
		String sql = "SELECT * FROM turismos WHERE matricula='" + matricula + "'";
		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				leerVehiculo = new VehiculoTurismos(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getInt(6),null);
			}
			stm.close();
			rs.close();
			co.close();
			if(leerVehiculo!=null) {
				co = Conexion.conectar();
				stm = co.createStatement();
				sql = "SELECT * FROM turismos WHERE matricula='" + leerVehiculo.getMatricula() + "'";
				rs = stm.executeQuery(sql);
				rs.next();
				leerVehiculo.setMarca(rs.getString(2));
				leerVehiculo.setModelo(rs.getString(3));
				leerVehiculo.setColor(rs.getString(4));
				leerVehiculo.setPrecio(rs.getInt(6));

				}

		} catch (SQLException e) {
			System.out.println("Error:  método leer");
			e.printStackTrace();
		}
		return (Vehiculo) leerVehiculo;
	}
	@Override
	public boolean actualizar(Vehiculo VehiculoTurismos, String matricula) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean actualizar=false;
		if(VehiculoTurismos.getMatricula().equals(matricula)) {			
		String sql="UPDATE turismos SET matricula='"+VehiculoTurismos.getMatricula()+"', marca='"+VehiculoTurismos.getMarca()+"', modelo='"+VehiculoTurismos.getModelo()+"', color='"+VehiculoTurismos.getColor()+"' , precio="+VehiculoTurismos.getPrecio()+"', capacidad="+((VehiculoTurismos)VehiculoTurismos).getNumpuertas()+" WHERE Matricula='"+VehiculoTurismos.getMatricula()+"'";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			actualizar=true;
			stm.close();
			connect.close();
		} catch (SQLException e) {
			System.out.println("Error: método actualizar");
			e.printStackTrace();
		}		
		}else {
		String sql="INSERT INTO turismos values ('"+VehiculoTurismos.getMatricula()+"','"+VehiculoTurismos.getMarca()+"', '"+VehiculoTurismos.getModelo()+"', '"+VehiculoTurismos.getColor()+"', "+VehiculoTurismos.getPrecio()+((VehiculoTurismos)VehiculoTurismos).getNumpuertas()+")";
		String sql2="DELETE FROM turismos WHERE matricula='"+matricula+"'";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			stm.execute(sql2);

			actualizar=true;
			stm.close();
			connect.close();
		} catch (SQLException e) {
			System.out.println("Error: método actualizar");
			e.printStackTrace();
		}	
		}
			
		
		return actualizar;
	}
 

	@Override
	public boolean eliminar(Vehiculo VehiculoTurismos) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean eliminar=false;
		
		String sql="DELETE FROM turismos WHERE matricula='"+VehiculoTurismos.getMatricula()+"'";

		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			eliminar=true;
		} catch (SQLException e) {
			System.out.println("Error:  método eliminar");
			e.printStackTrace();
		}				
			
		return eliminar;
	}


	@Override
	public boolean eliminarTodo() throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean eliminar=false;
				
		String sql="DELETE FROM turismos";
		
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			eliminar=true;
		} catch (SQLException e) {
			System.out.println("Error:  método eliminar");
			e.printStackTrace();
		}		
		return eliminar;		
	}


	}
	
	




	