package gerenciador.Modulos;

import gerenciador.Caixa.*;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mario Feles dos Santos Junior Implementacao Balanca Serial
 */
public class SerialComm1 implements SerialPortEventListener {

    private static SerialPort serialPort;
    private static InputStream inputStream;
    private static OutputStream ouput;
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;
    private JFrame parent;
    
    
    
    public void execute(String namePort) {
        //Pega a porta pelo S.O.
        String portName = getPortNameByOS(namePort);
        //Retorna o identificador da porta
        CommPortIdentifier portId = getPortIdentifier(portName);
        //Se o PortId for nulo, não há porta disponível
        if (portId != null) {
            try {
                //Abre a porta serial solicitada
                serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

                //Pega o InputStream da Porta Serial
                inputStream = serialPort.getInputStream();

                //Passa os parametros da porta serial
                serialPort.setSerialPortParams(DATA_RATE,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);

                ouput = serialPort.getOutputStream();

                //Cria um novo Listener de Eventos
                serialPort.addEventListener(this);
                //Avisa se tive alguma mudança na Porta Serial
                serialPort.notifyOnDataAvailable(true);

            } catch (PortInUseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedCommOperationException e) {
                e.printStackTrace();
            } catch (TooManyListenersException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Porta Serial não disponível");
        }
    }

    /*
     *Metodo responsavel por fechar a conexao Serial
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Verifica Sistema Operacional para montar caminho da porta rever este
     * metodo quando usar linux
     */
    private String getPortNameByOS(String name) {

        String osname = System.getProperty("os.name", "").toLowerCase();
        if (osname.startsWith("windows")) {
            // windows
            return name;
        } else if (osname.startsWith("linux")) {
            // linux
            return "/dev/tty0";
        } else if (osname.startsWith("mac")) {
            // mac
            return "???";
        } else {
            System.out.println("Sorry, your operating system is not supported");
            System.exit(1);
            return null;
        }

    }

    /**
     * Metodo responsavel por localizar todas as portas serial na pc
     *
     * @return
     */
    public static String[] getListPortIdentifier() {
        String[] ports = new String[8];
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();

        int aux = 0;
        while (portList.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName() != null) {
                    ports[aux] = portId.getName();
                    aux++;
                }
            }
        }
        return ports;
    }

    /**
     * Metodo responsavel por verificar se a porta exite se ela é serial e se
     * esta disponivel
     */
    private CommPortIdentifier getPortIdentifier(String portName) {
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        Boolean portFound = false;

        while (portList.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                //Porta disponivel
                if (portId.getName().equals(portName)) {
                    //Porta encontrada
                    portFound = true;
                    return portId;
                }
            }
            

        }

        return null;

    }

    /**
     * Metodo responsavel por Escutar a porta serial
     *
     * @param event : evento que fica escutando a porta serial
     */
    public synchronized void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            //Quebra interupção.
            case SerialPortEvent.OE:
            //Erro de saturação.
            case SerialPortEvent.FE:
            //Erro de enquadramento.
            case SerialPortEvent.PE:
            //Erro de pariedade.
            case SerialPortEvent.CD:
            //Detecção de portadora.
            case SerialPortEvent.CTS:
            //Limpa para enviar.
            case SerialPortEvent.DSR:
            //Conjunto de dados prontos.
            case SerialPortEvent.RI:
            //Caminho indicado.
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                /*Buffer de saída está vazio. O evento será gerado depois de uma gravação for concluída,
                 quando o buffer do sistema torna-se vazia novamente..."
                 */
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                /*Dados disponíveis na porta serial. "
                 + "Este evento será gerada uma vez quando dados novos chegam na porta de série. "
                 + "Mesmo se o usuário não ler os dados, "
                 + "não será gerado novamente até a próxima vez que novos dados chegam");
                 */
                byte[] readBuffer = new byte[20];

                try {
                    int numBytes = 0;
                    while (inputStream.available() > 0) {
                        numBytes = inputStream.read(readBuffer);
                    }
                    String result = new String(readBuffer);
                    
                    result = result.substring(1, numBytes);
                    IniciaImpressora.jlPeso.setText(result);
                    Tela_Caixa.jLabel16.setText(result);
                    ConfiguraçõesCaixa.jTextField10.setText(result);
                    
                    
                    
                } catch (IOException e) {
                   // e.printStackTrace();
                }
                break;
        }
    }

    /**
     * Metodo responsavel por enviar comanda para a porta serial
     *
     * @param comando
     */
    void enviaComando(int comando) throws IOException {
        try {
            if (ouput != null) {
                ouput.write(comando);
            }
        } catch (IOException ex) {
            ouput.close();
            JOptionPane.showMessageDialog(null, "Comando não enviado !!!");
        }
    }
}
