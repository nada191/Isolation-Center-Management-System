package Projet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
						int ref = Integer.parseInt(temp[0]) ; 
						String ad = temp[2] ; int max=Integer.parseInt(temp[3]) ; 
					    Centre_isolement c = new Centre_isolement(ref,ad,gov,max);
						ajouter_centre(c) ;
					}
				}
				lecture.close();
			} catch (FileNotFoundException e) {
				System.out.println("Fichier non trouvé") ;
				System.exit(-1);
			} catch (IOException e) {
				System.out.println("Lecture impossible") ;
				System.exit(-1);
			} 
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
	    public float personnes_contamines()  // satistiques 
	    {
	        float s = 0;
	        float q = 0;
	        for(Centre_isolement value : centres)
	        {
	        	s =s+ (value.get_ac()) ;
	            q =q+ (value.get_malades());

	        }
	        if(s==0)
	        	{System.out.println("les statistiques de contamination est de 0%");
	        	return(0);}
	        else {
		        System.out.println("les statistiques de contamination est de "+((float)(q/s))*100+"%");
	        	return((q/s)*100);

	        }
	    }
	    public float personne_contamines(int ref) //statistiques
	    {
	    	float a =0;
	    	for (Centre_isolement value : centres)
			{
				
				if(value.get_ref()==ref)
			    	{if((float)value.get_malades()==0)System.out.println("les statistiques de contamination est de 0%");
			    	else {
			    	a=(((float)value.get_malades())/((float)value.get_ac()))*100 ;
			    	System.out.println("\nles statistiques de contamination est de "+a+"%");
			    	break ;}}
			}
			return (a);
	    }

	    public String get_nom_gouv()
	    {
	    	return(nom);
	    }
	    public int get_nbc()
	    {
	    	return(nb_c);
	    }
	    public void afficher_personnes(int ref)
	    {
	    		for (Centre_isolement value : centres)
	    		{
	    			if((value.get_ref()==ref))
	    					{
	    				value.afficher_personnes();
	    				break ;
	    					}
	    		}
			}
	    public void supprimer(long cin , int ref)
	    {
	    	for (Centre_isolement value : centres)
    		{
    			if(value.get_ref()==ref)
    			{
    				for (int i=0 ;i<value.get_nbactuel();i++)
		    		{if((value.get_p(i)).getNum_cin()==cin)
		    		{
		    			if(value.etat(cin)!=2)
	    				{
	    					Scanner ab = new Scanner(System.in); 
	    					System.out.print("Note Equipe médicale : ");
	    					int a = ab.nextInt();
	    					System.out.print("Note nourriture");
	    					int b = ab.nextInt();
	    					System.out.print("Note mesure d'hygiéne");
	    					int c = ab.nextInt();
							try {
								PrintWriter ecriture = new PrintWriter (new FileWriter("evaluation.txt", true));
								ecriture.println(ref+"\t"+a+"\t"+b+"\t"+c) ;
								ecriture.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
		    		}
						value.supprimer(value.get_p(i));
	    		}}
    				
    			}
      	    		}}
	    public void changer(long cin , int e,int ref)
	    {
	    	for (Centre_isolement value : centres)
	    	{
	    		if(value.get_ref()==ref)
	    		{
		    		value.changer(cin, e);
		    		break;
	    		}
	    	}
	    }
	    public void afficher_departs(long ref)
		{
	    	for (Centre_isolement value : centres)
				{
				if(value.get_ref()==ref)	
					{value.afficher_departs();
					break ;
					}
				
				}
		}
	    public void hospitalisees(long ref)
		{
	    	for (Centre_isolement value : centres)
				{
				if(value.get_ref()==ref)
				{
		    		value.hospitalisees();
		    		break;
				}
				}
		}
	    public int[][] disponible() //disponibilités des centres d'isolements
	    {
	    	int mat[][]= new int [2][15];
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
	    	int mat2[][]= new int [2][i] ;
	    	for (int e=0 ;e<2 ;e++)
	    	{
	    		for(int f=0 ;f<i;f++)
	    		{
	    			mat2[e][f]=mat[e][f];
	    		}
	    		
	    	}
    	return(mat2);
	    }
	   
	    public void ajouter(Personne_concernee p,int ref)
	    {
	    	for (Centre_isolement value : centres)
			{
				if(value.get_ref()==ref) {
					value.ajouter_personne(p);
					break ;}
			}
	    	
	    }
	    boolean existe_centre_cin(int ref,long cin)		//existence d'une personne dans un centre donné
	    { boolean b=false;
	    	for (Centre_isolement value : centres)
			{
	    		if(value.get_ref()==ref)	
	    			if(value.existe(cin)) {
	    				b=true;
	    				break;
	    				
	    			
	    		}
	    			
			}
	   return b;
	    }
	    boolean existe_centre(int ref)	//existence d'un centre dans le gouvernorat
	    { boolean b=false;
    	for (Centre_isolement value : centres)
		{
    		if(value.get_ref()==ref)	
    		{
    				b=true;
    				break;
    				
    			
    		}
		}
   return b;
    }
	   
	    }
	    
	 

