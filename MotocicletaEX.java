import java.util.*;
import java.lang.*;

public class MotocicletaEX
{
	public static void main(String args[]) //metoda main
	{
		Motocicleta n1= new Motocicleta("Suzuki",200,"A2",199.5);  //verificarea constructorului
		System.out.println(n1.toString());


		Vector<Motocicleta> m= new Vector<Motocicleta>(4); //declararea si instantierea tabloului m de tip Motocicleta
		Scanner sc= new Scanner(System.in);
		int opt = -1;  //variabila pentru citirea optiunilor din meniu
		while (opt != 0)
		{
			System.out.println("1.Adauga motocicleta: ");
			System.out.println("2.Cauta motociclete din aceeasi categorie: ");
			System.out.println("3.Medie preturi: ");
			System.out.println("4.Modifica pretul: ");
            System.out.println("5.Elimina motocicleta: ");
			System.out.print("6.Iesire \n\n ");
			opt=sc.nextInt(); //citirea variabilei

			switch (opt)
			{
				case 1:  //Adaugare in tablou
				    if (m.size()>4) System.out.println("Eroare: Nu se mai pot adauga motociclete\n"); //verificare limita
					else
                    {
                        String Model,Categorie;
                        int Viteza;
                        double Pret;
                        System.out.println("\n\n");
                        System.out.print("Model: ");
                        Model = sc.next();
                        System.out.print("Viteza: ");
                        Viteza=sc.nextInt();
                        System.out.print("Categorie: ");
                        Categorie = sc.next();
                        System.out.print("Pret: ");
                        Pret=sc.nextDouble();
                        System.out.print("\n");
                        Motocicleta motocicletaNoua = new Motocicleta(Model,Viteza,Categorie,Pret); //instantierea obiectului nou de tip Motocicleta
                        m.addElement(motocicletaNoua); //adaugarea obiectului in tablou
                    }
				break;

				case 2:  //Cautare obiect dupa un criteriu(categorie)
				    String elementCautat;  //variabila care retine categoria dorita
				    boolean suntMoto = false; //variabila care verfica daca sunt motociclete din acea categorie
				    System.out.print("Categoria dorita: ");
				    elementCautat = sc.next();
				    for (int i=0; i<m.size(); i++)
                        {
                         if (m.get(i).getCategorie().equals(elementCautat) == true)
                         {
                             suntMoto = true;
                             System.out.println("\nMotocicleta din categoria " + elementCautat + ": " + m.get(i).getModel());  //travesarea vectorului si cautarea obiectelor care au aceeasi categorie cu elementCautat
                         }
                        }
                    if (suntMoto == false) System.out.println("\nNu sunt motociclete din categoria " + elementCautat + "\n"); //tratare eroare(lipsa Motociclete din categorie)
                break;

				case 3:
                        double medie=0;
                        int contor=0;
                        for (int i=0; i<m.size(); i++)
                        {
                            medie = medie + m.get(i).getPret();
                            if (m.get(i).getPret() != 0) contor++;  //travesarea vectorului de obiecte si calcularea mediei preturilor
                        }
                        medie = medie / contor;
                        if (contor <= 1) System.out.println("Eroare: Adaugati mai multe motociclete!!! \n"); //tratare erori(<2 Motociclete existente)
                        else System.out.println("Media preturilor este: " + medie + " \n");
                break;

                case 4:
                        boolean esteMoto=false;  //variabila care verifica care exista modelul caruia vrem sa-i modificam pretul
                        System.out.print("Model ");
                        String modelCautat=sc.next();  //variabila pentru retinerea modelului cautat
                        for (int i=0; i<m.size(); i++)
                        {
                            if (m.get(i).getModel().equals(modelCautat))
                            {
                                esteMoto=true;
                                System.out.print("Pret actual: " + m.get(i).getPret() + "\n");
                                System.out.print("Pret nou: ");
                                double pretNou = sc.nextDouble();
                                m.get(i).setPret(pretNou);
                                System.out.print("Pret actualizat: " + m.get(i).getPret() + "\n"); //traversare vector, cautare model si modificarea pretului actual
                            }
                        }
                        if (esteMoto==false) System.out.println("Eroare: Modelul nu exista\n"); //tratarea erorilor(nu exista modelul cautat)
                break;

                case 5:
                        boolean esteModel=false;  //variabila care verifica care exista modelul pe care vrem sa-l stergem
                        System.out.print("Model: ");
                        String modelEliminat=sc.next(); //variabila pentru retinerea modelului cautat
                        for (int i=0; i<m.size(); i++)
                        {
                            if (m.get(i).getModel().equals(modelEliminat))
                            {
                                esteModel=true;
                                System.out.println("\nModel eliminat: " + m.get(i).toString()); //traversare vector, cautare model si eliminarea acestuia
                                m.removeElement(m.get(i));
                            }
                        }
                        if (!esteModel) System.out.println("Eroare: Nu exista motocicleta\n "); //tratarea erorilor(nu exista modelul cautat)
                break;

				case 6:
					System.exit(0); //iesire din program
				break;
			}

		}
	}

}
