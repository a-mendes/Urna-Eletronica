package urna.gui;

public enum BotoesUrna {
	BTN_1("1"), BTN_2("2"), BTN_3("3"),
	BTN_4("4"), BTN_5("5"), BTN_6("6"),
	BTN_7("7"), BTN_8("8"), BTN_9("9"),
				BTN_0("0"),
	BRANCO("BRANCO"), CORRIGE("CORRIGE"), CONFIRMA("CONFIRMA");
	
	private String codigo;
	
	BotoesUrna(String codigo) {
		this.codigo = codigo;
	}
	
	public static BotoesUrna getBotaoUrna(String codigo) {
		if("1".equals(codigo)) return BTN_1;
		
		if("2".equals(codigo)) return BTN_2;
		
		if("3".equals(codigo)) return BTN_3;
		
		if("4".equals(codigo)) return BTN_4;
		
		if("5".equals(codigo)) return BTN_5;
		
		if("6".equals(codigo)) return BTN_6;
		
		if("7".equals(codigo)) return BTN_7;
		
		if("8".equals(codigo)) return BTN_8;
		
		if("9".equals(codigo)) return BTN_9;

		if("0".equals(codigo)) return BTN_0;
		
		if("BRANCO".equals(codigo)) return BRANCO;
		
		if("CORRIGE".equals(codigo)) return CORRIGE;
		
		if("CONFIRMA".equals(codigo)) return CONFIRMA;
	
		return null;
	}
	
	public String getCodigo() {
		return codigo;
	}
}
