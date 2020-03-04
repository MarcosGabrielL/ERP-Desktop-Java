/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.AcoesNfe;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.inf.portalfiscal.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import org.ini4j.Config;


/**
 *
 * @author Marcos
 */
public class StatusServicoSefaz {
    
    public static void main(String[] args) {

        try {

            // Inicia As Configurações - ver https://github.com/Samuel-Oliveira/Java_NFe/wiki/1-:-Configuracoes
            new IniciaConfiguraçãoNfce();

            TRetConsStatServ retorno = Nfe.statusServico(ConstantesUtil.NFCE);
            System.out.println("Status:" + retorno.getCStat());
            System.out.println("Motivo:" + retorno.getXMotivo());
            System.out.println("Data:" + retorno.getDhRecbto());

        } catch (NfeException e) {
            System.err.println(e.getMessage());
        }

    }
    
}
