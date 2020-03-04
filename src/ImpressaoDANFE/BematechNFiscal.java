package ImpressaoDANFE;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface BematechNFiscal extends Library {

    public BematechNFiscal Instance = (BematechNFiscal) Native.loadLibrary("BemaFI64", BematechNFiscal.class);

    public int ConfiguraModeloImpressora(int modelo);
    public int IniciaPorta(String porta);
    public int FechaPorta();
    public int FormataTX(String BufTras, int tipoletra, int italic, int sublin, int expand, int enfat);
    public int ImprimeBitmap(String bitmap, int orientacao);
    public int ComandoTX(String sTexto,int sComando);
	public void BematechTX(String string);
	public int AcionaGuilhotina(int i);
	public int AtualizaFirmware(String arquivo);
}
