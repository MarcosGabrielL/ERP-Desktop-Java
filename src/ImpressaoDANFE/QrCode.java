/**
 * 
 */
package ImpressaoDANFE;

/**
 * @author b1104279
 *
 */
public class QrCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		BematechNFiscal qrCode = BematechNFiscal.Instance;
		
		qrCode.ConfiguraModeloImpressora(7);
		qrCode.IniciaPorta("10.12.100.146");
		
		//String sTexto = (char)27+(char)97+(char)1 + "-----------------------------------------------"+(char)10+"Consulta via leitor de QR Code"+(char)29+"kQ"+(char)1+(char)8+(char)1+(char)1+"H"+(char)1+"https://www.sefaz.rs.gov.br/NFCE/NFCE-COM.aspx?chNFe=43140790180621000197650920000203541000203544&nVersao=100&tpAmb=2&dhEmi=323031342D30372D30335431313A32323A30342D30333A3030&vNF=11.50&vICMS=2.88&digVal=4A53384A63486C534A2B4453554B7679767532316D676A377644343D&cIdToken=000001&cHashQRCode=5D52AEEC364EFDDC61E98EBE728A91391CA01607Protocolo de Autoriza��o: 143140000957042"/*+#$A+"03/07/2014 11:21:31"+#$A*/+(char)27+(char)18+(char)27+"@";
		qrCode.FormataTX("-----------------------------------------------"+(char)10+"Consulta via leitor de QR Code"+(char)27+(char)97+(char)1+(char)29+"kQ"+(char)1+(char)8+(char)1+(char)1+"H"+(char)1+"https://www.sefaz.rs.gov.br/NFCE/NFCE-COM.aspx?chNFe=43140790180621000197650920000203541000203544&nVersao=100&tpAmb=2&dhEmi=323031342D30372D30335431313A32323A30342D30333A3030&vNF=11.50&vICMS=2.88&digVal=4A53384A63486C534A2B4453554B7679767532316D676A377644343D&cIdToken=000001&cHashQRCode=5D52AEEC364EFDDC61E98EBE728A91391CA01607Protocolo de Autoriza��o: 143140000957042"+(char)27+(char)18+(char)27+"@", 2, 0, 0, 0, 0);
		//qrCode.ComandoTX(sTexto,(char)10);
		qrCode.AcionaGuilhotina(1);
	}

}
