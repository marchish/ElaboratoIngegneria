package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Somministrazione {
	protected static Connection c = null;
    protected static Statement stmt = null;
    
	private Integer dose;
	private String modalitÓ;
	private Terapia terapia;//chiave
	private Farmaco farmaco;//chiave
	private String infermiere;//chiave
	
	public Somministrazione(Terapia t, Farmaco f, String i,String m, Integer d) {
		this.terapia=t;
		this.farmaco=f;
		this.infermiere=i;
		this.modalitÓ=m;
		this.dose=d;	
	}
	public Somministrazione(String t, String f,String i) {
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:GestioneOspedale.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      boolean ok=false;
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM Somministrazione;" );
		      while ( rs.next() ) {
		         if(rs.getString(1).equals(t) && rs.getString(2).equals(f) && rs.getString(3).equals(i)){
		        	 ok=true;
		        	 break;
		         }
		      }
		      if(ok){
		    	  this.terapia = new Terapia(rs.getString(1));
		        	 this.farmaco = new Farmaco(f);
		        	 this.infermiere = i;
		        	 this.modalitÓ=rs.getString(5);
		        	 this.dose=new Integer(rs.getInt(4));
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
	}
	
	public String getModalitÓ(){
		return modalitÓ;
	}
	public Integer getDosaggio(){
		return dose;
	}
	public Terapia getTerapia(){
		return this.terapia;
	}
	public Farmaco getFarmaco(){
		return this.farmaco;
	}
	public Infermiere getInfermiere(){
		return new Infermiere(this.infermiere);
	}
	public String getInfermiereAux(){
		return this.infermiere;
	}
	
	public void setTerapia(String k){
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:GestioneOspedale.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "UPDATE Somministrazione "
		      		+ "SET Terapia='"+ k +"' WHERE Dosaggio='"+ this.getDosaggio()+"' AND Terapia="+ terapia.getRicovero() +";" );
		      rs.close();
		      stmt.close();
		      c.close();
		      this.terapia=new Terapia(k);
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
	}
	public void setFarmaco(String k){
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:GestioneOspedale.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "UPDATE Somministrazione "
		      		+ "SET Farmaco='"+ k +"' WHERE Farmaco='"+ this.getFarmaco()+"' AND Terapia="+ terapia.getRicovero() +"' AND Infermiere="+ infermiere +";" );
		      rs.close();
		      stmt.close();
		      c.close();
		      this.farmaco=new Farmaco(k);
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
	}
	public void setInfermiere(String k){
		      this.infermiere= k;
	}
	public void setDosaggio(Integer k){
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:GestioneOspedale.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "UPDATE Somministrazione "
		      		+ "SET Dosaggio='"+ k +"' WHERE Farmaco='"+ this.getFarmaco()+"' AND Terapia="+ terapia.getRicovero() +"' AND Infermiere="+ infermiere +";" );
		      rs.close();
		      stmt.close();
		      c.close();
		      this.dose= k;
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
	}
	public void setModalitÓ(String k){
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:GestioneOspedale.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "UPDATE Somministrazione "
		      		+ "SET ModalitÓ='"+ k +"' WHERE Farmaco='"+ this.getFarmaco()+"' AND Terapia="+ terapia.getRicovero() +"' AND Infermiere="+ infermiere +";" );
		      rs.close();
		      stmt.close();
		      c.close();
		      this.modalitÓ= k;
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
	}
	
	public String toString(){
		return " "+terapia.getRicovero().getCodiceUnivoco()+" "+farmaco.getNome();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
