package Projet;

import java.util.Scanner;

public class tester {

	public static void main(String[] args) {
		Manipulation m = new Manipulation();
		
		String fonctionalite ="********************\tSystéme de gestion des centres d'isolements\t********************\n\n"+"Note : Il suffit de tapez le numéro correspondant à votre choix\n\n"+
		"\t1/ Affecter des personnes\n\n"+"\t2/ Liste des personnes ayant terminée leur période d'isolement\n\n"+
		"\t3/ Liste de toutes les personnes qui habitent un centre d'isolement\n\n"+
		"\t4/ Ajouter un centre d'isolement\n\n"+
		"\t5/ Afficher tous les centres d'isolement d'un gouvernorat\n\n"+
		"\t6/ Afficher le pourcentage de contamination d'un centre\n\n"+
		"\t7/ Afficher le pourcentage de contamination d'un gouvernorat\n\n"+
		"\t8/ Enregistrer le départ d'une personne\n\n"+
		"\t9/ Changer l'état de santé d'une personne\n\n"+
		"\t10/ Afficher les personnes hospitalisées\n\n"+
		"\t11/ Evaluer les services de votre centre d'isolement\n\n"+
		"\t12/ Demande d'un medecin disponible\n\n";
		System.out.println(fonctionalite);
		{		
		boolean test = false;
		while (test==false)
		{	Scanner cl = new Scanner(System.in);
			int ad=0 ;
			boolean t =true ;
			while(t)
			{
				try {
					System.out.print("Choix :");
					 String adr = cl.nextLine();
					 ad=Integer.parseInt(adr);
					 t=false;
					
				}
				catch(Exception e)
				{
					t=true;
				}
			}
			
		while(ad==1) {
			try {
			boolean b =true ;boolean ret = false;
			System.out.println("combien de personne? et leur gouvernorat? : ");
			String nb =cl.nextLine();
			int nbre=Integer.parseInt(nb);
			String gouv =cl.nextLine();
			if(m.existe(gouv)) {
			m.Demande(nbre,gouv);
			b=false;
			}
			if(b==false) break ;
			}
			catch(Exception e) {
				System.out.println("\nerreur de saisie veuillez réessayer ");
				ad=1;
			}
			
	}
		while(ad==2) {
			try {
				boolean b=true ;
			System.out.print("Donner la réference du centre à  visualiser : ");
			String nb =cl.nextLine();
		    int ref =  Integer.parseInt(nb);
		    if(m.existe(ref)) {
		    m.afficher(ref); b=false ;}
		    if(b==false) break ;
			}catch(Exception e) {
			System.out.println("\nerreur de saisie, veuillez réessayer  ");
			ad=2;
		}	
		}
		while(ad==3) {
			try {
				boolean b=true ;
			System.out.print("Donner la réference du centre à  visualiser : ");
			String nb =cl.nextLine();
		    int ref =  Integer.parseInt(nb);
		    if(m.existe(ref)) {
		    m.afficher_personnes(ref);
		    b=false ;} if (b==false) break;
		    }catch(Exception e) {
			System.out.println("\nerreur de saisie veuillez réessayer \n ");
			ad=3;
		}
		
			
		}
		while(ad==4) {	
		System.out.print("Dans quel gouvernorat? : ");
		String gouv =cl.nextLine();
		if(m.existe(gouv)) {
		m.ajouter_centre(gouv);
		ad=0 ;
		}
		
			
		}while(ad==5) {
			System.out.print("De quel gouvernorat? : ");
			String g =cl.nextLine();
			if(m.existe(g)) {
			m.afficher_centres(g); ad=0 ;}
			
		}while(ad==6) {try {
			boolean b=true ;
			System.out.print("Donner la réference du centre : ");
			String nb =cl.nextLine();
		    int ref =  Integer.parseInt(nb);
		    if(m.existe(ref)) {
		    m.statistiques_centre(ref); b=false ;}
		    if(b==false) break ;
		
		}catch(Exception e) {
			System.out.println("\nerreur de saisie veuillez réessayer  ");
			ad=6 ;
		}
		}
		
			
		while(ad==7) {
			System.out.print("Donner le gouvernorat : "); 
			String gouv =cl.nextLine();
			if(m.existe(gouv)) {
			m.statistiques_gouv( gouv);
			ad=0 ;}
			
		
		
		
			
		}while(ad==8) {
			try {
				boolean b=true;
			System.out.print("Donner son numéro de cin :"); 
			String c =cl.nextLine();
		    long cin =  Long.parseLong(c);
		    System.out.print("Donner la réference du centre auquel elle appartient :");
		    String nbre =cl.nextLine();	
		    int ref =  Integer.parseInt(nbre);
	        if(m.existe_centre_cin(ref,cin)) {
		    System.out.println("La suppresion est bien enregistrée");
		    m.supprimer_personne(cin, ref);
		    b=false ;
		
	}
	if(b==false) break ;
	}
	
	catch(Exception e) {
		System.out.println("\nerreur de saisie veuillez réessayer \n ");
	ad=8;
}
		}while(ad==9) {
			try {
				boolean b=true ;
				System.out.print("Donner son numéro de cin :"); 
				String ci =cl.nextLine();
			    long cin =  Long.parseLong(ci);
			    System.out.println("Taper: 0 si en bon santé ou la contamination n'est pas confirmée\n 1 si malade (situation grave (corona))\n 2 si situation normale (corona)");
			    String et =cl.nextLine();
			    int etat =  Integer.parseInt(et);
			    System.out.print("Donner la réference du centre : ");
		        String nbre =cl.nextLine();	
			    int ref =  Integer.parseInt(nbre);
			    if(m.existe_centre_cin(ref,cin))
		        {m.changer_sante(cin,etat,ref);
			    System.out.println("La mise à  jour est bien enregistree");
			    b=false;}
			    if(b==false) break ;
		}
		
		catch(Exception e) {
			System.out.println("\nerreur de saisie veuillez réessayer \n ");
			ad=9;

	}
			
		}while(ad==10) {
			try {
				boolean b= true ;
				System.out.print("Donner la réference du centre à  visualiser : ");
				 String nbre =cl.nextLine();	
				    int ref =  Integer.parseInt(nbre);
				    if(m.existe(ref)) {
			    m.hospitalisees(ref);
			    b=false ;}
				    if(b==false) break ;
			}catch(Exception e) {
				System.out.println("\nerreur de saisie veuillez réessayer \n ");
				ad=10;
			}
			
		}while(ad==11) {
			try {
				boolean b= true ;
				System.out.print("Donner la réference du centre auquel elle appartient : ");
				 String nbre =cl.nextLine();	
				  int ref =  Integer.parseInt(nbre);
				  if(m.existe(ref)) {
			    m.evaluation(ref);
			    b=false ;}
				  if(b==false) break ;
			}catch(Exception e) {
				System.out.println("\nerreur de saisie veuillez réessayer \n ");
				ad=11;
			}
		}while(ad==12) {
			System.out.print("Dans quel gouvernorat? : ");
			String gouv =cl.nextLine();
			if(m.existe(gouv)) {
				System.out.println("Choisissez La Spécialité voulue\n\t1)Geriatrie\n\t2)Urgence\n\t3)pneumologie\n\t4)Reanimation\n\t5)Pediatrie");
				ad=0;
			String z = cl.nextLine();
			int sp=Integer.parseInt(z);
			if(sp==1)
				m.Demande_docteur("geriatrie",gouv);
			if(sp==2)
				m.Demande_docteur("urgence",gouv);
			if(sp==3)
					m.Demande_docteur("pneumologie",gouv);
			if(sp==5)
				m.Demande_docteur("Pediatrie",gouv);
				if(sp==4)
					m.Demande_docteur("Reanimation",gouv);
				
			}
			
			
			
		}
		
		
		
	//retour au menu principal
		boolean b=true ;
		while(b)
		{
			try {
			System.out.print("\nTaper 0 pour retourner au menu principal et 1 pour quitter : ");
			String r = cl.nextLine();
			int retour = Integer.parseInt(r);
			if(retour==0) {System.out.println(fonctionalite); b=false;}
			  else if(retour==1) {b=false;System.exit(-1); cl.close();}}
			catch (Exception e)
			{
				b=true;
			}
		}
		  
		}
		
	}
	}
	
	}
