package control;

import model.Tabella;

public class prova {
	public static void main( String args[] ){
		for(model.Paziente p: new Tabella().getListaPazienti()){
			System.out.println(p.getCodiceFiscale());
		}
		
		System.out.println();
		for(String s: control.AccettazioneControl.listaPazientiComboBox()){
			System.out.println(s);
		}
		System.out.println();
		for(model.Medico m: new model.Tabella().getListaMedici()){
			System.out.println(m.getNome()+" "+m.getCognome());
		}
		
	}
}