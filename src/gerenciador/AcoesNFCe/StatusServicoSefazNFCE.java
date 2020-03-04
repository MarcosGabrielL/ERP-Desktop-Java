/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.AcoesNFCe;

import gerenciador.AcoesNfe.*;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.inf.portalfiscal.nfe.schema_4.retConsStatServ.TRetConsStatServ;


/**
 *
 * @author Marcos
 */
public class StatusServicoSefazNFCE {
    
    public static void main(String[] args) {

        try {

           
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
