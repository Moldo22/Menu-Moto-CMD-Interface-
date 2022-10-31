package Package;

public class Motocicleta //definirea calsei
{
	private String _model;
	private int _viteza;                   //incapsularea campurilor(private pentru a nu putea fii acesate din afara clasei)
	private String _categorie;
	private double _pret;

	public Motocicleta(String _model,int _viteza,String _categorie,double _pret) //constructor explicit
	{
		this._model = _model;
		this._viteza = _viteza;
		this._categorie = _categorie;
		this._pret = _pret;
	}

	//metodele Set

	public void setModel(String _model)
	{
		this._model = _model;
	}

	public void setViteza(int _viteza)
	{
		this._viteza=_viteza;
	}

	public void setCategorie(String _categorie)
	{
		this._categorie=_categorie;
	}

	public void setPret(double _pret)
	{
		this._pret=_pret;
	}

	//metodele Get

	public String getModel()
	{
	    return _model;
	}

	public int getViteza()
	{
	    return _viteza;
	}

	public String getCategorie()
	{
	    return _categorie;
	}

	public double getPret()
	{
	    return _pret;
	}

	//metoda toSting

	public String toString()
	{
	    return ("\nDetaliile motocicletei: " + _model + "  " + _viteza + "  " + _categorie + "  " + _pret + "\n");
	}
}

