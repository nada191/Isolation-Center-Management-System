package Projet;

import java.util.Calendar;


public class tester {

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(2020,4,7);
		Personne_concernee p = new Personne_concernee("hedi","amir",14587965,1,c);
		/*p.afficher_personne();
		p.afficher_personne();*/
		/*boolean b = p.fin_confinement();
		System.out.println("est ce que p a fini son confinement "+b);
		Calendar d = Calendar.getInstance();
		d.set(2020,4,20) ;
		Personne_concernee pr = new Personne_concernee("hedi","abc",14558865,1,d);
		boolean bo = pr.fin_confinement();
		System.out.println("est ce que p a fini son confinement "+bo);
		
		System.out.println("****************************************************************************************************");

		Centre_isolement s = new Centre_isolement(503,"abcdef","manouba",15);
		s.ajouter_personne(p);
		int nb = s.get_nbactuel();
		s.afficher_centre();
		System.out.println("le nombre des personnes actuel "+nb);
		System.out.println("le nombre des personnes acceuillis "+s.get_ac());
		s.ajouter_personne(pr);
		Calendar e = Calendar.getInstance();
		e.set(2019,2,17);
		Personne_concernee mourad = new Personne_concernee("mourad","gharbi",14587965,1,e);
		System.out.println("le nombre des personnes actuel "+s.get_nbactuel());
		System.out.println("le nombre des personnes acceuillis "+s.get_ac());
		Centre_isolement s1 = new Centre_isolement(879,"abcdef","gafsa",15);
		Personne_concernee p1 = new Personne_concernee("asma","ben ahmed",14587569,0,c);
		s1.ajouter_personne(p1);
		s1.afficher_centre();
		int a = s1.get_ac() ;
		System.out.println("le nombre des personnes acceuillis "+a);
		System.out.println("*****************");
		s.afficher_personnes();
		System.out.println("Suppression");
		s.supprimer(p);
		System.out.println("nombre actuel "+s.get_nbactuel());
		s.afficher_personnes(); 

		System.out.println("****************************************************************************************************");

		Gouvernorat g = new Gouvernorat("Manouba");
		Centre_isolement n = new Centre_isolement(407,"hhe","manouba",15);
		g.ajouter_centre(n);
		g.afficher_centres();
		System.out.println("les personnes contaminés sont"+g.personnes_contamines());
		Manipulation m = new Manipulation();
		m.afficher();*/
		Manipulation m = new Manipulation();
		//m.afficher();
		//m.afficher_centres("gabes");
		//m.afficher_personnes(503);
		//System.out.println("************************");
		//m.supprimer_personne(14529856,503);
		//m.afficher_personnes(503);
		//m.affpersonnes_contamines();
		//m.ajouter_centre("manouba");
		//m.afficher_centres("manouba");
		//System.out.println(m.est_unique(1));
		//Gouvernorat g = new Gouvernorat("manouba");
		/*Centre_isolement n = new Centre_isolement(407,"hhe","manouba",15);
System.out.println(n.get_nbmax())	;
n.ajouter_personne(p);
System.out.println(n.get_nbactuel())	;*/

//m.plus_proche("tunis");
		//System.out.println(g.personnes_contamines());
		//Centre_isolement c = new Centre_isolement(503,"Manouba","ghh",15) ;
		//System.out.println(g.personne_contaminés(503));
		/*System.out.println(c.get_malades());
		System.out.println(c.get_ac());*/
		/*Calendar e = Calendar.getInstance();
		e.set(2019,2,17);
		Personne_concernee mourad = new Personne_concernee("mourad","gharbi",14587965,1,e);
		c.ajouter_personne(mourad);
		System.out.println(c.get_malades());*/
		/*g.afficher_centres();*/
		//System.out.println(g.est_unique(1));
		/*m.plus_proche("manouba");
		m.afficher_personnes(100);
		m.changer_sante(14523698,0);
		m.afficher_personnes(100);*/
		
		//m.hospitalisés(100);
		//m.ajouter_personnes(1, "manouba");
//g.disponible();
		//m.afficher_personnes(503);
		m.Demande(200,"manouba");

	}

}
