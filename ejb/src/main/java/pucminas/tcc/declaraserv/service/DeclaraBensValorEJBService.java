package pucminas.tcc.declaraserv.service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pucminas.asd.tcc.canonicoserv.Declaracao;

@Stateless
public class DeclaraBensValorEJBService implements Serializable {

	private static final long serialVersionUID = -8590096513825611293L;

	@Inject
	private ProdutorFilaEmailEJBService produtorFilaEmailEJBService;
	
    public String processarDeclaracao(String msg) {
    	produtorFilaEmailEJBService.enviarMsgFilaEmail(msg);
        return "TESTE EJB";
    }
}
