package telas;

public class AjustarData {
	
	public static String ajustar (String data)
	{
		String ano,mes,dia,datacompleta,datafinal;
		datacompleta = data.toString();
		
		ano = datacompleta.substring(0, 4);
		mes = datacompleta.substring(5, 7);
		dia = datacompleta.substring(8, 10);
		
		datafinal = dia+"/"+mes+"/"+ano;
		
		return datafinal;
	}
}
