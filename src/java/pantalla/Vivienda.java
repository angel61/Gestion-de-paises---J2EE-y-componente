package pantalla;



public class Vivienda {
	private int codigo;
	private String nombremunicipio;
	private String direccion;
	private double metroscuadrados;
	private int anyo; // a�o de construccion
	private double impuesto;// impuesto a pagar por la vivienda
	private int numeropersonas;// personas que viven en la vivienda
	
	String modificar;
	String eliminar;
        String insertar;

	public Vivienda() {
		super();

	}

	
	public Vivienda(int codigo, String nombremunicipio, String direccion, double metroscuadrados, int anyo,
			double impuesto, int numeropersonas) {
		super();
		this.codigo = codigo;
		this.nombremunicipio = nombremunicipio;
		this.direccion = direccion;
		this.metroscuadrados = metroscuadrados;
		this.anyo = anyo;
		this.impuesto = impuesto;
		this.numeropersonas = numeropersonas;
	}

		
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	
	public String getNombremunicipio() {
		return nombremunicipio;
	}


	public void setNombremunicipio(String nombremunicipio) {
		this.nombremunicipio = nombremunicipio;
	}


	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getMetroscuadrados() {
		return metroscuadrados;
	}

	public void setMetroscuadrados(double metroscuadrados) {
		this.metroscuadrados = metroscuadrados;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public int getNumeropersonas() {
		return numeropersonas;
	}

	public void setNumeropersonas(int numeropersonas) {
		this.numeropersonas = numeropersonas;
	}

	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}
	public double getImpuesto() {
		return impuesto;

	}
	public String getModificar() {
		return modificar;
	}
	public void setModificar(String modificar) {
		this.modificar = modificar;
	}
	public String getEliminar() {
		return eliminar;
	}
	public void setEliminar(String eliminar) {
		this.eliminar = eliminar;
                
	}

    public String getInsertar() {
        return insertar;
    }

    public void setInsertar(String insertar) {
        this.insertar = insertar;
    }
	
	
	
}
