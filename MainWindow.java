import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Package.Motocicleta;
import Package.Logging;


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

    Count k = new Count();

    private static final String userVlad = "Vlad";
    private static final String passwordVlad = "vladproiect";
    private static final String userAlex = "Alex";
    private static final String passwordAlex = "alexproiect";

    public void Restart(ActionListener a1, ActionListener a2, ActionListener a3, ActionListener a4)
    {
        Next.removeActionListener(a1);
        Next.removeActionListener(a2);
        Next.removeActionListener(a3);
        Next.removeActionListener(a4);
    }
    public MainWindow() {
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
                 int viteza;
                 try{
                 viteza = Integer.parseInt(s);
                 }
                 catch(NumberFormatException ex) {
                    Rezultat.setText("Date invalide");
                    Next.setEnabled(false);
                    Logging log13=Logging.getInstance();
                    log13.setMessage(k.num()+": ~ERROR: Adaugare~\n");
                    log13.setMessage("Au fost introduse date invalide\n\n");
                     Model.setText(null);
                     Viteza.setText(null);
                     Categorie.setText(null);
                     Pret.setText(null);
                     Next.setEnabled(false);
                     return;
                 }
                 String categorie = Categorie.getText();
                 String s2 = Pret.getText();
                 double pret = Double.parseDouble(s2);
                 if (!model.isEmpty() && !categorie.isEmpty() && (viteza>0 || s.isEmpty()) && (pret>0 || s2.isEmpty())) {
                     for (int i = 0; i < moto.size(); i++)
                         if (moto.get(i).getModel().equals(model)) Este = true;
                     if (!Este) {
                         Motocicleta m1 = new Motocicleta(model, viteza, categorie, pret);
                         moto.add(m1);
                         Rezultat.setText(m1.toString());
                     } else {
                         Rezultat.setText("Exista deja acest model");
                         Logging log3 = Logging.getInstance();
                         log3.setMessage(k.num()+": ~ERROR: Adaugare~\n");
                         log3.setMessage("Adaugare model existent\n\n");
                     }
                 }
                 else {
                     Rezultat.setText("Date invalide");
                     Logging log = Logging.getInstance();
                     log.setMessage(k.num()+": ~ERROR: Adaugare~\n");
                     log.setMessage("Au fost introduse date invalide\n\n");
                 }
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
                         Logging log2 = Logging.getInstance();
                         log2.setMessage(k.num()+": ~ERROR: Cautare~\n");
                         log2.setMessage("Cautare in catalog gol\n\n");
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
                         } else {
                             Rezultat.setText("Date invalide");
                             Logging log4 = Logging.getInstance();
                             log4.setMessage(k.num()+": ~ERROR: Cautare~\n");
                             log4.setMessage("Introducere date invalide\n\n");
                         }
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
                        if (!EsteMoto) {
                            Rezultat.setText("Nu exista modelul");
                            Logging log4 = Logging.getInstance();
                            log4.setMessage(k.num()+": ~ERROR: Actualizare~\n");
                            log4.setMessage("Cautare model inexistent\n\n");
                        }
                    }
                    else {
                        Rezultat.setText("Date Invalide");
                        Logging log5 = Logging.getInstance();
                        log5.setMessage(k.num()+": ~ERROR: Actualizare~\n");
                        log5.setMessage("Introducere date invalide\n\n");
                    }
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
                     Logging log6 = Logging.getInstance();
                     log6.setMessage(k.num()+": ~ERROR: Eliminare~\n");
                     log6.setMessage("Eliminare din catalog gol\n\n");
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
                                 moto.remove(i);
                                 Rezultat.setText("Obiectul a fost eliminat cu succes");
                                 Next.setEnabled(false);
                             }
                         }
                         if (!EsteEliminat) {
                             Rezultat.setText("Modelul nu exista in catalog");
                             Logging log6 = Logging.getInstance();
                             log6.setMessage(k.num()+": ~ERROR: Eliminare\n");
                             log6.setMessage("Eliminare model inexistent\n\n");
                         }
                     }
                     else {
                         Rezultat.setText("Date Invalide");
                         Logging log7 = Logging.getInstance();
                         log7.setMessage(k.num()+": ~ERROR: Eliminare~\n");
                         log7.setMessage("Introducere date invalide\n\n");
                     }
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
                if (contor<2) {
                    Rezultat.setText("Eroare: Prea putine modele");
                    Logging log8 = Logging.getInstance();
                    log8.setMessage(k.num()+": ~ERROR: Medie~\n");
                    log8.setMessage("Media preturilor pentru un numar insuficient de elemente\n\n");
                }
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

         String[] buttons = {"yes", "no"};

         Iesire.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {

                 if (JOptionPane.showConfirmDialog(null, "Inchideti aplicatia?", "Iesire",
                         JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                     System.exit(0);

                 } else {
                     JOptionPane.getRootFrame().dispose();
                 }

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
        frame.setLocationRelativeTo(null);

        Frame frame2 = new JFrame("Log In");
        JLabel ln = new JLabel("Nume:");
        JTextField tn = new JTextField();
        JLabel lp = new JLabel("Parola:");
        JTextField tp = new JTextField();
        JLabel lbl = new JLabel("");
        JButton log = new JButton("Login");
        log.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nm = tn.getText();
                String pw = tp.getText();
                if ((nm.equals(userVlad) && pw.equals(passwordVlad)) || (nm.equals(userAlex) && pw.equals(passwordAlex)))
                {
                    frame2.setVisible(false);
                    frame.setVisible(true);
                }else {
                    System.out.println("a intrat in else");
                    lbl.setText("Nume si parola incorecte");
                }
            }
        });
        frame2.add(ln);
        frame2.add(tn);
        frame2.add(lp);
        frame2.add(tp);
        frame2.add(log);
        frame2.add(lbl);
        frame2.setSize(500,400);
        frame2.setLayout(new GridLayout(7,1));
        frame2.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
