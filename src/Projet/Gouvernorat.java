package Projet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class Gouvernorat {
	 private String nom;
	    private int nb_c; //nombre de centres par gouvernorat
	    private Vector <Centre_isolement> centres ;

	    Gouvernorat(String nom)
	    {
	    	this.nom=nom;
	    	try {
				BufferedReader lecture = new BufferedReader(new FileReader("centres.txt")) ;
				centres = new Vector<>();
				String l ;
				String temp[] = new String[4] ;
				while ((l=lecture.readLine()) != null)
				{
					temp= l.split("\t");
					 String gov=temp[1];
					if (temp[1].equalsIgnoreCase(nom))
					{
						long ref = Long.parseLong(temp[0]) ; 
						String ad = temp[2] ; int max=Integer.parseInt(temp[3]) ; 
					    Centre_isolement c = new Centre_isolement(ref,ad,gov,max);
						ajouter_centre(c) ;
					}
				}	
			} catch (FileNotFoundException e) {
				System.out.println("Fichier non trouvé") ;
				System.exit(-1);
			} catch (IOException e) {
				System.out.println("Lecture impossible") ;
				System.exit(-1);
			} 
	    }
	    public boolean est_unique(long ref)
	    {
    		boolean b = false;
	    	for (Centre_isolement value : centres)
    		{
	    	if((value.get_ref()==ref))
	    		b=true ;
    		}
	    	return(b);
	    }
	    public void ajouter_centre(Centre_isolement c)
	    {
			
	    		centres.add(c);
				nb_c++ ;
	    	}
	
	    public void afficher_centres()
	    {
	        for(Centre_isolement value : centres)
	        {
	            value.afficher_centre();
	        }
	    }
	    public float personnes_contamines()
	    {
	        float s = 0;
	        float q = 0;
	        for(int i = 0;i <nb_c; i++)
	        {
	        	s =s+ (centres.elementAt(i)).get_ac() ;
		           System.out.println(s);
	            q =q+ (centres.elementAt(i)).get_malades();
		           System.out.println(q);

	        }
	        System.out.println(((float)(q/s))*100);
	        return((q/s)*100);
	    }
	    public float personne_contaminees(long ref)
	    {
	    	float a =0;
	    	for (Centre_isolement value : centres)
			{
				
				if(value.get_ref()==ref)
			    	a=(float)value.get_malades()/(float)value.get_ac()*100 ;
			}
			return (a);
	    }
	    public void afficher_gouvernorat()
	    {
	    	System.out.println("nom "+nom+" centre "+nb_c);
	    }
	    public String get_nom_gouv()
	    {
	    	return(nom);
	    }
	    public int get_nbc()
	    {
	    	return(nb_c);
	    }
	    public void afficher_personnes(long ref)
	    {
	    		for (Centre_isolement value : centres)
	    		{
	    			if((value.get_ref()==ref))
	    					value.afficher_personnes();
	    		}
			}
	    public void supprimer(long cin , long ref)
	    {
	    	for (Centre_isolement value : centres)
    		{
    			if(est_unique(ref)==true)
    			{
    				for (int i=0 ;i<value.get_nbactuel();i++)
    		    		{if((value.get_p(i)).getNum_cin()==cin)
    	    					value.supprimer(value.get_p(i));}
    	    		}
    			}
	    		
	    }
	    public void changer(long cin , int e)
	    {
	    	for (Centre_isolement value : centres)
	    	{
	    		value.changer(cin, e);
	    	}
	    }
	    public void afficher_departs(long ref)
		{
	    	for (Centre_isolement value : centres)
				{
				if(value.get_ref()==ref)	
	    		value.afficher_departs();
				}
		}
	    public void hospitalisees(long ref)
		{
	    	for (Centre_isolement value : centres)
				{
				if(value.get_ref()==ref)	
	    		value.hospitalisees();
				}
		}
	    public long[][] disponible()
	    {
	    	long mat[][]= new long [2][20];
	    	int i=0 ;
	    	for (Centre_isolement value : centres)
	    	{
	    		if(value.get_nbactuel()<value.get_nbmax())
			{	
						mat[0][i]=value.get_ref();
						mat[1][i]=(value.get_nbmax()-value.get_nbactuel());
		    			i++ ;
					}
					}
	    	long mat2[][]= new long [2][i] ;
	    	for (int e=0 ;e<2 ;e++)
	    	{
	    		for(int f=0 ;f<i;f++)
	    		{
	    			mat2[e][f]=mat[e][f];
	    		}
	    		
	    	}
    	return(mat2);
	    }
	   
	    public void ajouter(Personne_concernee p)
	    {
	    	for (Centre_isolement value : centres)
			{
				value.ajouter_personne(p);
			}
	    	
	    }
	    }
	    
	 

