package Projet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
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
			lecture.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé") ;
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("Lecture impossible") ;
			System.exit(-1);
		}

	}
	public void afficher(int ref)
	{
		for (Gouvernorat value : pays)
		{

			value.afficher_departs(ref);		}
	}
	public void afficher_centres(String g)
	{
		for(Gouvernorat value : pays)
    {

			if ((value.get_nom_gouv()).equalsIgnoreCase(g))
			{
				value.afficher_centres();
				break;
			}
    }

	}
	public void afficher_personnes(int ref) // par centre
	{
		for (Gouvernorat value : pays)
		{
			value.afficher_personnes(ref);
		}


		}
	public void ajouter_centre(String g)
	{Scanner clavier = new Scanner(System.in);
	int i=1 ;
	for (Gouvernorat value : pays)
	{
		i=i+value.get_nbc();
	}
	for (Gouvernorat value : pays)
		{

			if((value.get_nom_gouv()).equalsIgnoreCase(g))
				{ boolean b = true;
				while(b)
				{
					try {
						System.out.print("donnez la capacite du nouveau centre : ");
						String c1 = clavier.nextLine();
						int cap = Integer.parseInt(c1);
						System.out.print("\ndonnez son adresse : ");
						String ad = clavier.next();
						Centre_isolement c = new Centre_isolement(i,ad,g,cap);
						value.ajouter_centre(c);
					System.out.println("la reference accordee a ce centre est : "+i);
					b=false;
					}
					catch(Exception e)
					{
						b=true;
					}
				}


		}
		}}
	public void supprimer_personne(long cin , int ref)
	{
		for (Gouvernorat value : pays)
		{
			value.supprimer(cin,ref);
		}
	}
	public void statistiques_gouv(String gouv)
	{
		for (Gouvernorat value : pays)
		{
			if((value.get_nom_gouv()).equalsIgnoreCase(gouv))
			{
				value.personnes_contamines();
				break ;

			}
		}
	}
	public void statistiques_centre(int ref)
	{
		for (Gouvernorat value : pays)
		{
			value.personne_contamines(ref);
		}
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
		System.out.println("          ********************************  Demande d'affectation  *********************************    \n");
		System.out.println("                                             Nombre de personnes : "+nb+"\n");
		System.out.println("                                            Gouvernorat : "+gouv+"\n");
		System.out.println("          *********************************  Affectation proposée  **********************************    \n");
		boolean b =false ;
		int a =nb ;
		Scanner cl = new Scanner(System.in);
		Vector<Gouvernorat> V = plus_proche(gouv);
		long CIN [] = new long[nb];//test
		for (Gouvernorat value : V) {
			int[][] mat = value.disponible();
				for (int i=0 ;i<mat[0].length;i++)
				{
					int cap = (int)(mat[1][i]);
					if (cap > nb)
					{System.out.println("\t\t\tNum Ref Centre : "+ mat[0][i] +"       Gouv :  "+value.get_nom_gouv()+"             Nb Personnes :  "+nb+"\n");
					nb=0 ;
					}
					else
					{	nb=nb-cap ;
					System.out.println("\t\t\tNum Ref Centre : "+ mat[0][i] +"         Gouv :  "+value.get_nom_gouv()+"             Nb Personnes :  "+cap+"\n");}

					if (nb<=0)
					{
						b = true ;
						break ;

					}
				}
				if (b)
					break ;
			}
		System.out.println("           *********************************  Confirmation  *************************************** \n");
		System.out.print("                                             Saisir oui ou non : ");
		String rep = cl.next();
		if(rep.equalsIgnoreCase("oui"))
		{
		int d = a-nb;
		boolean test= true ;
		int j=1 ;
		for (Gouvernorat value : V)
			{
			int[][] m=value.disponible();
			for(int x=0 ;x<m[0].length;x++)
			{
 					while(m[1][x]>0 & d>0)
					{
						System.out.print("Nom "+" "+j+" :");
						String nom = cl.next();
						if(nom.equalsIgnoreCase("-1"))
								{test=false ;break ; }
						System.out.print("Prénom "+" "+j+" :");
						String prenom = cl.next();
						if(prenom.equalsIgnoreCase("-1"))
						{test=false ;break ; }
						boolean t =true ;
						long cin =0 ;
						String c ;
						while(t){
						try
						{
							
							System.out.print("N°cin "+" "+j+" :");
							c = cl.next();
							cin=Long.parseLong(c);
							t=false ;
						}
						catch(Exception e)
						{
							System.out.println("cin invalide");
							t=true ;
						}}
						if(cin==-1)
						{test=false ;break ; }
						Calendar cal = Calendar.getInstance();
						Personne_concernee p = new Personne_concernee(nom,prenom,cin,0,cal);
						value.ajouter(p,m[0][x]);
						m[1][x]=m[1][x]-1;
						d-- ;
						j++ ;
					}
 					if (test==false) break ;

			}
			if(test==false) break ;
			}}


	}
	public void changer_sante(long cin ,int e,int ref)
{
	for (Gouvernorat value : pays)
	{
		value.changer(cin, e,ref);
	}

}
	public void evaluation(int ref)
	{
		int s=0; int n=0 ; int m=0 ;
		try {
			BufferedReader lecture = new BufferedReader(new FileReader("evaluation.txt")) ;
			String l;
			int i=0;
			while ((l=lecture.readLine()) != null)
			{
				String temp[] = l.split("\t") ;

				if(Integer.parseInt(temp[0])==ref)
				{
					s=s+Integer.parseInt(temp[1]);
					n=n+Integer.parseInt(temp[2]);
					m=m+Integer.parseInt(temp[3]);
					i++;

				}
			}
			if(i==0)
				System.out.println("Pas de Notes pour L'instant");
			else {
			float s1 =Math.round((float)(s)/(float)(i));
			float n1=Math.round((float)(n)/(float)(i));
			float m1=Math.round((float)(m)/(float)(i));
			System.out.println("Equipe médicale "+s1+"/20");
			System.out.println("nourriture "+n1+"/20");
			System.out.println("mesure d'hygiéne "+m1+"/20");}


		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé");
		} catch (IOException e) {
			System.out.println("Lecture impossible");
		}

	}
	public void hospitalisees(long ref)
{
		for (Gouvernorat value : pays)
		{
			value.hospitalisees(ref);
		}
}
	public void Demande_docteur(String spec,String gouv)
	{
		Vector<Gouvernorat> V = plus_proche(gouv);
		System.out.println("Les medecins disponibles spécialisés en "+spec+" "+"sont :\n");
		for (Gouvernorat value : V)
		{
			try {
				BufferedReader lecture = new BufferedReader(new FileReader("docteurs.txt")) ;
				String l ;
				String temp[] = new String[5] ;
				boolean b=true;
				while ((l=lecture.readLine()) != null)
				{
					temp= l.split("\t");
					if ((value.get_nom_gouv().equalsIgnoreCase(temp[4]) == true) && (spec.equalsIgnoreCase(temp[3]) == true))
					{
						String nom = temp[0] , prenom = temp[1] , gov = temp[4] ;
						long num = Integer.parseInt(temp[2]);
						System.out.println("Dr "+nom+" "+prenom+" de l'hopital de "+gov+"\t N°Téléphone: "+num);
						b=true;
						break;
					}
				}
				if(b==false) System.out.print("pas de médecins disponibles");
				lecture.close();
			}
			catch (FileNotFoundException e) {
				System.out.println("Fichier non trouvé") ;
			}
			catch (IOException e) {
				System.out.println("Lecture impossible") ;
			}
		}

}
	public boolean existe(String gouv)
	{ boolean b=false;
			for (Gouvernorat value : pays)
			{
				if((value.get_nom_gouv()).equalsIgnoreCase(gouv))
				{
					b=true;
					break;

				}
			}
			if(b==false)
				System.out.println("la gouvernorat n'est pas traité ou erreur de saisie  ");
			return b;
	}



	public boolean existe(int ref) {
		boolean b=false;
		for (Gouvernorat value : pays)
		{if(value.existe_centre(ref))
		{b=true;
		break;

		}
		}
		if(b==false)
			System.out.println("le centre de réference "+ref+" n'existe pas");
		return b;



	}
	public boolean existe_centre_cin(int ref,long cin) {
		boolean b=false;
		for (Gouvernorat value : pays)

		{if(value.existe_centre_cin(ref, cin)){
			b=true;

		break;

		}
		}
		if(b==false)
			System.out.println("les données n'existent pas ou l'une des données n'est pas trouvée");

		return b;



	}

}






