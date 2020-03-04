package ImpressaoDANFE;

public class Principal2 {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int iRetorno;
		
		BematechNFiscal cupom = BematechNFiscal.Instance;

		/*Modelos de impressoras
		0: MP-20 TH, MP-2000 CI ou MP-2000 TH 
		1: MP-20 MI, MP-20 CI ou MP-20 S
		2: Blocos t�rmicos (com comunicacao serial DTR/DSR)
		3: Bloco 112 mm
		4: ThermalKiosk
		5: MP-4000 TH
		7: MP-4200 TH*/
		String arch = System.getProperty("sun.arch.data.model");
	    System.out.println("Rodando: Java  [" + arch + "] bits");
	    
		iRetorno = cupom.ConfiguraModeloImpressora(0);
		System.out.println("Retorno ConfiguraModeloImpressora = "+iRetorno);
		iRetorno = cupom.IniciaPorta("10.12.100.146");
		System.out.println("Retorno IniciaPorta = "+iRetorno);
		
		
		for (int i=0;i < 10; i++) {
			iRetorno = cupom.FormataTX("1) Ades�o, impress�o, at� odontol�gico\r\n", 2, 0, 0, 0, 0);
			System.out.println("Linha "+i+(char)10+"Retorno FormataTX = "+iRetorno);
		}

		//cupom.BematechTX("2) Ades�o, impress�o, at� odontol�gico\r\n");
		//cupom.FormataTX((char)27 + (char)116 + (char)2 + "3) *** Teste de impress" + (char)198 + "o ***\r\n", 2, 0, 0, 0, 0);

		/*String sTexto = (char)27 + (char)116 + (char)2 + "4) Teste de impress" + (char)198 + "o\r\n";   
		iRetorno = cupom.ComandoTX(sTexto, sTexto.length());
		System.out.println("Retorno ComandoTX = "+iRetorno);*/	
		String Espaco = ""+ (char)13 + (char)10;
		iRetorno = cupom.FechaPorta();		
	}

}
