package Projet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Manipulation {
	private Vector <Gouvernorat> pays ;
	private float [][] distances ;
	public Manipulation()
	{
		try {
			BufferedReader lecture = new BufferedReader(new FileReader("pays.txt")) ;
			pays = new Vector<>(10);
			String l = lecture.readLine() ;
			String temp[] = l.split("\t") ;
			distances = new float [10][10];
			for (int i=0 ; i<temp.length;i++)
			{
				Gouvernorat g = new Gouvernorat(temp[i]) ;
				pays.add(g);
			}
			int j=0 ;
			while ((l=lecture.readLine()) != null)
			{
				String temp2[] = l.split("\t");
					for(int i=0 ; i<temp2.length ; i++)
					{
						distances[j][i]=Float.parseFloat(temp2[i]) ;
					}
					j++ ;
				
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé") ;
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("Lecture impossible") ;
			System.exit(-1);
		}

	}
	public void afficher()
	{
		for (Gouvernorat value : pays)
		{
			
			value.afficher_gouvernorat() ;
		}
	}
	public void afficher_centres(String g)
	{
		for(Gouvernorat value : pays)
    {
        
			if ((value.get_nom_gouv()).equalsIgnoreCase(g))
			{
				value.afficher_centres();
			}
    }
		
	}
	public void afficher_personnes(long ref) // par centre
	{
		for (Gouvernorat value : pays)
		{
			value.afficher_personnes(ref);
		}

				
		}
	 public boolean est_unique(long ref)
	    {
 		boolean b = false;
	    	for (Gouvernorat value : pays)
 		{
	    	if(value.est_unique(ref))
	    		b=true ;
 		}
	    	return(b);
	    }
	public void ajouter_centre(String g)
	{Scanner clavier = new Scanner(System.in); 
		for (Gouvernorat value : pays)
		{
			if((value.get_nom_gouv()).equalsIgnoreCase(g))
				{
				System.out.println("reference");
				long ref;
				ref = clavier.nextLong();
				while (est_unique(ref)==true)
					ref = clavier.nextLong();
				System.out.println("capacite");
				int cap = clavier.nextInt();
				System.out.println("adresse");
				Scanner cl = new Scanner(System.in); 
				String ad = cl.nextLine();
				
				Centre_isolement c = new Centre_isolement(ref,ad,g,cap);
				value.ajouter_centre(c);
			
		}
		}}
	public void supprimer_personne(int cin , int ref)
	{
		for (Gouvernorat value : pays)
		{
			
			value.supprimer(cin,ref);
		}
	}
	public void statistiques_centre(String gouv)
	{
		for (Gouvernorat value : pays)
		{
			if((value.get_nom_gouv()).equalsIgnoreCase(gouv))
			{
				System.out.println(value.personnes_contamines());

			}
		}
	}
	public void statistiques_gouv(long ref)
	{
		float b =0;
		for (Gouvernorat value : pays)
		{
			b =value.personne_contaminees(ref);
		}
		System.out.println(b);
	}
	public Vector<Gouvernorat> plus_proche(String g)
	{
		int i=0 ;
		for (Gouvernorat value : pays)
		{
			boolean b =value.get_nom_gouv().equalsIgnoreCase(g);
			if(!b)
				{
				i++;
				}
			else
				break ;
		}
		float tab[]= new float[10];
		int indice[]= {0,1,2,3,4,5,6,7,8,9};
		Vector<Gouvernorat>trie ;
		trie= new Vector<>();
		trie.setSize(10);
		for(int f=0;f<10;f++)
		{
			tab[f]=distances[i][f];

			}
		
		float min;
		int m ;
		boolean b=true;
		while (b==true)
		{
		b=false ;
		for (int k=0 ;k<tab.length-1;k++)
		{
				if(tab[k]>tab[k+1])
				{
					min = tab[k];
					tab[k]=tab[k+1];
					tab[k+1]=min;
					m = indice[k];
					indice[k]=indice[k+1];
					indice[k+1]=m;
					b=true;
							
				}
			}
		}
for(int e=0;e<10;e++)
{
	trie.setElementAt(pays.elementAt(indice[e]),e);
	
}
return(trie);
		
	}
	void Demande(int nb , String gouv)
	{
		boolean b =false ;
		int a =nb ;
		Scanner cl = new Scanner(System.in);
		Vector<Gouvernorat> V = plus_proche(gouv);
		for (Gouvernorat value : V) {
			long[][] mat = value.disponible();
				for (int i=0 ;i<mat[0].length;i++)
				{
					int cap = (int)(mat[1][i]);
					if (cap > nb)
					{System.out.println(mat[0][i]+" gouv "+value.get_nom_gouv()+" nb "+nb);
					nb=0 ;
					}
					else
					{	nb=nb-cap ;
					System.out.println(mat[0][i]+" gouv "+value.get_nom_gouv()+" nb "+cap);}

					if (nb<=0)
					{
						b = true ;
						break ;

					}
				}
				if (b)
					break ;
			}
		System.out.println("confirmer l'ajout : oui/non");
		String rep = cl.next();
		if(rep.equalsIgnoreCase("oui"))
		{				int j=0 ;

			for (Gouvernorat value : V)
			{
					try {
						while(a>j)
						{
						System.out.println("nom "+j);
						String nom = cl.next();
						System.out.println("prenom "+j);
						String prenom = cl.next();
						System.out.println("cin "+j);
						long cin = cl.nextLong();
						System.out.println("date "+j);
						String date = cl.next();
						SimpleDateFormat f=new SimpleDateFormat("dd-MM-yyyy"); 
					    Date d;
						d = f.parse(date);
						 Calendar cal = Calendar.getInstance();
						    cal.setTime(d);
							System.out.println("etat "+j);
							int etat = cl.nextInt();
							Personne_concernee p = new Personne_concernee(nom,prenom,cin,etat,cal);
							value.ajouter(p);
							j++;
							 }
								
					}
							
					catch (ParseException e) {
						System.out.println("date invalide") ;
						System.exit(-1);}
			}}
					
		
	}
	public void changer_sante(long cin ,int e) 
{
	for (Gouvernorat value : pays)
	{
		value.changer(cin, e);
	}
	    	
}
	public void hospitalisees(long ref)
{
		for (Gouvernorat value : pays)
		{
			value.hospitalisees(ref);
		}
}
	

}
		
		

