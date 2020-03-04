package ImpressaoDANFE;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int iRetorno;
		int iOpc = -1;
	    String libPathProperty = System.getProperty("java.library.path");
	    System.out.println(libPathProperty);
	    
		BematechNFiscal cupom = BematechNFiscal.Instance;

		while (iOpc != 0)
		{
			
			String arch = System.getProperty("sun.arch.data.model");
		    System.out.println("Rodando: Java  [" + arch + "] bits");
		
			System.out.println("\nEscolha uma op��o:" + "\n");
			System.out.println("<1> Imprime Texto");
			System.out.println("<2> Imprime Bitmap");
			System.out.println("<3> Caso cliente");
			System.out.println("<4> Caso Belchior");
			System.out.println("<5> Acionar Gaveta");
			System.out.println("<6> Atualizar firmware");
			System.out.println("<0> Finalizar");
			
			iOpc = Console.readInt( "\nOp��o -> ");		
			
			switch (iOpc)
			{
				case 1:

					//iRetorno = cupom.ConfiguraModeloImpressora(7);
					iRetorno = cupom.IniciaPorta("COM2");
					iRetorno = cupom.FormataTX("        Vale Compra\r\n", 2, 0, 0, 1, 0);
					iRetorno = cupom.FormataTX("--------------------------------------------------", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Vale um quilo de beterraba                        ", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("V�lido at� amanh�                                 ", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("--------------------------------------------------\n\n\n\n\n\n\n\n\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.AcionaGuilhotina(1);

					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Teste de impress" + (char)198 + "o\r\n", 2, 0, 0, 0, 0);
					
					iRetorno = cupom.FechaPorta();
					if (iRetorno == 1){
						System.out.println("Fun��o executada\n");
					}
					else {
						System.out.println("Erro de comunica��o\n");
					}
					
				break;	
				
				case 2:
					iRetorno = cupom.ConfiguraModeloImpressora(7);
					iRetorno = cupom.IniciaPorta("10.12.100.4");
					iRetorno = cupom.ImprimeBitmap("C:\\CBF_logo.bmp", 0);
					iRetorno = cupom.FechaPorta();
					System.out.println("Retorno: "+iRetorno);
				break;
				
				case 3:
					iRetorno = cupom.ConfiguraModeloImpressora(7);
					System.out.println("Retorno ConfiguraModeloImpressora: "+iRetorno);	
					iRetorno = cupom.IniciaPorta("10.12.100.4");
					System.out.println("Retorno IniciaPorta: "+iRetorno);	
					iRetorno = cupom.ComandoTX("",(char)29 + (char)249 + (char)32 + (char)0);
					System.out.println("Retorno ComandoTX: "+iRetorno);
					iRetorno = cupom.FormataTX("CA�QUE, ca�que, assun��o, ASSUN��O\n", 2, 0, 0, 1, 0); // Aqui a impressora n�o imprime o "i" com acento.
					System.out.println("Retorno FormataTX: "+iRetorno);	
					iRetorno = cupom.AcionaGuilhotina(1);
					iRetorno = cupom.FechaPorta();
				break;
				case 4:
					
					iRetorno = cupom.ConfiguraModeloImpressora (7);
					iRetorno = cupom.IniciaPorta("10.12.100.4"); 

					iRetorno = cupom.FormataTX("Linha um...\r\n", 2, 0, 0, 0, 0); 
					iRetorno = cupom.FormataTX("Linha dois...\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Linha tr�s...\r\n", 2, 0, 0, 0, 0);

					iRetorno = cupom.AcionaGuilhotina(0);                       

					iRetorno = cupom.FormataTX("Linha um...\r\n", 2, 0, 0, 0, 0); 
					iRetorno = cupom.FormataTX("Linha dois...\r\n", 2, 0, 0, 0, 0);
					iRetorno = cupom.FormataTX("Linha tr�s...\r\n", 2, 0, 0, 0, 0);

					iRetorno = cupom.AcionaGuilhotina(1);                     

					iRetorno = cupom.FechaPorta(); 
				break;
				case 5:
					
					iRetorno = cupom.ConfiguraModeloImpressora (7);
					iRetorno = cupom.IniciaPorta("10.12.100.4"); 
					
					String sTexto = ""+(char)27 + (char)118 + (char)40;
					iRetorno = cupom.ComandoTX(sTexto, sTexto.length());
					
				case 6:
					
					String arquivo = "C://42000.bin";
					iRetorno = cupom.AtualizaFirmware(arquivo );
					System.out.println(iRetorno);
					
				break;
			}
		}
		
	}

}
