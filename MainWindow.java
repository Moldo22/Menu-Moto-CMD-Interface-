import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Proiect.Motocicleta;

public class MainWindow
{

    private JPanel panel;
    private JButton Iesire;
    private JButton Adauga;
    private JButton Cauta;
    private JButton Medie;
    private JButton Schimb;
    private JButton Elimina;
    private JLabel Rezultat;

    private JTextField Model;
    private JButton Next;
    private JTextField Pret;
    private JTextField Viteza;
    private JTextField Categorie;
    private JLabel ModelM;
    private JLabel VitezaM;
    private JLabel CategorieM;
    private JLabel PretM;
    private JList ListaOB;
    private JLabel CatalogMotociclete1;

    public void Restart(ActionListener a1, ActionListener a2, ActionListener a3, ActionListener a4)
    {
        Next.removeActionListener(a1);
        Next.removeActionListener(a2);
        Next.removeActionListener(a3);
        Next.removeActionListener(a4);
    }
    public MainWindow()
     {
         final int CAPACITY=4;
         Next.setEnabled(false);
         ArrayList<Motocicleta> moto= new ArrayList<Motocicleta>(CAPACITY);
         ArrayList<Motocicleta> moto2= new ArrayList<Motocicleta>(CAPACITY);

         ActionListener a1= new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 boolean Este = false;
                 String model = Model.getText();
                 //System.out.println("Ni model "+ model);
                 String s = Viteza.getText();
                 int viteza = Integer.parseInt(s);
                 //System.out.println("Ni viteza "+viteza);
                 String categorie = Categorie.getText();
                 String s2 = Pret.getText();
                 double pret = Double.parseDouble(s2);
                 if (!model.isEmpty() && !categorie.isEmpty() && viteza!=0 && pret!=0 ) {
                     for (int i = 0; i < moto.size(); i++)
                         if (moto.get(i).getModel().equals(model)) Este = true;
                     if (!Este) {
                         Motocicleta m1 = new Motocicleta(model, viteza, categorie, pret);
                         moto.add(m1);
                         Rezultat.setText(m1.toString());
                     } else Rezultat.setText("Exista deja acest model");
                 }
                 else Rezultat.setText("Date invalide");
                 Model.setText(null);
                 Viteza.setText(null);
                 Categorie.setText(null);
                 Pret.setText(null);
                 Next.setEnabled(false);
             }
         };

         ActionListener a2=new ActionListener() {
             public void actionPerformed(ActionEvent e)
             {
                     for (int i = 0; i < moto2.size(); i++) moto2.remove(moto2.get(i));
                     if (moto.isEmpty())
                     {
                         Rezultat.setText("Eroare: Catalog gol, nu se poate efectua cautarea");
                         Categorie.setText(null);
                     }
                     else {
                         Model.setText(null);
                         Pret.setText(null);
                         Viteza.setText(null);
                         String motoCautat = Categorie.getText();
                         if (!motoCautat.isEmpty()) {
                             for (int i = 0; i < moto.size(); i++)
                                 if (moto.get(i).getCategorie().equals(motoCautat)) moto2.add(moto.get(i));
                             ListaOB.setListData(moto2.toArray());
                         } else Rezultat.setText("Date invalide");
                         Next.setEnabled(false);
                         Categorie.setText(null);
                     }
             }
         };

         ActionListener a3=new ActionListener() {
             public void actionPerformed(ActionEvent e)
             {
                    boolean EsteMoto=false;
                    String elemCautat=Model.getText();
                    if (!elemCautat.isEmpty()) {
                        for (int i = 0; i < moto.size(); i++) {
                            if (moto.get(i).getModel().equals(elemCautat)) {
                                EsteMoto = true;
                                Rezultat.setText("Tastati noua suma in campul Pret si apasati butonul 'Next'");
                                String s = Pret.getText();
                                double pretNou = Double.parseDouble(s);
                                moto.get(i).setPret(pretNou);
                                Rezultat.setText("Pret Actualizat: " + moto.get(i).toString());
                                Pret.setText(null);
                            }
                        }
                        Next.setEnabled(false);
                        if (!EsteMoto) Rezultat.setText("Nu exista modelul");
                    }
                    else Rezultat.setText("Date Invalide");
                    Model.setText(null);
             }
         };

         ActionListener a4=new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
                 if (moto.isEmpty())
                 {
                     Rezultat.setText("Eroare: Catalog gol, nu se poate efectua eliminarea");
                     Model.setText(null);
                 }
                 else
                 {
                     boolean EsteEliminat = false;
                     String model = Model.getText();
                     if (!model.isEmpty())
                     {
                         for (int i = 0; i < moto.size(); i++) {
                             if (moto.get(i).getModel().equals(model))
                             {
                                 EsteEliminat = true;
                                 moto.remove(moto.get(i));
                                 Rezultat.setText("Obiectul a fost eliminat cu succes");
                                 Next.setEnabled(false);
                             }
                         }
                         if (!EsteEliminat) Rezultat.setText("Modelul nu exista in catalog");
                     }
                     else Rezultat.setText("Date Invalide");
                     Model.setText(null);
                 }
             }
         };


         Adauga.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e)
             {
                 Restart(a1,a2,a3,a4);
                 ListaOB.setListData(new String[0]);
                 Next.setEnabled(true);
                 Rezultat.setText("Introduceti detalile motocicletei in chenarele \n de mai jos si apasati butonul 'Next'");
                 Next.addActionListener(a1);
             }
         });

         Cauta.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e)
             {
                 Restart(a1,a2,a3,a4);
                 Next.setEnabled(true);
                 ListaOB.setListData(new String[0]);
                 ListaOB.remove(moto2.size());
                 Rezultat.setText("Introduceti in campul 'Categorie' categoria pentru a vedea motocicletele din acea categorie");
                 Next.addActionListener(a2);
             }
         });

         Medie.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e)
             {
                 ListaOB.setListData(new String[0]);
              double suma = 0;
              int contor=0;
                for (int i=0; i<moto.size();i++)
                {
                        suma += moto.get(i).getPret();
                        contor++;
                }
                suma=suma/contor;
                if (contor<2) Rezultat.setText("Eroare: Prea putine modele");
                else Rezultat.setText("Media Preturilor: " + suma);
             }
         });

         Schimb.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e)
             {
                 Restart(a1,a2,a3,a4);
                 ListaOB.setListData(new String[0]);
                    Rezultat.setText("Introduceti modelul cautat in campul 'Model'");
                    Next.setEnabled(true);
                    Next.addActionListener(a3);
             }
         });

         Elimina.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 Restart(a1,a2,a3,a4);
                      ListaOB.setListData(new String[0]);
                    Rezultat.setText("Introduceti in campul 'Model' modelul dorit pentru eliminare si apasati 'Next'");
                    Next.setEnabled(true);
                    Next.addActionListener(a4);
             }
         });

         Iesire.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 System.exit(0);
             }
         });
     }

    public static void main(String args[]) {
        MainWindow main=new MainWindow();
        JFrame frame=new JFrame("ProiectOOP");
        frame.setContentPane(new MainWindow().panel);
        //frame.pack();
        frame.setSize(1000,600);
        main.Next.setVisible(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}