package Projet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.Calendar;
import java.util.Date;
public class Centre_isolement {
	private long num_ref ;
	private String Adresse ;
	private String gouv_appartenance ;
	private int max ;
	private int nb_actuel ;
	private  int nb_ac ;
	private  int nb_malades ;
	private Vector <Personne_concernee> personnes ;
	public Centre_isolement (long num_ref,String Adresse,String gouv_appartenance,int max )
	{
		this.num_ref=num_ref ;
		this.Adresse=Adresse ;
		this.gouv_appartenance=gouv_appartenance;
		this.max=max;
		try {
			BufferedReader lecture = new BufferedReader(new FileReader("personnes.txt")) ;
			personnes = new Vector<>();
			String l ;
			String temp[] = new String[6] ;
			while ((l=lecture.readLine()) != null)
			{
				temp= l.split("\t");
				if ( num_ref==(Integer.parseInt(temp[6])))
				{
					String nom = temp[0] , prenom = temp[1] , gov=temp[3] ;
					long num = Integer.parseInt(temp[2]);	int etat = Integer.parseInt(temp[4]);
				    SimpleDateFormat f=new SimpleDateFormat("dd-MM-yyyy"); 
				    Date d=f.parse(temp[5]); 
				    Calendar cal = Calendar.getInstance();
				    cal.setTime(d);
				    Personne_concernee p = new Personne_concernee(nom,prenom,num,etat,cal);
					ajouter_personne(p) ;	
				}
			}	
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé") ;
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("Lecture impossible") ;
			System.exit(-1);
		} catch (ParseException e) {
			System.out.println("date invalide") ;
			System.exit(-1);
		}	
	}
	public void ajouter_personne(Personne_concernee p)
	{
		if(nb_actuel<max)
		{
			personnes.add(p);
			nb_actuel++ ;
			nb_ac++ ;
			set_malades();
		}
		
	}
	public void afficher_centre()
	{
		System.out.println("réference"+"\t\t"+"Adresse"+"\t\t\t"+"Gouvernorat"+"\t\t"+"chambres"+"\t\t"+"chambres_occupés");
		System.out.println(num_ref+"\t\t\t"+Adresse+"\t\t\t\t"+gouv_appartenance+"\t\t"+max+"\t\t\t"+nb_actuel);
		
	}
	public void afficher_personnes()
	{
		for (Personne_concernee value : personnes)
		{
			value.afficher_personne() ;
		}
	}
	public int get_nbactuel()
	{
		return(nb_actuel) ;
	}
	public int get_ac()
	{
		return(nb_ac) ;
	}
	public void set_nbac()
	{
		nb_ac ++ ;
	}
	public int get_malades()
	{
		return(nb_malades) ;
	}
	public long get_ref()
	{
		return(num_ref) ;
	}
	public void set_malades()
	{
		int a = 0;
		for (Personne_concernee value : personnes)
		{
			if(value.getEtat_sante()==1 || value.getEtat_sante()==2)
				a++ ;
		}
		nb_malades=a;
	}
	public int get_nbmax()
	{
		return(max);
		
	}
	public void supprimer(Personne_concernee p)
	{
		p.set_d_depart();
		personnes.remove(p) ;
		nb_actuel-- ;
	}
	public Personne_concernee get_p(int i)
	{
		return(personnes.elementAt(i));
	}
	public void changer(long cin,int e)
	{
		for (Personne_concernee value : personnes)
		{
			if(value.getNum_cin()==cin)
				value.setEtat_sante(e);
		}
	}
	public void afficher_departs()
	{
		{
			for (Personne_concernee value : personnes)
			{
				if((value.getEtat_sante()==0 & value.fin_confinement()))
					value.afficher_personne();
			}
		}
	}
	public void hospitalisees()
	{
		for (Personne_concernee value : personnes)
		{
			if(value.getEtat_sante()==1)
				value.afficher_personne();
		}
	}
	
		
	
	
	

	
	

}
