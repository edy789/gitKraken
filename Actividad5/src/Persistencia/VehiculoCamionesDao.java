package Persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.*;
import Persistencia.*;


public class VehiculoCamionesDao extends VehiculoDao{
		public VehiculoCamionesDao() {
			
		}
		
		public boolean insertar(Vehiculo VehiculoCamiones) throws ClassNotFoundException {
			boolean registrar = false;
			
			Statement stm= null;
			Connection con=null;
			
			String sql="INSERT INTO Camines values ('"+VehiculoCamiones.getMatricula()+"','"+VehiculoCamiones.getMarca()+"','"+VehiculoCamiones.getModelo()+"',"+VehiculoCamiones.getColor() +")";
			String sql2="INSERT INTO Camiones values ('"+VehiculoCamiones.getMatricula()+"',"+((VehiculoCamiones)VehiculoCamiones).getCapacarga()+")";
			
			try {			
				con=Conexion.conectar();
				stm= con.createStatement();
				stm.execute(sql);
				registrar=true;
				stm.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Error: Clase VehiculoCamiones, método registrar");
				e.printStackTrace();
			}
			return registrar;
		}

		public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException {
			Connection co = null;
			Statement stm = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM Camiones ORDER BY matricula";

			ArrayList<Vehiculo> listaVehiculo = new ArrayList<Vehiculo>();

			try {
				co = Conexion.conectar();
				stm = co.createStatement();
				rs = stm.executeQuery(sql);
				while (rs.next()) {
					listaVehiculo.add(new VehiculoCamiones(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6)));
				}
				stm.close();
				rs.close();
				co.close();
				for (int i = 0; i < listaVehiculo.size(); i++) {
					co = Conexion.conectar();
					stm = co.createStatement();
					sql = "SELECT * FROM Camiones WHERE matricula='" + listaVehiculo.get(i).getMatricula() + "'";
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
				System.out.println("Error: Clase VegiculoCamion, método obtener");
				e.printStackTrace();
			}

			return listaVehiculo;
		}
		
		public Vehiculo leer(String Matricula) throws ClassNotFoundException {
			Connection co = null;
			Statement stm = null;
			ResultSet rs = null;

			Vehiculo leerVehiculo = null;
			String sql = "SELECT * FROM Camiones WHERE matricula='" + Matricula + "'";
			try {
				co = Conexion.conectar();
				stm = co.createStatement();
				rs = stm.executeQuery(sql);
				while (rs.next()) {
					leerVehiculo = new VehiculoCamiones(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6));
				}
				stm.close();
				rs.close();
				co.close();
				if(leerVehiculo!=null) {
					co = Conexion.conectar();
					stm = co.createStatement();
					sql = "SELECT * FROM Camiones WHERE matricula='" + leerVehiculo.getMatricula() + "'";
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
		
		
		public boolean actualizar(Vehiculo VehiculoCamiones, String Matricula) throws ClassNotFoundException {
			Connection connect= null;
			Statement stm= null;
			
			boolean actualizar=false;
			if(VehiculoCamiones.getMatricula().equals(Matricula)) {			
			String sql="UPDATE Camiones SET matricula='"+VehiculoCamiones.getMatricula()+"', marca='"+VehiculoCamiones.getMarca()+"', modelo='"+VehiculoCamiones.getModelo()+"', color='"+VehiculoCamiones.getColor()+"' , precio="+VehiculoCamiones.getPrecio()+"', capacidad="+((VehiculoCamiones)VehiculoCamiones).getCapacarga()+" WHERE Matricula='"+VehiculoCamiones.getMatricula()+"'";
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
			String sql="INSERT INTO Camiones values ('"+VehiculoCamiones.getMatricula()+"','"+VehiculoCamiones.getMarca()+"', '"+VehiculoCamiones.getModelo()+"', '"+VehiculoCamiones.getColor()+"', "+VehiculoCamiones.getPrecio()+"+((VehiculoCamiones)VehiculoCamiones).getCapacarga()+"+")";
			String sql3="DELETE FROM Camiones WHERE matricula='"+Matricula+"'";
			try {
				connect=Conexion.conectar();
				stm=connect.createStatement();
				stm.execute(sql);
				stm.execute(sql3);

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
	 
		
		public boolean eliminar(Vehiculo VehiculoCamiones) throws ClassNotFoundException {
			Connection connect= null;
			Statement stm= null;
			
			boolean eliminar=false;
			
			String sql="DELETE FROM Camiones WHERE atricula='"+VehiculoCamiones.getMatricula()+"'";

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

		public boolean eliminarTodo() throws ClassNotFoundException {
			Connection connect= null;
			Statement stm= null;
			
			boolean eliminar=false;
					
			String sql="DELETE FROM Camiones";
			
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
