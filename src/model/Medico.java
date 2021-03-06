package model;

import java.sql.DriverManager;
import java.sql.ResultSet;

public class Medico extends Operatore {
	
	protected String SpecialitÓ;
	
	public Medico(String codice, String nome2, String cognome2, String dataN, String luogoN, String dataS, String spec ) {
		super(codice, nome2, cognome2, dataN, luogoN, dataS);
		this.SpecialitÓ=spec;
	}
	
	public Medico(String key) {
		super(key);
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:GestioneOspedale.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM Medico;" );
		      while ( rs.next() ) {
		    	  if(rs.getString("Operatore").equals(key)){
		    		  this.SpecialitÓ= rs.getString("SpecialitÓ");
				      break;
		    	  }
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		// TODO Auto-generated constructor stub
	}
	
	public String getSpecialitÓ(){
		return this.SpecialitÓ;
	}
	

	
	public void setSpecialitÓ(String k){
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:GestioneOspedale.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "UPDATE Medico "
		      		+ "SET SpecialitÓ='"+ k +"' WHERE Operatore='"+ this.codiceFiscale +"';" );
		      rs.close();
		      stmt.close();
		      c.close();
		      this.SpecialitÓ=k;
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		
	}
	public String toString(){
		return codiceFiscale+" "+nome+" "+cognome;
	}
	
	public String stampa(){
		return "Codice Fiscale: "+this.codiceFiscale+
				"\nNome e Cognome: "+ this.nome +"  "+this.cognome+
				"\nLuogo e Data Nascita: "+ this.luogoNascita +"  "+this.dataNascita +
				"\nData Assunzione: "+ this.dataAssunzione+"  SpecialitÓ: "+this.SpecialitÓ;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
