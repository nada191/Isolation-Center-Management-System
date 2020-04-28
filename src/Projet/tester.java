package Projet;

import java.util.Calendar;


public class tester {

	public static void main(String[] args) {
		Manipulation m = new Manipulation();
		//Visualiser la liste des personnes ayant terminee leur periode d'isolement
		m.afficher(100);
		System.out.println("*************************");
		//Visualiser la liste de toutes les personnes qui habitent un centre d'isolement 
		m.afficher_personnes(100);
		System.out.println("*************************");
		//Ajouter un centre d'isolement ра un gouvernorat donne 
		//m.ajouter_centre("manouba");
		System.out.println("*************************");
		//Afficher tous les centres d'isolement d'un gouvernorat donne 
		m.afficher_centres("tunis");
		System.out.println("*************************");
		//Afficher le pourcentage de contamination pour un centre donne ou pour un gouvernorat donne
		m.statistiques_centre("manouba");
		m.statistiques_gouv(100);
		m.statistiques_gouv(547);
		System.out.println("*************************");
		//Enregistrer le depart d'une personne donnee d'un centre donne
		m.afficher_personnes(503);
		System.out.print("**");
		m.supprimer_personne(14529856, 503);
		m.afficher_personnes(503);
		System.out.println("*************************");
		//Affecter des personnes ра un ou plusieurs centres d'isolement 
		m.Demande(10,"manouba");
		
		
	}

}
